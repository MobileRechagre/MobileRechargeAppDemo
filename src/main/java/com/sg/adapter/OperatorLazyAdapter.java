package com.sg.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sg.helper.Net_Recharge_Constants;
import com.sg.mobilerecharge.R;
import com.sg.operator.GetListMobileOperator;


public class OperatorLazyAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    private static ImageView thumb_image,op_thumb_image;
    
    private static TextView title;
    String op_title;
    Uri op_thumb_image_uri;
    public OperatorLazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 View vi=convertView;
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.list_row_mobile_operator, null);
	        title = (TextView)vi.findViewById(R.id.title1); // title
	         thumb_image=(ImageView)vi.findViewById(R.id.list_image1); // thumb image
	          
	        HashMap<String, String> song = new HashMap<String, String>();
	        song = data.get(position);
	 
	        // Setting all values in listview
	        title.setText(song.get(GetListMobileOperator.KEY_TITLE));
	       // artist.setText(song.get(CustomizedListView.KEY_ARTIST));
	        //duration.setText(song.get(CustomizedListView.KEY_DURATION));
	        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
	        Uri imgUri=Uri.parse(GetListMobileOperator.KEY_THUMB_URL);
	       Log.d("thumb_url", GetListMobileOperator.KEY_THUMB_URL);
	        Uri uri = Uri.parse("android.resource://"+Net_Recharge_Constants.project_activitys+"/drawable/"+song.get(GetListMobileOperator.KEY_THUMB_URL.trim()));
	        thumb_image.setImageURI(uri);
	      //  thumb_image.setVisibility(View.GONE);
	        Animation	animation = AnimationUtils.loadAnimation(activity.getBaseContext(), R.anim.push_up_in);
			vi.startAnimation(animation);
// 
	        return vi;
	}
	
}
