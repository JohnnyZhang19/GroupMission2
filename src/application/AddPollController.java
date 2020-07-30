package application;


import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * This class is a controller which extends PollTrackerController
 * and set the new poll name.
 * @author Zonglin Zhang UCID: 30020965
 *
 */

public class AddPollController extends PollTrackerController{

    @FXML
    private ChoiceBox<String> selectPoll;
    @FXML
    private Label pollNameLabel;

    @FXML
    private Button addPoll;

    @FXML
    private Button clear;

    @FXML
    private Label selectLabel;

    @FXML
    private TextField enterPollName;
    
    /**
     * this method will update the user input in textFeild poll name to replace the poll name in the choiceBox.
     * @param event when click the addPoll button, the data will be update.
     */

    @FXML
    void addPoll(ActionEvent event) {
    	System.out.println("buttonClicked");
    	getPollList().getPolls()[selectPoll.getSelectionModel().getSelectedIndex()].setName(enterPollName.getText());
    	
    }
    
    /**
     * this method is to clear what the user typed in the textFiled
     * @param event when click the button, the input in textFiled will be delete.
     */

    @FXML
    void clear(ActionEvent event) {
    	System.out.println("clear");
    	enterPollName.clear();
    }
    
    @FXML
    void keyTyped(KeyEvent event) {
    	System.out.println("entered");
    	
    }

    /**
     * this method is to show the items in the choicebBox.
     */
	@Override
	public void refresh() {
		selectPoll.setItems(FXCollections.observableArrayList(getPoll()));
	}
	/**
	 * this method is to set the choiceBox
	 */
	@FXML 
	void initialize() {
		
	}
	/**
	 * this method is to call the polls in the Poll class and place them in the choiceBox.
	 * @return
	 */
	public String[] getPoll() {
		String[] choiceBox = new String[getPollList().getPolls().length];
		for(int i = 0; i < getPollList().getPolls().length; i ++) {
			choiceBox[i] = (i+1) + "(Replace poll " + getPollList().getPolls()[i].getPollName() + ")";
			
		}
		return choiceBox;
	}
	
}
