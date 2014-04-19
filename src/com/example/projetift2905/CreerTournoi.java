package com.example.projetift2905;

import org.json.JSONArray;
import org.json.JSONObject;

import com.binarybeast.api.BBRequest;
import com.binarybeast.api.BBRequestHandler;
import com.binarybeast.api.BBResult;
import com.binarybeast.api.BinaryBeastAPI;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class CreerTournoi extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_tournoi);
		
		((Button)findViewById(R.id.ValiderCreerTournoi)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				new BinaryBeastAPI("3ad9fe9061f6dfe3f0d7d495a3bf8611.533c43d1901466.70692389");					
				Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();		//Pour verifier si la librairie
																																	//est correctement referencee.				

				//TouneyId xSC214041924
				//xSC214041926
				//BBRequest.tourneyDelete("xSC214041926").execute(new BBRequestHandler()	//pour supprimer le tournoi créer
				
				BBRequest.tourneyCreate("testAPI2905").execute(new BBRequestHandler()						
				{
					public void onResponse(BBResult result)
					{
						//Toast.makeText(getApplicationContext(), "onResponse", Toast.LENGTH_SHORT).show();
						
						//System.out.println("result API : "+result.result);

						if(result.result == 200)
						{
							System.out.println("onResponse() tourneyCreate");
							System.out.println("Result: "+result);

						}
						else System.err.println("Response Error: " + result.result);
					}
				});
				
				
			}
		});	

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_tournoi, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_creer_tournoi,
					container, false);
			return rootView;
		}
	}

}
