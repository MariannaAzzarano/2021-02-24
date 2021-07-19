
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.GiocatoreMigliore;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	Match match = cmbMatch.getValue();
    	if(match == null) {
    		txtResult.appendText("DEVI PRIMA SELEZIONARE UN MATCH!!");
    		return;
    	}
    	model.creaGrafo(match);
    	txtResult.appendText("GRAFO CREATO!!");
    	txtResult.appendText("\n# VERTICI : " + model.nVertici());
    	txtResult.appendText("\n# ARCHI : "  + model.nArchi());
    	
    	
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {    
    	if(model.getGrafo() != null) {
    		Match m = cmbMatch.getValue();
    		GiocatoreMigliore gm = model.getGiocatoreMigliore(m);
    		txtResult.appendText("\n\n" + model.getGiocatoreMigliore(m));
    		
    	}
    	else {
    		txtResult.clear();
    		txtResult.appendText("DEVI PRIMA CREARE IL GRAFO!!");
    	}
    	
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	if(model.getGrafo() != null) {
    		Match m = cmbMatch.getValue();
    		Integer N = Integer.parseInt(txtN.getText());
    		if(N != null && m != null) {
        		model.simula(m, N);
        		txtResult.appendText("GOAL:  " + m.getTeamHomeNAME() +" - " + m.getTeamAwayNAME() + "    " + m.getGoalHome() + " - " + m.getGoalAway());
        		txtResult.appendText("\nESPULSIONI:  " + m.getTeamHomeNAME() +" - " + m.getTeamAwayNAME() + "    " + m.getEspulsioniHome() + " - " + m.getEspulsioniAway());
        	}
    	}
    	
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbMatch.getItems().addAll(model.getMatches());
    }
}
