package com.moda.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moda.R;

public class Activity_Flyer extends Activity {
	Vibrator vibrator;
	EditText et_note;
	Button bt_save;
	Button bt_clr;
	List<Integer> flyerIds;
	Map<Integer, String> flyer_webs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flyer_activity);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		initializing();
		addVibration();
		loadDatas();
	}

	private void loadDatas() {
		// if (et_note.getText().toString() != null) {
		SharedPreferences sharedata = getSharedPreferences("flyerNote",
				MODE_PRIVATE);
		String contentData = sharedata.getString("note_content", null);
		et_note.setText(contentData);
		// }
	}

	private void saveDatas() {
		// if (et_note.getText().toString() != null) {
		Context ctx = Activity_Flyer.this;
		SharedPreferences sp = ctx.getSharedPreferences("flyerNote",
				MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("note_content", et_note.getText().toString());
		editor.commit();
		// }
	}

	private void addVibration() {
		bt_clr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vibrator.vibrate(50);
				et_note.setText(null);
				saveDatas();
			}
		});

		bt_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vibrator.vibrate(50);
				saveDatas();
			}
		});

		for (int index = 0; index < flyerIds.size(); index++) {
			final int flyerId = flyerIds.get(index);
			final View flyerLogos = findViewById(flyerId);
			flyerLogos.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String flyerWebAddress = flyer_webs.get(flyerId);
					Uri uri = Uri.parse(flyerWebAddress);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			});
		}
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

	private void initializing() {
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		et_note = (EditText) findViewById(R.id.flyer_edittext_note);
		bt_save = (Button) findViewById(R.id.button_save);
		bt_clr = (Button) findViewById(R.id.button_clear);

		flyerIds = new ArrayList<Integer>();
		flyerIds.add(R.id.img_tt);
		flyerIds.add(R.id.img_fengtai);
		flyerIds.add(R.id.img_nofrills);
		flyerIds.add(R.id.img_loblaws);
		flyerIds.add(R.id.img_huasheng);
		flyerIds.add(R.id.img_metro);
		flyerIds.add(R.id.img_foodbasic);
		flyerIds.add(R.id.img_costco);
		flyerIds.add(R.id.img_wholefoods);
		flyerIds.add(R.id.img_galleria);
		flyerIds.add(R.id.img_longos);
		flyerIds.add(R.id.img_sobeys);

		flyerIds.add(R.id.img_walmart);
		flyerIds.add(R.id.img_canadiantire);
		flyerIds.add(R.id.img_shoppers);
		flyerIds.add(R.id.img_bestbuy);
		flyerIds.add(R.id.img_sears);
		flyerIds.add(R.id.img_winners);
		flyerIds.add(R.id.img_ikea);
		flyerIds.add(R.id.img_target);
		flyerIds.add(R.id.img_staples);
		flyerIds.add(R.id.img_petsmart);
		flyerIds.add(R.id.img_rona);
		flyerIds.add(R.id.img_futureshop);

		String flyer_tt = "http://www.tnt-supermarket.com/big5/weekly_special.php?";
		String flyer_fengtai = "http://fengtai.flyercenter.com/flyer/e/";
		String flyer_nofrills = "http://flyers.smartcanucks.ca/no-frills-canada";
		String flyer_loblaws = "http://www.loblaws.ca/en_CA/citymarket/weeklyflyer.html";
		String flyer_huasheng = "http://oriental.flyercenter.com/flyer";
		String flyer_metro = "http://www.metro.ca/flyer/index.en.html#";
		String flyer_foodbasics = "http://flyers.smartcanucks.ca/food-basics-canada";
		String flyer_costco = "http://flyers.smartcanucks.ca/costco-canada";
		String flyer_wholefoods = "http://flyers.smartcanucks.ca/whole-foods-market-canada";
		String flyer_galleria = "http://flyers.smartcanucks.ca/galleria-supermarket-canada";
		String flyer_longos = "http://flyers.smartcanucks.ca/longos-canada";
		String flyer_sobeys = "https://www.sobeys.com/en/flyer";

		String flyer_walmart = "http://flyers.smartcanucks.ca/walmart-canada";
		String flyer_canadiantire = "http://flyers.smartcanucks.ca/canadian-tire-canada";
		String flyer_shoppers = "http://flyers.smartcanucks.ca/shoppers-drug-mart-canada";
		String flyer_bestbuy = "http://flyers.smartcanucks.ca/best-buy-canada";
		String flyer_sears = "http://flyers.smartcanucks.ca/sears-canada";
		String flyer_winners = "http://flyers.smartcanucks.ca/winners-canada";
		String flyer_ikea = "http://flyers.smartcanucks.ca/ikea-canada";
		String flyer_target = "http://flyers.smartcanucks.ca/target-canada";
		String flyer_staples = "http://flyers.smartcanucks.ca/staples-canada";
		String flyer_petsmart = "http://flyers.smartcanucks.ca/petsmart-canada";
		String flyer_rona = "http://www.rona.ca/RonaFlyerDisplayView%3FstoreId%3D10151%26urlLangId%3D-1%26catalogId%3D10051%26langId%3D-1";
		String flyer_futureshop = "http://flyers.smartcanucks.ca/future-shop-canada";

		flyer_webs = new HashMap<Integer, String>();
		flyer_webs.put(R.id.img_tt, flyer_tt);
		flyer_webs.put(R.id.img_fengtai, flyer_fengtai);
		flyer_webs.put(R.id.img_nofrills, flyer_nofrills);
		flyer_webs.put(R.id.img_loblaws, flyer_loblaws);
		flyer_webs.put(R.id.img_huasheng, flyer_huasheng);
		flyer_webs.put(R.id.img_metro, flyer_metro);
		flyer_webs.put(R.id.img_foodbasic, flyer_foodbasics);
		flyer_webs.put(R.id.img_costco, flyer_costco);
		flyer_webs.put(R.id.img_wholefoods, flyer_wholefoods);
		flyer_webs.put(R.id.img_galleria, flyer_galleria);
		flyer_webs.put(R.id.img_longos, flyer_longos);
		flyer_webs.put(R.id.img_sobeys, flyer_sobeys);

		flyer_webs.put(R.id.img_walmart, flyer_walmart);
		flyer_webs.put(R.id.img_canadiantire, flyer_canadiantire);
		flyer_webs.put(R.id.img_shoppers, flyer_shoppers);
		flyer_webs.put(R.id.img_bestbuy, flyer_bestbuy);
		flyer_webs.put(R.id.img_sears, flyer_sears);
		flyer_webs.put(R.id.img_winners, flyer_winners);
		flyer_webs.put(R.id.img_ikea, flyer_ikea);
		flyer_webs.put(R.id.img_target, flyer_target);
		flyer_webs.put(R.id.img_staples, flyer_staples);
		flyer_webs.put(R.id.img_petsmart, flyer_petsmart);
		flyer_webs.put(R.id.img_rona, flyer_rona);
		flyer_webs.put(R.id.img_futureshop, flyer_futureshop);
	}

}
