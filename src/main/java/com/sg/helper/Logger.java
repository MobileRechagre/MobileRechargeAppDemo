package com.sg.helper;

import android.util.Log;


/**
 * 
 * @author sukhbirs
 * @class Logger.java
 * @description This class is used to display log details on LogCat screen.
 */
public class Logger {

	/*private static String path = "/mnt/sdcard/";
	private static FileOutputStream fos = null;
	private static PrintStream printStream = null;

	static{
		try {
			File file = new File(new File(path), "sqnLog.txt");
			if (file.exists())
				file.delete();

			file.createNewFile();
			fos = new FileOutputStream(file);
			printStream = new PrintStream(fos);

		} catch (Exception e) {
			Log.e("FileLogger", "error in static block of FileLogger. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void writeToFile(String tag, String message){
		try {
			System.setOut(printStream);
			System.out.println(getDateTime() + "\t" + tag + "\t" + message);

		} catch (Exception e) {
			Log.e(tag, "error in writeToFile() in FileLogger." + e.getMessage());
			e.printStackTrace();
		}
	}

	public static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateTime = sdf.format(new Date());

		return dateTime;
	}*/

	public static void e(String tag, String message){
		Log.e(tag, message);
//		writeToFile(tag, message);
	}

	public static void i(String tag, String message){
		Log.i(tag, message);
//		writeToFile(tag, message);
	}

	public static void v(String tag, String message){
		Log.v(tag, message);
	}

	public static void d(String tag, String message){
		Log.d(tag, message);
	}
}
