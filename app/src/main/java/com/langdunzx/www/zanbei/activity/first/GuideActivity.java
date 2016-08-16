package com.langdunzx.www.zanbei.activity.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.view.OnViewChangeListener;
import com.langdunzx.www.zanbei.view.ScrollLayout;

/**
 *
 * @author DarkCrow
 * @description 指导页控制类
 *
 */
public class GuideActivity extends AppCompatActivity implements OnViewChangeListener {
    private static final String TAG = "Guide";
    private ScrollLayout mScrollLayout;
    private ImageView[] imgs;
    private int count;
    private int currentItem;
    private Button startBtn;
    private RelativeLayout mainRLayout;
    private LinearLayout pointLLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();
    }
    /**
     * 初始化
     */
    private void init()
    {
        mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayout);
        pointLLayout = (LinearLayout) findViewById(R.id.llayout);
        mainRLayout = (RelativeLayout) findViewById(R.id.mainRLayout);
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(onClick);
        count = mScrollLayout.getChildCount();
        imgs = new ImageView[count];
        for (int i = 0; i < count; i++) {
            imgs[i] = (ImageView) pointLLayout.getChildAt(i);
            imgs[i].setEnabled(true);
            imgs[i].setTag(i);
        }
        currentItem = 0;
        imgs[currentItem].setEnabled(false);
        mScrollLayout.SetOnViewChangeListener(this);
    }
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startBtn:
                    mScrollLayout.setVisibility(View.GONE);
                    pointLLayout.setVisibility(View.GONE);
                    mainRLayout.setBackgroundResource(R.drawable.qidongye_01);
                    Intent intent = new Intent(
                            GuideActivity.this,
                            TypeActivity.class);
                    GuideActivity.this.startActivity(intent);
                    GuideActivity.this.finish();
            }
        }
    };

    @Override
    public void OnViewChange(int view) {
        setcurrentPoint(view);
    }
    private void setcurrentPoint(int position) {
        if (position < 0 || position > count - 1 || currentItem == position) {
            return;
        }
        imgs[currentItem].setEnabled(true);
        imgs[position].setEnabled(false);
        currentItem = position;
    }

}
