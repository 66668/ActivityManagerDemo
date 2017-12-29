package com.activitymanager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

/**
 * 相关好文章：
 * https://www.cnblogs.com/bastard/archive/2012/05/25/2517522.html
 */
public class SecondActivity extends BaseActivity {
    public static final String TAG = "SJY";
    ActivityManager activityManager;
    ActivityManager.AppTask appTask;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        activityManager = (ActivityManager) SecondActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
        appTask = activityManager.getAppTasks().get(0);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "moveToFront");

                appTask.moveToFront();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "finishAndRemoveTask");
                appTask.finishAndRemoveTask();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "finishAndRemoveTask");
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, MainActivity.class);
                appTask.startActivity(SecondActivity.this, intent, null);
            }
        });


    }

}
