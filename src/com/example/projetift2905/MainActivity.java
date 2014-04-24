package com.example.projetift2905;

import java.util.ArrayList;

import java.util.List;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import android.view.View;
 import android.view.View.OnClickListener;
 import android.view.ViewGroup;
 import android.widget.Button;
 import android.content.Intent;


public class MainActivity extends FragmentActivity {

	PagerAdapter adapter;
	ViewPager pager;
	SelectionTournoiAPI api;
	List<MainPagerFragment> fragList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* **************************************
		 * APPEL API POUR CREER LISTE DE TOURNOIS
		 * **************************************/
		getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		this.api = null;
        new DownloadLoginTask().execute();
		
		/* *******************************
		 * CREATION DE LA BARRE D'ONGLETS
		 * *******************************/
        fragList = new ArrayList<MainPagerFragment>();
		adapter = new PagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        	
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                pager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
            
        };
        
        for (int i = 0; i < adapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(tabListener));
        }
                
        //
        ((Button)findViewById(R.id.buttonToDetailsTournoi)).setOnClickListener(new OnClickListener() {
        			@Override
        			public void onClick(View arg0) {
        				Intent i = new Intent(MainActivity.this, DetailsTournoi.class);
        				startActivity(i);
        			}
        		});		
        		
        		((Button)findViewById(R.id.buttonToCreerTournoi)).setOnClickListener(new OnClickListener() {
        		@Override
        			public void onClick(View arg0) {
        				Intent i = new Intent(MainActivity.this, CreerTournoi.class);
        				startActivity(i);
        			}
        		});	
        
	}
	
	public void setApi(SelectionTournoiAPI api){
		this.api = api;
	}
	
	public SelectionTournoiAPI getApi(){
		return this.api;
	}
	
	private class DownloadLoginTask extends AsyncTask<String, String, SelectionTournoiAPI> {
		
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}
		
		protected SelectionTournoiAPI doInBackground(String... params) {
			SelectionTournoiAPI api = new SelectionTournoiAPI();
			return api;
		}
		
		protected void onProgressUpdate(String... s) {}
		
		protected void onPostExecute(SelectionTournoiAPI api) {
			setProgressBarIndeterminateVisibility(false);
			
			// On s'assure que l'objet de retour existe
			// et qu'il n'ait pas d'erreurs
			if( api == null ) {
				Toast.makeText(MainActivity.this, getText(R.string.api_vide), Toast.LENGTH_SHORT).show();
				return;
			}
			
			if( api.error != null ) {
				Toast.makeText(MainActivity.this, api.error, Toast.LENGTH_SHORT).show();
				return;
			}
			
			setApi(api);
			
			
			for(MainPagerFragment frg : fragList){
				frg.setTourneyData();
			}
		}
	}	
	
	private class PagerAdapter extends FragmentPagerAdapter{
		
		@Override
		public long getItemId(int position) {
			return super.getItemId(position);
		}

		public PagerAdapter(FragmentManager fm){
			super(fm);
		}
		
		public Fragment getItem(int i) {
			MainPagerFragment f = new MainPagerFragment();
			Bundle args = new Bundle();
			args.putInt("id", i);
			f.setArguments(args);
			fragList.add(f);
			return f;
		}
		
		public int getCount() {
			return 3;
		}
		
		public CharSequence getPageTitle(int position) {
			if(position == 0) return "Tous les tournois";
			if(position == 1) return "Tournois Favoris";
			if(position == 2) return "Mes tournois";
			return null;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
