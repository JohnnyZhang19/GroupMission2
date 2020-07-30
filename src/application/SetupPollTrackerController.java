/**
 * The purpose of this code is for the user to setup a new poll tracker
 * by specifying the number of seats and parties in the election and
 * the number of polls to track.
 * 
 * @author Ha Do
 */


package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.PollList;

public class SetupPollTrackerController extends PollTrackerController {

    @FXML
    private TextField inputSeat;

    @FXML
    private TextField inputPoll;

    @FXML
    private TextField inputParty;

    @FXML
    private Button SubmitButton;

    @FXML
    private Button Clear;

    @FXML
    void PollTyped(KeyEvent event) {

    }

    @FXML
    void seatTyped(KeyEvent event) {

    }

    @FXML
    void partyTyped(KeyEvent event) {

    }
/**
 * This method is triggered when the "Submit" button is pressed,
 * allowing the user to put the number of seats and parties in the
 * election and the number of polls to track.
 * It also generates random parties using the Factory class
 * @param event
 */
    @FXML
    void submitThing(ActionEvent event) {
    	int pollNum = Integer.parseInt(inputPoll.getText());
    	PollList list = getFactory().createRandomPollList(pollNum);
    	setPollList(list);
    	int seatNum = Integer.parseInt(inputSeat.getText());
    	getPollList().setNumOfSeats(seatNum);
    	String[] party = new String[Integer.parseInt(inputParty.getText())];
    	for(int i = 0; i < party.length; i ++) {
    		party[i] = i+1 + "";
    	}
    	getFactory().setPartyNames(party);
    }
/**
 * This method is triggered when the "Submit" button is pressed,
 * allowing the user to clear all input in the poll
 * @param event
 */
    @FXML
    void clearThings(ActionEvent event) {
    	refresh();
    }
/**
 * This method overrides the one in its parent class PollTrackerController,
 * allowing the user to clear input
 */
	@Override
	public void refresh() {
		inputParty.clear();
    	inputPoll.clear();
    	inputSeat.clear();
	}

}
