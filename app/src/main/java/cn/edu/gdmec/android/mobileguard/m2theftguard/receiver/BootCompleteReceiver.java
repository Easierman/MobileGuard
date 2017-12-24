package cn.edu.gdmec.android.mobileguard.m2theftguard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.edu.gdmec.android.mobileguard.App;
import cn.edu.gdmec.android.mobileguard.m9advancedtools.service.AppLockService;

/**
 * Created by student on 17/10/17.
 */

public class BootCompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        ((App)(context.getApplicationContext())).correctSIM();
        context.startService(new Intent(context, AppLockService.class));
    }
}
