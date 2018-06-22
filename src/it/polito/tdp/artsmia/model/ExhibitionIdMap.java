package it.polito.tdp.artsmia.model;

import java.util.*;

public class ExhibitionIdMap {
	
	private Map<Integer,Exhibition> map;
	
	public ExhibitionIdMap() {
		map = new HashMap<>();
	}
	
	public Exhibition get(Integer id) {
		return map.get(id);
	}
	
	public Exhibition get(Exhibition exhibition) {
		Exhibition old = map.get(exhibition.getId());
		if(old==null) {
			map.put(exhibition.getId(), exhibition);
			return exhibition;
		}
		return old;
	}
	
	public void put(Integer id, Exhibition exhibition) {
		map.put(id, exhibition);
	}

}