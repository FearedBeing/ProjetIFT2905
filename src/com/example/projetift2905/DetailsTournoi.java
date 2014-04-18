package com.example.projetift2905;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import com.binarybeast.api.BBRequest;
import com.binarybeast.api.BBRequestHandler;
import com.binarybeast.api.BBResult;
import com.binarybeast.api.BinaryBeastAPI;


public class DetailsTournoi extends Activity {
	
	
	//informations a charger
	TextView id;
	TextView name;
	TextView game;
	TextView type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_tournoi);
		
		
		
		((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "testAPI", Toast.LENGTH_SHORT).show();
				
				new BinaryBeastAPI("e17d31bfcbedd1c39bcb018c5f0d0fbf.4dcb36f5cc0d74.24632846");		//et la...c'est le drame. J'ai commenté la suite 
																									//mais en théorie ca devrait afficher le resultat 
																									//de l'appel sur la console
				
				
				/*
				System.out.println("Thanks for testing, welcome to the BinaryBeast API Version: " + BinaryBeastAPI.API_VERSION);
				
				BBRequest.gameSearch("starcraft").execute(new BBRequestHandler()
				{
					@Override
					public void onResponse(BBResult result)
					{
						//Toast.makeText(getApplicationContext(), "onResponse", Toast.LENGTH_SHORT).show();
						
						//System.out.println("result API : "+result.result);

						if(result.result == 200)
						{
							//Let's loop through the results
							JSONArray games = result.optJSONArray("Games");
							for(int x = 0; x < games.length(); x++)
							{
								JSONObject game = games.optJSONObject(x);
								System.out.println(x + ": " + game.optString("Game") + " (GameCode: " + game.optString("GameCode") + ")");
							}
						}
						else System.err.println("Response Error: " + result.result);
					}
				});
				*/

			}
		});	
		
		name=(TextView)findViewById(R.id.textView5);	
		name.setText(" ?");
		game=(TextView)findViewById(R.id.textView6);	
		game.setText(" ?");
		type=(TextView)findViewById(R.id.textView7);	
		type.setText(" ?");

		
		//new DownloadLoginTask().execute();	//appel a l'API

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details_tournoi, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_details_tournoi,
					container, false);
			return rootView;
		}
	}
	
	
	
}
