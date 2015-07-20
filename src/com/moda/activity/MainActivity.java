package com.moda.activity;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.moda.R;
import com.moda.viewpager.PagerAdapter;

public class MainActivity extends FragmentActivity {

	PagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	SharedPreferences languagePre;
	Configuration config;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadLanguage();
		createAdapter();
	}

	private void createAdapter() {
		// Create the adapter that will return a fragment for each of the three
		mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager(),
				this);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageScrollStateChanged(int state) {
						if (state == ViewPager.SCROLL_STATE_IDLE) {
							// Hide the keyboard.
							InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
							mgr.hideSoftInputFromWindow(
									mViewPager.getWindowToken(), 0);
							if (mgr != null) {
								getWindow()
										.setSoftInputMode(
												WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
							}
						}
					}
				});
	}

	void loadLanguage() {
		languagePre = getSharedPreferences("appLauguage", Context.MODE_PRIVATE);
		int id = languagePre.getInt("id", 0);
		if (id != 0) {
			Resources resources = getResources();
			config = resources.getConfiguration();// 获得设置对象
			DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素�?
			switch (id) {
			case 0:
				config.locale = Locale.getDefault(); // 系统默认语言
				break;
			case 1:
				config.locale = Locale.SIMPLIFIED_CHINESE;
				break;
			case 2:
				config.locale = Locale.TRADITIONAL_CHINESE;
				break;
			case 3:
				config.locale = Locale.KOREAN;
				break;
			// case 4:
			// config.locale = Locale.JAPANESE;
			// break;
			case 5:
				config.locale = Locale.ENGLISH;
				break;
			}
			resources.updateConfiguration(config, dm);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.main_menu_contactMe:
			Intent intent = new Intent(Intent.ACTION_SENDTO);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
			intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
			intent.setData(Uri.parse("mailto:zhangbole725@gmail.com"));
			// when user returns, returns to your app instead of the email app.
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		case R.id.main_menu_shareApp:
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
					"Hey, download this app!");
			startActivity(shareIntent);
			break;
		case R.id.main_menu_about:
			Intent settingIntent = new Intent(Intent.ACTION_SEND);
			settingIntent.setClass(getApplicationContext(),
					Activity_Setting.class);
			startActivity(settingIntent);
			break;
		case R.id.menu_language_chinese_simplify:
			languagePre.edit().putInt("id", 1).commit();
			refresh();
			break;
		case R.id.menu_language_chinese_traditional:
			languagePre.edit().putInt("id", 2).commit();
			refresh();
			break;
		case R.id.menu_language_korean:
			languagePre.edit().putInt("id", 3).commit();
			refresh();
			break;
		// case R.id.menu_language_japanese:
		// languagePre.edit().putInt("id", 4).commit();
		// refresh();
		// break;
		case R.id.menu_language_english:
			languagePre.edit().putInt("id", 5).commit();
			refresh();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	void refresh() {
		intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
	}
}
