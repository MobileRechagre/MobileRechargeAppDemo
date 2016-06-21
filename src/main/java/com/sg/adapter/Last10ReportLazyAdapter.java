package com.sg.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sg.Activity.ComplaintActivity;
import com.sg.mobilerecharge.R;


public class Last10ReportLazyAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater=null;
	String username,password;
	Dialog d;
	public Last10ReportLazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
			vi = inflater.inflate(R.layout.last10report_row, null);
		final  TextView id = (TextView)vi.findViewById(R.id.id);
		final TextView Number = (TextView)vi.findViewById(R.id.Number);
		TextView Amount = (TextView)vi.findViewById(R.id.Amount); // title
		TextView Date = (TextView)vi.findViewById(R.id.Date); // artist name
		TextView Status = (TextView)vi.findViewById(R.id.Status); // duration
		TextView OperatorName = (TextView)vi.findViewById(R.id.OperatorName);
		if(activity instanceof ComplaintActivity){
			Button submit = (Button)vi.findViewById(R.id.submit);
			submit.setVisibility(View.VISIBLE);
			submit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
				((ComplaintActivity) activity).showRemarkDialog();			
				}
			});
		}
		HashMap<String, String> song = new HashMap<String, String>();
		song = data.get(position);

		// Setting all values in listview
		id.setText(song.get("Id"));
		Number.setText(song.get("Number"));
		Amount.setText(song.get("Amount"));
		Date.setText(song.get("Date"));
		Status.setText(song.get("Status"));
		OperatorName.setText(song.get("OperatorName"));
		return vi;
	}
}