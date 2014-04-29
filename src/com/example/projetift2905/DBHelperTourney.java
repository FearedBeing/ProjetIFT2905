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
 * Si on utilise l'�mulateur Android (et seulement l'�mulateur), il est simple
 * d'acc�der et d'examiner la base de donn�es directement depuis Eclipse. Les
 * bases de donn�es Android sont SQLite3.
 * 
 * Dans la perspective DDMS, sous l'onglet File Explorer, vous pourrez naviguer
 * vers /data/data/iro.ift2905.bddmeteo/databases et s�lectionner "meteo.db". En
 * cliquant sur "Pull a file from the device", vous obtiendrez une copie de la
 * base de donn�es sur votre ordinateur que vous pourrez visualiser � votre guise.
 * 
 * Vous trouverez un ex�cutable sqlite3 dans le dossier du SDK (sous ADT, sdk/tools)
 * qui vous permet de facilement lire la base de donn�es. Quelques commandes :
 * 
 *  sqlite3 meteo.db
 *  > .help  -> Donne la liste des commandes
 *  > .dump  -> Affiche la structure de la base de donn�es et toutes les donn�es
 *  > .schema -> Affiche la structure de la base de donn�es
 *  > .quit
 * 
 * Si vous n'aimez pas la ligne de commande, il existe aussi un module Firefox
 * nomm� SQLite Manager qui vous permet de naviguer avec une interface graphique.
 */

/**
 * 
 * Cette classe permet de facilier la connexion � la
 * base de donn�e que nous utilisons. Elle s'occupe de
 * g�rer non seulement l'insertion et la lecture de donn�es,
 * mais aussi de cr�er et d�truire la base de donn�es au
 * besoin. Cette fonctionnalit� est standardis�e, ce qui
 * la rend r�utilisable dans d'autres classes d'Android,
 * par exemple un ContentProvider.
 *
 */
public class DBHelperTourney extends SQLiteOpenHelper {

	/*
	 * La version est un entier obligatoire qui indique
	 * la version de la *structure* de la base de donn�es.
	 * � chaque fois que celle-ci est chang�e, on doit
	 * incr�menter la version.
	 * 
	 * Un changement structurel peut �tre, par exemple,
	 * ajouter ou enlever des colonnes, changer la d�finition
	 * ou les propri�t�s d'une colonne, etc.
	 */
	static final int VERSION=2;

	static final String TABLE = "meteo";

	/*
	 * �num�ration des noms de colonne. La colonne
	 * ID est requise pour l'usage de la base de donn�es
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
		// Une application conserve toutes ses donn�es priv�es
		// dans /data/data/<package>/.
		// La base de donn�e est donc dans
		// /data/data/iro.ift2905.bddmeteo/databases (Voir DDMS)
		super(context, "meteo.db", null, VERSION);
		this.context=context;
	}

	/*
	 * onCreate est appel�e lorsqu'on cr�e la base de donn�es.
	 * Tous les appels d'initialisation, par exemple la cr�ation
	 * de tables, doivent �tre effectu�s ici.
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Toast.makeText(context, "Cr�ation BDD", Toast.LENGTH_LONG).show();
		Log.d("DBHelper", "Cr�ation BDD");
		
		// Appel standard pour cr�er une table dans la base de donn�es.
		String sql = "create table " + TABLE + " ("
				+ C_ID +" integer primary key, "
				+ C_GAMECODE + " text,"
				+ C_ELIMINATION + " text)";


		// ExecSQL prend en entr�e une commande SQL et l'ex�cute
		// directement sur la base de donn�es.
		db.execSQL(sql);
	}

	/*
	 * onUpgrade est appel�e lorsque l'application d�tecte une
	 * base de donn�es avec une version plus ancienne que celle
	 * actuellement utilis�e. Ceci peut arriver si, par exemple,
	 * on cr�e une mise � jour pour l'application qui change la
	 * structure de la base de donn�es et donc la version; tous
	 * les usagers ayant d�j� utilis� l'application avant cette
	 * mise � jour auront l'ancienne version de la structure et
	 * devront �tre mis � jour.
	 * 
	 * Notez qu'en g�n�ral, on souhaitera conserver les donn�es
	 * dans la base de donn�es en effectuant cette mise � jour.
	 * L'exemple ici se contente de tout effacer, mais ce comportement
	 * n'est d�sirable que si la base de donn�es ne sert que de cache
	 * et donc ne contient que des valeurs temporaires.
	 * 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int ancienneVersion, int nouvelleVersion) {
		Toast.makeText(context, "Mise � jour BDD", Toast.LENGTH_LONG).show();
		Log.d("DBHelper", "Mise � jour BDD");
		
		// Efface l'ancienne base de donn�es
		db.execSQL("drop table if exists "+TABLE);
		// Appelle onCreate, qui recr�e la base de donn�es
		onCreate(db);
	}

	/*
	 * Cette m�thode a �t� cr��e sp�cifiquement pour la base de donn�e
	 * pr�sente et permet d'ajouter un �l�ment � celle-ci.
	 * 
	 */
	public void addNewEntry(String temperature, String conditions,long heure) {
		// Malgr� le nom "get", la m�thode getWritableDatabase fait plus
		// que simplement retourner une r�f�rence; elle ouvre une
		// connexion � la base de donn�es. Il est donc imp�ratif d'appeler
		// db.close() � la fin du processus.
		SQLiteDatabase db = this.getWritableDatabase();
		
		// ContentValues est un objet utilitaire permettant
		// de stocker des donn�es � ins�rer dans une base
		// de donn�es.
		ContentValues val = new ContentValues();
		val.clear();
		
		// La valeur ID *doit* �tre unique; on prend ici le temps en milisecondes.
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
	 * M�thode utilitaire qui retourne le nombre
	 * d'�l�ments dans la base de donn�es.
	 * 
	 */
	public int querySize() {
		// Notez l'usage de getReadableDatabase; lorsqu'on ne
		// cherche pas � modifier la base de donn�es, il
		// est recommand� d'utiliser une connexion lecture seule.
		SQLiteDatabase db = this.getReadableDatabase();

		// Cursor est un objet tr�s utilis� qui permet de stocker
		// le r�sultat d'une requ�te SQL. Ce r�sultat peut �tre,
		// dans le cas pr�sent, la colonne ID de toutes les lignes
		// de la base de donn�e, ce qui nous permet de les compter.
		Cursor c = db.query(TABLE, new String[] {C_ID}, null, null, null, null, null);
		int size = c.getCount();
		db.close();
		return size;
	}


}
