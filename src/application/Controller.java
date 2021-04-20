/**
 * 
 */
package application;
import daytour.*;
import hotel.HotelController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
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
	Parameters searchParam;
	
	private boolean handicappedAssistanceRequired = false;
	private int difficultyLevel;
	
	
	
	@FXML
	private VBox SearchPage;
	//private VBox ConfirmationPage;
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
		//SpinnerValueFactory <Integer> sf; 
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
			searchParam = new Parameters (difficultyLevel,
					priceRange, groupS, dateRange,selectedAirportDeparture, selectedAirportDestination);
			System.out.println(searchParam.toString()); // temporary, for testing purposes
			/* HERE need to call a search mehtod with the Parameters object searchParam */
			TourController tc = new TourController();
			//ObservableList<Tour> tours = tc.searchTour(searchParam);
			//renderView(tours);
			
		} catch (Error e1) {
			/* Need to pop up a window to indicate what is the error */		
		}
		finally{ // To delete later!!!!!!!!!!!!!!!!!
		// These following to lines are temporary. ConfirmPage should appear either 
		// after the search result yields results or after selecting a package 
		boolean visibile = ConfirmPage.isVisible();
		ConfirmPage.setVisible(!visibile);
		LocalDate foo [] = new LocalDate[] {LocalDate.now().plusDays(1),LocalDate.now().plusDays(8)};
		Parameters searchParam = new Parameters (10,
				new int [] {1000,1000000}, 1, foo, 2,1);
		ObservableList hotelRooms = HotelController.GetHotelRooms(searchParam); 
		TourController tc = new TourController();
		ObservableList<String> list = FXCollections.observableArrayList("Easy","Moderate","Hard");
		//ObservableList<Tour> tours = tc.searchTour(searchParam);
		renderView(list);
		
		}
	}
	
	@FXML
	private Pane ConfirmPage;
	@FXML
	private VBox MainBox;
	public CheckBox handicapCheckbox;
	@FXML
	public ChoiceBox<String> difficultyChoose;
	ObservableList<String> list = FXCollections.observableArrayList("Easy","Moderate","Hard");
	public ChoiceBox<String> choiceDeparture;
	ObservableList<String> airporList = FXCollections.observableArrayList(
			"Reykjavík","Akureyri","Ísafjörður","Egilstaðir");
	public ChoiceBox<String> choiceDestination;
	ObservableList<String> travelDestination = FXCollections.observableArrayList(
			"South-west","North","west fjords","East fjords");

	@FXML
	private Button confirm;
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
 		//ConfirmPage.setPrefHeight(0);
		
		
 		difficultyChoose.setItems(list);	
		// Set Listener for the choiceBoxe
 		difficultyChoose.setOnAction((event) -> {
		    int selectedIndex = difficultyChoose.getSelectionModel().getSelectedIndex();
		    difficultyLevel = selectedIndex+Parameters.EASY;
		});
		choiceDeparture.setItems(airporList);
		choiceDestination.setItems(travelDestination);	
	}
	
	/*
	 * Toggles the boolean variable handicappedAssistanceRequired on/off
	 * and disables the difficulty ChoiceBox, when disabling it gives the
	 * difficulty value a negative value and when enabling it return the 
	 * positive value as before, so the variable difficultyLevel "remembers" 
	 * it's value before disabling the ChoiceBox. That means that every value
	 * smaller than Parameters.EASY represents handicap 
	 */
	@FXML
	private void checkBoxToggle () {
		handicappedAssistanceRequired = !handicappedAssistanceRequired;
		difficultyChoose.setDisable(handicappedAssistanceRequired);
		System.out.println("handicap = "+ handicappedAssistanceRequired);
		difficultyLevel = (-1) * difficultyLevel; // 0 and below is disabled
		System.out.println(difficultyLevel); // recover previous value when unmark the box
			
		
	}	
	@FXML
	private Pane PackagePage;
	
	Accordion accordion;
	/**
	 * Render the view according to the search results
	 * @param ol Observable list of tour packages
	 */
	private void renderView(ObservableList <String> ol) {
		accordion = new Accordion();
		//TitledPane [] tp = new TitledPane [ol.size()];
		double height = MainBox.getHeight()+70*ol.size();
		int i = -1;
		int j = ol.size();
		while (++i < j) {
			Label lbl = new Label(i+". foo \nbar");
			VBox content = new VBox(lbl);
			TitledPane tp = new TitledPane(i+". "+": trip"+(ol.get(i)) , content);
	        accordion.getPanes().add(tp);
/*			ol.forEach((String t) -> {
				System.out.println("bla bla bla "+t);
				Label lbl = new Label(ol.indexOf(t)+". foo \nbar");
				VBox content = new VBox(lbl);
				TitledPane tp = new TitledPane(t+": trip" , content);
		        accordion.getPanes().add(tp);
			});
*/		}
		MainBox.setPrefHeight(height);
 
        PackagePage.getChildren().addAll(accordion);
	}
	

	@FXML
	private TextField fname, lname, kt;
	@FXML
	/**
	 * Get the contact info from the user when clicking the Confirm button
	 * and send over booking requests to the hotel, flight and trips
	 * @param e
	 */
	private void getDetails (ActionEvent e) {
		try {			
			String fNm, lNm, kenitala;
			fNm = fname.getText();
			lNm = lname.getText();
			kenitala = kt.getText();
			flights.Passenger person = new flights.Passenger(kenitala,fNm,lNm);
			TitledPane tp = accordion.getExpandedPane();
			System.out.println(tp.getText());	
			boolean confirmed = HotelController.bookRoom("",searchParam, person);
		} catch (Error e2) {
		}
		/*
		String er = "Error";
		System.out.println(er);
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);

		VBox vbox = new VBox(new Text(er), new Button("Ok."));
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(15));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();	
		*/	
	}
	
}