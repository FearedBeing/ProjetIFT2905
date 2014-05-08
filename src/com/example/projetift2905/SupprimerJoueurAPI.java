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
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;


public class SupprimerJoueurAPI {
    
    public String error;
    String TourneyID;
    public String name;
    
    public class TourneyData{
        public final String TourneyTeamID,points,group,DisplayName,Losses,Wins;
        //public final Drawable gameLogo;
        
        public TourneyData(String TourneyTeamID, String points, String group, String DisplayName, String Losses, String Wins){
            //this.tourneyID = tourneyID;
            System.out.println("toto");
            this.TourneyTeamID=TourneyTeamID;
            this.points=points;
            this.group=group;
            this.DisplayName=DisplayName;
            this.Losses=Losses;
            this.Wins=Wins;
            System.out.println("lala");
            
            //this.gameLogo = gameLogo;
        }
    }
    
    SupprimerJoueurAPI(Context ctx, int typeID, String title, String gameCode, String TourneyID, String name, String TourneyTeamID){
        
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
        String apiCall="https://api.binarybeast.com/?APIService=Tourney.TourneyTeam.Delete&APIReturn=json&APIKey=" + ctx.getResources().getString(R.string.API_KEY) + "&TourneyID="+TourneyID+"&TourneyTeamID="+TourneyTeamID;
        //https://api.binarybeast.com/?APIService=Tourney.TourneyTeam.Insert&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&TourneyID=xLOL14050712&DisplayName=Toto
        //xCSS1405071
        //1650165
        //https://api.binarybeast.com/?APIService=Tourney.TourneyTeam.Insert&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&TourneyID=xCSS1405071&TourneyTeamID=1650165
        System.out.println("CREATE TOURNOI API: "+apiCall);
        try{
            
            HttpEntity page = getHttp(apiCall);
            JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
            
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