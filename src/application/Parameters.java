package application;
import java.time.LocalDate;


public class Parameters {
	final static int MAXGROUP = 20;
	// airports codes to be used, more departLocation codes should be added if needed	
	public final int RVK = 1;
	public final int Ak = 2;
	public final int ISAF = 3;
	public final int EGIL = 4;
	// Difficulty level
	
	public final static int HANDICAP = 10;
	public final static int EASY = 11;
	public final int MODERATE = 12;
	public final int HARD = 13;

	private int difficulty;
	private int [] price; // price range [minPrice, maxPrice]
	private int groupSize;	// "capacity" in the model, number of travellers
	private int departLocation;	// Should find a better name, take off departLocation
	private int destination;// the destination is where the trip will be
	
	private LocalDate checkIn;  
	private LocalDate checkOut; 

	/**
	 * Constructor for an object type Parameter
	 * @param dif the difficulty level, integer 11-13, and 10 is handicap accessible
	 * @param priceRange array size 2 of min to max price the customer willing to pay 
	 * @param groupS amount of members in the group
	 * @param dateRange array size 2 of starting date and end date
	 * @param from airport of departure
	 * @param destination of the trip
	 */
	Parameters (int dif, int [] priceRange,
			int groupS, LocalDate [] dateRange, int from, int to)
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
		setLocations(from,to);
	}
	
	/**
	 * empty constructor for Parameter object
	 */
	Parameters () {
		 price = new int [2];
	}
	
	private void setPrice(int [] p) {
		if (p[0]>0 && p[1]>p[0])
			price = p;
		else printError("Price error");
	}
	
	private void setDifficulty(int dif) {
		if (dif < 10 || dif > 13)
			printError("setDifficulty number code out of range");
		else difficulty = dif;
		
	}
	
	private void setGroupSize(int gs) {
		if (gs < 1 || gs > MAXGROUP)
			printError ("group size not valid");
		else
		groupSize = gs;
	}
	
	private void setCheckIn(LocalDate in) {
		LocalDate today = LocalDate.now();
		if (today.isBefore(in))
			checkIn = in;
		else
			printError ("bokking date must be in the future");
	}
	
	private void setCheckOut(LocalDate out) {
		if (checkIn!=null && out.isAfter(checkIn))
			checkOut = out;
		else printError ("check in must be before checkout");
	}
	private void setLocations(int from, int to) {
		if (from == to)
			printError("departure and destination must be different locations");
		else {
			destination = to;
			departLocation = from;
		}
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
	public int getdifficulty () {
		return difficulty;
	}
	public int getLowerPrice () {
		return price[0];
	}
	public int getMaxPrice () {
		return price[1];
	}
	public int getgroupSize () {
		return groupSize;
		
	}
	public LocalDate getcheckIn () {
		return checkIn;
	}
	public LocalDate getcheckOut () {
		return checkOut;
	}

	/**
	 * @return the departLocation
	 */
	public int getdepartLocation() {
		return departLocation;
	}
	/**
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}
	
	public String toString() {		
		String tostring =
				"Travel dates from "+checkIn.getDayOfMonth()+"/"+checkIn.getMonthValue()+
				"/"+checkIn.getYear()+ " ariport "+ extractCode(departLocation) + "\n"+
				"To "+checkOut.getDayOfMonth()+"/"+checkOut.getMonthValue()+
				"/"+checkOut.getYear()+ " ariport "+ extractCode(destination) + "\n"+
				"Price range is between "+ price[0] +" to "+price[1]+"\n"+
				"Difficulty level is "+extractCode(difficulty)+"\n"+
				"Group size is "+groupSize;
		return tostring;
	}

	public String extractCode(int s) {
		switch(s) {
		  case 1:
			    return "Reykjavík";
		  case 2:
			    return "Akureyri";
		  case 3:
			    return "Ísafjörður";
		  case 4:
			    return "Egilstaðir";
		  case 10:
			    return "Handicaped accessibility";
		  case 11:
			    return "easy";
		  case 12:
			    return "moderate";
		  case 13:
			    return "hard";
		  default:
			  return "Wrong value";
		}
		
	}
}
