package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	
	Model model; 
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInserisci;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private Button btnReset;

    @FXML
    void doAnagrammi(ActionEvent event) {

    	this.txtCorretti.clear();
    	String parola = this.txtInserisci.getText();
    	
    	List<String> risultatoCorretto = new ArrayList<String>();
    	risultatoCorretto = this.model.anagrammiCorretti(parola);
    	for (String s : risultatoCorretto) {
    		this.txtCorretti.appendText(s+"\n");
    	}
    	
    	List<String> risultatoSbagliato = new ArrayList<String>();
    	risultatoSbagliato = this.model.anagrammiSbagliati(parola);
    	for (String s : risultatoSbagliato) {
    		 this.txtErrati.appendText(s+"\n");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtInserisci.clear();
    	txtCorretti.clear();
    	txtErrati.clear();

    }

    public void setModel (Model model) {
    	this.model = model;
    	
    }
    
    @FXML
    void initialize() {
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
