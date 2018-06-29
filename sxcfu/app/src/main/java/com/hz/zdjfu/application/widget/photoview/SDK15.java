package com.hz.zdjfu.application.widget.photoview;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(16)
public class SDK15 {

	public static void postOnAnimation(View view, Runnable r) {
		view.post(r);
	}
}
