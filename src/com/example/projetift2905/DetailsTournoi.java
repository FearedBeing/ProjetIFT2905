package com.example.projetift2905;

import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

//
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.binarybeast.api.BinaryBeastAPI;


public class DetailsTournoi extends Activity {
        
    //informations a charger
    TextView id, name, game, type, status, dateCreated, teamsConfirmedCount;
    String gameCode;
    
    LoadTourInfoById api;    
    String TourneyID="0";
    int deleteTournoi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        /* **************************************
         * APPEL API POUR CREER LISTE DE TOURNOIS
         * **************************************/
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        
        setContentView(R.layout.activity_details_tournoi);
        
        //System.out.println("avant");
        Intent intent = getIntent();
        TourneyID = intent.getStringExtra("TourneyID");
        System.out.println("TourneyID: "+TourneyID);
        
        this.api = null;
        new DownloadLoginTask().execute();    
        
        
        name=(TextView)findViewById(R.id.textView5);    
        game=(TextView)findViewById(R.id.textView6);    
        type=(TextView)findViewById(R.id.textView7);
        dateCreated=(TextView)findViewById(R.id.textView9);    
        status=(TextView)findViewById(R.id.textView13);
        teamsConfirmedCount=(TextView)findViewById(R.id.textView11);
        
        
        
        ((Button)findViewById(R.id.popupOk)).setOnClickListener(new OnClickListener() {
            @Override
                public void onClick(View arg0) {
                            
                    Toast.makeText(DetailsTournoi.this, "Toto", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(DetailsTournoi.this, ListPlayers.class);
                    System.out.println("ID Tournoi avant le transfert : "+TourneyID);
                    i.putExtra("TourneyID", TourneyID);
                    startActivity(i);
                    
                }
            });    
        
        
        ((Button)findViewById(R.id.buttonDelete)).setOnClickListener(new OnClickListener() {
            @Override
                public void onClick(View arg0) {
                            
                    deleteTournoi=1;
                    (new SaveLoad(getApplicationContext())).removeFavorite(TourneyID);
                    new DownloadLoginTask().execute();    
                    
                }
            });    
        
    }
    
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        
        Intent intent = getIntent();
        TourneyID = intent.getStringExtra("TourneyID");
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
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void setApi(LoadTourInfoById api){
        this.api = api;
        String title = this.api.infoForId.title;
        name.setText(title);
        String gameCode = this.api.infoForId.gameCode;
        game.setText(gameCode);
        int typeId = this.api.infoForId.typeId;
        type.setText(String.valueOf(typeId));
        
        dateCreated.setText(String.valueOf(this.api.infoForId.dateCreated));
        
        status.setText(String.valueOf(this.api.infoForId.status));
        
        //teamsConfirmedCount.setText(this.api.infoForId.teamsConfirmedCount);
        
        ((TextView)findViewById(R.id.filtreNom)).setText(String.valueOf(this.api.infoForId.tourneyID));
        
    }
    
    public LoadTourInfoById getApi(){
        return this.api;
    }
    
    private class DownloadLoginTask extends AsyncTask<String, String, LoadTourInfoById> {
        
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }
        
        protected LoadTourInfoById doInBackground(String... params) {
            //LoadTourInfoById api = new LoadTourInfoById("defaultString");    //identifiant du tournoi a afficher
            String tId; 
            
            if(TourneyID!=null){
                System.out.println("(TourneyID!=null)");                
                tId = TourneyID;
                System.out.println("*-*-TourneyID: "+TourneyID);
            }
            else{
                System.out.println("(TourneyID==null)");
                tId = getText(R.string.InfoTourneyId).toString();
                                
            }
            
            System.out.println("tourney//Id: "+tId);//0
            
            if(deleteTournoi==0){
                LoadTourInfoById api = new LoadTourInfoById(getApplicationContext(), tId);     //identifiant du tournoi a afficher
                return api;
            }
            else{
                LoadTourInfoById api = new LoadTourInfoById(getApplicationContext(), tId, 0);     //identifiant du tournoi a afficher
                return api;
            }
            
        }
        
        protected void onProgressUpdate(String... s) {}
        
        protected void onPostExecute(LoadTourInfoById api) {
            setProgressBarIndeterminateVisibility(false);
            
            // On s'assure que l'objet de retour existe
            // et qu'il n'ait pas d'erreurs
            if( api == null ) {
                Toast.makeText(DetailsTournoi.this, getText(R.string.api_vide), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if( api.error != null ) {
                Toast.makeText(DetailsTournoi.this, api.error, Toast.LENGTH_SHORT).show();
                return;
            }
            
            if(deleteTournoi!=0){
                deleteTournoi=0;
                finish();
            }
            else{
                setApi(api);
            }
        }
    }    
        
}