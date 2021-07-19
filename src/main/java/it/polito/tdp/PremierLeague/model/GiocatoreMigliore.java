package it.polito.tdp.PremierLeague.model;

public class GiocatoreMigliore {
	
	Player p;
	Double delta;
	Team team;
	
	public GiocatoreMigliore(Player p, Double delta, Team team) {
		super();
		this.p = p;
		this.delta = delta;
		this.team = team;
	}
	
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public Double getDelta() {
		return delta;
	}
	public void setDelta(Double delta) {
		this.delta = delta;
	}

	@Override
	public String toString() {
		return "GiocatoreMigliore [p=" + p + ", delta=" + delta + "]";
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	
	
	
	
	

}
