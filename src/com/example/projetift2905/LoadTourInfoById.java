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

import android.content.Context;
import android.graphics.drawable.Drawable;


public class LoadTourInfoById {
	
	public TourneyData infoForId;
	public String error;
	
	public class TourneyData{
		public final String title, tourneyID, gameCode, dateCreated, status;
		public final int typeId, teamsConfirmedCount;
		
		public TourneyData(String tourneyID, String title, String gameCode, int typeId, String dateCreated, String status, int teamsConfirmedCount){
			this.tourneyID = tourneyID;
			this.title = title;
			this.gameCode = gameCode;
			this.typeId = typeId;			
			this.dateCreated= dateCreated;
			this.status=status;
			this.teamsConfirmedCount=teamsConfirmedCount;
			//this.gameLogo = gameLogo;
		}
	}
	
	LoadTourInfoById(Context ctx, String tourneyId){
		error = null;
		TourneyData infoTournoi;
		String apiCall1 = "https://api.binarybeast.com/?APIService=Tourney.TourneyLoad.Info&TourneyID=";
		String apiCall2 = "&APIReturn=json&APIKey=" + ctx.getResources().getString(R.string.API_KEY);
		String apiCall = apiCall1.concat(tourneyId);
		apiCall = apiCall.concat(apiCall2);
		System.out.println("LOADTOURNOIINFO/ apiCall: "+apiCall);		
		
		try{			
			HttpEntity page = getHttp(apiCall);
			JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
			JSONObject info = js.getJSONObject("TourneyInfo");
			
			System.out.println("****");	
			
			String title = info.getString("Title");
			String gameCode = info.getString("Game");
			int typeId = info.getInt("TypeID");
			String status = info.getString("Status");
			String dateCreated = info.getString("DateCreated");
			int teamsConfirmedCount = info.getInt("TeamsConfirmedCount");
			
			
			this.infoForId = new TourneyData(tourneyId, title, gameCode, typeId, dateCreated, status, teamsConfirmedCount);
			System.out.println("InfoTournoi: "+tourneyId+" "+title+" "+gameCode+" "+typeId+" "+teamsConfirmedCount);			
			
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


