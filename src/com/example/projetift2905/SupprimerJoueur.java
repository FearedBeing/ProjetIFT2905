package com.example.projetift2905;

import java.util.ArrayList;
import java.util.List;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class SupprimerJoueur extends Activity {
    
    SupprimerJoueurAPI api;
    String gameCode;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        setContentView(R.layout.activity_supprimer_joueur);
        
        
        
        
        
        
        lv = (ListView) findViewById(R.id.listView1);

        
        
        //((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
            //@Override
            //public void onClick(View arg0) {                                
                
                //new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
                //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

                /* **************************************
                 * APPEL API POUR CREER LISTE DE TOURNOIS
                 * **************************************/                
                //new DownloadLoginTask().execute();
                //BBRequest.tourneyDelete("xSC214041926").execute(new BBRequestHandler()    //pour supprimer le tournoi cr��
                

                
                
                //String apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyList.Popular&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&Limit=25";

            //}
        //});    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.creer_tournoi, menu);
        ((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
            @Override
                public void onClick(View arg0) {
                new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
                //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

                /* **************************************
                 * APPEL API POUR CREER LISTE DE TOURNOIS
                 * **************************************/                
                new DownloadLoginTask().execute();
                
                
                
                }
            });
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
    

    public void setApi(CreateTournoiAPI api){
        /*this.api = api;
        String title = this.api.infoForId.title;
        name.setText(title);
        String gameCode = this.api.infoForId.gameCode;
        game.setText(gameCode);
        int typeId = this.api.infoForId.typeId;
        type.setText(String.valueOf(typeId));
        */
        
        //((TextView)findViewById(R.id.textView1)).setText(this.api.infoForId.tourneyID);
        
    }
    
    public SupprimerJoueurAPI getApi(){
        return this.api;
    }
    
    
    private class DownloadLoginTask extends AsyncTask<String, String, SupprimerJoueurAPI> {
        
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }
        
        protected SupprimerJoueurAPI doInBackground(String... params) {
            EditText text = (EditText)findViewById(R.id.editText1);
            //String nom = text.getText().toString();
            
            Intent intent = getIntent();
            String TourneyID = intent.getStringExtra("TourneyID");
            String TourneyTeamID = intent.getStringExtra("TourneyTeamID");
            System.out.println("ID transfere :"+TourneyID);
            String nom="ERRoR Tourney";
            
            
            //String nom = getText(R.id.editNomNewTournoi).toString();
            
            System.out.println("editNomNewTournoi: "+nom);
            
            
            
            SupprimerJoueurAPI api = new SupprimerJoueurAPI(getApplicationContext(), 2, nom, gameCode, TourneyID,"Toto", TourneyTeamID);    //identifiant du tournoi a afficher
            return api;
        }
        
        protected void onProgressUpdate(String... s) {}
        
        protected void onPostExecute(CreateTournoiAPI api) {
            setProgressBarIndeterminateVisibility(false);
            
            // On s'assure que l'objet de retour existe
            // et qu'il n'ait pas d'erreurs
            if( api == null ) {
                Toast.makeText(SupprimerJoueur.this, getText(R.string.api_vide), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if( api.error != null ) {
                Toast.makeText(SupprimerJoueur.this, api.error, Toast.LENGTH_SHORT).show();
                return;
            }
            
            setApi(api);
        }
        protected void onPostExecute(ListPlayersAPI api) {
            setProgressBarIndeterminateVisibility(false);
            
            // On s'assure que l'objet de retour existe
            // et qu'il n'ait pas d'erreurs
             //List<String> your_array_list = new ArrayList<String>();
                //your_array_list.add("foo");
                //your_array_list.add("bar");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getApplicationContext(), 
                    android.R.layout.simple_list_item_1,
                    api.names );
            lv.setAdapter(arrayAdapter);
            
            
                
                
                
                
                
                
                
            
            
        }
    }    
    

}