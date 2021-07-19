package it.polito.tdp.PremierLeague.model;

public class PlayerEfficenza {
	
	Player player;
	Double e;
	Integer matchID;
	
	
	public PlayerEfficenza(Player player, Double e, Integer matchID) {
		super();
		this.player = player;
		this.e = e;
		this.matchID = matchID;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public Double getE() {
		return e;
	}


	public void setE(Double e) {
		this.e = e;
	}


	public Integer getMatchID() {
		return matchID;
	}


	public void setMatchID(Integer matchID) {
		this.matchID = matchID;
	}
	
	
	
	
	
	
	
	
	

}
