import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.client.HttpClient;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;



public class BasicClientHttp_1 {

    public static void main(String[] args) throws Exception{

        /**** 1. 일반 Client ****/

        /**
        HttpClient httpClient = new HttpClient();
        httpClient.start();
//      ContentResponse contentResponse = httpClient.newRequest("http://127.0.0.1:8080/helloworld")
//                .method(HttpMethod.GET).send();
        ContentResponse contentResponse = httpClient.newRequest("http://127.0.0.1:8080/requestDate")
                .method(HttpMethod.GET).send();
        System.out.println(contentResponse.getContentAsString());
        **/



        /*** 2. File Client - Client􏰘􏰉에서  Server에 접속하여 파일 목록을 json 형태로 전송하는 프로그램 􏰘    ****/
        HttpClient httpClient = new HttpClient();
        httpClient.start();
        String strFileList = getFileList();

        Request request = httpClient.newRequest("http://127.0.0.1:8080/fileList").method(HttpMethod.POST); // 􏰫􏰬 Method는 ‘POST’
        request.header(HttpHeader.CONTENT_TYPE, "application/json"); // Content Type
        request.content(new StringContentProvider(strFileList, "utf-8"));

        ContentResponse contentResponse = request.send();
        System.out.println(contentResponse.getContentAsString());
        httpClient.stop(); // 목록 전송 완료 후 종료




    }

    private static String getFileList(){
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        File directory = new File("./Input");
        jsonObject.addProperty("Folder","Input");
        JsonArray jsonArray = new JsonArray();
        File [] fileList = directory.listFiles();
        for( File file : fileList){
            jsonArray.add(file.getName());
        }
        jsonObject.add("FILES", jsonArray);
        String res = jsonObject.toString();
        return res;

        /**
        { "Folder" : "Input",
          "Files" : [ "...png", "config.js".... ] }
        **/


    }

}
