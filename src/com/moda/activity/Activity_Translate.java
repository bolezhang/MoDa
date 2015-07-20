package com.moda.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.moda.R;
import com.moda.newClass.Class_Language;

public class Activity_Translate extends Activity {
	List<Integer> translateAudioList;
	MediaPlayer translateMediaPlayer;
	ArrayList<Class_Language> translateGroup;
	ListView translateList = null;
	SimpleAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.translate_activity);
		initiateTranslatePages();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initiateTranslatePages() {
		// 获取ListView对象
		translateList = (ListView) super.findViewById(R.id.listView1);
		translateGroup = loadSentencesAndAudios();
		// 下面是数据映射关系,mFrom和mTo按顺序一一对应
		String[] mFrom = new String[] { "img", "title1", "title2" };
		int[] mTo = new int[] { R.id.img, R.id.show_chinese, R.id.show_english };
		// 获取数据,这里随便加了10条数据,实际开发中可能需要从数据库或网络读取
		List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mMap = null;
		for (int i = 0; i < translateGroup.size(); i++) {
			mMap = new HashMap<String, Object>();
			mMap.put("img", null);
			mMap.put("title1", translateGroup.get(i).getChinese());
			mMap.put("title2", translateGroup.get(i).getEnglish());
			mList.add(mMap);
		}
		// 创建适配器
		mAdapter = new SimpleAdapter(this, mList, R.layout.translate_item,
				mFrom, mTo);
		translateList.setAdapter(mAdapter);
		translateList.setOnItemClickListener(new OnItemClickListenerImpl());
		// 创建media player
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			translateMediaPlayer = mediaCreate(getApplicationContext(), translateAudioList.get(position));
			translateMediaPlayer.start();
		}
	}

	public static MediaPlayer mediaCreate(Context context, int resid) {
		try {
			AssetFileDescriptor afd = context.getResources().openRawResourceFd(
					resid);
			if (afd == null)
				return null;
			MediaPlayer mp = new MediaPlayer();
			mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());
			afd.close();
			mp.prepare();
			return mp;
		} catch (IOException ex) {
			Log.d(null, "create failed:", ex);
		}
		return null;
	}

	private ArrayList<Class_Language> loadSentencesAndAudios() {
		ArrayList<Class_Language> group = new ArrayList<Class_Language>();
		Class_Language l0 = new Class_Language(0,
				getString(R.string.translate1),
				getString(R.string.translateTo1));
		Class_Language l1 = new Class_Language(1,
				getString(R.string.translate2),
				getString(R.string.translateTo2));
		Class_Language l2 = new Class_Language(2,
				getString(R.string.translate3),
				getString(R.string.translateTo3));
		Class_Language l3 = new Class_Language(3,
				getString(R.string.translate4),
				getString(R.string.translateTo4));
		Class_Language l4 = new Class_Language(4,
				getString(R.string.translate5),
				getString(R.string.translateTo5));
		Class_Language l5 = new Class_Language(5,
				getString(R.string.translate6),
				getString(R.string.translateTo6));
		Class_Language l6 = new Class_Language(6,
				getString(R.string.translate7),
				getString(R.string.translateTo7));
		Class_Language l7 = new Class_Language(7,
				getString(R.string.translate8),
				getString(R.string.translateTo8));
		Class_Language l8 = new Class_Language(7,
				getString(R.string.translate9),
				getString(R.string.translateTo9));
		Class_Language l9 = new Class_Language(8,
				getString(R.string.translate10),
				getString(R.string.translateTo10));
		Class_Language l10 = new Class_Language(9,
				getString(R.string.translate11),
				getString(R.string.translateTo11));
		Class_Language l11 = new Class_Language(10,
				getString(R.string.translate12),
				getString(R.string.translateTo12));
		Class_Language l12 = new Class_Language(11,
				getString(R.string.translate13),
				getString(R.string.translateTo13));
		Class_Language l13 = new Class_Language(12,
				getString(R.string.translate14),
				getString(R.string.translateTo14));
		Class_Language l14 = new Class_Language(13,
				getString(R.string.translate15),
				getString(R.string.translateTo15));
		Class_Language l15 = new Class_Language(14,
				getString(R.string.translate16),
				getString(R.string.translateTo16));
		Class_Language l16 = new Class_Language(15,
				getString(R.string.translate17),
				getString(R.string.translateTo17));

		Class_Language l17 = new Class_Language(16,
				getString(R.string.translate18),
				getString(R.string.translateTo18));
		Class_Language l18 = new Class_Language(17,
				getString(R.string.translate19),
				getString(R.string.translateTo19));
		Class_Language l19 = new Class_Language(18,
				getString(R.string.translate20),
				getString(R.string.translateTo20));
		Class_Language l20 = new Class_Language(19,
				getString(R.string.translate21),
				getString(R.string.translateTo21));
		Class_Language l21 = new Class_Language(20,
				getString(R.string.translate22),
				getString(R.string.translateTo22));
		Class_Language l22 = new Class_Language(21,
				getString(R.string.translate23),
				getString(R.string.translateTo23));
		Class_Language l23 = new Class_Language(22,
				getString(R.string.translate24),
				getString(R.string.translateTo24));
		Class_Language l24 = new Class_Language(23,
				getString(R.string.translate25),
				getString(R.string.translateTo25));
		Class_Language l25 = new Class_Language(24,
				getString(R.string.translate26),
				getString(R.string.translateTo26));
		Class_Language l26 = new Class_Language(25,
				getString(R.string.translate27),
				getString(R.string.translateTo27));
		Class_Language l27 = new Class_Language(26,
				getString(R.string.translate28),
				getString(R.string.translateTo28));
		Class_Language l28 = new Class_Language(27,
				getString(R.string.translate29),
				getString(R.string.translateTo29));
		Class_Language l29 = new Class_Language(28,
				getString(R.string.translate30),
				getString(R.string.translateTo30));
		Class_Language l30 = new Class_Language(29,
				getString(R.string.translate31),
				getString(R.string.translateTo31));
		Class_Language l31 = new Class_Language(30,
				getString(R.string.translate32),
				getString(R.string.translateTo32));
		Class_Language l32 = new Class_Language(31,
				getString(R.string.translate33),
				getString(R.string.translateTo33));
		Class_Language l33 = new Class_Language(32,
				getString(R.string.translate34),
				getString(R.string.translateTo34));
		Class_Language l34 = new Class_Language(33,
				getString(R.string.translate35),
				getString(R.string.translateTo35));
		Class_Language l35 = new Class_Language(34,
				getString(R.string.translate36),
				getString(R.string.translateTo36));
		Class_Language l36 = new Class_Language(35,
				getString(R.string.translate37),
				getString(R.string.translateTo37));
		Class_Language l37 = new Class_Language(36,
				getString(R.string.translate38),
				getString(R.string.translateTo38));
		Class_Language l38 = new Class_Language(37,
				getString(R.string.translate39),
				getString(R.string.translateTo39));

		group.add(l0);
		group.add(l1);
		group.add(l2);
		group.add(l3);
		group.add(l4);
		group.add(l5);
		group.add(l6);
		group.add(l7);
		group.add(l8);
		group.add(l9);
		group.add(l10);
		group.add(l11);
		group.add(l12);
		group.add(l13);
		group.add(l14);
		group.add(l15);
		group.add(l16);
		group.add(l17);
		group.add(l18);
		group.add(l19);
		group.add(l20);
		group.add(l21);
		group.add(l22);
		group.add(l23);
		group.add(l24);
		group.add(l25);
		group.add(l26);
		group.add(l27);
		group.add(l28);
		group.add(l29);
		group.add(l30);
		group.add(l31);
		group.add(l32);
		group.add(l33);
		group.add(l34);
		group.add(l35);
		group.add(l36);
		group.add(l37);
		group.add(l38);

		translateAudioList = new ArrayList<Integer>();
		translateAudioList.add(R.raw.audio1);
		translateAudioList.add(R.raw.audio2);
		translateAudioList.add(R.raw.audio3);
		translateAudioList.add(R.raw.audio4);
		translateAudioList.add(R.raw.audio5);
		translateAudioList.add(R.raw.audio6);
		translateAudioList.add(R.raw.audio7);
		translateAudioList.add(R.raw.audio8);
		translateAudioList.add(R.raw.audio9);
		translateAudioList.add(R.raw.audio10);
		translateAudioList.add(R.raw.audio11);
		translateAudioList.add(R.raw.audio12);
		translateAudioList.add(R.raw.audio13);
		translateAudioList.add(R.raw.audio14);
		translateAudioList.add(R.raw.audio15);
		translateAudioList.add(R.raw.audio16);
		translateAudioList.add(R.raw.audio17);
		translateAudioList.add(R.raw.audio18);
		translateAudioList.add(R.raw.audio19);
		translateAudioList.add(R.raw.audio20);
		translateAudioList.add(R.raw.audio21);
		translateAudioList.add(R.raw.audio22);
		translateAudioList.add(R.raw.audio23);
		translateAudioList.add(R.raw.audio24);
		translateAudioList.add(R.raw.audio25);
		translateAudioList.add(R.raw.audio26);
		translateAudioList.add(R.raw.audio27);
		translateAudioList.add(R.raw.audio28);
		translateAudioList.add(R.raw.audio29);
		translateAudioList.add(R.raw.audio30);
		translateAudioList.add(R.raw.audio31);
		translateAudioList.add(R.raw.audio32);
		translateAudioList.add(R.raw.audio33);
		translateAudioList.add(R.raw.audio34);
		translateAudioList.add(R.raw.audio35);
		translateAudioList.add(R.raw.audio36);
		translateAudioList.add(R.raw.audio37);
		translateAudioList.add(R.raw.audio38);
		translateAudioList.add(R.raw.audio39);
		return group;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.activity__menu_contactMe:
			Intent intent = new Intent(Intent.ACTION_SENDTO);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
			intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
			intent.setData(Uri.parse("mailto:zhangbole725@gmail.com"));
			// when user returns, returns to your app instead of the email app.
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		case R.id.activity__menu_shareApp:
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
					"Hey, download this app!");
			startActivity(shareIntent);
			break;
		case R.id.activity_menu_about:
			Intent settingIntent = new Intent(Intent.ACTION_SEND);
			settingIntent.setClass(getApplicationContext(),
					Activity_Setting.class);
			startActivity(settingIntent);
			break;
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}
}
