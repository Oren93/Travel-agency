package application;
import java.time.LocalDateTime;

public class Parameters { // public keyword is temporary, will change to abstract (don't think it needs to be abstract, since there are no abstract methods)
	private final int MAXGROUP = 20;
	protected enum LEVEL {
		HANDICAP,
		EASY,
		MODERATE,
		HARD
	}; // I am not sure I like this enum thing, perhaps better to use constants instead
	private LEVEL difficulty;
	private float [] price;
	private int groupSize;	// "capacity" in the model
	private LocalDateTime checkIn; // might be changed later to nanoseconds instead  
	private LocalDateTime checkOut; /* this object includes both date and time, 
	 								    it makes sense for the objects shown to the user
	 								    but not for the search bar, the search needs to
	 								    read date only from the user and it is converted 
	 								    to date and time to be handled here*/

	/**
	 * Constructor for an object type Parameter
	 * @param dif the difficulty level, integer 1-3, and 0 is handicap accessible
	 * @param priceRange array size 2 of min to max price the customer willing to pay 
	 * @param groupS amount of members in the group
	 * @param dateRange array size 2 of starting date and end date
	 */
	protected Parameters (int dif, float [] priceRange,
			int groupS, LocalDateTime [] dateRange)
	{
		setDifficulty(dif);
		setPrice(priceRange);
		setGroupSize(groupS);	// "capacity" in the model
		if (dateRange.length != 2)
			printError("date array error");
		else {
			setCheckIn(dateRange[0]);
			setCheckOut(dateRange[1]);
		}
	}
	
	/**
	 * empty constructor for Parameter object
	 */
	protected Parameters () {
		 price = new float [2];
	}
	
	protected void setPrice(float [] p) {
		if (p[0]>0 && p[1]>p[0])
			price = p;
		else printError("Price error");
	}
	protected void setPrice (float priceMin, float priceMax) {
		price[0] = priceMin;
		price[1] = priceMax;
 	}
	
	protected void setDifficulty(int dif) {
		switch (dif) {
		case 0: difficulty = LEVEL.HANDICAP;
		break;
		case 1: difficulty = LEVEL.EASY;
		break;
		case 2: difficulty = LEVEL.MODERATE;
		break;
		case 3: difficulty = LEVEL.HARD;
		break;
		default:
			printError("setDifficulty number code out of range");
		}
	}
	
	protected void setGroupSize(int gs) {
		if (gs < 1 || gs > MAXGROUP)
			printError ("group size not valid");
		else
		groupSize = gs;
	}
	
	protected void setCheckIn(LocalDateTime in) {
		LocalDateTime today = LocalDateTime.now();
		if (today.isBefore(checkIn))
			checkIn = in;
		else
			printError ("bokking date must be in the future");
	}
	
	protected void setCheckOut(LocalDateTime out) {
		if (checkIn!=null && out.isAfter(checkIn))
			checkOut = out;
		else printError ("check in must be before checkout");
	}
	/**
	 * A temporary implementation of error handler, should later on pop an error message
	 * @param e the error string, indicates where the error occured
	 */
	private void printError (String e) {
		System.out.println("an error message popup should appear here");
		System.out.println("Error: "+ e);
		
	}
	
	// get methods
	protected LEVEL getdifficulty () {
		return difficulty;
		// TODO check if enum are stored as 0-3
	}
	protected float getLowerPrice () {
		return price[0];
	}
	protected float getHigherPrice () {
		return price[1];
	}
	protected int getgroupSize () {
		return groupSize;
		
	}
	protected LocalDateTime getcheckIn () {
		return checkIn;
	}
	protected LocalDateTime getcheckOut () {
		return checkOut;
	}

}
