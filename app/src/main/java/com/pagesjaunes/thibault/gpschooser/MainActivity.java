package com.pagesjaunes.thibault.gpschooser;

import com.pagesjaunes.thibault.gpschooser.market.OpenMappyMarketActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				getGpsChooser(view.getContext());
			}
		});
	}

	public void getGpsChooser(Context context)
	{
		boolean showMappy = true;
		String address = "geo:0,0?q=204 Rond-Point du Pont de Sèvres 92100 Boulogne-Billancourt";
		Uri locationUri = Uri.parse(address);
		List<Intent> gpsIntentList = new ArrayList<>();
		Intent gpsIntent = new Intent(Intent.ACTION_VIEW, locationUri);
		List<ResolveInfo> resInfo = context.getPackageManager()
		                                   .queryIntentActivities(gpsIntent, 0);
		if (!resInfo.isEmpty())
		{
			for (ResolveInfo info : resInfo)
			{
				ActivityInfo activityInfo = info.activityInfo;
				if (activityInfo.packageName.contains("mappy"))
				{
					showMappy = false;
				}
				Intent targeted = new Intent(Intent.ACTION_VIEW, locationUri);
				targeted.setPackage(activityInfo.packageName);
				targeted.setClassName(activityInfo.packageName, activityInfo.name);
				gpsIntentList.add(targeted);
			}
		}

		// Add a custom intent to handle the "copy to clipboard" option.
		Intent copyToClipboard = new Intent(this, ShareToClipboardActivity.class);
		copyToClipboard.putExtra(Intent.EXTRA_TEXT, address);
		gpsIntentList.add(copyToClipboard);

		// Add Mappy if note installed
		if (showMappy)
		{
			gpsIntentList.add(new Intent(this, OpenMappyMarketActivity.class));
		}

		// Start App Chooser
		Intent chooserIntent = Intent.createChooser(gpsIntentList.remove(0), getResources().getText(R.string.open_gps));
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, gpsIntentList.toArray(new Parcelable[]{}));
		chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(chooserIntent);
	}
}
