package model;
/**
 * This class provides methods to handle invalid data related to parties,
 * which can be found in classes like PollList, Factory, AddPollView and AddPollController
 * 
 * @author Zonglin Zhang UCID: 30020965
 *
 */

public class PollListFullException extends Exception{
	/**
	 * Since PollListFullException class is the child class of Exception class,
	 * it inherits all constructors of the parent class.
	 */
	public PollListFullException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PollListFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PollListFullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public PollListFullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PollListFullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
