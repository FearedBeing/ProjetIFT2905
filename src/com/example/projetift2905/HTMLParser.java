package com.example.projetift2905;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class HTMLParser{
	private Document mainDoc;
	
	public HTMLParser(String html){
		if(html.length() < 25){
			// Tourney ID
			try {
				this.mainDoc = Jsoup.connect("http://www.binarybeast.com/" + html).get();
			} catch (IOException e) {
				Log.e("DATA","[HTMLParser] Erreur HTTP (IO) :"+e.getMessage());
				e.printStackTrace();
			}
		}else{
			// Texte HTML
			this.mainDoc = Jsoup.parse(html);
		}
		
		
	}
	
	public ArrayList<String> getBracketTypes(){
		ArrayList<String> retour = new ArrayList<String>();
		Elements brackets = mainDoc.select("div.Bracket");
		Iterator<Element> it = brackets.iterator();
		while(it.hasNext()){
			Element elem = it.next();
			String id = elem.id();
			if(!id.equals("") && !id.contains("bracket")){
				if(id.equals("winners")){
					Elements rounds = elem.select("div.Map");
					if(rounds.first().text().equals("3rd Place Match")){
						id = "bronze";
					}
				}
				retour.add(id);
			}
			
		}
		return retour;
	}
	
	public ArrayList<String> getRoundsName(String bracketName){
		ArrayList<String> retour = new ArrayList<String>();
		Element bracket;
		if(bracketName.equals("bronze")){
			bracket = mainDoc.select("div#winners").last();
		}else{
			bracket = mainDoc.select("div#"+bracketName).first();
		}
		Elements rounds = bracket.select("div.Map");
		ListIterator<Element> it = rounds.listIterator();
		while(it.hasNext()){
			Element elem = it.next();
			retour.add(elem.text());
		}
		retour.remove(retour.size()-1); // Car le dernier round ne contient pas de match (montre seulement le gagnant)
		return retour;
	}
	
	public ArrayList<Integer> getBestOfPerRound(String bracketName){
		ArrayList<Integer> retour = new ArrayList<Integer>();
		Element bracket;
		if(bracketName.equals("bronze")){
			bracket = mainDoc.select("div#winners").last();
		}else{
			bracket = mainDoc.select("div#"+bracketName).first();
		}
		Elements bo = bracket.select("div.BestOf");
		ListIterator<Element> it = bo.listIterator();
		while(it.hasNext()){
			Element elem = it.next();
			int num = Integer.parseInt(elem.text().substring(8));
			retour.add(num);
		}
		retour.remove(retour.size()-1); // Car le dernier round ne contient pas de match (montre seulement le gagnant)
		return retour;
	}
	
	public ArrayList<MatchData> getMatchDataPerRound(String bracketName, String roundName){
		ArrayList<MatchData> retour = new ArrayList<MatchData>();
		Element bracket;
		if(bracketName.equals("bronze")){
			bracket = mainDoc.select("div#winners").last();
		}else{
			bracket = mainDoc.select("div#"+bracketName).first();
		}
		Elements allRounds = bracket.select("div.BracketColumn");
		Element round = null;
		ListIterator<Element> i = allRounds.listIterator();
		boolean done = false;
		while(i.hasNext() && !done){
			Element rnd = i.next();
			Log.d("DATA", rnd.select("div.Map").size()+"");
			Element rndName = rnd.select("div.Map").first();
			if(rndName.text().equals(roundName)){
				round = rnd;
				done = true;
			}
		}
		Elements matchs = round.select("div.match");
		ListIterator<Element> it = matchs.listIterator();
		while(it.hasNext()){
			Element match = it.next();
			
			Elements players = match.select("span.DisplayName");
			String player1 = "[À déterminer]";
			if(players.size() >= 1){
				player1 = players.get(0).text();
			}
			String player2 = "[À déterminer]";
			if(players.size() == 2){
				player2 = players.get(1).text();
			}
			
			Elements scores = match.select("span.score");
			int scoreP1 = 0;
			if(!scores.get(0).text().equals("-")){
				scoreP1 = Integer.parseInt(scores.get(0).text());
			}
			int scoreP2 = 0;
			if(!scores.get(1).text().equals("-")){
				scoreP2 = Integer.parseInt(scores.get(1).text());
			}
			
			Element id = match.select("a.view").first();
			String matchId = "0";
			if(id != null){ 
				matchId = id.attr("href").substring(20); 
			}
			retour.add(new MatchData(player1, scoreP1, player2, scoreP2, matchId));
		}
		return retour;
	}
	
	public String getHTML(){
		return mainDoc.html();
	}
	
	public class MatchData{
		public final String player1, player2, matchID, scoreP1, scoreP2;
		
		public MatchData(String player1, int scoreP1, String player2, int scoreP2, String matchID){
			this.player1 = player1;
			this.scoreP1 = scoreP1 + "";
			this.player2 = player2;
			this.scoreP2 = scoreP2 + "";
			this.matchID = matchID;
		}
	}
}
