package com.sg.menu.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sg.Activity.ChangePassword;
import com.sg.Activity.MyApplication;
import com.sg.Activity.OperatorListtActivity;
import com.sg.Activity.ParentActivity;
import com.sg.RechargeManger.Posts;
import com.sg.constants.SPKeyConstants;
import com.sg.mobilerecharge.R;
import com.sg.utility.ConfirmDialog;
import com.sg.utility.Utils;




public class SliderMenuFragment extends ListFragment {
	private static final String TAG = "SampleListFragment";
	public  ImageView mUserProfileImage;
	private View headerView;
	private SampleAdapter adapter;
	private static final String[] menu_items = {"Change Password","Logout"};
	String mposts;
	private TextView userName;
	//private ModuleScreen moduleScreen;
	private ParentActivity moduleScreen;
	enum whichRecharge{
		Mobile,
		DTH,
		Postpaid,
		Utility
	}
	whichRecharge rechargeService;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.slider, null);
		moduleScreen=(ParentActivity)getActivity();
		mposts=Utils.getValueFromSharedPreferences(getActivity(),SPKeyConstants.SHARED_PREF_POSTS);
		setlayoutRef(view);
		return view;
	}

	private void setlayoutRef(View view) {
		if(mposts!=null){
			if(mposts.equalsIgnoreCase(Posts.RT)){
				ImageView mobile=(ImageView)view.findViewById(R.id.mobileR);
				ImageView dth=(ImageView)view.findViewById(R.id.dthR);
				ImageView postpaid=(ImageView)view.findViewById(R.id.postpaidR);
				ImageView utility=(ImageView)view.findViewById(R.id.utilityR);
				dth.setOnClickListener(new ClickListener());
				postpaid.setOnClickListener(new ClickListener());
				utility.setOnClickListener(new ClickListener());
				mobile.setOnClickListener(new ClickListener());
			}
			else{
				View sliderHeader=(View)view.findViewById(R.id.sliderDashboard);
				sliderHeader.setVisibility(View.GONE);
			}
		}
		TextView name=(TextView)view.findViewById(R.id.username);
		name.setText(Utils.getValueFromSharedPreferences(getActivity(),SPKeyConstants.SHARED_PREF_UNAME));
	}
	private class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.mobileR :
				rechargeService=whichRecharge.Mobile;
				openRechargeActivity();
				break;

			case R.id.dthR :
				rechargeService=whichRecharge.DTH;
				openRechargeActivity();
				break;

			case R.id.utilityR :
				rechargeService=whichRecharge.Utility;
				openRechargeActivity();
				break;

			case R.id.postpaidR :
				rechargeService=whichRecharge.Postpaid;
				openRechargeActivity();
				break;
			default:
				break;
			}			
		}
	}
	private void openRechargeActivity() {
		if(rechargeService.equals(whichRecharge.Mobile)){
			MyApplication.selectedRecharge="Mobile";
			getActivity().startActivity(new Intent(getActivity(),OperatorListtActivity.class));
		}
		else if(rechargeService.equals(whichRecharge.DTH)){
			MyApplication.selectedRecharge="dth";
			getActivity().startActivity(new Intent(getActivity(),OperatorListtActivity.class));
		}

		else if(rechargeService.equals(whichRecharge.Postpaid)){
			MyApplication.selectedRecharge="postpaid";
			getActivity().startActivity(new Intent(getActivity(),OperatorListtActivity.class));
		}

		else if(rechargeService.equals(whichRecharge.Utility)){
			MyApplication.selectedRecharge="utility";
			getActivity().startActivity(new Intent(getActivity(),OperatorListtActivity.class));
		}

	}
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setVerticalScrollBarEnabled(false);
		getListView().setCacheColorHint(Color.parseColor("#00000000"));
		getListView().setDivider(null);
		getListView().setDividerHeight(0);
		adapter = new SampleAdapter(menu_items);
		setListAdapter(adapter);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		View view=l.getChildAt(position);
		TextView title=(TextView)view.findViewById(R.id.row_title);
		String mTitle=title.getText().toString();
		if(mTitle.equalsIgnoreCase("Change Password")){
			startActivity(new Intent(getActivity(),ChangePassword.class));
		}
		else if(mTitle.equalsIgnoreCase("logout")){
			new ConfirmDialog((ParentActivity) getActivity()).showConfirmDialog("Are you sure you want to logout?");
		}
	}

	private class SampleAdapter extends BaseAdapter {
		ViewHolder holder;
		String[] menus;
		public SampleAdapter(String[] menu_items) {
			this.menus=menu_items;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null)
			{   
				holder = new ViewHolder();
				LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.menu_row, null);
				holder.title = (TextView)convertView.findViewById(R.id.row_title);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();

			}
			setData(position);
			return convertView;
		}

		private void setData(int position) {
			holder.title.setText(menus[position]);
		}

		@Override
		public int getCount() {
			return menus.length;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}

	}

	static class ViewHolder
	{
		TextView title;
	}


}


