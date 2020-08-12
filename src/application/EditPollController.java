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
import javafx.scene.paint.Color;
import model.InvalidPartyDataException;

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
    private Label warninglabel21;
	
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
	private int userPollChoose = 0;
	private int userPartyChoose = 0;
	private float voteInput = 1;
	private float seatsInput = 1;
	
	/**
	 * This method can make what people type in 
	 * this textField appear.
	 * @param event What people type. 
	 */
	@FXML
	void textVote(KeyEvent event){
		voteInput = Float.valueOf(projectedVotes.getText());
		
	}
	
	/**
	 * This method is to refresh all the the people chose and typed which can make
	 * the interface like the initialization.
	 * @param event When people click the button. 
	 */
	@FXML
	void buttonClear(ActionEvent event){
		refresh();
	}

	/**
	 * This method will update what people typed in textFeild to the data in
	 * "partyToUpdate" chioceBox.
	 * @param event When click the button, the data will be updated.
	 */
	@FXML
	void buttonUpdate(ActionEvent event){ 
		/**
		 * We need to ensure userPartyChoose and userPollChoose are not -1 when people update second time
		 * Otherwise "java.lang.ArrayIndexOutOfBoundsException: -1" will occur.
		 */
		if (userPartyChoose == -1 || userPollChoose == -1){
			return;
		} 
		/**
		 * 	We get the userPollChoose poll from the pollList, and get the userPartyChoose 
		 * party from the userPollChoose poll, and set the number of seats and votes of userPartyChoose party
		 * in party choiceBox.
		 * 
		 * 
		 * We use try/catch statements to avoid InvalidPartyDataException.
		 * If the projectedNumberOfSeats and projectedPercentOfVotes are valid, the labels will be set to blank "".
		 * Otherwise, if they are invalid, the labels will print on screen error message(s) in red
		 */
		try {
			getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats()[userPartyChoose].setProjectedPercentageOfVotes(voteInput);
			warninglabel21.setText("");
		} catch (InvalidPartyDataException e) {
			// TODO Auto-generated catch block
			warninglabel21.setTextFill(Color.RED);
			warninglabel21.setText("Percentage of Votes must be written as a float between 0 and 1.");
		}
		try {
			getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats()[userPartyChoose].setProjectedNumberOfSeats(seatsInput);
			warninglabel2.setText("");
		} catch (InvalidPartyDataException e) {
			// TODO Auto-generated catch block
			warninglabel2.setTextFill(Color.RED);
			warninglabel2.setText("Projected Number of Seats must be a positive float.");
		}
	}
		 	
	/**
	 * This method can make what people type in 
	 * this textField appear.
	 * @param event What people type in this field.
	 */
	@FXML
	void textSeats(KeyEvent event){
		seatsInput = Float.valueOf(projectedSeats.getText());
	}
	

	/**
	 * The initialization of the objects in GUI.
	 */
	@FXML
	void initialize(){
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
	public void refresh(){
		/**
		 * Get the number and name of polls from previous classes
		 */
			int length = getPollList().getPolls().length;
			String[] pollName = new String[length];
			for(int i = 0;i < length;i++){
				pollName[i] = getPollList().getPolls()[i].getPollName();
			}
		/**
		 * set the values from previous classes to this pollToEdit.
		 */
		pollToEdit.setItems(FXCollections.observableArrayList(pollName));
		pollToEdit.getSelectionModel().selectedIndexProperty().addListener(
	        	new ChangeListener<Number>() {
	        			
	        		@Override
	        		public void changed(ObservableValue ob, Number old, Number new1) {
	        		
	        		userPollChoose = new1.intValue();
	        		
	        		if (userPollChoose!=-1) {
	        			/**
	        			 * Get to number of seats and votes people sets in previous classes.
	        			 */
	        			String[] partyNames = new String[getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats().length];
	        			for(int i = 0;i < getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats().length;i++) {
	        				/**
	        				 * We get a list of poll at first, then find a certain poll userPollChoose, then find a certain party
	        				 *  i sorted by seats, then we get the number of this party's votes.
	        				 */
	        				partyseats = getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats()[i].getProjectedNumberOfSeats();
	        				partyvotes = getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats()[i].getProjectedPercentageOfVotes();
	        				partyNames[i] = getPollList().getPolls()[userPollChoose].getPartiesSortedBySeats()[i].getName() +"(" + 
	        						partyseats +" of seats, " + partyvotes + " of votes)";
	        			}
	        			/**
	        			 * set the values from previous classes to this pollToEdit.
	        			 */
	        			partyToUpdate.setItems(FXCollections.observableArrayList(partyNames));
	        		}
	        		partyToUpdate.getSelectionModel().selectedIndexProperty().addListener(
	    					new ChangeListener<Number>() {

	    						@Override
	    						public void changed(ObservableValue observable, Number oldValue, Number newValue) {
	    							userPartyChoose = newValue.intValue();
	    						}
	    					});
	        		}
	        	}	
	        );
		/**
		 * get the number of whole seats in all the polls the end of this line
		 * clear the things in textField. 
		 */
		numberofwholeseats.setText(" /" + getPollList().getNumOfSeats());  
		projectedSeats.clear();
		projectedVotes.clear();
	}

}