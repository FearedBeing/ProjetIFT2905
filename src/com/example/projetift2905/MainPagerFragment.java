package com.example.projetift2905;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projetift2905.SelectionTournoiAPI.TourneyData;

public class MainPagerFragment extends Fragment{
	
	private List<TourneyData> dataList;
	private ListView mainList;
	private TourneyAdapter mainAdapter;
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle args = getArguments();
        
        this.dataList = new ArrayList<TourneyData>();
        this.mainAdapter = new TourneyAdapter(this.getActivity().getApplicationContext(), this.dataList);
        this.setTourneyData();
        mainList = (ListView) rootView.findViewById(R.id.listTournois);
        mainList.setAdapter(mainAdapter);
        mainList.setOnItemClickListener(new MainListOnItemClick());
        
        return rootView;
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
			switch(position){
			default:
				Toast.makeText(getActivity().getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					
			}
		}
	}
}    