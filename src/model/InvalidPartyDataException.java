package model;
/**
 * This class provides methods to handle invalid data related to parties,
 * which can be found in classes like Party, PollList, Factory, EditPollView and EditPollController
 * 
 * @author Ha Do UCID: 30097811
 *
 */
public class InvalidPartyDataException extends Exception {

	/**
	 * Since InvalidPartyDataException class is the child class of Exception class,
	 * it inherits all constructors of the parent class.
	 */
	public InvalidPartyDataException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidPartyDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidPartyDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPartyDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPartyDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
