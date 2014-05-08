package com.example.projetift2905;

import java.util.ArrayList;

import android.view.ContextMenu;  
import android.view.View;  
import android.view.ContextMenu.ContextMenuInfo; 

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.binarybeast.api.BBRequest;
import com.binarybeast.api.BBRequestHandler;
import com.binarybeast.api.BBResult;
import com.binarybeast.api.BinaryBeastAPI;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ListPlayers extends Activity {
    
    ListPlayersAPI api;
    SupprimerJoueurAPI api2;
    String gameCode;
    private ListView lv;
    String TourTeamID;
    ListPlayersAPI ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        setContentView(R.layout.activity_list_players);
        
        
        
        
        
        
        lv = (ListView) findViewById(R.id.listView1);
        

        new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
        //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

        /* **************************************
         * APPEL API POUR CREER LISTE DE TOURNOIS
         * **************************************/                
        new DownloadLoginTask().execute();
        
        
        
        
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
    public void onResume()
        {  // After a pause OR at startup
        super.onResume();
        Intent intent = getIntent();
        String TourneyID = intent.getStringExtra("TourneyID");
        new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
        //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

        /* **************************************
         * APPEL API POUR CREER LISTE DE TOURNOIS
         * **************************************/                
        new DownloadLoginTask().execute();
        //Refresh your stuff here
         }
    
    
    
    //protected void onRestart(Bundle savedInstanceState){
        //new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
        //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

        /* **************************************
         * APPEL API POUR CREER LISTE DE TOURNOIS
         * **************************************/                
        //new DownloadLoginTask().execute();
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.creer_tournoi, menu);
        ((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
            @Override
                public void onClick(View arg0) {
                    Intent i = new Intent(ListPlayers.this, AddPlayerActivity.class);
                    /* HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEREEEEEEEEEEEEEEEEEEEE !!!!!*/
                    Intent intent = getIntent();
                    boolean own = intent.getBooleanExtra("owned", false);
                    System.out.println("Owned : "+ own);
                    String TourneyID = intent.getStringExtra("TourneyID");
                    i.putExtra("TourneyID", TourneyID);
                    i.putExtra("owned", own);
                    startActivity(i);
                }
            });
        Intent intent = getIntent();
        boolean own = intent.getBooleanExtra("owned", false);
        if(!own)((Button)findViewById(R.id.button1)).setVisibility(View.INVISIBLE);
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
    
    public ListPlayersAPI getApi(){
        return this.api;
    }
    
    
    private class DownloadLoginTask extends AsyncTask<String, String, ListPlayersAPI> {
        
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }
        
        protected ListPlayersAPI doInBackground(String... params) {
            EditText text = (EditText)findViewById(R.id.editNomNewTournoi);
            //String nom = text.getText().toString();
            
            Intent intent = getIntent();
            String TourneyID = intent.getStringExtra("TourneyID");
            System.out.println("ID transfere :"+TourneyID);
            String nom="ERRoR Tourney";
            
            
            //String nom = getText(R.id.editNomNewTournoi).toString();
            
            System.out.println("editNomNewTournoi: "+nom);
            
            
            
            ListPlayersAPI api = new ListPlayersAPI(getApplicationContext(), 2, nom, gameCode, TourneyID);    //identifiant du tournoi a afficher
            return api;
        }
        
        protected void onProgressUpdate(String... s) {}
        
        protected void onPostExecute(CreateTournoiAPI api) {
            setProgressBarIndeterminateVisibility(false);
            
            // On s'assure que l'objet de retour existe
            // et qu'il n'ait pas d'erreurs
            if( api == null ) {
                Toast.makeText(ListPlayers.this, getText(R.string.api_vide), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if( api.error != null ) {
                Toast.makeText(ListPlayers.this, api.error, Toast.LENGTH_SHORT).show();
                return;
            }
            
            setApi(api);
        }
        protected void onPostExecute(final ListPlayersAPI api) {
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
            //lv.setOnItemClickListener(new OnItemClickListener() {
              //  @Override
                //public void onItemClick(AdapterView<?> parent, View view, int position,
                  //      long id) {
                   // Intent intent = getIntent();
                    //String TourneyID = intent.getStringExtra("TourneyID");
                    //Intent i = new Intent(ListPlayers.this, SupprimerJoueur.class);
                    //ap=api;
                    //i.putExtra("TourneyTeamID",api.infoForId.get(position).TourneyTeamID);
                    //i.putExtra("TourneyID", TourneyID);
                    //startActivity(i);
                    //new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
                    //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

                    /* **************************************
                     * APPEL API POUR CREER LISTE DE TOURNOIS
                     * **************************************/                
                    //new DownloadLoginTask().execute();
                    
                    //System.out.println(position);
                //}
            //});
            
            
            lv.setOnItemLongClickListener(new OnItemLongClickListener()
            {
                   public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2,long arg3)
                   {
                       final String selectedValue = (String) lv.getItemAtPosition(arg2);
                       AlertDialog.Builder alertDialog = new  AlertDialog.Builder(ListPlayers.this);
                       alertDialog.setTitle("Supprimer ce joueur ?");
                       alertDialog.setMessage(selectedValue);     
                       alertDialog.setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int which) {

                                  System.out.println("plz");
                                  TourTeamID=api.infoForId.get(arg2).TourneyTeamID;
                                  System.out.println("plzzzz");
                                  new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
                                  //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

                                  /* **************************************
                                   * APPEL API POUR CREER LISTE DE TOURNOIS
                                   * **************************************/                
                                  new DownloadLoginTask2().execute();
                                  new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
                                  //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

                                  /* **************************************
                                   * APPEL API POUR CREER LISTE DE TOURNOIS
                                   * **************************************/                
                                  new DownloadLoginTask().execute();

                          } }); 
                          alertDialog.setPositiveButton("Non", new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int which) {
                                 // alertDialog.dismiss();
                          } }); 
                          Intent intent = getIntent();
                          boolean owned = intent.getBooleanExtra("owned", false);
                          System.out.println(owned);
                          if(owned){
                              alertDialog.show();
                          }
                          
                       
                       
                       
                       
                       
                       
                       return true;
                   }
           });
            
            
                
                
                
                
                
                
                
            //
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//NEWEND !!!!!!!!!!!!    
private class DownloadLoginTask2 extends AsyncTask<String, String, SupprimerJoueurAPI> {
        
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }
        
        protected SupprimerJoueurAPI doInBackground(String... params) {
            EditText text = (EditText)findViewById(R.id.editNomNewTournoi);
            //String nom = text.getText().toString();
            
            Intent intent = getIntent();
            String TourneyID = intent.getStringExtra("TourneyID");
            System.out.println("ID transfere :"+TourneyID);
            String nom="ERRoR Tourney";
            
            
            //String nom = getText(R.id.editNomNewTournoi).toString();
            
            System.out.println("editNomNewTournoi: "+nom);
            
            
            
            SupprimerJoueurAPI api = new SupprimerJoueurAPI(getApplicationContext(), 2, nom, gameCode, TourneyID,nom,TourTeamID);    //identifiant du tournoi a afficher
            return api;
        }
        
        protected void onProgressUpdate(String... s) {}
        
        protected void onPostExecute(CreateTournoiAPI api) {
            setProgressBarIndeterminateVisibility(false);
            
            // On s'assure que l'objet de retour existe
            // et qu'il n'ait pas d'erreurs
            if( api == null ) {
                Toast.makeText(ListPlayers.this, getText(R.string.api_vide), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if( api.error != null ) {
                Toast.makeText(ListPlayers.this, api.error, Toast.LENGTH_SHORT).show();
                return;
            }
            
            setApi(api);
        }
        protected void onPostExecute(final ListPlayersAPI api) {
            setProgressBarIndeterminateVisibility(false);
            
            // On s'assure que l'objet de retour existe
            // et qu'il n'ait pas d'erreurs
             //List<String> your_array_list = new ArrayList<String>();
                //your_array_list.add("foo");
                //your_array_list.add("bar");
            //new BinaryBeastAPI(getApplicationContext().getResources().getString(R.string.API_KEY) + "");                    
            //Toast.makeText(getApplicationContext(), "Version :" + BinaryBeastAPI.API_VERSION, Toast.LENGTH_SHORT).show();        //Pour verifier si la librairie

            /* **************************************
             * APPEL API POUR CREER LISTE DE TOURNOIS
             * **************************************/                
            //new DownloadLoginTask2().execute();
            
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getApplicationContext(), 
                    android.R.layout.simple_list_item_1,
                    api.names );
            lv.setAdapter(arrayAdapter);
            /*lv.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                        long id) {
                    Intent intent = getIntent();
                    String TourneyID = intent.getStringExtra("TourneyID");
                    Intent i = new Intent(ListPlayers.this, SupprimerJoueur.class);
                    i.putExtra("TourneyTeamID",api.infoForId.get(position).TourneyTeamID);
                    i.putExtra("TourneyID", TourneyID);
                    startActivity(i);
                    
                    System.out.println(position);
                }
            });
            */
            
            
                
                
                
                
                
                
                
            
            
        }
    }    
    

}