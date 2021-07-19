package it.polito.tdp.PremierLeague.model;

public class Evento implements Comparable<Evento>{
	
	public enum EvenType {
		GOAL,
		ESPULSIONE, 
		INFORTUNIO
	}
	
	private EvenType type;
	private int num;

	public Evento(EvenType type, int i) {
		super();
		this.type = type;
		this.num = i;
	}

	public EvenType getType() {
		return type;
	}

	public void setType(EvenType type) {
		this.type = type;
	}

	@Override
	public int compareTo(Evento o) {
		return this.num-o.num;
	}
	
	
	

}
