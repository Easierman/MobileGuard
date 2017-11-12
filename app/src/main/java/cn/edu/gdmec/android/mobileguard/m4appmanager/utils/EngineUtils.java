package cn.edu.gdmec.android.mobileguard.m4appmanager.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.widget.Toast;


import org.apache.commons.codec.binary.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;


/**
 * Created by 黄煜辉 on 2017/9/20.
 */

public class EngineUtils {

    public static void shareApplication(Context context, AppInfo appInfo){
        Intent intent=new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                "推荐您使用一款软件，名称叫："+appInfo.appName
                        +"下载路径：https://play.google.com/store/apps/details?id="
                        +appInfo.packageName);
        context.startActivity(intent);
    }
    public static void startApplication(Context context,AppInfo appInfo){
        PackageManager pm=context.getPackageManager();
        Intent intent=pm.getLaunchIntentForPackage(appInfo.packageName);
        if (intent!=null){
            context.startActivity(intent);
        }else {
            Toast.makeText(context,"该应用没有启动界面",Toast.LENGTH_SHORT).show();
        }
    }
    public static void SettingAppDetail(Context context,AppInfo appInfo){
        Intent intent=new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:"+appInfo.packageName));
        context.startActivity(intent);
    }
    public static void uninstallApplication(Context context,AppInfo appInfo){
        if (appInfo.isUserApp) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:" + appInfo.packageName));
            context.startActivity(intent);
        }else {
            Toast.makeText(context,"应用无法卸载",Toast.LENGTH_LONG).show();
        }
    }
    public static void AboutApp(Context context,AppInfo appInfo){
        try {
            PackageManager pm=context.getPackageManager();
            PackageInfo packInfo= pm.getPackageInfo(appInfo.packageName,0);
            String version = packInfo.versionName;

            long firstInstallTime = packInfo.firstInstallTime;

            PackageInfo packinfo1 = pm.getPackageInfo(appInfo.packageName, PackageManager.GET_SIGNATURES);
            String certMsg ="";
            Signature[] sigs = packinfo1.signatures;    //签名
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            //获取证书
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(
                    new ByteArrayInputStream(sigs[0].toByteArray()));
            //获取证书发行者
            certMsg+= cert.getIssuerDN().toString();
            certMsg+= cert.getSubjectDN().toString();
            String date=null;
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd号 hh:mm:ss");
            date=dateformat.format(firstInstallTime);


            //List<PermissionInfo> permissionInfoList = new ArrayList<PermissionInfo>();
            List<String> a=new ArrayList<String>();
            PackageInfo packinfo2 = pm.getPackageInfo(appInfo.packageName, PackageManager.GET_PERMISSIONS);
            String[] permissions =packinfo2.requestedPermissions;

            if(permissions != null){
                for(String str : permissions){
                       /* PermissionInfo permissionInfo = context.getPackageManager().getPermissionInfo(str, 0);
                        permissionInfoList.add(permissionInfo);*/
                    a.add(str);
                }
            }
            String s = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString().substring(1,a.toString().length()-1)).replaceAll(".");

            AlertDialog.Builder builder =new AlertDialog.Builder(context);
            builder.setTitle(appInfo.appName);
            builder.setMessage("version:"+version+"\n"+
                    "Install time:"+"\n"+date+"\n"+
                    "Certificate issuer:"+certMsg+"\n"+
                    "Permission:"+"\n"+s);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
