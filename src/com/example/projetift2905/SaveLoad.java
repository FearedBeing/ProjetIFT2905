package com.example.projetift2905;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences.Editor;


public class SaveLoad{

    public static final String FAVORIS = "favorites";
    private static final String APP_SHARED_PREFS = SaveLoad.class.getSimpleName();
    private SharedPreferences _sharedPrefs;
    private Editor _prefsEditor;
	
	

	public SaveLoad(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();		
	}
    

	public String[] getFavorites(){
		
		// "Limite artificielle... on verra pour la suite."
		String[] liste = new String[50];
		
		String str = _sharedPrefs.getString(FAVORIS, "");

		String mot = "";
		int emptyspot = 0;
		
		for (int x=0; x < str.length(); x++){
			if (str.charAt(x) == ','){
				
				for (int i = liste.length - 1; i >= 0; i--){
					if (liste[i] == null) emptyspot = i;
				}
				
				liste[emptyspot] = mot;
				mot = "";
			}
			else{
				mot = mot + str.charAt(x);
			}
		}
		return liste;
    }
	
	public void setFavorites(String[] liste){
		
		String str = "";
		
		for (int x=0; x < liste.length; x++){
			if (liste[x] != null)str = str + liste[x] + ",";
		}

		
        _prefsEditor.putString(FAVORIS, str);
        _prefsEditor.commit();
		
	}
	
	public void addFavorite(String fav){
		
		String[] liste = getFavorites();
			
		int emptyspot = 0;
		int dejala = 0;
		
		for (int x = liste.length - 1; x >= 0; x--){
			if (liste[x] == null) emptyspot = x;
			else if (liste[x].equals(fav)) dejala = 1;
		}
			
		liste[emptyspot] = fav;
		

		if (dejala == 0)setFavorites(liste);
	}
	
	public void removeFavorite(String fav){
		
		String[] listein = getFavorites();
		
		// "Limite artificielle... on verra pour la suite."
		String[] listeout = new String[50];
		
		for (int x = listein.length - 1; x >= 0; x--){
			if (listein[x] != null)	if (listein[x].equals(fav) == false)listeout[x] = listein[x];
		}
		
		
		setFavorites(listeout);
		
	}

}
