package com.pagesjaunes.thibault.gpschooser.market;

import android.support.annotation.NonNull;

/**
 * Created by Thibault on 12/03/2017.
 */

public class OpenMappyMarketActivity extends OpenMarketActivity
{
	@NonNull
	@Override
	public String getMarketPackage()
	{
		return "com.mappy.app";
	}
}
