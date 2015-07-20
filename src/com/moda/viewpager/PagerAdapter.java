package com.moda.viewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.moda.R;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
 * of the sections/tabs/pages.
 */
public class PagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> fragments = null;
	private Activity myActivity;

	public PagerAdapter(FragmentManager fm, Activity myActivity) {
		super(fm);
		this.myActivity = myActivity;
	}

	@Override
	public Fragment getItem(int position) {
		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentPage1());
		fragments.add(new FragmentPage2());
		fragments.add(new FragmentPage3());
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return myActivity.getString(R.string.title_page1).toUpperCase();
		case 1:
			return myActivity.getString(R.string.title_page2).toUpperCase();
		case 2:
			return myActivity.getString(R.string.title_page3).toUpperCase();
		}
		return null;
	}
}
