package com.moda.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.moda.R;
import com.moda.newClass.Class_Setting;

public class Activity_Setting extends Activity {
	ListView settingList = null;
	SimpleAdapter mAdapter = null;
	List<Map<String, Object>> mList;
	ArrayList<Class_Setting> settingGroup;
	Map<String, Object> mSetting = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		LoadSettingSentences();
		initiateHyperlinkPages();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initiateHyperlinkPages() {
		settingList = (ListView) super.findViewById(R.id.listView_setting); // 取得组件
		// 下面是数据映射关�?,mFrom和mTo按顺序一�?对应
		String[] mFrom = new String[] { "title1", "title2" };
		int[] mTo = new int[] { R.id.show_setting_name,
				R.id.show_setting_name_below };
		mList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < settingGroup.size(); i++) {
			mSetting = new HashMap<String, Object>();
			mSetting.put("title1", settingGroup.get(i).getTextabove());
			mSetting.put("title2", settingGroup.get(i).getTextbelow());
			mList.add(mSetting);
		}
		// 创建适配�?
		mAdapter = new SimpleAdapter(this, mList, R.layout.setting_item, mFrom,
				mTo);
		settingList.setAdapter(mAdapter);
	}

	private void LoadSettingSentences() {
		settingGroup = new ArrayList<Class_Setting>();
		Class_Setting l1 = new Class_Setting(1,
				getString(R.string.about_row1_title),
				getString(R.string.about_row1_content));
		Class_Setting l2 = new Class_Setting(2,
				getString(R.string.about_row2_title),
				getString(R.string.about_row2_content));
		Class_Setting l3 = new Class_Setting(3,
				getString(R.string.about_row3_title),
				getString(R.string.about_row3_content));
		settingGroup.add(l1);
		settingGroup.add(l2);
		settingGroup.add(l3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}
}
