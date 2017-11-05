package cn.edu.gdmec.android.mobileguard.m1home.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by 10255 on 2017/9/21.
 */

public class MyUtils {
    public static String getVersion(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo= null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
