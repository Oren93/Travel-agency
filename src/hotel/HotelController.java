import sample.DataFactory;


package hotel;

import application;
import flights;

public class HotelController implements Initializable {


    private static HotelDataFactory dataFactory = new HotelDataFactory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }


    public static ObservableList<HotelRoom> GetHotelRooms(Parameters p){
            ObservableList<HotelRoom> hotelrooms = FXCollections.observableArrayList();

            /// hotelrooms.findHotel(p);
            hotelrooms  = DataFactory.findHotelRoom(p);
            // finna laus herbergi
            /*
                    Select *
            from room r,hotel h ,booking b
            where
            h.hotelname = r.hotelname and b.hotelname = h.hotelname and r.roomnumber = b.roomnumber and
            h.hotelcity = p.destination and
            (           r.pricepernight between p.minprice and p.maxprice) and
            ((p.difficulty = HANDICAP and r.disability = já) or p.difficulty != HANDICAP) and
            not exists (
            select * from booking bb where b.hotelname = bb.hotelname and b.roomnumber = bb.roomnumber and
            not (p.checkoutdate > b.checkindate and p.checkindate < bb.checkoutdate) )
            and sum(occupancy) >= p.groupsize
            group by h.hotelname
            ) */


            //Næst þarf að fara í gegnum skil frá select og búa til tilvik af HotelRoom og bæta við hotelrooms

            return hotelrooms;
     }
     public static boolean bookRoom(String hotelName, Parameters p, Passenger pass){
           // Finna hvort g.ssn er til í töflunni guest í gagnagrunni, Create ef ekki. Annars update.
           dataFactory.newGuest(pass);

            ObservableList<HotelRoom> hotelrooms = GetHotelRooms(p);
            boolean ok = false;
            int gestirEftir = p.getgroupSize();

            for(HotelRoom hr : hotelrooms){
                if(gestirEftir <= 0) break;
                if (hr.getHotelName() == hotelName) {
                    // create booking; nota hr til að ná í infó fyrir það sem og frá parameters
                    dataFactory.booking(hr, p, pass);
                    gestirEftir -= hr.getOccupancy();
                }
                if (gestirEftir <= 0) ok = true;
                return ok;
            }

        }
    }
