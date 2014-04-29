package com.example.projetift2905;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/*
 * Si on utilise l'émulateur Android (et seulement l'émulateur), il est simple
 * d'accéder et d'examiner la base de données directement depuis Eclipse. Les
 * bases de données Android sont SQLite3.
 * 
 * Dans la perspective DDMS, sous l'onglet File Explorer, vous pourrez naviguer
 * vers /data/data/iro.ift2905.bddmeteo/databases et sélectionner "meteo.db". En
 * cliquant sur "Pull a file from the device", vous obtiendrez une copie de la
 * base de données sur votre ordinateur que vous pourrez visualiser à votre guise.
 * 
 * Vous trouverez un exécutable sqlite3 dans le dossier du SDK (sous ADT, sdk/tools)
 * qui vous permet de facilement lire la base de données. Quelques commandes :
 * 
 *  sqlite3 meteo.db
 *  > .help  -> Donne la liste des commandes
 *  > .dump  -> Affiche la structure de la base de données et toutes les données
 *  > .schema -> Affiche la structure de la base de données
 *  > .quit
 * 
 * Si vous n'aimez pas la ligne de commande, il existe aussi un module Firefox
 * nommé SQLite Manager qui vous permet de naviguer avec une interface graphique.
 */

/**
 * 
 * Cette classe permet de facilier la connexion à la
 * base de donnée que nous utilisons. Elle s'occupe de
 * gérer non seulement l'insertion et la lecture de données,
 * mais aussi de créer et détruire la base de données au
 * besoin. Cette fonctionnalité est standardisée, ce qui
 * la rend réutilisable dans d'autres classes d'Android,
 * par exemple un ContentProvider.
 *
 */
public class DBHelperTourney extends SQLiteOpenHelper {

	/*
	 * La version est un entier obligatoire qui indique
	 * la version de la *structure* de la base de données.
	 * À chaque fois que celle-ci est changée, on doit
	 * incrémenter la version.
	 * 
	 * Un changement structurel peut être, par exemple,
	 * ajouter ou enlever des colonnes, changer la définition
	 * ou les propriétés d'une colonne, etc.
	 */
	static final int VERSION=2;

	static final String TABLE = "meteo";

	/*
	 * Énumération des noms de colonne. La colonne
	 * ID est requise pour l'usage de la base de données
	 * dans un ContentProvider.
	 */
	static final String C_ID = "_id";
	static final String C_GAMECODE = "gamecode";
	static final String C_ELIMINATION = "eliminationtype";

	/*
	 * On utilisera ce contexte pour afficher des toasts.
	 */
	Context context;	

	public DBHelperTourney(Context context) {
		// Une application conserve toutes ses données privées
		// dans /data/data/<package>/.
		// La base de donnée est donc dans
		// /data/data/iro.ift2905.bddmeteo/databases (Voir DDMS)
		super(context, "meteo.db", null, VERSION);
		this.context=context;
	}

	/*
	 * onCreate est appelée lorsqu'on crée la base de données.
	 * Tous les appels d'initialisation, par exemple la création
	 * de tables, doivent être effectués ici.
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Toast.makeText(context, "Création BDD", Toast.LENGTH_LONG).show();
		Log.d("DBHelper", "Création BDD");
		
		// Appel standard pour créer une table dans la base de données.
		String sql = "create table " + TABLE + " ("
				+ C_ID +" integer primary key, "
				+ C_GAMECODE + " text,"
				+ C_ELIMINATION + " text)";


		// ExecSQL prend en entrée une commande SQL et l'exécute
		// directement sur la base de données.
		db.execSQL(sql);
	}

	/*
	 * onUpgrade est appelée lorsque l'application détecte une
	 * base de données avec une version plus ancienne que celle
	 * actuellement utilisée. Ceci peut arriver si, par exemple,
	 * on crée une mise à jour pour l'application qui change la
	 * structure de la base de données et donc la version; tous
	 * les usagers ayant déjà utilisé l'application avant cette
	 * mise à jour auront l'ancienne version de la structure et
	 * devront être mis à jour.
	 * 
	 * Notez qu'en général, on souhaitera conserver les données
	 * dans la base de données en effectuant cette mise à jour.
	 * L'exemple ici se contente de tout effacer, mais ce comportement
	 * n'est désirable que si la base de données ne sert que de cache
	 * et donc ne contient que des valeurs temporaires.
	 * 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int ancienneVersion, int nouvelleVersion) {
		Toast.makeText(context, "Mise à jour BDD", Toast.LENGTH_LONG).show();
		Log.d("DBHelper", "Mise à jour BDD");
		
		// Efface l'ancienne base de données
		db.execSQL("drop table if exists "+TABLE);
		// Appelle onCreate, qui recrée la base de données
		onCreate(db);
	}

	/*
	 * Cette méthode a été créée spécifiquement pour la base de donnée
	 * présente et permet d'ajouter un élément à celle-ci.
	 * 
	 */
	public void addNewEntry(String temperature, String conditions,long heure) {
		// Malgré le nom "get", la méthode getWritableDatabase fait plus
		// que simplement retourner une référence; elle ouvre une
		// connexion à la base de données. Il est donc impératif d'appeler
		// db.close() à la fin du processus.
		SQLiteDatabase db = this.getWritableDatabase();
		
		// ContentValues est un objet utilitaire permettant
		// de stocker des données à insérer dans une base
		// de données.
		ContentValues val = new ContentValues();
		val.clear();
		
		// La valeur ID *doit* être unique; on prend ici le temps en milisecondes.
		val.put(C_ID,System.currentTimeMillis());
		val.put(C_GAMECODE, temperature);
		val.put(C_ELIMINATION, conditions);

		try {
			db.insertOrThrow(TABLE, null,val);
		} catch ( SQLException e ) {
			Log.d("DBHelper", "Erreur BDD: " + e.getMessage());
		}
		
		// N'oubliez pas ceci!
		db.close();		
	}

	
	/*
	 * Méthode utilitaire qui retourne le nombre
	 * d'éléments dans la base de données.
	 * 
	 */
	public int querySize() {
		// Notez l'usage de getReadableDatabase; lorsqu'on ne
		// cherche pas à modifier la base de données, il
		// est recommandé d'utiliser une connexion lecture seule.
		SQLiteDatabase db = this.getReadableDatabase();

		// Cursor est un objet très utilisé qui permet de stocker
		// le résultat d'une requête SQL. Ce résultat peut être,
		// dans le cas présent, la colonne ID de toutes les lignes
		// de la base de donnée, ce qui nous permet de les compter.
		Cursor c = db.query(TABLE, new String[] {C_ID}, null, null, null, null, null);
		int size = c.getCount();
		db.close();
		return size;
	}


}
