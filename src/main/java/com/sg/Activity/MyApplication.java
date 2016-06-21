/**
 * 
 */
package com.sg.Activity;

import android.app.Application;


/**
 * @author saurabhc3
 * @created : Jul 16, 2014
 * @description : 
 */

public class MyApplication extends Application {

	private String tag = "MyApplication";
	public static String selectedRecharge = "";
	public static boolean isDrawerOpen=false;
	@Override
	public void onCreate() {
		super.onCreate();
	}
}
