package it.polito.tdp.PremierLeague.model;

import java.util.PriorityQueue;

import it.polito.tdp.PremierLeague.model.Evento.EvenType;

public class Simulator {
	
	//Eventi
	private PriorityQueue<Evento> queue = new PriorityQueue<Evento>();
	
	//Paraetri iniziali
	private Integer N;
	private int infortuni;
	
	
	//Stato del sistema 
	Match m;
	
	
	//da restituire
	//attributi di Match
	
	
	public void setN(Integer N) {
		this.N = N;
	}
	
	public void aumentaInfortuni(int i) {
		this.infortuni += i;
	}
	
	public void run(Match m, int N) {
		this.m = m;
		m.azzeraSimulazione();
		this.queue = new PriorityQueue<>();
		
		infortuni = 0;
		int contatore = 0;
		do {
			infortuni = 0;
			for(int i=contatore; i<N; i++) {
				Double perc = Math.random();
				if(perc < 0.5) {
					this.queue.add(new Evento(EvenType.GOAL, i));
				}
				else if(perc < 0.8) {
					this.queue.add(new Evento(EvenType.ESPULSIONE, i));
				}
				else {
					this.queue.add(new Evento(EvenType.INFORTUNIO, i));
					Double perc1 = Math.random();
		        	if(perc1 < 0.5) {
		        		this.aumentaInfortuni(2);
		        	}
		        	else {
		        		this.aumentaInfortuni(3);
		        	}
				}
			}
			contatore = N;
			
			N = infortuni + contatore;
			
		}while(infortuni > 0);
		
		
		
		while(!this.queue.isEmpty()) {  //finchè la coda non è vuota
			Evento e = this.queue.poll();    //estrai l'ultimo evento--> di regola potrebbe restituire anche null --> ma per via della condizione nel while è impossibile
			System.out.println(e);
			processEvent(e);
		}
		
	}

	
	
	
	
	private void processEvent(Evento e) {
		switch (e.getType()) {
		case GOAL:
			
			if(m.getEspulsioniAway() > m.getEspulsioniHome()) {
				m.segnaTeamHome();
			}
			else if(m.getEspulsioniAway() < m.getEspulsioniHome()) {
				m.segnaTeamAway();
			}
			else {
				Team t = m.getGiocatoreMigliore().getTeam();   //team del giocatore migliore
				if(t.getTeamID() == m.getTeamAwayID()) {
					m.segnaTeamAway();
				}
				else {
					m.segnaTeamHome();
				}
			}
			break;
			
        case ESPULSIONE:
        	Double perc = Math.random();
        	Team t = m.getGiocatoreMigliore().getTeam();
        	if(perc < 0.6) {
        		if(t.getTeamID() == m.getTeamAwayID()) {
        			m.espulsioneAway();
        		}
        		else {
        			m.espulsioneHome();
        		}
        	}
        	else {
        		if(t.getTeamID() == m.getTeamAwayID()) {
        			m.espulsioneHome();
        		}
        		else {
        			m.espulsioneAway();
        		}
        	}
			break;
			
        case INFORTUNIO:
        	
			
			break;

		default:
			break;
		}
		
	}
	
	public int nGoalHome() {
		return this.m.getGoalHome();
	}
	
	public int nGoalAway() {
		return this.m.getGoalAway();
	}
	
	public int nEspulsiHome() {
		return this.m.getEspulsioniHome();
	}
	
	public int nEspulsiAway() {
		return this.m.getEspulsioniAway();
	}
	
	
	
	

}
