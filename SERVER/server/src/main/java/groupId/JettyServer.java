package groupId;


import org.eclipse.jetty.http.HttpMethod;
/**
 * Hello world!
 *
 */
public class JettyServer
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Server server = new Server(8080);
        server.start();

    }
}
