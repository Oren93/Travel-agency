/**
 * 
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

/**
 * @author Oren1
 *
 */
public class Controller implements Initializable {
	@FXML
	private Label lbl;
	private long no1 = 0;
	private String op = "";
	private boolean start = true;

	@FXML
	public void proccessData(ActionEvent e) {
	}
	
	public void proccess(ActionEvent e) {
		
	}

	@FXML
	private double calculate() {
		return 0;
	}

	protected static Parameters[] search(Parameters p) {
		return null;
	}

	@FXML
	public ChoiceBox<String> choiceBox;
	ObservableList<String> list = FXCollections.observableArrayList("Easy","Moderate","Hard");
	public ChoiceBox<String> choiceDeparture;
	ObservableList<String> airporList = FXCollections.observableArrayList(
			"Reykjavík","Akureyri","Egilstaðir","Ísafjörður");
	public ChoiceBox<String> choiceDestination;
	ObservableList<String> travelDestination = FXCollections.observableArrayList(
			"South-west","North","East fjords","west fjords");
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBox.setItems(list);	
		choiceBox.setOnAction((event) -> {
		    int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + choiceBox.getValue());
		});
		choiceDeparture.setItems(airporList);	
		choiceDeparture.setOnAction((event) -> {
		    int selectedIndex = choiceDeparture.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceDeparture.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + choiceDeparture.getValue());
		});
		choiceDestination.setItems(travelDestination);	
		choiceDestination.setOnAction((event) -> {
		    int selectedIndex = choiceDestination.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceDestination.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + choiceDestination.getValue());
		});
	}
	
}
