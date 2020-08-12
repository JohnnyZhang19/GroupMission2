package model;

/**
 * This class creates Parties, Polls and PollLists either randomly or by prompting the end user for the data for the polls and parties.
 * For mission 3, Factory is updated class to catch the InvalidPartyDataException where required to ensure the Factory class compiles.
 * In each catch statement, a stack trace is printed. 
 * 
 * For mission 3, Factory is updated class to catch the PollListFullException where required to ensure the Factory class compiles.
 * In each catch statement, a stack trace is printed.
 * 
 * @author Musaab Shahid
 *
 */
import java.util.Random;
import java.util.Scanner;

public class Factory {
	private int numOfSeats;
	String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
	
	public Factory(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public void setPartyNames(String[] names) {
		if (names == null) {
			return;
		}
		partyNames = names;
	}
	
	public String[] getPartyNames() {
		return partyNames;
	}
	
	public Party createRandomParty(String partyName, int maximumSeats, int maximumPercent) {
		Random rand = new Random();
		try {
			return new Party(partyName, maximumSeats > 0 ? rand.nextInt(maximumSeats) : 0, maximumPercent > 0 ? rand.nextInt(maximumPercent)/(float)100.0 : 0);
		} catch (InvalidPartyDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Poll createRandomPoll(String name) {
		Poll poll = new Poll(name, partyNames.length);
		int numOfSeatsTillNow = 0;
		float votesPercentTillNow = 0;
		Random rand = new Random();
		int i = 0;
		Party party;
		for (String partyName : partyNames) {
			int maximumSeats =  (numOfSeats - numOfSeatsTillNow) > (partyNames.length - i) ? rand.nextInt((numOfSeats -  numOfSeatsTillNow)/(partyNames.length - i)) + 1 : 0;
			int maximumPercent = (100 - votesPercentTillNow) > (partyNames.length - i) ? rand.nextInt((int)(100 - votesPercentTillNow)/(partyNames.length - i)) + 1 : 0;
			party = this.createRandomParty(partyName, maximumSeats, maximumPercent);
			poll.addParty(party);
			numOfSeatsTillNow += party.getProjectedNumberOfSeats();
			votesPercentTillNow += party.getProjectedPercentageOfVotes();
			i++;
		}
		if (numOfSeatsTillNow < numOfSeats || votesPercentTillNow < 100) {
			int randomIndex = rand.nextInt(partyNames.length);
			Party party1 = poll.getPartiesSortedBySeats()[randomIndex];
			try {
				party1.setProjectedNumberOfSeats(party1.getProjectedNumberOfSeats() + numOfSeats - numOfSeatsTillNow);
			} catch (InvalidPartyDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				party1.setProjectedPercentageOfVotes(party1.getProjectedPercentageOfVotes() + (1 - votesPercentTillNow));
			} catch (InvalidPartyDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return poll;
	}

	public PollList createRandomPollList(int numOfPolls) {
		PollList list = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			try {
				list.addPoll(createRandomPoll("Poll" + counter));
			} catch (PollListFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public PollList promptForPollList(int numOfPolls) {
		PollList list = new PollList(numOfPolls,numOfSeats);
		Scanner sc = new Scanner(System.in);
		String pollName;
		for (int counter = 0; counter < numOfPolls; counter++) {
			System.out.println("Poll " + counter + " name: ");
			pollName = sc.next();
			try {
				list.addPoll(createRandomPoll(pollName));
			} catch (PollListFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
}