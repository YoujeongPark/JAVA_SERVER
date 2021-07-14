package groupId;

//import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.server.Server;

public class JettyClient {
    public static void main(String[] agrs){
        System.out.println("hello");
        Server server = new Server(8080);
        server.start();
        start.join();
    }
}
