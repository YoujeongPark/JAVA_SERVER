import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletHandler;

public class BasicServerHttp_1 {

    public static void main(String[] agrs) throws Exception {
        new BasicServerHttp_1().start();
    }

    public void start() throws Exception{
        Server server = new Server();

        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setHost("127.0.0.1");
        serverConnector.setPort(8080);

        server.addConnector(serverConnector);

        ServletHandler servletHandler = new ServletHandler();
        //servletHandler.addServletWithMapping(Myservlet.class, "/helloworld");
        servletHandler.addServletWithMapping(Myservlet.class, "/*");
        server.setHandler(servletHandler);

        server.start();
        server.join();

    }


}




