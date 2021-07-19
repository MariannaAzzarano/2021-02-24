package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Player, DefaultWeightedEdge> grafo;
	private Map<Integer, Match> mappa_matches;
	private List<Match> matches;
	private Simulator sim;
	
	
	public Model() {
		dao = new PremierLeagueDAO();
		mappa_matches = new HashMap<>();
		matches = dao.listAllMatches();
		for(Match m : matches) {
			mappa_matches.put(m.getMatchID(), m);
		}
		sim = new Simulator();
	}
	
	
	public List<Match> getMatches(){
		Collections.sort(matches, new Comparator<Match>() {

			@Override
			public int compare(Match o1, Match o2) {
				return o1.getMatchID().compareTo(o2.getMatchID());
			}
			
		});
		
		return matches;
	}
	
	
	
	public void creaGrafo(Match m) {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		//estraggo i player la player_efficenza
		List<PlayerEfficenza> player_efficenza = dao.listPlayerEfficenza(m);
		List<Player> players = new ArrayList<>();
		for(PlayerEfficenza pe : player_efficenza) {
			players.add(pe.getPlayer());
		}
		
		//aggiungi i nodi
		Graphs.addAllVertices(this.grafo, players);
		
		
		//bisogna aggiungere gli archi
		for(PlayerEfficenza pe : player_efficenza) {
			for(PlayerEfficenza pe1 : player_efficenza) {
				if(pe1.getPlayer().getTeam().getTeamID() != pe.getPlayer().getTeam().getTeamID() &&
						(!this.grafo.containsEdge(pe.getPlayer(), pe1.getPlayer()) && !this.grafo.containsEdge(pe1.getPlayer(), pe.getPlayer()))) {
					//allora posso creare un arco
					Double peso = Math.abs(pe.getE()-pe1.getE());
					if(pe.getE() > pe1.getE()) {
						//l'arco parte da pe
						Graphs.addEdge(this.grafo, pe.getPlayer(), pe1.getPlayer(), peso);
					}
					else {
						Graphs.addEdge(this.grafo, pe1.getPlayer(), pe.getPlayer(), peso);
					}
				}
			}
		}
	}
	
	
	
	public Integer nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public Integer nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
	
	public GiocatoreMigliore getGiocatoreMigliore(Match m) {
		
		Double delta = 0.0;
		GiocatoreMigliore g = new GiocatoreMigliore(null, 0.0, null);
		Set<Player> nodi = this.grafo.vertexSet();
		for(Player p : nodi) {
			delta = 0.0;
			for(DefaultWeightedEdge edge : this.grafo.outgoingEdgesOf(p)) {
				delta += this.grafo.getEdgeWeight(edge);
			}
			for(DefaultWeightedEdge edge : this.grafo.incomingEdgesOf(p)) {
				delta -= this.grafo.getEdgeWeight(edge);
			}
			if(delta > g.getDelta()) {
				g = new GiocatoreMigliore(p, delta, p.getTeam());
			}
		}
		m.setGiocatoreMigliore(g);
		return g;
	}
	
	
	public Graph<Player, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	
	
	public Match getMatch(Integer ID) {
		return mappa_matches.get(ID);
	}
	
	
	public void simula(Match m, int N) {
		sim.run(m, N);
	}
	
	public int nGoalHome() {
		return sim.nGoalHome();
	}
	
	public int nGoalAway() {
		return sim.nGoalAway();
	}
	
	public int nEspulsiHome() {
		return sim.nEspulsiHome();
	}
	
	public int nEspulsiAway() {
		return sim.nEspulsiAway();
	}
	
	
	
	
	
	
}
