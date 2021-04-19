# Day tours - group 3D
## Authors:
* Guðrún Herdís Arnarsdóttir - gha18@hi.is - gha18
* Marcelo Felix Audibert - mfa5@hi.is - Gitcelo
* Marzuk Ingi Lamsiah Svanlaugar - mil4@hi.is - Marzuklngi
* Natanel Demissew Ketema - ndk1@hi.is - natidemis

***

## Packages
#### *Can all be found under the src folder*
* **Controller**: Contains the programs responsible for handling user requests
* **Model**: Contains programs that represent the application
* **Application**: Contains the Parameters class from team 3T and a class that contains commonly used methods between
  classes  
* **data**: Contains the database and programs that set up and do queries on the database
  * The programs that do the queries are used by the programs in the Controller package
* **fakeData**: Contains two .txt files with fake data for the database  

***

## Database setup (not really needed since our .db file comes with our packages)
* To create a new database run `MakeDatabase.java` in the data package from the parent directory of src
  (here Tour)
    * When MakeDatabase is run the .db folder is created and saved in the data package
* To populate the database with fake data run `PopulateDatabase.java` in the data package from the 
  parent directory of src (here Tour)
    * The fake data comes from the .txt files in the package fakeData   

***

## Integration
* The only classes used by the UI in the integration are the two classes in the `Controller` package. `ReservationController` contains
  the booking method, `confirmBooking`, and `TourController` contains the search method, `searcTour`.