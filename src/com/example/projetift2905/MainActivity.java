package com.example.projetift2905;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

	PagerAdapter pagerAdapter;
	ArrayAdapter<String> filtreAdapter;
	ListView filtreView;
	ViewPager pager;
	SelectionTournoiAPI api;
	List<MainPagerFragment> fragList;
	String[] filtres = {"",""};
	
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
		pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        
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
        
        /* *******************************
		 * CREATION DU POP-UP DES FILTRES
		 * *******************************/
        
        ArrayList<String> items = new ArrayList<String>();
        items.add("Filtres: Aucun");
        
        filtreAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        filtreView = (ListView) findViewById(R.id.filtres);
        filtreView.setAdapter(filtreAdapter);
        filtreView.setOnItemClickListener(new OnItemClickListener(){
        	
        	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        		// Ouvrir le pop-up
        		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        		View v = inflater.inflate(R.layout.popup_filtres, null, false);
        		v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);   // voir http://stackoverflow.com/questions/3014118/popup-window-size-in-android
        		int height = v.getMeasuredHeight();
        		int width = v.getMeasuredWidth() + 200;
        		final PopupWindow pw = new PopupWindow(v,width,height, true); // final pour avoir acces dans les classes internes (comme OnClickListener)
        		pw.showAtLocation(findViewById(R.id.mainRootContainer), Gravity.CENTER, 0, 0);
        		
        		// Controle du menu
        		final EditText titre = (EditText)v.findViewById(R.id.popup_titre);
        		final EditText jeu = (EditText)v.findViewById(R.id.popup_jeu);
        		final ListView filtreView = (ListView)findViewById(R.id.filtres);
        		final Button ok = (Button)v.findViewById(R.id.popupOk);
        		ok.setOnClickListener(new OnClickListener(){
        			@SuppressWarnings("unchecked")
					public void onClick(View v) {
						String filtre = "Filtres:";
						setFiltres(titre.getText().toString(), jeu.getText().toString());
        				if(getFiltres()[0].equals("") && getFiltres()[1].equals("")){
        					filtre += " Aucun";
        				}else{
        					if(!getFiltres()[0].equals("")){
        						filtre += " [Titre: " + getFiltres()[0] + "]";
        					}
        					if(!getFiltres()[1].equals("")){
        						filtre += " [Jeu: " + getFiltres()[1] + "]";
        					}
        				}
        				((ArrayAdapter<String>)filtreView.getAdapter()).clear();
        				((ArrayAdapter<String>)filtreView.getAdapter()).add(filtre);
        				((ArrayAdapter<String>)filtreView.getAdapter()).notifyDataSetChanged();
        				pw.dismiss();
					}
        		});
        	}
        });
        
        /* ************
		 * TEMPORAIRES
		 * ************/
        		
		((Button)findViewById(R.id.buttonToCreerTournoi)).setOnClickListener(new OnClickListener() {
		@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this, CreerTournoi.class);
				startActivity(i);
			}
		});	
        
	}
	
	public void setFiltres(String filtreTitre, String filtreJeu){
		this.filtres = new String[2];
		filtres[0] = filtreTitre;
		filtres[1] = filtreJeu;
		for(int k=0; k<fragList.size(); k++){
			fragList.get(k).setFiltres(this.filtres);
		}
	}
	
	public String[] getFiltres(){
		return filtres;
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
