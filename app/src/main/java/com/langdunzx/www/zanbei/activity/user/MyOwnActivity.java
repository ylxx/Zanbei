package com.langdunzx.www.zanbei.activity.user;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;

public class MyOwnActivity extends BaseFragmentActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithActionBar(R.layout.activity_my_own,"我的");

        tv = (TextView) findViewById(R.id.tv_ceshi);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(R.layout.snackbar_myown, "it is Snackbar", Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
