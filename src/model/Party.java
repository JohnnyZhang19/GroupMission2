package model;
/**
 * This class is for storing information about a party (name and projections) and displaying in a visualization.
 * 
 * @author Ayoub Bin Nakhi
 *
 */
public class Party {
	private String name;
	private float projectedNumberOfSeats;
	private float projectedPercentageOfVotes;
	
	public Party(String partyName) {
		name = partyName;
	}
	
	/**
	 * Constructor for class, projected number of seats should be a positive float
	 * projected percent of votes must be entered as value between 0 and 1.
	 */
	public Party(String partyName, float projectedNumOfSeats, float projectedPercentOfVotes) {
		name = partyName;
		if (projectedNumOfSeats >= 0)
			projectedNumberOfSeats = projectedNumOfSeats;
		else 
			System.err.println("Projected Number of Seats must be a positive float.");
		if (projectedPercentOfVotes >= 0 && projectedPercentOfVotes <= 1)
			projectedPercentageOfVotes = projectedPercentOfVotes;
		else
			System.err.println("Percentage of Votes must be written as a float between 0 and 1");
	}
	
	//Next few methods are getters and setters for the instance variables
	
	public String getName() {
		return name;
	}
	
	public String setName(String name) {	
		return this.name = name;
	}
	
	public float getProjectedNumberOfSeats() {
		return projectedNumberOfSeats;
	}
	
	public float setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if (projectedNumberOfSeats >= 0) 
			this.projectedNumberOfSeats = projectedNumberOfSeats;	
		else 
			System.err.println("Projected Number of Seats must be a positive float.");
		return this.projectedNumberOfSeats;		
	}
	
	public float getProjectedPercentageOfVotes() {
		return this.projectedPercentageOfVotes;
	}
	
	public float setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		if (projectedPercentageOfVotes >= 0 && projectedPercentageOfVotes <= 1)
			this.projectedPercentageOfVotes = projectedPercentageOfVotes;
		else
			System.err.println("Projected Percentage of Votes must be a float between 0 and 1");
		return this.projectedPercentageOfVotes;
	}

	
	/**
	 * Method to calculate the percentage of seats a party is projected to win
	 * Divides projected number of seats (obtained from the poll) by the total seats
	 * @param totalSeats total seats available in the election
	 * @return the percentage of projected seats as value between 0 and 1
	 */
	public double projectedPercentOfSeats(int totalSeats) {		
		double projectedPercentOfSeats = 0;
		if (totalSeats > 0)
			projectedPercentOfSeats = projectedNumberOfSeats / totalSeats;
		return projectedPercentOfSeats;
	}
	
	/**
	 * Method to create visualization for poll results for each party
	 * Visualization shows projected seats/votes represented as *
	 * Method is private as it is called by other methods depending on which type of information is used
	 * for creating the visualization (using seats or using votes)
	 * @param maxStars determines total length of visualization
	 * @param starsNeededForMajority a marker '|' for how many seats/votes party needs for majority 
	 * @param perStar how many votes/seats each * should represent
	 * @param projectedSeatsOrVotes the projected votes/seats the party has from the poll
	 * @return visualization is returned as a string concatenated with the toString method to show
	 * 		   party information alongside visualization
	 */
	private String textVisualization(int maxStars, int starsNeededForMajority, double perStar, float projectedSeatsOrVotes) {
		int numOfStars = (int) (projectedSeatsOrVotes / perStar);
		String visualization = "";
		int starCounter = 1;	// starCounter variable used as a reference for the while loop and as an index for the string visualization		
		while (starCounter <= maxStars) {
			if (starCounter <= numOfStars) {
				visualization += "*";
				if (starCounter == starsNeededForMajority)
					visualization += "|";
			}	
			if (starCounter > numOfStars) {
				visualization += " ";
				if (starCounter == starsNeededForMajority)
					visualization += "|";
			}				
			starCounter ++;
		}
		visualization += " " + this.toString();
		return visualization;
	}
	
	//Method to call the textVisualization method using SEATS as parameter
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		return textVisualization(maxStars, starsNeededForMajority, numOfSeatsPerStar, projectedNumberOfSeats);
	}
	
	//Method to call the textVisualization method using VOTES as parameter
	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double numOfVotesPerStar) {
		return textVisualization(maxStars, starsNeededForMajority, numOfVotesPerStar, projectedPercentageOfVotes * 100);
	}
	
	@Override 
	public String toString() {
		return name + " (" + (int)(projectedPercentageOfVotes * 100) + "% of votes, " + projectedNumberOfSeats + " seats)";
	}
}