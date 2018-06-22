package it.polito.tdp.artsmia.model;

import java.util.*;

public class ArtObjectIdMap {
	
	private Map<Integer,ArtObject> map;
	
	public ArtObjectIdMap() {
		map = new HashMap<>();
	}
	
	public ArtObject get(Integer id) {
		return map.get(id);
	}
	
	public ArtObject get(ArtObject artObject) {
		ArtObject old = map.get(artObject.getId());
		if(old==null) {
			map.put(artObject.getId(), artObject);
			return artObject;
		}
		return old;
	}
	
	public void put(Integer id, ArtObject artObject) {
		map.put(id, artObject);
	}

}