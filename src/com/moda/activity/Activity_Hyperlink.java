package com.moda.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.moda.R;
import com.moda.newClass.Class_Website;

public class Activity_Hyperlink extends Activity {
	ListView hyperlinList = null;
	SimpleAdapter mAdapter = null;
	ArrayList<Class_Website> hyperlinkGroup;
	Vibrator vib;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hyperlink_activity);
		initializing();
		initiateHyperlinkPages();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initializing() {
		// 获取ListView对象
		hyperlinList = (ListView) super.findViewById(R.id.listView_hyperlink); // 取得组件
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	private void initiateHyperlinkPages() {
		loadHttpAdress();
		// 下面是数据映射关系,mFrom和mTo按顺序一一对应
		String[] mFrom = new String[] { "img", "title1", "title2" };
		int[] mTo = new int[] { R.id.img, R.id.show_web_name,
				R.id.show_web_address };
		// 获取数据,这里随便加了10条数据,实际开发中可能需要从数据库或网络读取
		List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mMap = null;
		for (int i = 0; i < hyperlinkGroup.size(); i++) {
			mMap = new HashMap<String, Object>();
			mMap.put("img", hyperlinkGroup.get(i).getImagePath());
			mMap.put("title1", hyperlinkGroup.get(i).getName());
			mMap.put("title2", hyperlinkGroup.get(i).getAddress());
			mList.add(mMap);
		}
		// 创建适配器
		mAdapter = new SimpleAdapter(this, mList, R.layout.hyperlink_item,
				mFrom, mTo);
		hyperlinList.setAdapter(mAdapter);
		hyperlinList.setOnItemClickListener(new OnItemClickListenerImpl());
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {
		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			vib.vibrate(200);
			String WebAddress = hyperlinkGroup.get(position).getAddress();
			Uri uri = Uri.parse("http://" + WebAddress);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
		}
	}

	private void loadHttpAdress() {
		hyperlinkGroup = new ArrayList<Class_Website>();
		Class_Website l0 = new Class_Website(0, R.drawable.youtube,
				getString(R.string.link_youtube), "www.youtube.com");
		Class_Website l1 = new Class_Website(1, R.drawable.google,
				getString(R.string.link_Google), "www.google.com");
		Class_Website l2 = new Class_Website(2, R.drawable.facebook,
				getString(R.string.link_Facebook), "www.facebook.com");
		Class_Website l3 = new Class_Website(3, R.drawable.twitter,
				getString(R.string.link_Twitter), "www.twitter.com");
		Class_Website l4 = new Class_Website(3, R.drawable.yahoo,
				getString(R.string.link_Yahoo), "www.yahoo.com");
		Class_Website l5 = new Class_Website(3, R.drawable.kijiji,
				getString(R.string.link_Kijiji), "www.kijiji.com");
		Class_Website l6 = new Class_Website(3, R.drawable.yorkbbs,
				getString(R.string.link_yorkbbs), "www.yorkbbs.ca");
		Class_Website l7 = new Class_Website(3, R.drawable.koreannet,
				getString(R.string.link_koreannet), "www.korean.net");
		Class_Website l8 = new Class_Website(3, R.drawable.naver,
				getString(R.string.link_naver), "www.naver.com");
		Class_Website l9 = new Class_Website(3, R.drawable.baidu,
				getString(R.string.link_baidu), "www.baidu.com");
		Class_Website l10 = new Class_Website(3, R.drawable.koreantimes,
				getString(R.string.link_koreantimes), "www.koreantimes.net");
		Class_Website l11 = new Class_Website(3, R.drawable.amazon,
				getString(R.string.link_amazon), "www.amazon.ca");
		Class_Website l12 = new Class_Website(3, R.drawable.ebay,
				getString(R.string.link_ebay), "www.ebay.ca");
		Class_Website l13 = new Class_Website(3, R.drawable.wangyi,
				getString(R.string.link_wangyi), "mail.163.com");
		Class_Website l14 = new Class_Website(3, R.drawable.taobao,
				getString(R.string.link_taobao), "www.taobao.com");
		Class_Website l15 = new Class_Website(3, R.drawable.iqiyi,
				getString(R.string.link_iqiyi), "www.iqiyi.com");
		Class_Website l16 = new Class_Website(3, R.drawable.youku,
				getString(R.string.link_youku), "www.youku.com");
		Class_Website l17 = new Class_Website(3, R.drawable.sina,
				getString(R.string.link_sina), "www.sina.com.cn");
		Class_Website l18 = new Class_Website(3, R.drawable.jingdong1,
				getString(R.string.link_jd), "www.jd.com");
		Class_Website l19 = new Class_Website(11, R.drawable.embassy,
				getString(R.string.link_embassy), "toronto.china-consulate.org");
		hyperlinkGroup.add(l0);
		hyperlinkGroup.add(l1);
		hyperlinkGroup.add(l2);
		hyperlinkGroup.add(l3);
		hyperlinkGroup.add(l4);
		hyperlinkGroup.add(l5);
		hyperlinkGroup.add(l6);
		hyperlinkGroup.add(l7);
		hyperlinkGroup.add(l8);
		hyperlinkGroup.add(l9);
		hyperlinkGroup.add(l10);
		hyperlinkGroup.add(l11);
		hyperlinkGroup.add(l12);
		hyperlinkGroup.add(l13);
		hyperlinkGroup.add(l14);
		hyperlinkGroup.add(l15);
		hyperlinkGroup.add(l16);
		hyperlinkGroup.add(l17);
		hyperlinkGroup.add(l18);
		hyperlinkGroup.add(l19);
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
