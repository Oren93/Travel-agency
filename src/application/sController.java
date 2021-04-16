package application;
import java.time.LocalDate;
import java.time.LocalDate;
temporal.ChronoUnit;

import 3T.Tour; //need to get other team's code
import 3H.Room;
import 3F.Flight;
import 3F.fController;
import 3H.hController;
import 3H.fController;
private fController = new fController();
private fController = new fController();
private fController = new fController();

class sController {
    sController() {

    }
}

search(Parameter parameters){
    Flight[] departureF = fController.getAvailableFlights(Parameters, true);
    Flight[] arrivalF = fController.getAvailableFlights(Parameters, false); //will return an Observable list, not arrray, need to check that
    int n = departureF.length;
    int m = arrivalF.length;
    Flight cheapFlight; Flight shortFlight;
    long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//was long online, not sure if can be int
    double price1 = parameters.getPrice();
    double price2 = parameters.getPrice();

    for(int i=0; i<max(n,m); i++) {
        if (departureF[i].getbasePrice() <= Double.POSITIVE_INFINITY) {
            cheapFlight = Flight[i];
            price1 -= departureF[i].getbasePrice();
        }
        if (departureF[i].getDuration() <= Double.POSITIVE_INFINITY) {
            shortFlight = Flight[i]; //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
            price2 -= departureF[i].getbasePrice();
        }

    for(long i=0; i<voyageLength; i++) {
        //for one of the package select cheapest room each night, use leftover budget to select trips
    }
}