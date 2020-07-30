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

    @FXML
    void clearThings(ActionEvent event) {
    	inputParty.clear();
    	inputPoll.clear();
    	inputSeat.clear();
    }

	@Override
	public void refresh() {
		
	}

}
