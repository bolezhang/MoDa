/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moda.activity;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moda.R;

public class Activity_Zoom extends FragmentActivity {
	private Animator mCurrentAnimator;
	private int mShortAnimationDuration;
	List<Integer> thumbImageIds;
	List<Integer> zoomImageIds;
	LinearLayout basicLayout;
	ImageView expandedImageView;
	TextView expandedTexiView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zoom_activity);
		initAndZoomAllImages();
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Retrieve and cache the system's default "short" animation time.
		mShortAnimationDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);
	}

	private void initAndZoomAllImages() {
		LinearLayout ll_row1 = (LinearLayout) findViewById(R.id.linearLayout_thumb1);
		LinearLayout ll_row2 = (LinearLayout) findViewById(R.id.linearLayout_thumb2);
		LinearLayout ll_row3 = (LinearLayout) findViewById(R.id.linearLayout_thumb3);
		LinearLayout ll_row4 = (LinearLayout) findViewById(R.id.linearLayout_thumb4);
		LinearLayout ll_row5 = (LinearLayout) findViewById(R.id.linearLayout_thumb5);

		thumbImageIds = new ArrayList<Integer>();
		thumbImageIds.add(R.id.thumb_im_row1_1);
		thumbImageIds.add(R.id.thumb_im_row1_2);
		thumbImageIds.add(R.id.thumb_im_row1_3);
		thumbImageIds.add(R.id.thumb_im_row1_4);
		thumbImageIds.add(R.id.thumb_im_row1_5);
		thumbImageIds.add(R.id.thumb_im_row1_6);
		thumbImageIds.add(R.id.thumb_im_row2_1);
		thumbImageIds.add(R.id.thumb_im_row2_2);
		thumbImageIds.add(R.id.thumb_im_row2_3);
		thumbImageIds.add(R.id.thumb_im_row2_4);
		thumbImageIds.add(R.id.thumb_im_row2_5);
		thumbImageIds.add(R.id.thumb_im_row2_6);
		thumbImageIds.add(R.id.thumb_im_row3_1);
		thumbImageIds.add(R.id.thumb_im_row3_2);
		thumbImageIds.add(R.id.thumb_im_row3_3);
		thumbImageIds.add(R.id.thumb_im_row3_4);
		thumbImageIds.add(R.id.thumb_im_row3_5);
		thumbImageIds.add(R.id.thumb_im_row4_1);
		thumbImageIds.add(R.id.thumb_im_row4_2);
		thumbImageIds.add(R.id.thumb_im_row4_3);
		thumbImageIds.add(R.id.thumb_im_row4_4);
		thumbImageIds.add(R.id.thumb_im_row4_5);
		thumbImageIds.add(R.id.thumb_im_row5_1);
		thumbImageIds.add(R.id.thumb_im_row5_2);
		thumbImageIds.add(R.id.thumb_im_row5_3);
		thumbImageIds.add(R.id.thumb_im_row5_4);
		thumbImageIds.add(R.id.thumb_im_row5_5);

		zoomImageIds = new ArrayList<Integer>();
		zoomImageIds.add(R.drawable.zoom_image_row1_1);
		zoomImageIds.add(R.drawable.zoom_image_row1_2);
		zoomImageIds.add(R.drawable.zoom_image_row1_3);
		zoomImageIds.add(R.drawable.zoom_image_row1_4);
		zoomImageIds.add(R.drawable.zoom_image_row1_5);
		zoomImageIds.add(R.drawable.zoom_image_row1_6);
		zoomImageIds.add(R.drawable.zoom_image_row2_1);
		zoomImageIds.add(R.drawable.zoom_image_row2_2);
		zoomImageIds.add(R.drawable.zoom_image_row2_3);
		zoomImageIds.add(R.drawable.zoom_image_row2_4);
		zoomImageIds.add(R.drawable.zoom_image_row2_5);
		zoomImageIds.add(R.drawable.zoom_image_row2_6);
		zoomImageIds.add(R.drawable.zoom_image_row3_1);
		zoomImageIds.add(R.drawable.zoom_image_row3_2);
		zoomImageIds.add(R.drawable.zoom_image_row3_3);
		zoomImageIds.add(R.drawable.zoom_image_row3_4);
		zoomImageIds.add(R.drawable.zoom_image_row3_5);
		zoomImageIds.add(R.drawable.zoom_image_row4_1);
		zoomImageIds.add(R.drawable.zoom_image_row4_2);
		zoomImageIds.add(R.drawable.zoom_image_row4_3);
		zoomImageIds.add(R.drawable.zoom_image_row4_4);
		zoomImageIds.add(R.drawable.zoom_image_row4_5);
		zoomImageIds.add(R.drawable.zoom_image_row5_1);
		zoomImageIds.add(R.drawable.zoom_image_row5_2);
		zoomImageIds.add(R.drawable.zoom_image_row5_33);
		zoomImageIds.add(R.drawable.zoom_image_row5_4);
		zoomImageIds.add(R.drawable.zoom_image_row5_5);
		
		// Corresponding image to each thumb image
		int totalViewButtonNum = ll_row1.getChildCount()
				+ ll_row2.getChildCount() + ll_row3.getChildCount()
				+ ll_row4.getChildCount() + ll_row5.getChildCount();
		for (int i = 0; i < totalViewButtonNum; i++) {
			final View thumbView = findViewById(thumbImageIds.get(i));
			final int imId = zoomImageIds.get(i);
			thumbView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					zoomImageFromThumb(thumbView, imId);
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

	private void zoomImageFromThumb(final View thumbView, int imageResId) {
		// If there's an animation in progress, cancel it immediately and
		// proceed with this one.
		if (mCurrentAnimator != null) {
			mCurrentAnimator.cancel();
		}

		// Load the high-resolution "zoomed-in" image.
		expandedImageView = (ImageView) findViewById(R.id.expanded_image);
		expandedImageView.setImageResource(imageResId);

		// Calculate the starting and ending bounds for the zoomed-in image.
		// This step
		// involves lots of math. Yay, math.
		final Rect startBounds = new Rect();
		final Rect finalBounds = new Rect();
		final Point globalOffset = new Point();

		// The start bounds are the global visible rectangle of the thumbnail,
		// and the
		// final bounds are the global visible rectangle of the container view.
		// Also
		// set the container view's offset as the origin for the bounds, since
		// that's the origin for the positioning animation properties (X, Y).
		thumbView.getGlobalVisibleRect(startBounds);
		findViewById(R.id.zoom_container).getGlobalVisibleRect(finalBounds,
				globalOffset);
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);

		// Adjust the start bounds to be the same aspect ratio as the final
		// bounds using the
		// "center crop" technique. This prevents undesirable stretching during
		// the animation.
		// Also calculate the start scaling factor (the end scaling factor is
		// always 1.0).
		float startScale;
		if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds
				.width() / startBounds.height()) {
			// Extend start bounds horizontally
			startScale = (float) startBounds.height() / finalBounds.height();
			float startWidth = startScale * finalBounds.width();
			float deltaWidth = (startWidth - startBounds.width()) / 2;
			startBounds.left -= deltaWidth;
			startBounds.right += deltaWidth;
		} else {
			// Extend start bounds vertically
			startScale = (float) startBounds.width() / finalBounds.width();
			float startHeight = startScale * finalBounds.height();
			float deltaHeight = (startHeight - startBounds.height()) / 2;
			startBounds.top -= deltaHeight;
			startBounds.bottom += deltaHeight;
		}

		// Hide the thumbnail and show the zoomed-in view. When the animation
		// begins,
		// it will position the zoomed-in view in the place of the thumbnail.
		// thumbView.setAlpha(0f);
		basicLayout = (LinearLayout) findViewById(R.id.linearlayout_flyer_left);
		basicLayout.setVisibility(View.GONE);
		expandedImageView.setVisibility(View.VISIBLE);

		expandedTexiView = (TextView) findViewById(R.id.expanded_textview);
		expandedTexiView.setVisibility(View.VISIBLE);
		// Set the pivot point for SCALE_X and SCALE_Y transformations to the
		// top-left corner of
		// the zoomed-in view (the default is the center of the view).
		expandedImageView.setPivotX(0f);
		expandedImageView.setPivotY(0f);

		// Construct and run the parallel animation of the four translation and
		// scale properties
		// (X, Y, SCALE_X, and SCALE_Y).
		AnimatorSet set = new AnimatorSet();
		set.play(
				ObjectAnimator.ofFloat(expandedImageView, View.X,
						startBounds.left, finalBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
						startBounds.top, finalBounds.top))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
						startScale, 1f))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y,
						startScale, 1f));
		set.setDuration(mShortAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mCurrentAnimator = null;
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mCurrentAnimator = null;
			}
		});
		set.start();
		mCurrentAnimator = set;

		// Upon clicking the zoomed-in image, it should zoom back down to the
		// original bounds
		// and show the thumbnail instead of the expanded image.
		final float startScaleFinal = startScale;
		expandedImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mCurrentAnimator != null) {
					mCurrentAnimator.cancel();
				}

				// Animate the four positioning/sizing properties in parallel,
				// back to their
				// original values.
				AnimatorSet set = new AnimatorSet();
				set.play(
						ObjectAnimator.ofFloat(expandedImageView, View.X,
								startBounds.left))
						.with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
								startBounds.top))
						.with(ObjectAnimator.ofFloat(expandedImageView,
								View.SCALE_X, startScaleFinal))
						.with(ObjectAnimator.ofFloat(expandedImageView,
								View.SCALE_Y, startScaleFinal));
				set.setDuration(mShortAnimationDuration);
				set.setInterpolator(new AccelerateInterpolator());
				set.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						// thumbView.setAlpha(1f);
						basicLayout.setVisibility(View.VISIBLE);
						expandedImageView.setVisibility(View.GONE);
						expandedTexiView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation) {
						// thumbView.setAlpha(1f);
						basicLayout.setVisibility(View.VISIBLE);
						expandedImageView.setVisibility(View.GONE);
						expandedTexiView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}
				});
				set.start();
				mCurrentAnimator = set;
			}
		});
	}
}
