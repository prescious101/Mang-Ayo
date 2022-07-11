package Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BackgroundService extends Service {

    private String mechanic_id,urlString;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true){

                    Log.d("SERVICE", "running service: "+ i++);
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();




        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getService(){
        urlString="http://192.168.254.113:9999/Mangayo-Admin/mobile/getService.php?mechanic_id="+mechanic_id;
        try {
            Log.d("URL",urlString);
            URL url =new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String service=br.readLine();
            br.close();conn.disconnect();

            JSONObject serviceData = new JSONObject(service);
            JSONObject serviceView = serviceData.getJSONObject("service");
            Log.d("SERVICE", "service: "+ serviceView.getString("mechanic_id"));
            mechanic_id = serviceView.getString("mechanic_id");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
