package model;
/**
 * This class provides methods to handle invalid data related to parties,
 * which can be found in classes like Poll, PollList, Factory, and AddPollController
 * 
 * @author Yuzhe Zhou UCID 30102199
 *
 */
public class PollFullException extends Exception {

	/**
	 * Since PollFullException class is the child class of Exception class,
	 * it inherits all constructors of the parent class.
	 */
	public PollFullException() {
		// TODO Auto-generated constructor stub
	}

	public PollFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PollFullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public PollFullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PollFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
