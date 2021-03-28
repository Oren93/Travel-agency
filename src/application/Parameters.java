package application;
import java.time.LocalDateTime;

public class Parameters {
	enum LEVEL {
		HANDICAP,
		EASY,
		MODERATE,
		HARD
	}; // I am not sure I like this enum thing, perhaps better to use constants instead
	LEVEL difficulty;
	float price;
	int groupSize;	// "capacity" in the model
	LocalDateTime checkIn; // might be changed later to nanoseconds instead  
	LocalDateTime checkOut;
	
	public Parameters ()
	{
		
	}
	
	public void setPrice(float p) {
		price = p;
	}
	
	public void setDifficulty(int dif) {
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
			System.out.println("an error message popup should appear here");	
		}
	}
	
	public void setGroupSize(int gs) {
		groupSize = gs;
	}
	
	public void setCheckIn(LocalDateTime in) {
		LocalDateTime today = LocalDateTime.now();
		if (today.isBefore(checkIn))
			checkIn = in;
		else
		System.out.println("an error message popup should appear here");
	}
	
	public void setCheckOut(LocalDateTime out) {
		if (checkIn!=null && out.isAfter(checkIn))
			checkOut = out;
		else
		System.out.println("an error message popup should appear here");
	}
}
