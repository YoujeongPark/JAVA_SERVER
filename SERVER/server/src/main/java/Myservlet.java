import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalTime;
import java.util.Date;

public class Myservlet extends HttpServlet {

    /** Get : 존재하는 자원에 대한 요청 **/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Request : " + request.getRequestURL());
        response.setStatus(200); // Get 요청에 대한 성공
        //response.getWriter().write("Hello World!");
        response.getWriter().write(new Date().toString());

    }


    /** Post : 새로운 자원을 생성 **/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Request : " + request.getRequestURL());

        File file = new File("./OUTPUT");
        if(!file.exists()){
            file.mkdirs();
        }

        LocalTime localTime = LocalTime.now();
        String fname = String.format("./OUTPUT/%02d%02%02d.json", localTime.getHour(), localTime.getMinute(), localTime.getSecond());

        PrintWriter printWriter = new PrintWriter(new FileWriter(new File(fname)));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String buffer;

        while((buffer = bufferedReader.readLine())!= null){
            printWriter.print(buffer);
        }

        bufferedReader.close();
        printWriter.close();

        response.setStatus(200); // Get 요청에 대한 성공
        response.getWriter().write(fname + " saved! ");

    }

}
