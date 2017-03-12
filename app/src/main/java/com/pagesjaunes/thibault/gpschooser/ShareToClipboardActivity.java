package com.pagesjaunes.thibault.gpschooser;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Thibault on 12/03/2017.
 */
public class ShareToClipboardActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		CharSequence text = getIntent().getCharSequenceExtra(Intent.EXTRA_TEXT);
		ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		clipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
		Toast.makeText(this, R.string.copyToClipBoardOk, Toast.LENGTH_LONG)
		     .show();
		finish();
	}
}
