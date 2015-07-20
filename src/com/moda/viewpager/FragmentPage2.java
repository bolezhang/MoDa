package com.moda.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.moda.R;
import com.moda.activity.Activity_Call;
import com.moda.activity.Activity_Flyer;
import com.moda.activity.Activity_Hyperlink;
import com.moda.activity.Activity_Zoom;

public class FragmentPage2 extends Fragment {
	int i = 0;

	public FragmentPage2() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_page2, container,
				false);
		Button call = (Button) rootView.findViewById(R.id.bt_call);
		Button hyperlink = (Button) rootView.findViewById(R.id.bt_hyperlink);
		Button market = (Button) rootView.findViewById(R.id.bt_market);
		Button gallery = (Button) rootView.findViewById(R.id.bt_gallery);
		// getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		call.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Activity_Call.class);
				startActivity(intent);
			}
		});

		hyperlink.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Activity_Hyperlink.class);
				startActivity(intent);
			}
		});

		market.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Activity_Flyer.class);
				startActivity(intent);
			}
		});

		gallery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Activity_Zoom.class);
				startActivity(intent);
			}
		});

		return rootView;
	}
}