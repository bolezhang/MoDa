package com.moda.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.moda.R;
import com.moda.newClass.Class_Call;

public class Activity_Call extends Activity {
	ListView callList = null;
	EditText eit_username = null;
	EditText eit_phoneNumber = null;
	SimpleAdapter mAdapter = null;
	String[] mFrom = null;
	int[] mTo = null;
	Map<String, Object> mMap = null;
	List<Map<String, Object>> mList = null;
	ArrayList<Class_Call> callGroup;
	View popupWindowView = null;
	Menu callMenu = null;
	Class_Call newOne;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_activity);
		initializing();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initializing() {
		callList = (ListView) super.findViewById(R.id.listView_call);
		callGroup = new ArrayList<Class_Call>();
		createDatabase();
		loadDatabase();
		corresponding();
	}

	private void loadDatabase() {
		Cursor cursor = db.rawQuery("SELECT * FROM phonebook", null);
		String name = null;
		String number = null;
		while (cursor.moveToNext()) {
			name = cursor.getString(cursor.getColumnIndex("name"));
			number = cursor.getString(cursor.getColumnIndex("phonenumber"));
			Class_Call contact = new Class_Call(R.drawable.people, name, number);
			callGroup.add(contact);
		}
		cursor.close();
	}

	private void createDatabase() {
		db = openOrCreateDatabase("phonebook.db", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS phonebook( id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, phonenumber VARCHAR)");
		Cursor cursor = db.rawQuery("SELECT * FROM phonebook", null);
		if (cursor.getCount() == 0) {// if no data in table, insert basic ones
			db.execSQL("INSERT INTO phonebook (name,phonenumber ) VALUES ('"
					+ getString(R.string.call_num_police) + "', '911')");
			db.execSQL("INSERT INTO phonebook (name,phonenumber ) VALUES ('"
					+ getString(R.string.call_num_findnumber) + "', '411')");
			Log.i(null, "Database table create ok");
		}
		Log.i(null, "Database table already exist");
	}

	private void corresponding() {
		// 下面是数据映射关系,mFrom和mTo按顺序一一对应
		mFrom = new String[] { "img", "title1", "title2" };
		mTo = new int[] { R.id.img, R.id.show_people_name,
				R.id.show_phoneNumber };
		mList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < callGroup.size(); i++) {
			mMap = new HashMap<String, Object>();
			mMap.put("img", callGroup.get(i).getImagePath());
			mMap.put("title1", callGroup.get(i).getName());
			mMap.put("title2", callGroup.get(i).getPhoneNumber());
			mList.add(mMap);
		}
		// 创建适配器
		mAdapter = new SimpleAdapter(this, mList, R.layout.call_item, mFrom,
				mTo);
		callList.setAdapter(mAdapter);
		callList.setOnItemClickListener(new OnItemClickListenerImpl_call());
	}

	private class OnItemClickListenerImpl_call implements OnItemClickListener {
		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// start activity, call a number
			String phoneNumber = "tel:"
					+ callGroup.get(position).getPhoneNumber();
			Uri number = Uri.parse(phoneNumber);
			Intent callIntent = new Intent(Intent.ACTION_CALL, number);
			startActivity(callIntent);
		}
	}

	private class OnItemClickListenerImpl_delete implements OnItemClickListener {
		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// To find telephonenumber on selected item
			RelativeLayout ry = (RelativeLayout) callList.getChildAt(position);
			LinearLayout ll = (LinearLayout) ry.getChildAt(1);
			TextView username = (TextView) ll.getChildAt(0);
			TextView phoneNumber = (TextView) ll.getChildAt(1);
			String deleteUsername = (String) username.getText();
			String deletePhoneNumber = (String) phoneNumber.getText();
			// delete from UI
			callGroup.remove(position);
			// delete data from database
			db.execSQL("DELETE FROM phonebook where phonenumber='"
					+ deletePhoneNumber + "'");
			corresponding();
			showPopupWindow(deleteUsername,
					getString(R.string.call_popup_alreadydelete));
			menuIconsRestore();
		}
	}

	private void saveNewContactData() {
		eit_username = (EditText) popupWindowView
				.findViewById(R.id.eit_newContactName);
		eit_phoneNumber = (EditText) popupWindowView
				.findViewById(R.id.eit_phonenumber);
		if (eit_username.getText() != null && eit_phoneNumber.getText() != null) {
			String username = eit_username.getText().toString();
			String phoneNumber = eit_phoneNumber.getText().toString();

			db.execSQL("INSERT INTO phonebook (name,phonenumber) VALUES ('"
					+ username + "','" + phoneNumber + "')");
			Cursor cursor = db.rawQuery(
					"SELECT * FROM phonebook where phoneNumber = '"
							+ phoneNumber + "'", null);
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String number = cursor.getString(cursor
						.getColumnIndex("phonenumber"));

				Class_Call contact = new Class_Call(R.drawable.people, name,
						number);
				callGroup.add(contact);
			}
		}
	}

	private void menuIconsRestore() {
		MenuItem item_add = callMenu.findItem(R.id.menu_call_addContact);
		MenuItem item_delete = callMenu.findItem(R.id.menu_call_deleteContact);
		item_add.setIcon(R.drawable.add);
		item_delete.setIcon(R.drawable.delete);
	}

	private void showPopupWindow(String title, String message) {
		AlertDialog.Builder popupMessage = new AlertDialog.Builder(this);
		popupMessage
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(
						getString(R.string.call_popup_deleteconfirm), null)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_call, menu);
		callMenu = menu;
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// when select item1 button add
		case R.id.menu_call_addContact:
			item.setIcon(R.drawable.add2);
			LayoutInflater inflater = (LayoutInflater) getApplicationContext()
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			popupWindowView = inflater
					.inflate(R.layout.call_popup_window, null);
			// popup window
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getString(R.string.call_popup_title));
			// Set up the input
			builder.setView(popupWindowView);
			// Set up the buttons
			builder.setNegativeButton(getString(R.string.call_popup_cancel),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							menuIconsRestore();
						}
					});
			builder.setPositiveButton(getString(R.string.call_popup_confirm),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							saveNewContactData();
							corresponding();
							menuIconsRestore();
						}
					});
			builder.show();
			break;
		// when select item2 button delete
		case R.id.menu_call_deleteContact:
			item.setIcon(R.drawable.delete2);
			mAdapter = new SimpleAdapter(this, mList, R.layout.call_item,
					mFrom, mTo);
			callList.setAdapter(mAdapter);
			callList.setOnItemClickListener(new OnItemClickListenerImpl_delete());
			break;
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
