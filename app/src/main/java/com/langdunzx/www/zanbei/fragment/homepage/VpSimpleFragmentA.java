package com.langdunzx.www.zanbei.fragment.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langdunzx.www.zanbei.R;


public class VpSimpleFragmentA extends Fragment {

	private View view;
	private String mTitle;
	private int position;
	private static final String BUNDLE_TITLE = "title";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Bundle bundle = getArguments();
		if (bundle != null) {
			mTitle = bundle.getString(BUNDLE_TITLE);
			position=bundle.getInt("position");
		}
		view = inflater.inflate(R.layout.fragment_homepage_first, null);



		return view;
	}

	public static VpSimpleFragmentA newInstance(String title,int position) {
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, title);
		bundle.putInt("position", position);
		VpSimpleFragmentA fragment = new VpSimpleFragmentA();
		fragment.setArguments(bundle);

		return fragment;

	}




	//	@Override
	//	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
	//		// TODO Auto-generated method stub
	//		super.onActivityCreated(savedInstanceState);
	//		LogUtil.e("VpSimpleFragment", "onActivityCreated()");
	//	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	//	@Override
	//	public void onResume() {
	//		// TODO Auto-generated method stub
	//		super.onResume();
	//		LogUtil.e("VpSimpleFragment", "onResume()");
	//	}



	//	@Override
	//	public void onPause() {
	//		// TODO Auto-generated method stub
	//		super.onPause();
	//		LogUtil.e("VpSimpleFragment", "onPause()");
	//	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	//	@Override
	//	public void onDetach() {
	//		// TODO Auto-generated method stub
	//		super.onDetach();
	//		LogUtil.e("VpSimpleFragment", "onDetach()");
	//	}

}
