package strikeaturkeytechnologiesllc.strikeymate;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.corn.httpclient.HttpForm;
import net.sf.corn.httpclient.HttpResponse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by jbeach on 12/3/15.
 */
public class Server {
    public static String strUrl = "http://strikeymatebackend.herokuapp.com/";
//    public static String strUrl = "http://10.0.2.2:2738/";

    private static String getServerData(URL url) {
        class RunnableGet implements Runnable {
            String data;
            URL url;

            public RunnableGet(URL url) {
                this.url = url;
            }
            @Override
            public void run() {
                try {
                    HttpRequest req = HttpRequest.get(url);
                    data = req.body();
                }
                catch(HttpRequest.HttpRequestException e) {
                    e.printStackTrace();
                }
                System.out.println(data);
            }
        }
        RunnableGet task;
        task = new RunnableGet(url);
        Thread t = new Thread(task);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task.data;
    }
    private static String postServerData(HttpForm inputForm) {
        class RunnablePost implements Runnable {
            HttpForm form;
            String data;
            public RunnablePost(HttpForm _form) {
                form = _form;
            }
            @Override
            public void run() {
                HttpResponse res = null;
                try {
                    res = form.doPost();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // if the result is still null
                if (res == null) {
                    // couldn't communicate with server
                    data = "Couldn't communicate with server.";
                    return;
                }
                else if (res.hasError()) {
                    throw new RuntimeException("Server error");
                }
                String data = res.getData();
                System.out.println(data);
                this.data = data;
            }
        }
        RunnablePost task;
        task = new RunnablePost(inputForm);
        Thread t = new Thread(task);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task.data;
    }

    public static String requestLogin(String username, String pass) {
        String strUrlLogin = strUrl+"login";
        URI url = null;
        try {
            url = new URI(strUrlLogin);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpForm form = new HttpForm(url);
        form.putFieldValue("identity",username);
        form.putFieldValue("pass", pass);
        String data = postServerData(form);
        return data;
    }

    public static JsonObject getUserDataById(UUID id) {
        String strUrlLogin = strUrl+"specificuser";
        URL url = null;
        try {
            url = new URL(strUrlLogin+"?id="+id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String data = getServerData(url);
        JsonParser parser = new JsonParser();
        JsonObject account = parser.parse(data).getAsJsonObject();
        return account;
    }

}
