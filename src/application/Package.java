 class Package {
    String packageID;
    Tour [] tours;
    Flight [] flights;
    Room [] rooms;
    float price;

    /**
	 * Constructor for an object type Package
	 * @param pID package ID number
	 * @param t array of tours contained in the package 
	 * @param f array of flights contained in the package
	 * @param r array of rooms contained in the package
	 * @param p array of price contained in the package
	 */
    Package (String pID, Tour [] t, Flight [] f,
     Room [] r, float p)
    {
        setPackageID(pID);
        setTours(t);
        setFlights(f);
        setRooms(r);
        setPrice(p);
    }

    // set methods
    private void setPackageID(packID) {
        // add here if statement to check format
        packageID = packID;
    }

    private void setTours(tr) {
        tours = tr;
    }

    private void setFlights(fli) {
        flights = fli;
    }

    private void setRooms(rs) {
        rooms = rs;
    }

    private void setPrice(pri) {
        price = pri;
    }

    // get methods
    String getPackageID() {
        return packageID;
    }

    Tour [] getTours() {
        return tours;
    }

    Flight [] getFlights() {
        return flights;
    }

    Room [] getRooms() {
        return rooms,
    }

    float getPrice() {
        return price;
    }
}