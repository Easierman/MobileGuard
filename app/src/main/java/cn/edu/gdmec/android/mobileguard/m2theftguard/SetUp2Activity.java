package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Shinelon on 2017/9/21.
 */

public class SetUp2Activity extends BaseSetUpAcitivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_2);
        ((RadioButton) findViewById(R.id.rb_second)).setChecked(true);

    }

    @Override
    public void showNext() {
        startActivityAndFinishSelf(SetUp3Activity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(SetUp1Activity.class);
    }

}
