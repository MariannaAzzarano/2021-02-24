package it.polito.tdp.PremierLeague.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Match m = model.getMatch(1);
		
		model.creaGrafo(m);
		System.out.println("GRAFO CREATO!!");
		System.out.println("# VERTICI: " + model.nVertici());
		System.out.println("# ARCHI: " + model.nArchi());
		
		GiocatoreMigliore g = model.getGiocatoreMigliore(m);
		System.out.println("\nGiocatore migliore : " +g);
		
		model.simula(m, 3);
		System.out.println("\nGOAL:" + m.getGoalHome() +" - " + m.getGoalAway());
		System.out.println("ESPULSIONI:" + m.getEspulsioniHome() +" - " + m.getEspulsioniAway());
	}

}
