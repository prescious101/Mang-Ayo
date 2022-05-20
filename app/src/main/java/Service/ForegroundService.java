package Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import com.example.mangayo.MechanicHomepage;
import com.example.mangayo.R;

public class ForegroundService extends Service {
    private String mechanic_id ="23",urlString,user_id;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    int i = 0;
                    Log.d("SERVICE", "running service: "+ i);i++;
                    getService();
                    try {Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}
                }
            }
        }).start();

        createNotif();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getService(){
        urlString="http://192.168.254.104:9999/Mangayo-Admin/mobile/getService.php?mechanic_id="+mechanic_id;
        try {
            URL url =new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String service=br.readLine();
            br.close();conn.disconnect();
            if(service!=null){
                JSONObject serviceData = new JSONObject(service);
                JSONArray serviceView = serviceData.getJSONArray("service");
                JSONObject json = serviceView.getJSONObject(0);
                user_id = json.getString("user_id");
                Log.d("SERVICE", "Pending Service(s): "+ user_id);
                sharedPref(user_id);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void sharedPref(String id) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("user_id", id);
        myEdit.commit();
    }

    public void createNotif(){
        final String CHANNEL_ID = "Foreground Service ID";
        Intent notifyIntent = new Intent(this, MechanicHomepage.class);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                CHANNEL_ID,
                NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this,CHANNEL_ID)
                .setContentText("Mangayo is Running")
                .setContentTitle("Mangayo Service Enabled")
                .setSmallIcon(R.drawable.mang_ayo_logo)
                .setContentIntent(notifyPendingIntent);
        startForeground(1001,notification.build());
    }

}
