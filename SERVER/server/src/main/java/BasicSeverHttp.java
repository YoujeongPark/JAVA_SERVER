import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicSeverHttp {

    private final String DEFAULT_HOSTNAME = "0.0.0.0";
    private final int DEFAULT_PORT = 8080;
    private final int DEFAULT_BACKLOG = 0;
    private HttpServer server = null;



    /**
     * 생성자
     */
    public BasicSeverHttp() throws IOException {
        createServer(DEFAULT_HOSTNAME, DEFAULT_PORT);
    }
    public BasicSeverHttp(int port) throws IOException {
        createServer(DEFAULT_HOSTNAME, port);
    }
    public BasicSeverHttp(String host, int port) throws IOException {
        createServer(host, port);
    }

    /**
     * 서버 생성
     */
    private void createServer(String host, int port) throws IOException {
        // HTTP Server 생성
        this.server = HttpServer.create(new InetSocketAddress(host, port), DEFAULT_BACKLOG);
        // HTTP Server Context 설정
        server.createContext("/", new RootHandler());
    }

    /**
     * 서버 실행
     */
    public void start() {
        server.start();
    }

    /**
     * 서버 중지
     */
    public void stop(int delay) {
        server.stop(delay);
    }

    public static void main(String[] args) {

        BasicSeverHttp httpServerManager = null;

        try {
            // 시작 로그
            System.out.println(
                    String.format(
                            "[%s][HTTP SERVER][START]",
                            new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date())
                    )
            );

            // 서버 생성
            httpServerManager = new BasicSeverHttp("localhost", 8080);
            httpServerManager.start();
            // Shutdown Hook
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    // 종료 로그
                    System.out.println(
                            String.format(
                                    "[%s][HTTP SERVER][STOP]",
                                    new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date())
                            )
                    );
                }
            }));

            // Enter를 입력하면 종료
            System.out.print("Please press 'Enter' to stop the server.");
            System.in.read();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 종료
            // 0초 대기후  종료
            httpServerManager.stop(0);
        }
    }

    /**
     * Sub Class
     */
    class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            // Initialize Response Body
            OutputStream respBody = exchange.getResponseBody();

            try {
                // Write Response Body
                StringBuilder sb = new StringBuilder();
                sb.append(new Date());
//                sb.append("<!DOCTYPE html>");
//                sb.append("<html>");
//                sb.append("   <head>");
//                sb.append("       <meta charset=\"UTF-8\">");
//                sb.append("       <meta name=\"author\" content=\"Dochi\">");
//                sb.append("       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//                sb.append("       <title>Example</title>");
//                sb.append("   </head>");
//                sb.append("   <body>");
//                sb.append("       <h5>Hello, HttpServer!!!</h5>");
//                sb.append("       <span>Method: "+(exchange.getRequestMethod())+"</span></br>");
//                sb.append("       <span>URI: "+(exchange.getRequestURI())+"</span></br>");
//                sb.append("       <span>PATH: "+(exchange.getRequestURI().getPath())+"</span></br>");
//                sb.append("       <span>QueryString: "+(exchange.getRequestURI().getQuery())+"</span></br>");
//                sb.append("   </body>");
//                sb.append("</html>");

                // Encoding to UTF-8
                ByteBuffer bb = Charset.forName("UTF-8").encode(sb.toString());
                int contentLength = bb.limit();
                byte[] content = new byte[contentLength];
                bb.get(content, 0, contentLength);

                // Set Response Headers
                Headers headers = exchange.getResponseHeaders();
                headers.add("Content-Type", "text/html;charset=UTF-8");
                headers.add("Content-Length", String.valueOf(contentLength));

                // Send Response Headers
                exchange.sendResponseHeaders(200, contentLength);

                respBody.write(content);

                // Close Stream
                // 반드시, Response Header를 보낸 후에 닫아야함
                respBody.close();

            } catch ( IOException e ) {
                e.printStackTrace();

                if( respBody != null ) {
                    respBody.close();
                }
            } finally {
                exchange.close();
            }
        }
    }


}
