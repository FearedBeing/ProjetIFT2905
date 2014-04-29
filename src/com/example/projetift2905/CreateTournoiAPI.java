package com.example.projetift2905;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;


public class CreateTournoiAPI {
	
	public TourneyData infoForId;
	public String error;
	
	public class TourneyData{
		public final String title, tourneyID, gameCode;
		public final int typeId;
		//public final Drawable gameLogo;
		
		public TourneyData(String tourneyID, String title, String gameCode, int typeId){
			this.tourneyID = tourneyID;
			this.title = title;
			this.gameCode = gameCode;
			this.typeId = typeId;
			
			//this.gameLogo = gameLogo;
		}
	}
	
	CreateTournoiAPI(String tourneyId, int typeID, String title){
		
		
		
		error = null;
		//TourneyData infoTournoi;
		String apiCallp1 = "https://api.binarybeast.com/?APIService=Tourney.TourneyCreate.Create&";
		String apiCallp2 = "APIReturn=json&APIKey=3ad9fe9061f6dfe3f0d7d495a3bf8611.533c43d1901466.70692389&";
		String argTitle = "Title=";
		argTitle=argTitle.concat(title);
		argTitle=argTitle.concat("&");
		String argTypeID = "TypeID=";
		argTypeID=argTypeID.concat("0");
		//argTypeID=argTypeID.concat("&");
		
		//String apiCall = apiCallp1+argTitle+argTypeID+apiCallp2;
		String apiCall = apiCallp1+apiCallp2+argTitle+argTypeID;
		
		System.out.println("CREATE TOURNOI API: "+apiCall);
		
		//String apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyLoad.Info&TourneyID=xSC214042710&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210";			
		
		try{
			
			HttpEntity page = getHttp(apiCall);
			JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
			//JSONObject info = js.getJSONObject("TourneyInfo");
			String tourneyIdd = js.getString("TourneyID");
			
			System.out.println("****");	
			/*String title = info.getString("Title");
			String gameCode = info.getString("Game");
			int typeId = info.getInt("TypeID");
			this.infoForId = new TourneyData(tourneyId, title, gameCode, typeId);
			*/
			
			System.out.println("tourneyId: "+tourneyIdd);
			
			/*
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
			}*/
			
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


