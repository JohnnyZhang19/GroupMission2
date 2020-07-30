package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * This class is a controller which extends PollTrackerController and
 * set the seats and vote of the party people chose.
 * @author Yuzhe Zhou UCID: 30102199
 *
 */

public class EditPollController extends PollTrackerController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
    private Label numberofwholeseats;
	
	@FXML
	private ChoiceBox<String> pollToEdit;

	@FXML
    private Label warninglabel2;
	
	@FXML
    private Label warningLabel;
	
	@FXML
	private Button clear;

	@FXML
	private Button updateParty;

	@FXML
	private TextField projectedSeats;

	@FXML
	private TextField projectedVotes;

	@FXML
	private ChoiceBox<String> partyToUpdate;
	
	
	private float partyseats =1;
	private float partyvotes =1;
	private int userPChoose = 0;
	private int userPartyChoose = 0;
	private float a = 1;
	private float b = 1;
	
	/**
	 * This method can make what people type in 
	 * this textField appear.
	 * @param event What people type. 
	 */
	@FXML
	void textVote(KeyEvent event) {
		a = Float.valueOf(projectedVotes.getText());
		
	}
	
	/**
	 * This method is to refresh all the the people chose and typed which can make
	 * the interface like the initialization.
	 * @param event When people click the button. 
	 */
	@FXML
	void buttonClear(ActionEvent event) {
		refresh();
	}

	/**
	 * This method will update what people typed in textFeild to the data in
	 * "partyToUpdate" chioceBox.
	 * @param event When click the button, the data will be updated.
	 */
	@FXML
	void buttonUpdate(ActionEvent event) { 
		if (userPartyChoose == -1 || userPChoose == -1) {
			return;
		}
			getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[userPartyChoose].setProjectedPercentageOfVotes(a);
			getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[userPartyChoose].setProjectedNumberOfSeats(b);
			
		
		
	}
		 	
	/**
	 * This method can make what people type in 
	 * this textField appear.
	 * @param event What people type in this field.
	 */
	@FXML
	void textSeats(KeyEvent event) {
		b = Float.valueOf(projectedSeats.getText());
	}
	
	/**
	 * Get the number of polls from "Setup Poll Tracker View" class
	 */
	public String[] getuserchoiceofpoll() {
		int length = getPollList().getPolls().length;
		String[] values = new String[length];
		for(int i = 0;i < length;i++) {
			values[i] = getPollList().getPolls()[i].getPollName();
		}
		return values;
	}
	
	/**
	 * Get to number of seats and votes people sets in previous classes.
	 */
	public String[] getUserChoiceOfParty() {
		int length = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats().length;
		String[] values = new String[length];
		for(int i = 0;i < length;i++) {
			/**
			 *  We get a list of poll at first, then find a certain poll i, then find a certain party
			*  k sorted by seats, then we get the number of this party's seats.
			*/
			
				partyseats = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[i].getProjectedNumberOfSeats();
				/**
				 * We get a list of poll at first, then find a certain poll i, then find a certain party
				 *  k sorted by vote, then we get the number of this party's votes.
				 */
				partyvotes = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[i].getProjectedPercentageOfVotes();
				values[i] = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[i].getName() +"(" + 
						partyseats +" of seats, " + partyvotes + " of votes)";
		}
		return values;
	}

	/**
	 * The initialization of the objects in GUI.
	 */
	@FXML
	void initialize() {
		assert pollToEdit != null : "fx:id=\"pollToEdit\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert clear != null : "fx:id=\"clear\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert warninglabel2 != null : "fx:id=\"warninglabel2\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert warningLabel != null : "fx:id=\"warningLabel\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert updateParty != null : "fx:id=\"updateParty\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert projectedSeats != null : "fx:id=\"projectedSeats\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert projectedVotes != null : "fx:id=\"projectedVotes\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert numberofwholeseats != null : "fx:id=\"numberofwholeseats\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert partyToUpdate != null : "fx:id=\"partyToUpdate\" was not injected: check your FXML file 'EditPollView.fxml'.";
		
	}

	/**
	 * This method is to refresh all the the people choose and typed which can make
	 * the interface like the initialization.
	 */
	@Override
	public void refresh() {

		
		String[] pollnames= new String[getPollList().getPolls().length];
		
		for (int i = 0; i < getPollList().getPolls().length; i++) {
			pollnames[i] = getPollList().getPolls()[i].getPollName();
		}
		
		
		pollToEdit.setItems(FXCollections.observableArrayList(pollnames));
		
		pollToEdit.getSelectionModel().selectedIndexProperty().addListener(
	        	new ChangeListener<Number>() {
	        			
	        		@Override
	        		public void changed(ObservableValue ob, Number old, Number new1) {
	        		
	        		userPChoose = new1.intValue();
	        		
	        		if (userPChoose!=-1) {
	        			String[] partynames = new String[getPollList().getPolls()[userPChoose].getPartiesSortedBySeats().length];
	        			for(int i = 0;i < getPollList().getPolls()[userPChoose].getPartiesSortedBySeats().length;i++) {
	        				  
	        			
	        				partyseats = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[i].getProjectedNumberOfSeats();
	        				
	        				partyvotes = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[i].getProjectedPercentageOfVotes();
	        				
	        				partynames[i] = getPollList().getPolls()[userPChoose].getPartiesSortedBySeats()[i].getName() +"(" + 
							partyseats +" of seats, " + partyvotes + " of votes)";
	        			}
	        			
	        			partyToUpdate.setItems(FXCollections.observableArrayList(partynames));
	        		}
	        		
	        		partyToUpdate.getSelectionModel().selectedIndexProperty().addListener(
	    					new ChangeListener<Number>() {

	    						@Override
	    						public void changed(ObservableValue observable, Number oldValue, Number newValue) {
	    							userPartyChoose = newValue.intValue();
	    							System.out.println(2 + ","+ userPChoose + "," + userPartyChoose);
	    						}
	    					});
	        		
	        			System.out.println(1 + "," + userPChoose + "," + userPartyChoose);
	        		}
	        	}	
	        );
		
		numberofwholeseats.setText(" /" + getPollList().getNumOfSeats());  
		projectedSeats.clear();
		projectedVotes.clear();
	}

}