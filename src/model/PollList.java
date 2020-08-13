package model;

public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
	public PollList(int numOfPolls, int numOfSeats) {

		if (numOfPolls <= 0) {           // add condition with numOfPolls is less than 1, the length of Poll[] is 5.
			polls = new Poll[5];
		}
		else {
			polls = new Poll[numOfPolls]; 	// if length of Poll[] is greater than 0, the length is keeps.
		}
		if ( numOfSeats <= 0) {			// add condition with numOfSeats is less than 1, the number of seats is 5.
			this.numOfSeats = 10;
		}
		else {
			this.numOfSeats = numOfSeats;  // if the seats is greater than 0, then remain the seats number.
		}
	} 
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public Poll[] getPolls() {
		return polls;
	}

	public void addPoll(Poll aPoll) throws PollListFullException{
		if (aPoll == null) {							// if aPoll is null, the list keeps unchanged.
			System.err.println("The enter is null.");
			return;
		}
		int index = 0;
		for (; index < polls.length && polls[index] != null; index++) {
			if (polls[index].getPollName().equalsIgnoreCase(aPoll.getPollName())) {		// if the name of poll already in the list, replace it with the new poll.
				polls[index] = aPoll;
				return;
			}
		}
		if (index <polls.length) {  	// if the list has room, then can add new poll to it.
			polls[index] = aPoll;
			index ++;
		}
		else {
			throw new PollListFullException ("There is no more rooms in the list, the list is full."); 	// if the list is full, then cannot add new poll to it and print a error.
		}
	}
	/**
	 * I choose to throw the InvalidPartyDataException because I am not sure how to handle the exception invalid data related to parties
	 * @param partyNames
	 * @return
	 * @throws InvalidPartyDataException, PollFullException
	 */
	
	public Poll getAggregatePoll(String[] partyNames) throws InvalidPartyDataException, PollFullException {
		Poll poll = new Poll("Aggregate",partyNames.length);	
		float totalSeats = 0;
		float totalVote = 0;
		//I choose to throw the PollFullException, because there will be other method in other class need to call this method
		//and this Exception will be handled in those methods.
		try {
			for (int i = 0; i < partyNames.length; i++) {
				String partyName = partyNames[i];
				Party averageParty = getAveragePartyData(partyName);
				totalSeats += averageParty.getProjectedNumberOfSeats();
				totalVote += averageParty.getProjectedPercentageOfVotes();
	
				poll.addParty(averageParty);
			}
		}catch(PollFullException a) {
			throw new PollFullException();
		}
			for (int i = 0; i < partyNames.length; i++) {
				Party party = poll.getParty(partyNames[i]);
				if (totalSeats > numOfSeats) {
					float finalAverageSeat = party.getProjectedNumberOfSeats()/totalSeats*numOfSeats;
					party.setProjectedNumberOfSeats(finalAverageSeat);
				}else 
					throw new InvalidPartyDataException("Projected Number of Seats must be a positive float.");
				
				if (totalVote > 1) {
					float finalAverageVote = party.getProjectedPercentageOfVotes() / totalVote * 1;
					party.setProjectedPercentageOfVotes(finalAverageVote);
				}else
					throw new InvalidPartyDataException("Projected Percentage of Votes must be a float between 0 and 1");
			}
		return poll;
	}
	/**
	 * Similarly to the one above, I choose to throw the InvalidPartyDataException because I am not sure how to handle the exception invalid data related to parties
	 * @param partyName
	 * @return
	 * @throws InvalidPartyDataException
	 */
	public Party getAveragePartyData(String partyName) throws InvalidPartyDataException {
		float averageSeats = 0;
		float averageVote = 0;
		float numOfOccur = 0.0f;
		for (int i = 0; i < polls.length; i++) {
			Party tmp = polls[i].getParty(partyName);
			if (tmp == null) {
				continue;
			}
			averageSeats += tmp.getProjectedNumberOfSeats();
			averageVote += tmp.getProjectedPercentageOfVotes();
			numOfOccur++;
		}
		averageSeats = averageSeats / numOfOccur;
		averageVote = averageVote / numOfOccur;
		Party average = new Party(partyName, averageSeats, averageVote);
		return average;
	}
	
	
	public Poll adjustPollToMaximums(Poll aPoll) {
		return aPoll;
	}
	
	@Override
	public String toString() {
		return "Number of seats: " + numOfSeats; 		
	}
	

}
