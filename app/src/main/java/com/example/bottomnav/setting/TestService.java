package com.example.bottomnav.setting;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


import com.example.bottomnav.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;


public class TestService extends Service  {

    Notification notification = null; //通知オブジェクトの用意と初期化
    Notification notification_start = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("debug", "onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);  //システムから通知マネージャー取得
        final NotificationManager notificationManager_start = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);  //システムから通知マネージャー取得
        String chID = getString(R.string.app_name);  //アプリ名をチャンネルIDとして利用

        int requestCode = intent.getIntExtra("REQUEST_CODE",0);
        Context context = getApplicationContext();

        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, requestCode, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        long time = System.currentTimeMillis();


        //アンドロイドのバージョンで振り分け
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //APIが「26」以上の場合

            NotificationChannel notificationChannel = new NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_DEFAULT);  //通知チャンネルIDを生成してインスタンス化
            notificationChannel.setLightColor(Color.GREEN);                                // 通知時のライトの色
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);  // ロック画面で通知を表示
            notificationChannel.enableVibration(true);

            NotificationChannel notificationChannel_start = new NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_MIN);  //通知チャンネルIDを生成してインスタンス化
            notificationChannel_start.enableVibration(false);
            notificationChannel_start.setSound(null,null);
            notificationChannel_start.enableLights(false);

            notificationChannel.setDescription(chID);  //通知の説明をset
            notificationChannel_start.setDescription(chID);

            assert notificationManager != null;   //通知チャンネルの作成
            notificationManager.createNotificationChannel(notificationChannel);
            assert notificationManager_start != null;
            notificationManager_start.createNotificationChannel(notificationChannel);


            notification = new Notification.Builder(this, chID)        //通知の生成と設定とビルド
                    .setContentTitle(getString(R.string.app_name))             //通知タイトル
                    .setContentText("アドバイザーからコメントが届きました！")     //通知内容
                    .setSmallIcon(R.drawable.ic_launcher_background)  //通知用アイコン
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setWhen(time)
                    .build();//通知のビルド

            notification_start = new Notification.Builder(this, chID)        //通知の生成と設定とビルド
                    .setContentTitle(getString(R.string.app_name))//通知タイトル
                    .setContentText("通知をONにしました．")     //通知内容
                    .setSmallIcon(R.drawable.ic_launcher_background)  //通知用アイコン
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent)
                    .build();//通知のビルド
        }

        //通知の発行
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                assert notificationManager != null;
                notificationManager.notify(0, notification);
                timer.cancel();
            }
        };

        try {
            timer.schedule(task, sdf.parse("2020/07/21 00:19:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        startForeground(1, notification_start);
        return START_NOT_STICKY;
//        return START_STICKY;
//        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("debug", "onDestroy()");
        // Service終了
        stopSelf();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
