/**
 * 
 */
package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * @author Oren1
 *
 */
public class Controller implements Initializable {
	// Variables
	final private int MIN_PRICE = 1000;
	final private int MAX_PRICE = 1000000;
	final private int GROUOP_SIZE = Parameters.MAXGROUP;
	
	private boolean handicappedAssistanceRequired = false;
	private int difficultyLevel;
	
	
	
	@FXML
	private VBox SearchPage;
	private VBox ConfirmationPage;
	private Label lbl;
	private long no1 = 0;
	private String op = "";
	private boolean start = true;
	@FXML
	private DatePicker fromDate, toDate;

	@FXML
	/**
	 * Fetch the data from the user when clicking "search" and construct a parameter 
	 * object with this data if that is possible
	 * @param e actionEvent
	 */
	public void proccessInput(ActionEvent e) {
		int priceRange [] = new int[2];
		int groupS;
		LocalDate dateRange [] = new LocalDate[2];
		SpinnerValueFactory <Integer> sf; 
		try {
			priceRange[0] = priceMin.getValueFactory().getValue();
			priceRange[1] = priceMax.getValueFactory().getValue();
			groupS = GroupSize.getValueFactory().getValue();

			dateRange[0] = fromDate.getValue();
			dateRange[1] = toDate.getValue();
			// Tester: -----------------------------------------------------
			System.out.println(dateRange[0].getDayOfMonth()+"/"+dateRange[0].getMonthValue());
			System.out.println(dateRange[1].getDayOfMonth()+"/"+dateRange[1].getMonthValue());

			int selectedAirportDeparture = choiceDeparture.getSelectionModel().getSelectedIndex()+1;
			int selectedAirportDestination = choiceDestination.getSelectionModel().getSelectedIndex()+1;
			if (difficultyLevel < Parameters.EASY)
				difficultyLevel = Parameters.HANDICAP;
			Parameters searchParam = new Parameters (difficultyLevel,
					priceRange, groupS, dateRange,selectedAirportDeparture, selectedAirportDestination);
			System.out.println(searchParam.toString()); // temporary, for testing purposes
			/* HERE need to call a search mehtod with the Parameters object searchParam */
		} catch (Error e1) {
			/* Need to pop up a window to indicate what is the error */		
		}
		// These following to lines are temporary. ConfirmPage should appear either 
		// after the search result yields results or after selecting a package 
		boolean visibile = ConfirmPage.isVisible();
		ConfirmPage.setVisible(!visibile);
		ConfirmPage.setPrefHeight(visibile? 270 : 0); // NOT WORKING AS PLANNED
		
	}
	
	// This will process packages selection or confirmation
	public void proccess(ActionEvent e) {
		
	}

	// Can't remember why this is here, probably will be deleted
	@FXML
	private double calculate() {
		return 0;
	}

	@FXML
	//private Button SearchBtn;
	private Pane ConfirmPage;
	private VBox MainScene;
	public CheckBox handicapCheckbox;
	@FXML
	public ChoiceBox<String> difficultyChoose;
	ObservableList<String> list = FXCollections.observableArrayList("Easy","Moderate","Hard");
	public ChoiceBox<String> choiceDeparture;
	ObservableList<String> airporList = FXCollections.observableArrayList(
			"Reykjavík","Akureyri","Egilstaðir","Ísafjörður");
	public ChoiceBox<String> choiceDestination;
	ObservableList<String> travelDestination = FXCollections.observableArrayList(
			"South-west","North","East fjords","west fjords");
	@FXML
	private Spinner <Integer> priceMin, priceMax, GroupSize;
	//@FXML 
	//private Spinner ;
	//@FXML
	//private Spinner <Integer> ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		handicapCheckbox = new CheckBox();
		//priceMin = new Spinner<Integer>();
		//priceMax = new Spinner<Integer>();

        SpinnerValueFactory<Integer> valueFactory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRICE, MAX_PRICE, MIN_PRICE,1000);
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRICE, MAX_PRICE, MAX_PRICE,1000);
        SpinnerValueFactory<Integer> valueFactory3 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        priceMin.setValueFactory(valueFactory1);
        priceMax.setValueFactory(valueFactory2);
        GroupSize.setValueFactory(valueFactory3);
        //priceMin.setEditable(true); 
        
 		ConfirmPage.setVisible(false);
 		ConfirmPage.setPrefHeight(0);
		
		
 		difficultyChoose.setItems(list);	
		// Set Listener for the choiceBoxes
 		difficultyChoose.setOnAction((event) -> {
		    int selectedIndex = difficultyChoose.getSelectionModel().getSelectedIndex();
		    difficultyLevel = selectedIndex+Parameters.EASY;
		});
		choiceDeparture.setItems(airporList);	
		// This listener is useless
		choiceDeparture.setOnAction((event) -> {
		    int selectedIndex = choiceDeparture.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceDeparture.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + choiceDeparture.getValue());
		});
		choiceDestination.setItems(travelDestination);	
		// this listener is also useless
		choiceDestination.setOnAction((event) -> {
		    int selectedIndex = choiceDestination.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceDestination.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ChoiceBox.getValue(): " + choiceDestination.getValue());
		});
	}
	
	/*
	 * Toggles the boolean variable handicappedAssistanceRequired on/off
	 * and disables the difficulty ChoiceBox, when disabling it gives the
	 * difficulty value a negative value and when enabling it return the 
	 * positive value as before, so the variable difficultyLevel "remembers" 
	 * it's value before disabling the ChoiceBox. That means that every value
	 * smaller than Parameters.EASY represents handicap 
	 */
	private void checkBoxToggle () {
		handicappedAssistanceRequired = !handicappedAssistanceRequired;
		difficultyChoose.setDisable(handicappedAssistanceRequired);
		System.out.println("handicap = "+ handicappedAssistanceRequired);
		difficultyLevel = (-1) * difficultyLevel; // 0 and below is disabled
		System.out.println(difficultyLevel); // recover previous value when unmark the box
			
		
	}	
	
}
