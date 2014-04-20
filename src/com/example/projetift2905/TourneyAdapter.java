package com.example.projetift2905;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projetift2905.SelectionTournoiAPI.TourneyData;

public class TourneyAdapter extends BaseAdapter{	
	
	private Context context;
	private List<TourneyData> data;
	
	public TourneyAdapter(Context context, List<TourneyData> data){
		this.context = context;
		this.data = data;
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
		
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.tournoi_element, parent, false);
		}
		
		TextView nomTournoi = (TextView) view.findViewById(R.id.nomTournoi);
		nomTournoi.setText(tourneyData.title);
		
		// DOIT AJOUTER AVEC VIEWS DU LAYOUT
		
		return view;
	}

	
}
