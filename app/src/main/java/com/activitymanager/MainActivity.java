package com.activitymanager;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

/**
 * 相关好文章：
 * https://www.cnblogs.com/bastard/archive/2012/05/25/2517522.html
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = "SJY";
    ActivityManager activityManager;
    ActivityManager.AppTask appTask;
    ActivityManager.MemoryInfo memoryInfo;
    ActivityManager.ProcessErrorStateInfo processErrorStateInfo;
    ActivityManager.RecentTaskInfo recentTaskInfo;
    ActivityManager.RunningServiceInfo runningServiceInfo;
    ActivityManager.RunningTaskInfo runningTaskInfo;
    ActivityManager.RunningAppProcessInfo runningAppProcessInfo;
    ActivityManager.TaskDescription taskDescription;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        //01
        initManager();

        //02
        initAppTask();

        //04
        initMemoryInfo();

        //05
        initRunningTaskInfo();

        //06
        initRunningAppProcessInfo();


        //07
        initrunningServiceInfo();

        //08
        initTaskDescription();

        //09
        initProcessErrorStateInfo();
    }

    /**
     * 01获取对象
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initManager() {
        Log.d(TAG, "01");
        //获得ActivityManager服务的对象
        activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        Log.d(TAG, "getMemoryClass:" + activityManager.getMemoryClass());
        Log.d(TAG, "getLargeMemoryClass:" + activityManager.getLargeMemoryClass());
        Log.d(TAG, "getLockTaskModeState:" + activityManager.getLockTaskModeState());
        Log.d(TAG, "getLauncherLargeIconSize:" + activityManager.getLauncherLargeIconSize());
        Log.d(TAG, "getLauncherLargeIconDensity:" + activityManager.getLauncherLargeIconDensity());
        Log.d(TAG, "isLowRamDevice:" + activityManager.isLowRamDevice());
        Log.d(TAG, "activityManager--还有其他方法没有调用");
    }

    //02
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initAppTask() {
        Log.d(TAG, "02");
        Log.d(TAG, "appTask集合：" + activityManager.getAppTasks().size());
        //通常，一个app只有一个appTask
        for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
            this.appTask = appTask;
            //            appTask.finishAndRemoveTask();
            appTask.moveToFront();

            //获取对象：recentTaskInfo
            recentTaskInfo = appTask.getTaskInfo();
            //03
            initRecentTaskInfo(recentTaskInfo);

        }
    }

    /**
     * 03
     * ComponentName类主要用户获取 act对应的信息:包名，类名
     * @param recentTaskInfo0
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("WrongConstant")
    private void initRecentTaskInfo(ActivityManager.RecentTaskInfo recentTaskInfo0) {
        Log.d(TAG, "03");
        Log.d(TAG, "affiliatedTaskId:" + recentTaskInfo0.affiliatedTaskId);
        Log.d(TAG, "describeContents():" + recentTaskInfo0.describeContents());
        Log.d(TAG, "description:" + recentTaskInfo0.description);
        Log.d(TAG, "describeContents:" + recentTaskInfo0.describeContents());
        Log.d(TAG, "id:" + recentTaskInfo0.id);
        Log.d(TAG, "numActivities:" + recentTaskInfo0.numActivities);

        ComponentName componentName1 = recentTaskInfo0.baseActivity;
        if (componentName1 != null) {
            Log.d(TAG, "baseActivity--ComponentName--getClassName:" + componentName1.getClassName());
            Log.d(TAG, "baseActivity--ComponentName--getPackageName:" + componentName1.getPackageName());
            Log.d(TAG, "baseActivity--ComponentName--getShortClassName:" + componentName1.getShortClassName());
            Log.d(TAG, "baseActivity--ComponentName--flattenToShortString:" + componentName1.flattenToShortString());
            Log.d(TAG, "baseActivity--ComponentName--flattenToString:" + componentName1.flattenToString());
            Log.d(TAG, "baseActivity--ComponentName--toShortString:" + componentName1.toShortString());
            Log.d(TAG, "baseActivity--ComponentName--describeContents:" + componentName1.describeContents());
        }

        ComponentName componentName2 = recentTaskInfo0.origActivity;
        if (componentName2 != null) {
            Log.d(TAG, "origActivity--ComponentName--getClassName:" + componentName2.getClassName());
            Log.d(TAG, "origActivity--ComponentName--getPackageName:" + componentName2.getPackageName());
            Log.d(TAG, "origActivity--ComponentName--getShortClassName:" + componentName2.getShortClassName());
            Log.d(TAG, "origActivity--ComponentName--flattenToShortString:" + componentName2.flattenToShortString());
            Log.d(TAG, "origActivity--ComponentName--flattenToString:" + componentName2.flattenToString());
            Log.d(TAG, "origActivity--ComponentName--toShortString:" + componentName2.toShortString());
            Log.d(TAG, "origActivity--ComponentName--describeContents:" + componentName2.describeContents());

        }

        ComponentName componentName3 = recentTaskInfo0.topActivity;
        if (componentName3 != null) {
            Log.d(TAG, "topActivity--ComponentName--getClassName:" + componentName3.getClassName());
            Log.d(TAG, "topActivity--ComponentName--getPackageName:" + componentName3.getPackageName());
            Log.d(TAG, "topActivity--ComponentName--getShortClassName:" + componentName3.getShortClassName());
            Log.d(TAG, "topActivity--ComponentName--flattenToShortString:" + componentName3.flattenToShortString());
            Log.d(TAG, "topActivity--ComponentName--flattenToString:" + componentName3.flattenToString());
            Log.d(TAG, "topActivity--ComponentName--toShortString:" + componentName3.toShortString());
            Log.d(TAG, "topActivity--ComponentName--describeContents:" + componentName3.describeContents());

        }

    }

    //04
    @SuppressLint("WrongConstant")
    private void initMemoryInfo() {
        Log.d(TAG, "04");
        memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        Log.d(TAG, "activityManager--getLargeMemoryClass:" + activityManager.getLargeMemoryClass());
        Log.d(TAG, "activityManager--getMemoryClass:" + activityManager.getMemoryClass());
        Log.d(TAG, "memoryInfo--availMem:" + (memoryInfo.availMem / 1024 / 1024) + "M");
        Log.d(TAG, "memoryInfo--totalMem:" + (memoryInfo.totalMem / 1024 / 1024) + "M");
        Log.d(TAG, "memoryInfo--lowMemory:" + memoryInfo.lowMemory);
        Log.d(TAG, "memoryInfo--threshold:" + memoryInfo.threshold);
        Log.d(TAG, "memoryInfo--describeContents():" + memoryInfo.describeContents());
    }


    //05
    private void initRunningTaskInfo() {
        Log.d(TAG, "05");
        Log.d(TAG, "RunningTaskInfo集合：" + activityManager.getRunningTasks(10).size());

        for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(10)) {
            this.runningTaskInfo = runningTaskInfo;
            ComponentName componentName1 = runningTaskInfo.baseActivity;
            if (componentName1 != null) {
                Log.d(TAG, "runningTaskInfo--ComponentName--baseActivity--getClassName:" + componentName1.getClassName());
                Log.d(TAG, "runningTaskInfo--ComponentName--baseActivity--getPackageName:" + componentName1.getPackageName());
                Log.d(TAG, "runningTaskInfo--ComponentName--baseActivity--getShortClassName:" + componentName1.getShortClassName());
                Log.d(TAG, "runningTaskInfo--ComponentName--baseActivity--flattenToShortString:" + componentName1.flattenToShortString());
                Log.d(TAG, "runningTaskInfo--ComponentName--baseActivity--flattenToString:" + componentName1.flattenToString());
                Log.d(TAG, "runningTaskInfo--ComponentName--baseActivity--toShortString:" + componentName1.toShortString());
                Log.d(TAG, "\n");
            }

            ComponentName componentName2 = runningTaskInfo.topActivity;
            if (componentName1 != null) {
                Log.d(TAG, "runningTaskInfo--ComponentName--topActivity--getClassName:" + componentName2.getClassName());
                Log.d(TAG, "runningTaskInfo--ComponentName--topActivity--getPackageName:" + componentName2.getPackageName());
                Log.d(TAG, "runningTaskInfo--ComponentName--topActivity--getShortClassName:" + componentName2.getShortClassName());
                Log.d(TAG, "runningTaskInfo--ComponentName--topActivity--flattenToShortString:" + componentName2.flattenToShortString());
                Log.d(TAG, "runningTaskInfo--ComponentName--topActivity--flattenToString:" + componentName2.flattenToString());
                Log.d(TAG, "runningTaskInfo--ComponentName--topActivity--toShortString:" + componentName2.toShortString());
                Log.d(TAG, "\n");
            }
        }
    }

    //06
    @SuppressLint("WrongConstant")
    private void initRunningAppProcessInfo() {
        Log.d(TAG, "06");
        Log.d(TAG, "RunningAppProcessInfo集合：" + activityManager.getRunningAppProcesses().size());
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            this.runningAppProcessInfo = runningAppProcessInfo;
            ComponentName componentName1 = runningAppProcessInfo.importanceReasonComponent;
            if (componentName1 != null) {
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--getClassName:" + componentName1.getClassName());
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--getPackageName:" + componentName1.getPackageName());
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--getShortClassName:" + componentName1.getShortClassName());
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--flattenToShortString:" + componentName1.flattenToShortString());
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--flattenToString:" + componentName1.flattenToString());
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--toShortString:" + componentName1.toShortString());
                Log.d(TAG, "runningAppProcessInfo--importanceReasonComponent--describeContents:" + componentName1.describeContents());
                Log.d(TAG, " \n");
            }
            Log.d(TAG, "processName:" + runningAppProcessInfo.processName);
            Log.d(TAG, "describeContents():" + runningAppProcessInfo.describeContents());
            Log.d(TAG, "importance:" + runningAppProcessInfo.importance);
            Log.d(TAG, "importanceReasonCode:" + runningAppProcessInfo.importanceReasonCode);
            Log.d(TAG, "importanceReasonPid:" + runningAppProcessInfo.importanceReasonPid);
            Log.d(TAG, "lastTrimLevel:" + runningAppProcessInfo.lastTrimLevel);
            Log.d(TAG, "lru:" + runningAppProcessInfo.lru);
            Log.d(TAG, "uid:" + runningAppProcessInfo.uid);
            for (int i = 0; i < runningAppProcessInfo.pkgList.length; i++) {
                Log.d(TAG, "runningAppProcessInfo.pkgList:" + runningAppProcessInfo.pkgList[i]);
                Log.d(TAG, " \n");
            }
        }
    }

    /**
     * 07 测试发现，三星xplay6 7.11版本，运行的Service有92个
     */
    @SuppressLint("WrongConstant")
    private void initrunningServiceInfo() {
        Log.d(TAG, "07");
        Log.d(TAG, "RunningServicesInfo集合：" + activityManager.getRunningServices(100).size());

        for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(100)) {
            this.runningServiceInfo = runningServiceInfo;
            ComponentName componentName1 = runningServiceInfo.service;
            if (componentName1 != null) {
                Log.d(TAG, "runningServiceInfo--ComponentName--service--getClassName:" + componentName1.getClassName());
                Log.d(TAG, "runningServiceInfo--ComponentName--service--getPackageName:" + componentName1.getPackageName());
                Log.d(TAG, "runningServiceInfo--ComponentName--service--getShortClassName:" + componentName1.getShortClassName());
                Log.d(TAG, "runningServiceInfo--ComponentName--service--flattenToShortString:" + componentName1.flattenToShortString());
                Log.d(TAG, "runningServiceInfo--ComponentName--service--flattenToString:" + componentName1.flattenToString());
                Log.d(TAG, "runningServiceInfo--ComponentName--service--toShortString:" + componentName1.toShortString());
                Log.d(TAG, "runningServiceInfo--ComponentName--service--describeContents:" + componentName1.describeContents());
                Log.d(TAG, " \n");
            }
            Log.d(TAG, "clientPackage:" + runningServiceInfo.clientPackage);
            Log.d(TAG, "clientCount:" + runningServiceInfo.clientCount);
            Log.d(TAG, "clientLabel:" + runningServiceInfo.clientLabel);
            Log.d(TAG, "activeSince:" + runningServiceInfo.activeSince);
            Log.d(TAG, "process:" + runningServiceInfo.process);
            Log.d(TAG, "crashCount:" + runningServiceInfo.crashCount);
            Log.d(TAG, "describeContents:" + runningServiceInfo.describeContents());
            Log.d(TAG, "flags:" + runningServiceInfo.flags);
            Log.d(TAG, "pid:" + runningServiceInfo.pid);
            Log.d(TAG, "restarting:" + runningServiceInfo.restarting);
            Log.d(TAG, "started:" + runningServiceInfo.started);
            Log.d(TAG, "uid:" + runningServiceInfo.uid);
            Log.d(TAG, "lastActivityTime:" + runningServiceInfo.lastActivityTime);
            Log.d(TAG, " \n");
        }
    }

    //08
    @SuppressLint("WrongConstant")
    private void initTaskDescription() {
        Log.d(TAG, "08");
        //自定义一个
        String label = this.getResources().getString(this.getApplicationInfo().labelRes);
        int colorPrimary = this.getResources().getColor(R.color.colorPrimary);
        Bitmap sIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.news_alerm);

        //创建自定义实例
        taskDescription = new ActivityManager.TaskDescription(label, sIcon, colorPrimary);
        setTaskDescription(taskDescription);

        Log.d(TAG, "getLabel:" + taskDescription.getLabel());
        Log.d(TAG, "describeContents():" + taskDescription.describeContents());
        Log.d(TAG, "getIcon()==null" + (taskDescription.getIcon() == null));
        Log.d(TAG, "getPrimaryColor:" + taskDescription.getPrimaryColor());
    }

    //09
    @SuppressLint("WrongConstant")
    private void initProcessErrorStateInfo() {
        Log.d(TAG, " \n");
        Log.d(TAG, "09");
        Log.d(TAG, "processErrorStateInfo集合：" + activityManager.getProcessesInErrorState());
        if (activityManager.getProcessesInErrorState() != null) {
            for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : activityManager.getProcessesInErrorState()) {
                this.processErrorStateInfo = processErrorStateInfo;
                Log.d(TAG, "longMsg:" + processErrorStateInfo.longMsg);
                Log.d(TAG, "shortMsg:" + processErrorStateInfo.shortMsg);
                Log.d(TAG, "processName:" + processErrorStateInfo.processName);
                Log.d(TAG, "stackTrace:" + processErrorStateInfo.stackTrace);
                Log.d(TAG, "tag:" + processErrorStateInfo.tag);
                Log.d(TAG, "condition:" + processErrorStateInfo.condition);
                Log.d(TAG, "describeContents():" + processErrorStateInfo.describeContents());
                Log.d(TAG, "pid:" + processErrorStateInfo.pid);
                Log.d(TAG, "uid:" + processErrorStateInfo.uid);
            }
        }

    }


}
