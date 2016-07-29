package com.langdunzx.www.zanbei.activity.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.langdunzx.www.zanbei.R;
import com.langdunzx.www.zanbei.activity.BaseFragmentActivity;
import com.langdunzx.www.zanbei.dialog.SetNicknameDialog;
import com.langdunzx.www.zanbei.utils.ClickUtil;
import com.langdunzx.www.zanbei.utils.GetPhotoFileName;
import com.langdunzx.www.zanbei.utils.MaxLengthWatcher;
import com.langdunzx.www.zanbei.view.CompressImage;
import com.langdunzx.www.zanbei.view.GetBitmapPath;
import com.langdunzx.www.zanbei.view.MasterPopupWindow;
import com.langdunzx.www.zanbei.view.RoundCorner;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

public class MyInforActivity extends BaseFragmentActivity implements ImageLoadingListener {

    private LinearLayout llHeadView,llNickName;
    private TextView tvNickName;
    private ImageView photo;
    private Button btSave;
    private PopupWindow popupWindow;
    private View popView;
    private SetNicknameDialog dialog;
    @SuppressLint("SdCardPath")
    private String photoPath = "/sdcard/zanbei";
    private String photoNamee;
    private GetPhotoFileName getPhotoFileName;
    private Uri imageUri;
    private CompressImage cpimage;
    private Bitmap finabit;
    private RoundCorner roundcorner;
    public static final int PHOTOGRAPH = 4;
    public static final int ALBUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithActionBar(R.layout.activity_my_infor,"我的信息");
        initView();
        ClickUtil.setClickListener(this,llHeadView,llNickName,btSave,
                popView.findViewById(R.id.tv_pic_1),
                popView.findViewById(R.id.tv_pic_2),
                popView.findViewById(R.id.tv_pic_3));
    }

    private void initView() {
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset <= -head_layout.getHeight() / 2){
                    mCollapsingToolbarLayo*//**//*ut.setTitle("个人中心");
                }else{
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });*/

        photo = (ImageView) findViewById(R.id.user_photo_iv);
        llHeadView = (LinearLayout) findViewById(R.id.ll_head_iv);
        llNickName = (LinearLayout) findViewById(R.id.ll_nick_name);
        tvNickName = (TextView) findViewById(R.id.tv_nick);
        btSave = (Button) findViewById(R.id.bt_save);

        roundcorner = new RoundCorner(cpimage, photo);
        getPhotoFileName = new GetPhotoFileName();
        photoNamee = photoPath + getPhotoFileName.getPhotoFileName() + ".jpg";

        popView = View.inflate(this, R.layout.view_popmenu, null);
        popupWindow = new MasterPopupWindow(popView, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new poponDismissListener());
        popupWindow.update();
    }



    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_head_iv:
                backgroundAlpha(0.7f);

                popupWindow.showAtLocation(findViewById(R.id.myinfo_layout_ll),
                        Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_pic_1:
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File file = new File(photoPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                File photo = new File(photoNamee);
                imageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, PHOTOGRAPH);
                popupWindow.dismiss();
                break;
            case R.id.tv_pic_2:
                Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum, ALBUM);
                popupWindow.dismiss();
                break;
            case R.id.tv_pic_3:
                popupWindow.dismiss();
                break;
            case R.id.ll_nick_name:
                dialog = new SetNicknameDialog(this);
                dialog.setView(new EditText(this));
                dialog.show();
                dialog.nickname.addTextChangedListener(new MaxLengthWatcher(20,
                        dialog.nickname));
                dialog.sure.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!checkIfNickname(dialog.nickname.getText().toString())) {
                            showError("输入昵称要小于6汉字或12字母");
                            return;
                        }
                        tvNickName.setText(dialog.nickname.getText().toString());
                        dialog.dismiss();
                    }
                    // private void getData() {
                    // // TODO Auto-generated method stub
                    // HashMap<String, String> map = new HashMap<String, String>();
                    // map.put("memberId", userId);
                    // map.put("nickName", dialog.nickname.getText().toString());
                    // new RequestCommant().requestNickname(new requestHandler(
                    // MyInfoActivity.this), MyInfoActivity.this, map);
                    // }
                });
                break;
            case R.id.bt_save:
                break;
            default:
                break;
        }
    }

    private void backgroundAlpha(float f) {
        // TODO Auto-generated method stub
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    private boolean checkIfNickname(String str) {
        int num = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (19968 <= charArray[i] && charArray[i] < 40623) {
                num = num + 2;
            } else {
                num = num + 1;
            }
        }
        if (num > 0 && num <= 12) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PHOTOGRAPH) {
            crop(imageUri);
        } else if (requestCode == ALBUM) {
            Uri orginalUri = data.getData();
            crop(orginalUri);
        }
        else if (requestCode == 3) {
            Bitmap bitmap = data.getParcelableExtra("data");
            finabit = roundcorner.toRoundCorner(bitmap, photo);
        }
    }
    /**
     * @Description: 裁剪图片
     */
    public void crop(Uri uri) {
        // 裁剪图片意图

        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            String url = GetBitmapPath.getPath(getApplicationContext(), uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        } else {
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }


    @Override
    public void onLoadingStarted(String imageUri, View view) {

    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

    }

    @Override
    public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {

        if (arg2 != null) {
            roundcorner = new RoundCorner(cpimage, photo);
            Bitmap roundBitmap = roundcorner.toRoundCorner(arg2, photo);
            photo.setImageBitmap(roundBitmap);
        }
    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {

    }

}
