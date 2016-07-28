package com.langdunzx.www.zanbei.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

import com.langdunzx.www.zanbei.R;


public class SetNicknameDialog extends AlertDialog implements
		OnClickListener {
	private View view;
	public Button sure, cancle;
	public EditText nickname;

	public SetNicknameDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.dialog_set_nick, null);
		sure = (Button) view.findViewById(R.id.dialog_sure);
		cancle = (Button) view.findViewById(R.id.dialog_cancel);
		nickname = (EditText) view.findViewById(R.id.nickname_et);
		cancle.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);

		this.addContentView(view, params);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.dialog_cancel) {
			this.dismiss();
		}
	}

}
