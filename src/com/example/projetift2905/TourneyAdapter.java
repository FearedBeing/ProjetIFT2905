package com.example.projetift2905;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetift2905.SelectionTournoiAPI.TourneyData;

public class TourneyAdapter extends BaseAdapter{	
	
	private Context context;
	private List<TourneyData> data;
	boolean favoriOnly, ownedOnly;
	String[] filtres;
	
	public TourneyAdapter(Context context, List<TourneyData> data, boolean favoriOnly, boolean ownedOnly, String[] filtres){
		this.context = context;
		this.data = data;
		this.favoriOnly = favoriOnly;
		this.ownedOnly = ownedOnly;
		this.filtres = filtres;
	}
	
	@Override
	public int getCount() {
		if(data != null){
			return data.size();
		}else{
			return 0;
		}
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
		TourneyData tourneyData = data.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(checkIfVisible(tourneyData)){
			// ELEMENT VISIBLE
			view = inflater.inflate(R.layout.tournoi_element, parent, false);
			
			TextView nomTournoi = (TextView) view.findViewById(R.id.nomTournoi);
			nomTournoi.setText(tourneyData.title);
			
			ImageView gameLogo = (ImageView) view.findViewById(R.id.LogoImage);
			gameLogo.setBackgroundDrawable(tourneyData.gameLogo); // voir http://stackoverflow.com/questions/5454491/what-is-the-difference-between-src-and-background-of-imageview
			// DOIT AJOUTER AVEC VIEWS DU LAYOUT
			
		}else{
			// ELEMENT INVISIBLE
			view = inflater.inflate(R.layout.null_layout, parent, false);
		}
		
		return view;
		
	}
	
	private boolean checkIfVisible(TourneyData data){
		if(!data.title.contains(filtres[0])) return false;
		if(!data.game.contains(filtres[1])) return false;
		return true;
	}
	
	public void setFiltres(String[] filtres){
		this.filtres = filtres;
		this.notifyDataSetChanged();
	}

	
}
