package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private ArtObjectIdMap artobjectIdMap;
	private ExhibitionIdMap exhibitionIdMap;
	private List<ArtObject> artObjects;
	private List<Exhibition> exhibitions;
	private Graph<ArtObject,DefaultWeightedEdge> graph;
	private List<ArtObject> listaBest;
	private double pesoBest;
	
	public Model() {
		dao = new ArtsmiaDAO();
		artobjectIdMap = new ArtObjectIdMap();
		exhibitionIdMap = new ExhibitionIdMap();
		artObjects = new ArrayList<>(dao.listObjects(artobjectIdMap));
		exhibitions = new ArrayList<>(dao.listExhibitions(exhibitionIdMap));
		for(Exhibition e : this.exhibitions)
			e.setArtobjects(new ArrayList<>(dao.listObjectsFromExhibition(e, artobjectIdMap)));
	}
	
	public List<Integer> listObjectId() {
		return dao.listObjectId();
	}
	
	public void analizzaOggetti() {
		graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.artObjects);
		
		for(Exhibition e :this.exhibitions) {
			for(int i=0; i<e.getArtobjects().size();i++) {
				for (int j=i+1; j<e.getArtobjects().size();j++) {
					ArtObject a1 = e.getArtobjects().get(i);
					ArtObject a2 = e.getArtobjects().get(j);
					
					if(this.graph.containsEdge(a1, a2)) {	//Se il grafo già contiene l'arco incremento il suo peso
						DefaultWeightedEdge edge = this.graph.getEdge(a1, a2);
						this.graph.setEdgeWeight(edge, this.graph.getEdgeWeight(edge)+1);
					}
					
					else {	//altrimenti creo un nuovo arco con peso pari a 1
						Graphs.addEdge(this.graph, a1, a2, 1);
					}
					
				}
			}
		}
		
//		System.out.println(this.graph.vertexSet().size());
//		System.out.println(this.graph.edgeSet().size());
	}
	
	public Set<ArtObject> componenteConnessa(int artObjectId) {
		ConnectivityInspector<ArtObject,DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.graph);
		return ci.connectedSetOf(this.artobjectIdMap.get(artObjectId));
	}
	
	public RisultatoCercaCammino cercaCammino(int artObjectId, int lunghezza) {
		
		ArtObject start =this.artobjectIdMap.get(artObjectId);
		
		pesoBest=Double.MIN_VALUE;
		listaBest= new ArrayList<>();
		
		List<ArtObject> parziale = new ArrayList<>();
		parziale.add(start);
		
		this.recursive(parziale, 1, lunghezza);
		
		return new RisultatoCercaCammino(listaBest,pesoBest);
	}

	private void recursive(List<ArtObject> parziale, int livello, int lunghezza) {
		
		if(livello==lunghezza) {
			this.calcolaPunteggio(parziale);
			return;
		}
		
		for(ArtObject o : Graphs.neighborListOf(this.graph, parziale.get(parziale.size()-1))) {
			if((!parziale.contains(o)) && o.getClassification().equals(parziale.get(parziale.size()-1).getClassification()) ) {
				parziale.add(o);
				this.recursive(parziale, livello+1, lunghezza);
				parziale.remove(parziale.size()-1);
			}
		}
	}

	private void calcolaPunteggio(List<ArtObject> parziale) {
		double peso=0.0;
		
		for(int i=0; i<parziale.size()-1; i++) {
			peso+=this.graph.getEdgeWeight(this.graph.getEdge(parziale.get(i), parziale.get(i+1)));
		}
		
		if(peso>pesoBest) {
			pesoBest=peso;
			listaBest = new ArrayList<>(parziale);
		}
	}

	
}
