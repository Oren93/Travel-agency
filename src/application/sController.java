package application;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import 3D.Tour; //need to get other team's code
import 3H.Room;
import 3F.Flight;
import 3F.fController;
import 3H.hController;
import 3H.dController;
private fController = new fController();
private hController = new fController();
private dController = new fController();

class sController {
    sController() {
    }
}

private getFlights(Parameter parameters) {

}

search(Parameter parameters){
    Flight[] departureF = fController.getAvailableFlights(Parameters, true); //boolean variable true if flight is dearture to destination
    Flight[] returnF = fController.getAvailableFlights(Parameters, false); //will return an Observable list, not arrray, need to check that

    int n = departureF.length;
    int m = returnF.length;

    long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
    
    double price1 = parameters.getPrice();
    double price2 = parameters.getPrice();

    Flight[] cheapFlight; // cheapest departure Flight[0] + return flights Flight[1]
    Flight[] shortFlight; // same but for shortes flights

    double departPrice = Double.POSITIVE_INFINITY;
    double returnPrice = Double.POSITIVE_INFINITY;

    double departTime = Double.POSITIVE_INFINITY;
    double returnTime = Double.POSITIVE_INFINITY;

    for(int i=0; i<max(n,m); i++) {

        if(i<n) {
            if (departureF[i].getbasePrice() <= departPrice) {
                cheapFlight[0] = Flight[i];
                departPrice = departureF[i].getbasePrice();
            }
            if (departureF[i].getDuration() <= departTime) { //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
                shortFlight[0] = Flight[i];
                departTime = departureF[i].getDuration();
            }
        }
        if(i<m) {
            if (returnF[i].getbasePrice() <= returnPrice) {
                cheapFlight[1] = Flight[i];
                returnPrice = returnF[i].getbasePrice();
            }
            if (returnF[i].getDuration() <= returnTime) {
                shortFlight[1] = Flight[i];
                returnTime = returnF[i].getDuration();
            }
        }

    }   

    price1 -= departPrice + returnPrice;
    price2 -= shortFLight[0].getPrice() + shortFLight[1].getPrice();

    for(long i=0; i<voyageLength; i++) {
        //create cheapestRoom array of length voyageLength, fill it with cheapest room for each night
    }

}

//this is the local one
