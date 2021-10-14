import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class BasicServerSocket_3 {
    public static void main( String[] args ) throws IOException, InterruptedException {
        ThreadClass threadClass = new ThreadClass();
        Thread thread = new Thread(threadClass);
        thread.start();

        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;

        while(true){
            str = bufferedReader.readLine();
            if(str.equals("QUIT")){
                threadClass.listener.close();
                thread.join();
                break;
            }

        }
    }
}

class ThreadClass implements Runnable{
    public ServerSocket listener;
    public void run(){
        final int BUF_SIZE = 4096;
        int recvLen;
        String filename = "test.exe";

        byte[] buffer = new byte[BUF_SIZE];

        listener = null;

        try{
            listener = new ServerSocket(8082);
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            while (true) {
                Socket socket = listener.accept();
                InputStream inputStream = socket.getInputStream();
                int cnt = 0;

                while((recvLen = inputStream.read(buffer,0,BUF_SIZE))!= -1){
                    FileOutputStream fileOutputStream = new FileOutputStream("//ServerFiles//" + filename, true);
                    fileOutputStream.write(buffer,cnt,recvLen);
                    fileOutputStream.close();
                }

            }
        } catch(IOException e){

        }finally{
            try{
                listener.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }



    }
}
