package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.os.Bundle;
import android.widget.RadioButton;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Shinelon on 2017/9/21.
 */

public class SetUp3Activity extends BaseSetUpAcitivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_3);
        ((RadioButton) findViewById(R.id.rb_third)).setChecked(true);

    }

    @Override
    public void showNext() {
        startActivityAndFinishSelf(SetUp4Activity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(SetUp2Activity.class);
    }
}
