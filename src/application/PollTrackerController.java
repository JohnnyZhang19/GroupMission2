/**
 * The PollTrackerController updates and provides shared PollList or Factory class
 * using the setters getter methods for other controller classes.
 * 
 */
package application;

import model.Factory;
import model.PollList;

public abstract class PollTrackerController {
	private PollTrackerApp app;
/**
 * This "refresh()" method will automatically be called each time the view in the application changes.	
 * This will ensure that the user's view will have the most up-to-date poll and factory information displayed.
 */
	public abstract void refresh();
	
	public void setPollTrackerApp(PollTrackerApp app) {
		this.app = app;
		refresh();
	}
/**
 * This provides the shared PollList class	
 * @return
 */
	protected PollList getPollList() {
		return app.getPolls();
	}
	/**
	 * This provides the shared Factory class	
	 * @return
	 */	
	protected Factory getFactory() {
		return app.getFactory();
	}
/**
 * This updates the PollList class	
 * @param polls
 */
	protected void setPollList(PollList polls) {
		app.setPolls(polls);
	}
	/**
	 * This updates the Factory class	
	 * @param polls
	 */	
	protected void setFactory(Factory aFactory) {
		app.setFactory(aFactory);
	}
}
