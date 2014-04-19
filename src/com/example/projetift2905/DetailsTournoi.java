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
		
		
		
		((Button)findViewById(R.id.buttonTestAPI)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				
				new BinaryBeastAPI("3ad9fe9061f6dfe3f0d7d495a3bf8611.533c43d1901466.70692389");	
				
				Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();		//Pour verifier si la librairie
																																	//est correctement referencee.
				
				//Methode1			
				BBRequest request = new BBRequest("Game.GameSearch.Search");
				request.addArg("filter", "star");
				request.execute(new BBRequestHandler(){
						public void onResponse(BBResult result)
						{
							if(result.result == 200)
							{
								System.out.println("*Methode1: ");
								
								//Let's loop through the results
								JSONArray games = result.optJSONArray("Games");
								for(int x = 0; x < games.length(); x++)
								{
									JSONObject game = games.optJSONObject(x);
									System.out.println("*"+x + ": " + game.optString("Game") + " (GameCode: " + game.optString("GameCode") + ")");
									//System.out.println("// "+x + ": " + game.optString("TourneyID") + " (GameCode: " + game.optString("GameCode") + ")");
								}
							}
							else System.err.println("Response Error: " + result.result);
						}
					});

				//Methode2
				BBRequest.gameSearch("star").execute(new BBRequestHandler()
				{
					public void onResponse(BBResult result)
					{
						//Toast.makeText(getApplicationContext(), "onResponse", Toast.LENGTH_SHORT).show();
						
						//System.out.println("result API : "+result.result);

						if(result.result == 200)
						{
							System.out.println("Methode2: ");
							
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
