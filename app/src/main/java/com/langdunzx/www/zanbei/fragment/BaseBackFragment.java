package com.langdunzx.www.zanbei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.langdunzx.www.zanbei.application.LangDunApplication;



/**
 * Created by cuiyinglai on 16/5/17.
 */
public class BaseBackFragment extends Fragment {

    private LangDunApplication mApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mApplication=(LangDunApplication) getActivity().getApplication();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        this.getView().setFocusable(true);
        this.getView().setFocusableInTouchMode(true);
        this.getView().requestFocus();
        this.getView().setOnKeyListener(backKeyListener);
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        this.getView().setFocusable(false);
        this.getView().setFocusableInTouchMode(false);
        // this.getView().requestFocus();
        this.getView().setOnKeyListener(null);
        //if (AppUtils.isRunningBackground(mApplication)) {
         //   System.out.println("------------------------------true");
           // SPUtils.put(mApplication, "isbackground", true);
       // }
    }


    /**
     * 监听返回键
     */
    private View.OnKeyListener backKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_UP
                    && keyCode == KeyEvent.KEYCODE_BACK) {
                //exit();
                return true;
            } else
                return false;
        }

    };


   // public void exit() {
        // 弹出对话框 然后结束程序
       /// exitDialog= new LDDialog(getActivity(), this);
      //  exitDialog.show();
      //  exitDialog.getDialogContentTxt().setText("确定要离开吗？");
       // exitDialog.getPreviousBtn().setText(getString(R.string.leave));
        //exitDialog.getNextBtn().setText(getString(R.string.no_leave));
        //exitDialog.getDialogImage().setBackgroundResource(
         //       R.drawable.dialog_error_icon);
   // }


   // @Override
   //public void OnPreviousButtonClicked(LDDialog dialog) {
        // TODO Auto-generated method stub
      //  if (null != exitDialog && exitDialog.isShowing()) {
         //   exitDialog.dismiss();
         //   exitDialog = null;

       // }
      //  System.exit(0);
   // }

   // @Override
   // public void OnMiddleButtonClicked(LDDialog dialog) {
        // TODO Auto-generated method stub

   // }
//
   // @Override
   // public void OnNextButtonClicked(LDDialog dialog) {
        // TODO Auto-generated method stub
       // if (null != exitDialog && exitDialog.isShowing()) {
        //    exitDialog.dismiss();
        //    exitDialog = null;
       // }

    //}

}
