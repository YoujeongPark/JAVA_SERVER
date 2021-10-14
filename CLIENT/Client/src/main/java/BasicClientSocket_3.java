import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class BasicClientSocket_3 {
    public static void main(String[] agrs) throws IOException {
        SendToServer("test.exe");
    }

    public static void SendToServer(String fileName) throws UnknownHostException, IOException{

        Socket socket = new Socket("127.0.0.1", 8082);
        java.io.OutputStream outputStream = socket.getOutputStream();
        byte[] buffer = new byte[4096];
        int readLen;

        FileInputStream fileInputStream = new FileInputStream(".//ClientFiles//" + fileName);
        while((readLen = fileInputStream.read(buffer))!= -1){
            outputStream.write(buffer,0,  readLen);
        }
        fileInputStream.close();
        socket.close();
        System.out.println(fileName + "'s sent ");


    }

}
