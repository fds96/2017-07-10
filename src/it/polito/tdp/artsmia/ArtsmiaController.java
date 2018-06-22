/**
 /**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxLUN"
    private ChoiceBox<Integer> boxLUN; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcolaComponenteConnessa"
    private Button btnCalcolaComponenteConnessa; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaOggetti"
    private Button btnCercaOggetti; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizzaOggetti"
    private Button btnAnalizzaOggetti; // Value injected by FXMLLoader

    @FXML // fx:id="txtObjectId"
    private TextField txtObjectId; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaOggetti(ActionEvent event) {
    	try {
    		model.analizzaOggetti();
    		this.btnCalcolaComponenteConnessa.setDisable(false);
    		this.txtObjectId.setDisable(false);
    	}
    	catch(RuntimeException e) {
    		this.txtResult.appendText("Errore DB!\n");
    	}
    }

    @FXML
    void doCalcolaComponenteConnessa(ActionEvent event) {
    	try {
    		if(this.txtObjectId.getText()==null) {
    			this.txtResult.appendText("Inserire un identificativo dell'oggetto nell'apposito spazio!\n");
        		return;
    		}
    		Integer.parseInt(this.txtObjectId.getText());
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserire solamente numeri interi!\n");
    		return;
    	}
    	try {
    		int artObjectId = Integer.parseInt(this.txtObjectId.getText());
    		int result = model.componenteConnessa(artObjectId).size();
    		this.boxLUN.setDisable(false);	
    		this.btnCercaOggetti.setDisable(false);
    		
    		List<Integer> temp = new LinkedList<>();	//Faccio così per risparmiare tempo altrimenti ci mette troppo
    		if(result>8) {
    			for(int i=1;i<=8;i++) 
    			temp.add(i);
    		}
    		else {
    			for(int i=1;i<=result;i++) 
        			temp.add(i);
    		}
    		
    		this.boxLUN.getItems().addAll(temp);
    		this.txtResult.appendText("La dimensione della componente connessa dell'oggetto selezionato è " + result + "\n");
    	}
    	catch(RuntimeException e) {
    		this.txtResult.appendText("Errore DB!\n");
    	}
    }

    @FXML
    void doCercaOggetti(ActionEvent event) {
    	try {
    		if(this.txtObjectId.getText()==null) {
    			this.txtResult.appendText("Inserire un identificativo dell'oggetto nell'apposito spazio!\n");
        		return;
    		}
    		Integer.parseInt(this.txtObjectId.getText());
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserire solamente numeri interi!\n");
    		return;
    	}
    	try {
    		int artObjectId = Integer.parseInt(this.txtObjectId.getText());
    		if(this.boxLUN.getValue()==null) {
    			this.txtResult.appendText("Selezionare un numero!\n");
    			return;
    		}
    		int lunghezza = this.boxLUN.getValue();
    		this.txtResult.appendText(model.cercaCammino(artObjectId, lunghezza).toString());
    	}
    	catch(RuntimeException e) {
    		this.txtResult.appendText("Errore DB!\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxLUN != null : "fx:id=\"boxLUN\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaComponenteConnessa != null : "fx:id=\"btnCalcolaComponenteConnessa\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCercaOggetti != null : "fx:id=\"btnCercaOggetti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnAnalizzaOggetti != null : "fx:id=\"btnAnalizzaOggetti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtObjectId != null : "fx:id=\"txtObjectId\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
	}
}
