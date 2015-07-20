package com.moda.viewpager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.moda.R;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class FragmentPage3 extends Fragment {
	Context ctx;
	EditText et_note;
	Button bt_save;
	Button bt_clr;
	View rootView;
	Vibrator vibrator;

	public FragmentPage3() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ctx = container.getContext();
		vibrator = (Vibrator) getActivity().getSystemService(
				Context.VIBRATOR_SERVICE);
		rootView = inflater.inflate(R.layout.fragment_page3, container, false);
		initializing();
		loadDatas();
		return rootView;
	}

	private void initializing() {
		et_note = (EditText) rootView.findViewById(R.id.fragment_edittext_note);
		bt_save = (Button) rootView.findViewById(R.id.note_save);
		bt_clr = (Button) rootView.findViewById(R.id.note_clear);

		bt_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vibrator.vibrate(50);
				saveDatas();
			}
		});
		bt_clr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				et_note.setText(null);
				vibrator.vibrate(50);
				saveDatas();
			}
		});
	}

	private void loadDatas() {
		SharedPreferences sharedata = ctx.getSharedPreferences("fragmentNote",
				0);
		String contentData = sharedata.getString("note_content", null);
		et_note.setText(contentData);
	}

	private void saveDatas() {
		SharedPreferences sp = ctx.getSharedPreferences("fragmentNote", 0);
		Editor editor = sp.edit();
		editor.putString("note_content", et_note.getText().toString());
		editor.commit();
	}
}