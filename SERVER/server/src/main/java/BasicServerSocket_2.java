import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class BasicServerSocket_2 {
    public static void main( String[] args ) throws IOException {
        ServerSocket listener = new ServerSocket(8081);

        try{
            while(true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(new Date().toString());
                } finally {
                    socket.close();
                }
            }
        }finally{
            listener.close();

        }

    }
}
