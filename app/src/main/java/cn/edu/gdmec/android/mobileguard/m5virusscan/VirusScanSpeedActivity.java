package cn.edu.gdmec.android.mobileguard.m5virusscan;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.edu.gdmec.android.mobileguard.R;
import cn.edu.gdmec.android.mobileguard.m5virusscan.adapter.ScanVirusAdapter;
import cn.edu.gdmec.android.mobileguard.m5virusscan.entity.ScanAppInfo;

import static android.R.id.edit;

/**
 * Created by Shinelon on 2017/9/21.
 */

public class VirusScanSpeedActivity extends AppCompatActivity implements View.OnClickListener{
    protected static final int SCAN_BENGIN = 100;
    protected static final int SCANNING = 101;
    protected static final int SCAN_FINISH = 102;
    private int total;
    private int process;
    private TextView mProcessTV;
    private PackageManager pm;
    private boolean flag;
    private boolean isStop;
    private TextView mScanAppTV;
    private Button mCancleBtn;
    private ImageView mScanningIcon;
    private RotateAnimation rani;
    private ListView mScanListView;
    private ScanVirusAdapter adapter;
    private List<ScanAppInfo> mScanAppIofos = new ArrayList<ScanAppInfo>();
    private SharedPreferences mSP;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){

                case SCAN_BENGIN:
                    mScanAppTV.setText("初始化杀毒引擎中...");
                    break;

                case SCANNING:
                 ScanAppInfo info  = (ScanAppInfo) msg.obj;
                    mScanAppTV.setText("正在扫描："+info.appName);
                    mScanAppIofos.add(info);
                    adapter.notifyDataSetChanged();
                    mScanListView.setSelection(mScanAppIofos.size());
                    break;

                case SCAN_FINISH:
                    mScanAppTV.setText("扫描完成！");
                    mScanningIcon.clearAnimation();
                    mCancleBtn.setBackgroundResource(R.drawable.scan_comlete);
                    saveScanTime();
                    break;
            }
        }
        private void saveScanTime(){
            SharedPreferences.Editor editor = mSP.edit();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentTime = sdf.format(new Date());
            currentTime="上次查杀："+currentTime;
            edit.putString("lastVirusScan",currentTime);
            edit.commit();
        };
    };
    @Override
    public void onClick(View view) {

    }
}
