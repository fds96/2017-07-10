package it.polito.tdp.artsmia.model;

import java.util.*;

public class RisultatoCercaCammino {
	
	private List<ArtObject> listaBest;
	private double pesoBest;
	public RisultatoCercaCammino(List<ArtObject> listaBest, double pesoBest) {
		super();
		this.listaBest = listaBest;
		this.pesoBest = pesoBest;
	}
	public List<ArtObject> getListaBest() {
		return listaBest;
	}
	public void setListaBest(List<ArtObject> listaBest) {
		this.listaBest = listaBest;
	}
	public double getPesoBest() {
		return pesoBest;
	}
	public void setPesoBest(double pesoBest) {
		this.pesoBest = pesoBest;
	}
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("L'insieme con peso massimo (pari a %.1f) è:\n", this.pesoBest));
		for(ArtObject a : this.listaBest)
			str.append(a+"\n");
		return str.toString();
	}
	
	

}
