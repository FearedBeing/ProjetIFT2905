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
import android.widget.Toast;


public class ListPlayersAPI {
    
    public TourneyData infoForId;
    public String error;
    
    public class TourneyData{
        public final String title, gameCode;
        public final int typeId;
        //public final Drawable gameLogo;
        
        public TourneyData(String title, String gameCode, int typeId){
            //this.tourneyID = tourneyID;
            this.title = title;
            this.gameCode = gameCode;
            this.typeId = typeId;            
            
            //this.gameLogo = gameLogo;
        }
    }
    
    ListPlayersAPI(Context ctx, int typeID, String title, String gameCode){
        
        error = null;
        
        System.out.println("Debug: "+title+" //"+gameCode);
        
        /*String apiCallp1 = "https://api.binarybeast.com/?APIService=Tourney.TourneyCreate.Create&";
        String apiCallp2 = "APIReturn=json&APIKey=" + ctx.getResources().getString(R.string.API_KEY) + "&";
        
        String argTitle = "Title=";
        argTitle=argTitle.concat(title);
        argTitle=argTitle.concat("&");
        
        String argTypeID = "TypeID=";
        argTypeID=argTypeID.concat("0");
        argTypeID=argTypeID.concat("&");
        
        String argTypeGameCode = "GameCode=";
        argTypeGameCode=argTypeGameCode.concat(gameCode);
        String apiCall = apiCallp1+apiCallp2;
        apiCall+=argTitle;
        apiCall+=argTypeID;
        apiCall+=argTypeGameCode;
        */
        String apiCall="https://api.binarybeast.com/?APIService=Tourney.TourneyLoad.Teams&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&TourneyID=xDOTA214050111";
        
        System.out.println("CREATE TOURNOI API: "+apiCall);
                
        try{
            
            HttpEntity page = getHttp(apiCall);
            JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
            
            String text=js.getJSONArray("Teams").getJSONObject(0).getString("Points");
            System.out.println(text);
            
            String tourneyIdd = js.getString("TourneyID");
            
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