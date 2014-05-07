package com.example.projetift2905;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetift2905.HTMLParser.MatchData;

public class ResultsAdapter extends BaseAdapter{
	
	private Context context;
	private List<MatchData> data;
	
	public ResultsAdapter(Context context, List<MatchData> data){
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
		MatchData matchData = data.get(position);
		
		if(view == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.result_element, parent, false);
		}
		
		TextView nameLeft = (TextView)view.findViewById(R.id.left_player_name);
		nameLeft.setText(matchData.player1);
		
		TextView nameRight = (TextView)view.findViewById(R.id.right_player_name);
		nameRight.setText(matchData.player2);
		
		TextView scoreLeft = (TextView)view.findViewById(R.id.left_player_score);
		scoreLeft.setText(matchData.scoreP1);
		
		TextView scoreRight = (TextView)view.findViewById(R.id.right_player_score);
		scoreRight.setText(matchData.scoreP2);
		
		LinearLayout left = (LinearLayout)view.findViewById(R.id.left_player);
		LinearLayout right = (LinearLayout)view.findViewById(R.id.right_player);
		if(matchData.winner.equals("1")){
			left.setBackgroundColor(view.getResources().getColor(R.color.winner_color));
			right.setBackgroundColor(view.getResources().getColor(R.color.white));
		}else if(matchData.winner.equals("2")){
			left.setBackgroundColor(view.getResources().getColor(R.color.white));
			right.setBackgroundColor(view.getResources().getColor(R.color.winner_color));
		}else{
			left.setBackgroundColor(view.getResources().getColor(R.color.white));
			right.setBackgroundColor(view.getResources().getColor(R.color.white));
		}
		
		return view;
	}

}
