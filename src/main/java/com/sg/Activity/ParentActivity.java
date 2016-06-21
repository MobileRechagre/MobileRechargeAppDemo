package com.sg.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sg.menu.fragment.SliderMenuFragment;
import com.sg.mobilerecharge.R;
import com.sg.utility.ConfirmDialog;
import com.skusuma.interfaces.ConfirmCommand;

public class ParentActivity extends SlidingFragmentActivity implements ConfirmCommand{
	@Override
	public void onBackPressed() {
	}

	protected ListFragment mSlidingMenu;
	protected SlidingMenu sm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setSlidingMenu(savedInstanceState);
	}

	public void setSlidingMenu(Bundle savedInstanceState) {
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mSlidingMenu = new SliderMenuFragment();
			t.replace(R.id.menu_frame, mSlidingMenu); 
			t.commit();
		} else {
			mSlidingMenu = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		//sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		//sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		sm.setMode(SlidingMenu.RIGHT);
	}
	public void enableSlidingMenu(){
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	}
	public void openMenuImageClicked() {
		MyApplication.isDrawerOpen=true;
		toggle();
	}
	public void startNewActivity( Intent intent){
		startActivity(intent);	
	}
	public void backClicked(){
		finish();
	}
	public void logoutButtonClicked(){
		// cleaning the directory
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	public void sendResponseLogin(Object response){}
	public void sendResponseCheckBalance(Object response){}
	public void sendResponseBalanceTransaction(Object response){}
	public void sendResponseRecharge(Object response){}
	public void sendResponseChangPass(Object response){}
	public void sendResponseRechargeReport(Object response){}
	public void sendResponseTransactionReport(Object response){}
	public void sendResponseDthBooking(Object response){}
	public void sendResponseSaleReport(Object response){}
	public void sendResponseRefundReport(Object response){}
	public void sendResponseRevertBal(Object response){}
	public void sendResponseRechargeDIerReport(Object response){}
	public void sendResponseComplaint(Object response){}
	public void sendResponseComplaintFile(Object response){}
	@Override
	public void commandYes() {
		logoutButtonClicked();
	}

	@Override
	public void commandNo() {
		ConfirmDialog.cancelConfirm();
	}
}
