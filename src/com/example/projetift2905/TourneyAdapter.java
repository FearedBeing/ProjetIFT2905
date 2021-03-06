package com.example.projetift2905;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetift2905.SelectionTournoiAPI.TourneyData;

/*
 * TourneyAdapter est le custom adapter qui g�re le listView contenu dans MainPagerFragment.
 * TourneyAdapter est donc n�cessaire � MainActivity.
 */
public class TourneyAdapter extends BaseAdapter{	
	
	private Context context;
	private List<TourneyData> data;
	boolean favoriOnly, ownedOnly;
	String[] filtres;
	MainActivity activity;
	
	public TourneyAdapter(Context context, List<TourneyData> data, boolean favoriOnly, boolean ownedOnly, MainActivity activity){
		this.context = context;
		this.data = data;
		this.favoriOnly = favoriOnly;
		this.ownedOnly = ownedOnly;
		this.filtres = activity.getFiltres();
		this.activity = activity;
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
		final TourneyData tourneyData = data.get(position); // final pour acceder dans les classes internes
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(checkIfVisible(tourneyData)){
			// ELEMENT VISIBLE
			view = inflater.inflate(R.layout.tournoi_element, parent, false);
			final SaveLoad favorites = new SaveLoad(this.context); // final pour acceder dans les classes internes
			
			
			TextView nomTournoi = (TextView) view.findViewById(R.id.nomTournoi);
			nomTournoi.setText(tourneyData.title);
			
			ImageView gameLogo = (ImageView) view.findViewById(R.id.LogoImage);
			gameLogo.setBackgroundDrawable(tourneyData.gameLogo); // voir http://stackoverflow.com/questions/5454491/what-is-the-difference-between-src-and-background-of-imageview

			ImageButton buttonFavori = (ImageButton) view.findViewById(R.id.favoriButton);
			if(favorites.hasFavorite(tourneyData.tourneyID)) buttonFavori.setSelected(true);
			buttonFavori.setOnClickListener(new OnClickListener(){
				public void onClick(View button){
					if(button.isSelected()){
						// TOGGLE OFF
						button.setSelected(false);
						favorites.removeFavorite(tourneyData.tourneyID);
					}else{
						// TOGGLE ON
						button.setSelected(true);
						favorites.addFavorite(tourneyData.tourneyID);
					}
					activity.notifyFragmentDataSetChanged(); // Passe le changement a tous les fragments
				}
			});
			
			ImageView owner = (ImageView) view.findViewById(R.id.ownedImage);
			owner.setSelected(tourneyData.owner);
			
			
		}else{
			// ELEMENT INVISIBLE
			view = inflater.inflate(R.layout.null_layout, parent, false);
		}
		
		return view; 
		
	}
	
	private boolean checkIfVisible(TourneyData data){
		if(!data.title.contains(filtres[0])) return false;
		if(!data.game.contains(filtres[1])) return false;
		if(this.favoriOnly){
			if(!new SaveLoad(this.context).hasFavorite(data.tourneyID)) return false;	
		}
		if(this.ownedOnly){
			if(!data.owner) return false;
		}
		return true;
	}
	
	public void setFiltres(String[] filtres){
		this.filtres = filtres;
	}

	
}
