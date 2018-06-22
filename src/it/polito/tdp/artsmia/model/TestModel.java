package it.polito.tdp.artsmia.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		model.analizzaOggetti();
		
		System.out.println("Ho cominciato");
		//System.out.println(model.listObjectId().size());
		//System.out.println(model.componenteConnessa(8485).size());
		System.out.println(model.cercaCammino(8485, 6));
	}

}
