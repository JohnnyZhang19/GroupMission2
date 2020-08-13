package model;
import java.util.Arrays;
import java.util.Comparator;

	/**
	 * This is Poll class which is used to get the names of 
	 * every party and get the order of parties by their poll
	 * number of seats and votes.
	 * 
	 * @author Yuzhe Zhou (UCID: 30102199) TeamC1
	 * For mission 3:
	 * @author Yuzhe Zhou UCID: 30102199
	 */
public class Poll {
	
	/**
	 * set the instance variables and encapsulation 
	 */
	private String name;
	private Party[] parties;
	private int partiesInPoll;

	/**
	 * Method: Generate getter method to get the poll name 
	 * 
	 * @return the names of parties in this poll
	 */
	public String getPollName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method: Generate getter method to get the number of parties
	 * in the poll.
	 * 
	 * @return return the number of parties in the poll.
	 */
	public int getNumberOfParties() {
		return partiesInPoll;
	}

	/**
	 * @param name: the certain name of the poll.
	 * @param maxNumOfParties: the maximum number of 
	 * parties in the Party array.
	 */
	public Poll(String name, int maxNumOfParties ) {
		this.name = name;
		if(maxNumOfParties >= 1) {
			parties = new Party[maxNumOfParties]; 
		}else {
			parties = new Party[10];
		}
	}

	/**
	 * Method: create an toString method with no argument and the return type is String.
	 * 
	 * @return the name represent different party.
	 */
	public String toString() {
		String nameofthepoll = name + "\n";
		String party = new String(nameofthepoll);
		for(int i = 0;i < partiesInPoll;i++) {
			party = party + parties[i].toString() + "\n";
		}
		return party;                                                   
	}

	/**
	 * Method: create a addParty method regard party as the argument, which add all the certain party   
	 * in our poll.
	 * 
	 * @param party: the certain party in our poll.
	 * @throws PollFullException If the poll is full, we cannot add any parties in it,
	 * this Exception will be throw.
	 */
	public void addParty(Party party) throws PollFullException{
		if(party == null){
			System.err.println("The party object is null");
			return;
		}
		/**
		 * i < partiesInPoll is because the number of parties may not fill up the array, and only the index with parties
		 * can be get name.
		 */
		for (int i = 0; i < partiesInPoll; i++) {
			if(parties[i].getName().equalsIgnoreCase(party.getName())){
				parties[i] = party;
				return;
			}
		}
		/**
		 * To put the party name party at the end of the array.
		 */
		if(partiesInPoll<parties.length){
			parties[partiesInPoll] = party;
			partiesInPoll++;
		}else{
			// This PollFullException will be throw because other method need to call this method and this PollFullException
			//will be handled in those methods.
			throw new PollFullException("Parties has no more rooms, the poll is full");
		}
	}

	/**
	 * Method: name of the party is the argument, use the getParty method to 
	 * find the party name in array which is same as the input one.
	 * 
	 * @param name: the input name of the party
	 * @return return the name which is same input name, if it does not have the 
	 * same number, return null.
	 */
	public Party getParty(String name){
		for (int i = 0; i < partiesInPoll; i++) {
			if(parties[i].getName().equalsIgnoreCase(name)){
				return parties[i];
			}
		}
		return null;
	}

	/**
	 * Method: use this method to get the party in increasing order by their seats.
	 * Creating a newParties which has the same objects as parties array and
	 * same length as partiesInPoll, and use this array to sort, so i used copyOf.
	 * 
	 * @return this newParties is a sub-array which contain the order of the every parties 
	 */
	public Party[] getPartiesSortedBySeats(){
		Party[] newParties =  Arrays.copyOf(parties,partiesInPoll);
		Arrays.sort(newParties, new Comparator<Party>() {
			@Override
			public int compare(Party o1, Party o2) {
				return (int)(o2.getProjectedNumberOfSeats() - o1.getProjectedNumberOfSeats());
			}
		});
		return newParties;
	}

	/**
	 * Method: use this method to get the party in increasing order. Creating a newParties 
	 * which has the same objects as parties array and same length as partiesInPoll, and 
	 * use this array to sort, so i used copyOf.
	 * 
	 * @return this newParties is a sub-array which contain the order of the every parties.
	 */
	public Party[] getPartiesSortedByVotes(){
		Party[] newParties =  Arrays.copyOf(parties,partiesInPoll);
		Arrays.sort(newParties, new Comparator<Party>() {
			@Override
			public int compare(Party o1, Party o2) {
				/**
				 * Because the return type of compare is integer, so i have to convert the float type return to integer type
				 * return, so i need to *100 for each one to make these percentages not zero, after convert.
				 */
				return (int)(o2.getProjectedPercentageOfVotes()*100 - o1.getProjectedPercentageOfVotes()*100);
			}
		});
		return newParties;
	}
	/**
	 * Method: use the getAmountOfUndeterSeatsAndVotes to get the undetermined seats and votes from the poll.
	 * And use float type of array to contain these two number. We use 1 subtract the seat and vote rate we already got
	 * is the rate of undetermined seats and votes from the poll
	 * 
	 * @return this array is undetermined seats and votes from the poll.
	 */
	public float[] getAmountOfUndeterSeatsAndVotes(){
		float amountOfSeats = 0.0f;
		float amountOfVotes = 0.0f;
		float amountOfUndeterSeats = 0.0f;
		float amountOfUndeterVotes = 0.0f;
		for (int i = 0; i < partiesInPoll; i++) {
			amountOfSeats = amountOfSeats + parties[i].getProjectedNumberOfSeats();
			amountOfVotes = amountOfVotes + parties[i].getProjectedPercentageOfVotes();
			amountOfUndeterSeats = 1 - amountOfSeats;
			amountOfUndeterVotes = 1 - amountOfVotes;
		}
		/**
		 * Create a array to save the rate of undetermined seats and votes.
		 */
		float[] array = new float[2];
		array[0] = amountOfUndeterSeats;
		array[1] = amountOfUndeterVotes;
		return array;
	}
}
