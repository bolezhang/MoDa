package com.moda.viewpager;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.moda.R;
import com.moda.activity.Activity_Translate;

public class FragmentPage1 extends Fragment {
	AnimationDrawable animDrawable;
	Button bt_translate;

	public FragmentPage1() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_page1, container,
				false);
		bt_translate = (Button) rootView.findViewById(R.id.bt_translate);
		startAnimation();
		bt_translate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), Activity_Translate.class);
				startActivity(intent);
			}
		});

		bt_translate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					animDrawable.stop();
					break;
				case MotionEvent.ACTION_UP:
					animDrawable.start();
					break;
				}

				return false;
			}
		});
		return rootView;
	}

	private void startAnimation() {
		animDrawable = (AnimationDrawable) getResources().getDrawable(
				R.anim.animation_translate);
		bt_translate.setBackground(animDrawable);
		animDrawable.start();
	}

}