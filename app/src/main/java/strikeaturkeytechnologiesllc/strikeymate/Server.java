package strikeaturkeytechnologiesllc.strikeymate;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.corn.httpclient.HttpForm;
import net.sf.corn.httpclient.HttpResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Created by jbeach on 12/3/15.
 */
public class Server {
    public static String strUrl = "http://strikeymatebackend.herokuapp.com/";
//    public static String strUrl = "http://192.168.1.5:2738/";

    private static String getServerData(HttpForm inputForm) {
        class RunnableGet implements Runnable {
            String data;
            HttpForm form;
            public RunnableGet(HttpForm _form) {
                form = _form;
            }
            @Override
            public void run() {
                HttpResponse res = null;
                try {
                    res = form.doGet();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                if (res == null) {
                    data = "Couldn't communicate with server";
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
        RunnableGet task;
        task = new RunnableGet(inputForm);
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
        String strUrlLogin = strUrl+"specificUser";
        URI url = null;
        try {
            url = new URI(strUrlLogin);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpForm form = new HttpForm(url);
        form.putFieldValue("id",id.toString());
        String data = getServerData(form);
        JsonParser parser = new JsonParser();
        JsonObject account = parser.parse(data).getAsJsonObject();
        return account;
    }
}
