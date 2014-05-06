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


public class ListPlayersAPI {
    
    public List<TourneyData> infoForId;
    public String error;
    String TourneyID;
    
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
    
    ListPlayersAPI(Context ctx, int typeID, String title, String gameCode, String TourneyID){
        
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
        String apiCall="https://api.binarybeast.com/?APIService=Tourney.TourneyLoad.Teams&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&TourneyID="+TourneyID;
        
        System.out.println("CREATE TOURNOI API: "+apiCall);
        List<TourneyData> lst = new ArrayList<TourneyData>();
        try{
            
            HttpEntity page = getHttp(apiCall);
            JSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));
            
            int i=0;
            System.out.println(js.getJSONArray("Teams").length());
            while(i<js.getJSONArray("Teams").length()){
                System.out.println(i+":Teams");
                String TourneyTeamID=js.getJSONArray("Teams").getJSONObject(i).getString("TourneyTeamID");
                String DisplayName=js.getJSONArray("Teams").getJSONObject(i).getString("DisplayName");
                /*System.out.println(i+":ID");
                String group=js.getJSONArray("Teams").getJSONObject(i).getString("Group");
                System.out.println(i+":Points");
                String points=js.getJSONArray("Teams").getJSONObject(i).getString("Points");
                System.out.println(i+":Name");
                String DisplayName=js.getJSONArray("Teams").getJSONObject(i).getString("DisplayName");
                System.out.println(i+":Losses");
                String Losses=js.getJSONArray("Teams").getJSONObject(i).getString("Losses");
                System.out.println(i+":Wins");
                String Wins=js.getJSONArray("Teams").getJSONObject(i).getString("Wins");
                */
                TourneyData td = new TourneyData(TourneyTeamID,"A","0",DisplayName,"0","0");
                lst.add(td);
                
                i++;
            }
            this.infoForId=lst;
            for(i=0;i<this.infoForId.size();i++){
                System.out.println(this.infoForId.get(i).DisplayName);
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