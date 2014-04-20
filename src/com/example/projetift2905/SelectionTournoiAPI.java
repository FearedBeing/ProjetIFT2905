package com.example.projetift2905;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class SelectionTournoiAPI {
	
	public List<TourneyData> dataList;
	public String error;
	
	public class TourneyData{
		public final String title, tourneyID;
		public final Drawable gameLogo;
		
		public TourneyData(String tourneyID, String title, Drawable gameLogo){
			this.tourneyID = tourneyID;
			this.title = title;
			this.gameLogo = gameLogo;
		}
	}
	
	SelectionTournoiAPI(){
		error = null;
		dataList = new ArrayList<TourneyData>();
		String apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyList.Popular&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&Limit=25";
		
		try{
			
			HttpEntity page = getHttp(apiCall);
			JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
			JSONArray list = js.getJSONArray("List");
			
			for(int index=0; index<list.length(); index++){
				JSONObject element = list.getJSONObject(index);
				String tourneyID = element.getString("TourneyID");
				String title = element.getString("Title");
				String gameIconURL = element.getString("GameIcon");
				Log.d("DATA", tourneyID);
				Log.d("DATA", title);
				Log.d("DATA", gameIconURL);
				Drawable gameLogo = null;
				if( !gameIconURL.equals("null") ) {
					gameLogo = loadHttpImage(gameIconURL);
				}
				dataList.add(new TourneyData(tourneyID, title, gameLogo));
			}
			
		} catch (ClientProtocolException e) {
			error = "Erreur HTTP (protocole) :"+e.getMessage();
		} catch (IOException e) {
			error = "Erreur HTTP (IO) :"+e.getMessage();
		} catch (ParseException e) {
			error = "Erreur JSON (parse) :"+e.getMessage();
		} catch (JSONException e) {
			error = "Erreur JSON :"+e.getMessage();
		}	
	}
	
	private HttpEntity getHttp(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet http = new HttpGet(url);
		HttpResponse response = httpClient.execute(http);
		return response.getEntity();    		
	}
	
	private Drawable loadHttpImage(String url) throws ClientProtocolException, IOException {
		InputStream is = getHttp(url).getContent();
		Drawable d = Drawable.createFromStream(is, "src");
		return d;
	}
	

}
