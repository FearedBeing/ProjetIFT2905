package com.example.projetift2905;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.projetift2905.SelectionTournoiAPI.TourneyData;

/*
 * MainPagerFragment contient les fragments affich�s par la MainActivity. Elle contient principalement le listView qui affiche la liste de tournois.
 * MainPagerFragment est �videmment utilis� par MainActivity.
 */
public class MainPagerFragment extends Fragment{
	
	private List<TourneyData> dataList;
	private ListView mainList;
	private TourneyAdapter mainAdapter;
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle args = getArguments();
        
        boolean favoriOnly = args.getInt("id") == 1;
        boolean ownedOnly = args.getInt("id") == 2; 
        
        this.dataList = new ArrayList<TourneyData>();
        this.mainAdapter = new TourneyAdapter(rootView.getContext(), this.dataList, favoriOnly, ownedOnly, ((MainActivity)getActivity()));
        this.setTourneyData();
        this.mainList = (ListView) rootView.findViewById(R.id.listTournois);
        this.mainList.setAdapter(mainAdapter);
        this.mainList.setOnItemClickListener(new MainListOnItemClick());
        
        return rootView;
    }
    
    public void notifyDataSetChanged(){
    	this.mainAdapter.notifyDataSetChanged();
    }
    
    public void setFiltres(String[] filtres){
    	this.mainAdapter.setFiltres(filtres);
    }
    
    public void setTourneyData(){
    	MainActivity activity = (MainActivity)this.getActivity();
    	SelectionTournoiAPI api = activity.getApi();
    	if(api != null){
    		this.dataList.clear();
    		this.dataList.addAll(api.dataList);
    		this.mainAdapter.notifyDataSetChanged();
    	}
    }
    
    private class MainListOnItemClick implements OnItemClickListener{
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			Intent i = new Intent(getActivity().getApplicationContext(), DetailsTournoi.class);
			System.out.println("dataList.get(position).tourneyID: "+dataList.get(position).tourneyID);
			i.putExtra("TourneyID", dataList.get(position).tourneyID);
			i.putExtra("owned", dataList.get(position).owner);
			startActivity(i);
		}
	}
    
}    