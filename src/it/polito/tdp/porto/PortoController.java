/**
 * Sample Skeleton for 'Porto.fxml' Controller Class
 */

package it.polito.tdp.porto;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxPrimo"
    private ComboBox<Author> boxPrimo; // Value injected by FXMLLoader

    @FXML // fx:id="boxSecondo"
    private ComboBox<Author> boxSecondo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrova"
    private Button btnTrova; // Value injected by FXMLLoader

    @FXML // fx:id="btnSequenza"
    private Button btnSequenza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCoautori(ActionEvent event) {
    	if(btnTrova.isArmed()) {
    		txtResult.clear();
    		Author a=boxPrimo.getValue();
    		if(a==null) {
    			txtResult.appendText("autore non scelto");
    			return;
    		}
    		else {
    			model.creaGrafo();
    			txtResult.appendText("i co-autori di "+a.getFirstname()+" "+a.getLastname()+" sono: \n");
    			try {
    			for(Author x:model.coautori(a))
    				txtResult.appendText(x.getFirstname()+" "+x.getLastname()+" \n");
    			}catch(NullPointerException e) {
    				txtResult.appendText("non ci sono co-autori");
    				return;
    			}
    		}
    	}

    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnSequenza != null : "fx:id=\"btnSequenza\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		List<Author> lista=model.tuttiAutori();
		Collections.sort(lista);
		boxPrimo.getItems().setAll(lista);
	}
    
}
