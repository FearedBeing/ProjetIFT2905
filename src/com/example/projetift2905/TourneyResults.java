package com.example.projetift2905;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;



public class TourneyResults extends FragmentActivity {
	
	HTMLParser parser = null;
	ViewPager pager;
	PagerAdapter pagerAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_tourney_results);
		
		new DownloadLoginTask().execute();
		
	}
	
	public void setHTMLParser(HTMLParser parser){
		this.parser = parser;
	}
	
	public void createActionBar(){
		// Pager
		this.pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        this.pager = (ViewPager) findViewById(R.id.results_pager);
        this.pager.setAdapter(this.pagerAdapter);
		
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        	
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                pager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
            
        };
        
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(tabListener));
        }
        
        pager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tourney_results, menu);
		return true;
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	private class DownloadLoginTask extends AsyncTask<String, String, HTMLParser> {
		
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}
		
		protected HTMLParser doInBackground(String... params) {
			HTMLParser parser = new HTMLParser(getIntent().getStringExtra("TourneyID"));
			return parser;
		}
		
		protected void onProgressUpdate(String... s) {}
		
		protected void onPostExecute(HTMLParser parser) {
			setProgressBarIndeterminateVisibility(false);
			
			// On s'assure que l'objet de retour existe
			if( parser == null ) {
				Toast.makeText(TourneyResults.this, getText(R.string.parser_vide), Toast.LENGTH_SHORT).show();
				return;
			}
			
			setHTMLParser(parser);
			createActionBar();

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
		TourneyResultsFragment f = new TourneyResultsFragment();
		Bundle args = new Bundle();
		args.putInt("id", i);
		args.putString("tourneyID",getIntent().getStringExtra("TourneyID"));
		args.putString("bracket", this.getPageTitle(i).toString().split(" : ")[0]); // le nom est composé du bracket et du round. on veut le bracket!
		args.putString("round", this.getPageTitle(i).toString().split(" : ")[1]); // le nom est composé du bracket et du round. on veut le bracket!
		args.putString("html", parser.getHTML());
		f.setArguments(args);
		return f;
	}
	
	public int getCount() {
		int count = 0;
		for(String bracket: parser.getBracketTypes()){
			for(String round: parser.getRoundsName(bracket)){
				count++;
			}
		}
		return count;
	}
	
	public CharSequence getPageTitle(int position) {
		int pos = position;
		for(String bracket: parser.getBracketTypes()){
			for(String round: parser.getRoundsName(bracket)){
				if(pos == 0) return bracket + " : " + round;
				pos--;
			}
		}
		return null;
	}
}


}



