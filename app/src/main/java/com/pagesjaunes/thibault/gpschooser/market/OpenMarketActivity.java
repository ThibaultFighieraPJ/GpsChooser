package com.pagesjaunes.thibault.gpschooser.market;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Thibault on 12/03/2017.
 */

abstract class OpenMarketActivity extends Activity
{
	private static final String MARKET_PREFIX = "market://details?id=";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//Link to the market
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(MARKET_PREFIX + getMarketPackage()));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

	@NonNull
	public abstract String getMarketPackage();
}
