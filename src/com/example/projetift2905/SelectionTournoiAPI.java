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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

/*
 * SelectionTournoiAPI est la classe qui gère tous les appels API nécessaire à MainActivity. Elle va chercher les infos des tournois récents et possèdés en 2 appels api.
 * Pour les favoris, il faut 1 appel api par tournoi, car les favoris ne sont pas gérés par l'api elle-même.
 */
public class SelectionTournoiAPI {
	
	public List<TourneyData> dataList;
	public String error;
	
	public class TourneyData{
		public final boolean owner;
		public final String title, tourneyID, game;
		public final Drawable gameLogo;
		
		public TourneyData(String tourneyID, String title, String game, boolean owner, Drawable gameLogo){
			this.tourneyID = tourneyID;
			this.title = title;
			this.game = game;
			this.gameLogo = gameLogo;
			this.owner = owner;
		}
	}
	
	SelectionTournoiAPI(Context ctx){
		error = null;
		dataList = new ArrayList<TourneyData>();
		
		try{
			// TOURNOIS RECENTS
			String apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyList.Popular&APIReturn=json&APIKey=" + ctx.getResources().getString(R.string.API_KEY) + "&Limit=25";
			HttpEntity page = getHttp(apiCall);
			JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
			JSONArray list = js.getJSONArray("List");
			JSONObject element;
			for(int index=0; index<list.length(); index++){
				element = list.getJSONObject(index);
				dataList.add(getTourneyData(element, false));
			}
			
			// TOURNOIS FAVORIS
			for(String fav: (new SaveLoad(ctx)).getFavorites()){
				if(fav != null){
					apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyLoad.Info&APIReturn=json&APIKey=" + ctx.getResources().getString(R.string.API_KEY) + "&TourneyID=" + fav;
					page = getHttp(apiCall);
					js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
					element = js.getJSONObject("TourneyInfo");
					if(!containsTourney(dataList, getTourneyData(element, false))) dataList.add(getTourneyData(element, false)); // Ajouter seulement si pas un doublon
				}
			}
			
			// TOURNOIS PROPRIETAIRES
			apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyList.My&APIReturn=json&APIKey=" + ctx.getResources().getString(R.string.API_KEY);
			page = getHttp(apiCall);
			js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
			JSONObject creator = js.getJSONObject("Creator");
			list = creator.getJSONArray("List");
			for(int index=0; index<list.length(); index++){
				element = list.getJSONObject(index);
				TourneyData tourney = getTourneyData(element, true);
				if(containsTourney(dataList, tourney)){
					dataList.remove(getTourneyById(dataList, tourney.tourneyID));  // Si existe deja on retire (car celui dans dataList a été initialisé avec ownership == false
				}
				dataList.add(tourney);
			}
			
		} catch (ClientProtocolException e) {
			error = "[SelectionTournoiAPI] Erreur HTTP (protocole) :"+e.getMessage();
		} catch (IOException e) {
			error = "[SelectionTournoiAPI] Erreur HTTP (IO) :"+e.getMessage();
		} catch (ParseException e) {
			error = "[SelectionTournoiAPI] Erreur JSON (parse) :"+e.getMessage();
		} catch (JSONException e) {
			error = "[SelectionTournoiAPI] Erreur JSON :"+e.getMessage();
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
	
	private TourneyData getTourneyData(JSONObject element, boolean ownership){
		try{
			String tourneyID = element.getString("TourneyID");
			String title = element.getString("Title");
			String gameIconURL = element.getString("GameIcon");
			String game = element.getString("Game");
			Drawable gameLogo = null;
			if( !gameIconURL.equals("null") ) {
				gameLogo = loadHttpImage(gameIconURL);
			}
			return new TourneyData(tourneyID, title, game, ownership, gameLogo);
		} catch (ClientProtocolException e) {
			error = "[SelectionTournoiAPI] Erreur HTTP (protocole) :"+e.getMessage();
		} catch (IOException e) {
			error = "[SelectionTournoiAPI] Erreur HTTP (IO) :"+e.getMessage();
		} catch (ParseException e) {
			error = "[SelectionTournoiAPI] Erreur JSON (parse) :"+e.getMessage();
		} catch (JSONException e) {
			error = "[SelectionTournoiAPI] Erreur JSON :"+e.getMessage();
		}
		
		return null;
	}
	
	private boolean containsTourney(List<TourneyData> list, TourneyData tourney){
		String newID = tourney.tourneyID;
		String existingID;
		for(TourneyData data: list){
			existingID = data.tourneyID;
			if(newID.equals(existingID)){
				return true;
			}
		}
		return false;
	}
	
	private TourneyData getTourneyById(List<TourneyData> list, String tourneyID){
		for(TourneyData data: list){
			if(data.tourneyID.equals(tourneyID)){
				return data;
			}
		}
		return null;
	}
	

}

