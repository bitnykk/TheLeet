package com.rubika.aotalk.towerwars;

import java.util.List;
import java.util.Vector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.rubika.aotalk.Preferences;
import com.rubika.aotalk.R;
import com.rubika.aotalk.util.Logging;
import com.viewpagerindicator.TitlePageIndicator;

public class Towerwars extends SherlockFragmentActivity implements ViewPager.OnPageChangeListener {
	protected static final String APP_TAG   = "--> The Leet :: Towerwars";

	public static ViewPager fragmentPager;
	private static TitlePageIndicator titleIndicator;
	private static List<SherlockListFragment> fragments;
	private static SharedPreferences settings;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//super.setTheme(R.style.Theme_AOTalkTheme_Light);
                
        setContentView(R.layout.main);
        
        settings = PreferenceManager.getDefaultSharedPreferences(this);

        final ActionBar bar = getSupportActionBar();
        
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        bar.setDisplayHomeAsUpEnabled(true);
        
		fragments = new Vector<SherlockListFragment>();
        fragments.add(FragmentAttacks.newInstance());
        fragments.add(FragmentSites.newInstance());
        
        FragmentAdapter fragmentAdapter = new FragmentAdapter(super.getSupportFragmentManager(), fragments);

        fragmentPager = (ViewPager) findViewById(R.id.fragmentpager);
        fragmentPager.setAdapter(fragmentAdapter);
        fragmentPager.setOnPageChangeListener(this);
        fragmentPager.setPageMargin(0);

        titleIndicator = (TitlePageIndicator)findViewById(R.id.titles);
        titleIndicator.setViewPager(fragmentPager);
        
        setTitleIndicator();
	}
    
    private static void setTitleIndicator() {
        if (settings.getBoolean("hideTitles", false)) {
        	titleIndicator.setVisibility(View.GONE);
        } else {
        	titleIndicator.setVisibility(View.VISIBLE);
        }
    }
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
	}
	
	@Override
	protected void onPause() {
		System.gc();
		super.onPause();
	}
    
    @Override
    protected void onResume() {
    	super.onResume();
        setTitleIndicator();
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	
    	try {
        	EasyTracker.getInstance().activityStart(this);
    	} catch (IllegalStateException e) {
    		Logging.log(APP_TAG, e.getMessage());
    	}
    }
    
    @Override
    protected void onStop() {
    	super.onStop();

    	try {
            EasyTracker.getInstance().activityStop(this);
    	} catch (IllegalStateException e) {
    		Logging.log(APP_TAG, e.getMessage());
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.menu_tower, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            finish();
	            return true;
			case R.id.preferences:
				Intent intent = new Intent(this, Preferences.class);
				startActivity(intent);
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
