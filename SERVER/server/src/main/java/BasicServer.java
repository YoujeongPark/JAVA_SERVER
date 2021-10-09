import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {
    public static void main( String[] args ) throws IOException {
        ServerSocket listener = new ServerSocket(8080);
        Socket socket = listener.accept();

        PrintWriter printerWriter = new PrintWriter(socket.getOutputStream(),true);
        printerWriter.println("Hello World ");
        socket.close();
        listener.close();

    }
}
