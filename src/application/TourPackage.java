package application;
import daytour.*;
//import 3H.*;
import flights.*;

class TourPackage {
    //String packageID;
    ObservableList<Tour> tours;
    ObservableList<Flight> [] flights;
    //ObservableList<Room> [] rooms;
    double price;

    /**
	 * Constructor for an object type TourPackage
	 * @param pID TourPackage ID number
	 * @param t array of tours contained in the TourPackage 
	 * @param f array of flights contained in the TourPackage
	 * @param r array of rooms contained in the TourPackage
	 * @param p array of price contained in the TourPackage
	 */
    TourPackage (String pID, Tour [] t, Flight [] f,
     /*Room [] r,*/ double p)
    {
        //setPackageID(pID);
        setTours(t);
        setFlights(f);
        setRooms(r);
        setPrice(p);
    }

    /* set methods
    //private void setTourPackageID() {
    //    String packID = ; // add here math.Random to create TourPackage ID according to format
    //    TourPackageID = packID;
    }*/

    private void setTours(ObservableList<Tour> tr) {
        tours = tr;
    }

    private void setFlights(ObservableList<Flight> fli) {
        if(fli.length == 2) {
            flights = fli;
        }
        else printError("there should be only 2 flights");
    }

    //private void setRooms(ObservableList<Room>rs) {
    //    rooms = rs;
    //}

    private void setPrice(double pri) {
        price = pri;
    }

    // get methods
    String getTourPackageID() {
        return TourPackageID;
    }

    ObservableList<Tour> getTours() {
        return tours;
    }

    ObservableList<Flight> getFlights() {
        return flights;
    }

    //ObservableList<Room> getRooms() {
    //    return rooms,
    //}

    double getPrice() {
        return price;
    }

    /**
	 * A temporary implementation of error handler, should later on pop an error message
	 * @param e the error string, indicates where the error occured
	 */
	private void printError (String e) {
		System.out.println("an error message popup should appear here");
		System.out.println("Error: "+ e);
		
	}
}