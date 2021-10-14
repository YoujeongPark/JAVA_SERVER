import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BasicClientSocket_1 {
    public static void main(String[] agrs) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);
    }
}
