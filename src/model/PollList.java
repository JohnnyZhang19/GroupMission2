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

	public void addPoll(Poll aPoll) {
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
			System.err.println("There is no more rooms in the list, the list is full."); 	// if the list is full, then cannot add new poll to it and print a error.
		}
	}
	
	
	public Poll getAggregatePoll(String[] partyNames) {
		Poll poll = new Poll("Aggregate",partyNames.length);	
		float totalSeats = 0;
		float totalVote = 0;
		for (int i = 0; i < partyNames.length; i++) {
			String partyName = partyNames[i];
			Party averageParty = getAveragePartyData(partyName);
			totalSeats += averageParty.getProjectedNumberOfSeats();
			totalVote += averageParty.getProjectedPercentageOfVotes();

			poll.addParty(averageParty);
		}
			for (int i = 0; i < partyNames.length; i++) {
				Party party = poll.getParty(partyNames[i]);
				if (totalSeats > numOfSeats) {
					float finalAverageSeat = party.getProjectedNumberOfSeats()/totalSeats*numOfSeats;
					party.setProjectedNumberOfSeats(finalAverageSeat);
				}
				
				if (totalVote > 1) {
					float finalAverageVote = party.getProjectedPercentageOfVotes() / totalVote * 1;
					party.setProjectedPercentageOfVotes(finalAverageVote);
				}
			}
		return poll;
	}
	
	public Party getAveragePartyData(String partyName) {
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
		return "Number of seats: " + numOfSeats; 		// this return the ‘Number of seats: <numOfSeats>’.
	}
	

}
