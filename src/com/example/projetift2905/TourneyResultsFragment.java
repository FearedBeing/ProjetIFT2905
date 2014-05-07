package com.example.projetift2905;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.projetift2905.HTMLParser.MatchData;

public class TourneyResultsFragment extends Fragment{
	
	private String bracketType, roundType, tourneyId;
	private HTMLParser parser;
	private List<MatchData> dataList;
	private ListView mainList;
	private ResultsAdapter mainAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_tourney_results, container, false);
        Bundle args = getArguments();
        this.tourneyId = args.getString("tourneyId");
        this.bracketType = args.getString("bracket");
        this.roundType = args.getString("round");
        this.parser = new HTMLParser(args.getString("html"));
        
        this.dataList = this.parser.getMatchDataPerRound(this.bracketType, this.roundType);
        this.mainAdapter = new ResultsAdapter(rootView.getContext(), this.dataList);
        this.mainList = (ListView) rootView.findViewById(R.id.list_results);
        this.mainList.setAdapter(this.mainAdapter);
        this.mainList.setOnItemClickListener(new MainListOnItemClick());
        
        return rootView;
    }
	
	private class MainListOnItemClick implements OnItemClickListener{
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			/*Intent i = new Intent(getActivity().getApplicationContext(), DetailsTournoi.class);
			System.out.println("dataList.get(position).tourneyID: "+dataList.get(position).tourneyID);
			i.putExtra("TourneyID", dataList.get(position).tourneyID);
			startActivity(i);*/
		}
	}

}
