package com.example.projetift2905;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TournoiAdapter extends BaseAdapter{
	
	public static class TournoiData{
		
		public final boolean favori, proprietaire;
		public final String nom, jeu;
		
		public TournoiData(String nom, String jeu, boolean favori, boolean proprietaire){
			this.nom = nom;
			this.jeu = jeu;
			this.favori = false; // TEMPORAIRE AVANT D'AJOUTER LES FAVORIS
			this.proprietaire = false; // TEMPORAIRE AVANT D'AJOUTER CETTE CATEGORIE
		}
	}
	
	private List<TournoiData> data;
	private Context context;
	

	@Override
	public int getCount() {
		if(data != null)
			return data.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if(data != null && position >= 0 && position < data.size())
			return data.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		TournoiData tourneyData = data.get(position);
		
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.tournoi_element, parent, false);
		}
		
		TextView nomTournoi = (TextView) view.findViewById(R.id.nomTournoi);
		nomTournoi.setText(tourneyData.nom);
		// DOIT AJOUTER AVEC VIEWS DU LAYOUT
		
		return view;
	}

	
}
