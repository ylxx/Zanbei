package com.langdunzx.www.zanbei.activity.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.home.HomeActivity;
import com.langdunzx.www.zanbei.utils.ClickUtil;
import com.langdunzx.www.zanbei.view.VisibilityUtil;

/**
 * 首次启动，选择感兴趣的栏目
 * @author DarkCrow
 *
 */
public class TypeActivity extends AppCompatActivity {
    /**
     * 记录Student,Worker,Parent点击状态
     */
    private boolean isStudentClick = false;
    private boolean isWorkerClick = false;
    private boolean isParentClick = false;
    /**
     * 各种动画
     */
    private Animation amWorkerToRight,amWorkerBackLeft,amWorkerToLeft,amWorkerBackRight,
            amStudentToRight,amStudentBackLeft,amParentToLeft,amParentBackRight,
            amChuzToRight,amChuzBackLeft,amGaozToDown,amGaozBackUp,amDaxToLeft,amDaxBackRight,
            amZhongxToLeft,amZhongxueBackRight,amXiaoxToDown,amXiaoxBackUp,amXueqToRight,amXueqBackLeft,
            amXingbToUp,amXingbBackDown,amToTransulcence,amBackEntity;

    /**
     * 学生，上班族，家长
     */
    private TextView tvStudent,tvWorker,tvParent;
    /**
     * 上班族：男生／女生
     */
    private TextView tvBoy,tvGirl;
    /**
     * 家长：学前／小学／中学
     */
    private TextView tvPerschool,tvXiaoxue,tvZhongxue;
    /**
     * 学生：初中／高中／大学
     */
    private TextView tvChuzhong,tvGaozhong,tvDaxue;

    private TextView tvSkip;

    private LinearLayout llWorker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initview();
    }
    private void initview() {

        tvStudent = (TextView) findViewById(R.id.tv_type_student);
        tvParent = (TextView) findViewById(R.id.tv_type_parent);
        tvWorker = (TextView) findViewById(R.id.tv_type_worker);
        tvBoy = (TextView) findViewById(R.id.tv_type_boy);
        tvGirl = (TextView) findViewById(R.id.tv_type_girl);
        tvPerschool = (TextView) findViewById(R.id.tv_type_preschool);
        tvZhongxue = (TextView) findViewById(R.id.tv_type_zhongxue);
        tvXiaoxue = (TextView) findViewById(R.id.tv_type_xiaoxue);
        tvChuzhong = (TextView) findViewById(R.id.tv_type_chuzhong);
        tvGaozhong = (TextView) findViewById(R.id.tv_type_gaozhong);
        tvDaxue = (TextView) findViewById(R.id.tv_type_daxue);
        llWorker = (LinearLayout) findViewById(R.id.ll_type_worker);
        tvSkip = (TextView) findViewById(R.id.type_skip);

        amWorkerToRight = AnimationUtils.loadAnimation(this, R.anim.worker_to_right);
        amWorkerBackLeft = AnimationUtils.loadAnimation(this, R.anim.worker_back_left);
        amWorkerToLeft = AnimationUtils.loadAnimation(this, R.anim.worker_to_left);
        amWorkerBackRight = AnimationUtils.loadAnimation(this, R.anim.worker_back_right);
        amStudentBackLeft = AnimationUtils.loadAnimation(this, R.anim.student_back_left);
        amStudentToRight = AnimationUtils.loadAnimation(this, R.anim.student_to_right);
        amParentToLeft = AnimationUtils.loadAnimation(this, R.anim.parent_to_left);
        amParentBackRight = AnimationUtils.loadAnimation(this, R.anim.parent_back_right);
        amChuzToRight = AnimationUtils.loadAnimation(this, R.anim.chuzhong_to_right);
        amChuzBackLeft = AnimationUtils.loadAnimation(this, R.anim.chuzhong_back_left);
        amGaozToDown = AnimationUtils.loadAnimation(this, R.anim.gaozhong_to_down);
        amGaozBackUp = AnimationUtils.loadAnimation(this, R.anim.gaozhong_back_up);
        amDaxToLeft = AnimationUtils.loadAnimation(this, R.anim.daxue_to_left);
        amDaxBackRight = AnimationUtils.loadAnimation(this, R.anim.daxue_back_right);
        amZhongxToLeft = AnimationUtils.loadAnimation(this, R.anim.zhongxue_to_left);
        amZhongxueBackRight = AnimationUtils.loadAnimation(this, R.anim.zhongxue_back_right);
        amXiaoxToDown = AnimationUtils.loadAnimation(this, R.anim.xiaoxue_to_down);
        amXiaoxBackUp = AnimationUtils.loadAnimation(this, R.anim.xiaoxue_back_up);
        amXueqToRight = AnimationUtils.loadAnimation(this, R.anim.xueqian_to_right);
        amXueqBackLeft = AnimationUtils.loadAnimation(this, R.anim.xueqian_back_left);
        amXingbToUp = AnimationUtils.loadAnimation(this, R.anim.xingbie_to_up);
        amXingbBackDown = AnimationUtils.loadAnimation(this, R.anim.xingbie_back_down);
        amToTransulcence = AnimationUtils.loadAnimation(this, R.anim.student_parent_to_translucence);
        amBackEntity = AnimationUtils.loadAnimation(this, R.anim.student_parent_back_entity);

        /**
         * 实现点击接口
         */
        ClickUtil.setClickListener(listener, tvStudent,tvParent,tvWorker,
                tvBoy,tvGirl,tvPerschool,tvZhongxue,tvXiaoxue,
                tvChuzhong,tvGaozhong,tvDaxue,tvSkip);

    }

    /**
     * 监听事件
     *
     */
    private View.OnClickListener listener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_type_student:
                    //只有上班族和家长都处于未点击状态才会执行
                    if(isParentClick == false && isWorkerClick == false){
                        VisibilityUtil.setVisible(0, llWorker,tvPerschool,tvXiaoxue,tvZhongxue);
                        isParentClick = false;
                        isWorkerClick = false;
                        if(!isStudentClick){
                            /**
                             * tvWorker view移动
                             */
                            tvWorker.startAnimation(amWorkerToLeft);
                            tvParent.startAnimation(amParentToLeft);
                            tvChuzhong.startAnimation(amChuzToRight);
                            tvGaozhong.startAnimation(amGaozToDown);
                            tvDaxue.startAnimation(amDaxToLeft);

                            amWorkerToLeft.setFillAfter(true);
                            amParentToLeft.setFillAfter(true);
                            amChuzToRight.setFillAfter(true);
                            amGaozToDown.setFillAfter(true);
                            amDaxToLeft.setFillAfter(true);
                            VisibilityUtil.setVisible(1, tvChuzhong,tvGaozhong,tvDaxue);
                            isStudentClick = true;
                        }else{
                            tvWorker.startAnimation(amWorkerBackRight);

                            tvParent.startAnimation(amParentBackRight);
                            tvChuzhong.startAnimation(amChuzBackLeft);
                            tvGaozhong.startAnimation(amGaozBackUp);
                            tvDaxue.startAnimation(amDaxBackRight);
                            amWorkerBackRight.setFillAfter(true);
                            amParentBackRight.setFillAfter(true);
                            amChuzBackLeft.setFillAfter(true);
                            amGaozBackUp.setFillAfter(true);
                            amDaxBackRight.setFillAfter(true);
                            VisibilityUtil.setVisible(0, tvChuzhong,tvGaozhong,tvDaxue);
                            isStudentClick = false;
                        }
                    }else{
                        break;
                    }
                    break;
                case R.id.tv_type_worker:

                    if(isParentClick == false && isStudentClick == false){
                        VisibilityUtil.setVisible(0, tvChuzhong,tvGaozhong,tvDaxue,tvPerschool,tvXiaoxue,tvZhongxue);
                        isParentClick = false;
                        isStudentClick = false;
                        if(!isWorkerClick){
                            llWorker.startAnimation(amXingbToUp);
                            tvStudent.startAnimation(amToTransulcence);
                            tvParent.startAnimation(amToTransulcence);
                            amXingbToUp.setFillAfter(true);
                            amToTransulcence.setFillAfter(true);
                            VisibilityUtil.setVisible(1, llWorker);
                            isWorkerClick = true;
                        }else{
                            llWorker.startAnimation(amXingbBackDown);
                            tvStudent.startAnimation(amBackEntity);
                            tvParent.startAnimation(amBackEntity);
                            amXingbBackDown.setFillAfter(true);
                            amBackEntity.setFillAfter(true);
                            VisibilityUtil.setVisible(0, llWorker);
                            isWorkerClick = false;
                        }

                    }else{
                        break;
                    }
                    break;
                case R.id.tv_type_parent:
                    if(isStudentClick == false && isWorkerClick == false){
                        VisibilityUtil.setVisible(0, llWorker,tvChuzhong,tvGaozhong,tvDaxue);
                        isStudentClick = false;
                        isWorkerClick = false;
                        if(!isParentClick){
                            tvWorker.startAnimation(amWorkerToRight);
                            tvStudent.startAnimation(amStudentToRight);
                            tvZhongxue.startAnimation(amZhongxToLeft);
                            tvXiaoxue.startAnimation(amXiaoxToDown);
                            tvPerschool.startAnimation(amXueqToRight);
                            amWorkerToRight.setFillAfter(true);
                            amStudentToRight.setFillAfter(true);
                            amZhongxToLeft.setFillAfter(true);
                            amXiaoxToDown.setFillAfter(true);
                            amXueqToRight.setFillAfter(true);
                            VisibilityUtil.setVisible(1, tvPerschool,tvZhongxue,tvXiaoxue);
                            isParentClick = true;
                        }else{
                            tvWorker.startAnimation(amWorkerBackLeft);
                            tvStudent.startAnimation(amStudentBackLeft);
                            tvZhongxue.startAnimation(amZhongxueBackRight);
                            tvXiaoxue.startAnimation(amXiaoxBackUp);
                            tvPerschool.startAnimation(amXueqBackLeft);
                            amWorkerBackLeft.setFillAfter(true);
                            amStudentBackLeft.setFillAfter(true);
                            amZhongxueBackRight.setFillAfter(true);
                            amXiaoxBackUp.setFillAfter(true);
                            amXueqBackLeft.setFillAfter(true);
                            VisibilityUtil.setVisible(0, tvPerschool,tvZhongxue,tvXiaoxue);
                            isParentClick = false;
                        }
                    }else{
                        break;
                    }
                    break;
                case R.id.tv_type_boy:
                    break;
                case R.id.tv_type_girl:
                    break;
                case R.id.tv_type_preschool:
                    break;
                case R.id.tv_type_zhongxue:
                    break;
                case R.id.tv_type_xiaoxue:
                    break;
                case R.id.tv_type_chuzhong:
//				Animation translateAnimation = new TranslateAnimation(30, 180 ,40,200);
//				translateAnimation.setDuration(500);
//				translateAnimation.setFillAfter(true);
//				tvChuzhong.startAnimation(translateAnimation);
//				VisibilityUtil.setVisible(0, tvStudent,tvWorker,tvParent,tvGaozhong,tvDaxue);
                    break;
                case R.id.tv_type_gaozhong:
                    break;
                case R.id.tv_type_daxue:
                    break;
                case R.id.type_skip:
                    startActivity(new Intent(TypeActivity.this,HomeActivity.class));
                    break;
                default:
                    break;
            }
        }

    };
}
