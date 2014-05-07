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
		for(int k=rounds.size()-1; k >= 1; k--){  // jusqu'à k=1 car le dernier round ne contient pas de match (montre seulement le gagnant)
			retour.add("Round of " + (int)(Math.pow(2, k)));
		}
		return retour;
	}
	
	public Integer getBestOf(String bracketName, String roundName){
		ArrayList<Integer> retour = new ArrayList<Integer>();
		int nbJoueurs = Integer.parseInt(roundName.substring(9));
		
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
			retour.add(0,num);
		}
		for(int k=0; k<retour.size(); k++){
			if((int)(Math.pow(2, k)) == nbJoueurs) return retour.get(k);
		}
		return null;
	}
	
	public ArrayList<MatchData> getMatchDataPerRound(String bracketName, String roundName){
		ArrayList<MatchData> retour = new ArrayList<MatchData>();
		Element bracket;
		if(bracketName.equals("bronze")){
			bracket = mainDoc.select("div#winners").last();
		}else{
			bracket = mainDoc.select("div#"+bracketName).first();
		}
		
		Elements matchs = bracket.select("div.match");
		ListIterator<Element> it = matchs.listIterator();
		int nbMatchRound = Integer.parseInt(roundName.substring(9))/2; // Divise par 2 car "Round of X" contient X/2 matchs;
		int nbMatchIterator = matchs.size();
		int winsToWin = (getBestOf(bracketName, roundName)+1)/2;
		while(it.hasNext()){
			Element match = it.next();
			if(nbMatchIterator <= nbMatchRound*2 && nbMatchIterator > nbMatchRound){
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
				
				int winner = 0;
				if(scoreP1 == winsToWin) winner = 1;
				if(scoreP2 == winsToWin) winner = 2;
				retour.add(new MatchData(player1, scoreP1, player2, scoreP2, winner, matchId));
			}
			nbMatchIterator--;
			
		}
		return retour;
	}
	
	public String getHTML(){
		return mainDoc.html();
	}
	
	public class MatchData{
		public final String player1, player2, matchID, scoreP1, scoreP2, winner;
		
		public MatchData(String player1, int scoreP1, String player2, int scoreP2, int winner, String matchID){
			this.player1 = player1;
			this.scoreP1 = scoreP1 + "";
			this.player2 = player2;
			this.scoreP2 = scoreP2 + "";
			this.winner = winner + "";
			this.matchID = matchID;
		}
	}
}
