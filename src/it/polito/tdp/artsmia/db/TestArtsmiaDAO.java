package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.ArtObjectIdMap;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ExhibitionIdMap;

public class TestArtsmiaDAO {

	public static void main(String[] args) {

		ArtsmiaDAO dao = new ArtsmiaDAO();
		ArtObjectIdMap omap = new ArtObjectIdMap();
		ExhibitionIdMap emap = new ExhibitionIdMap();
			
		List<ArtObject> objects = dao.listObjects(omap);
		System.out.println(objects.get(0));
		System.out.println(objects.size());
		
		List<Exhibition> e = dao.listExhibitions(emap);
		System.out.println(e.get(0));
		System.out.println(e.size());
		
		List<ArtObject> eobj = dao.listObjectsFromExhibition(e.get(1), omap);
		System.out.println(eobj.get(0));
		System.out.println(eobj.size());
	}

}
