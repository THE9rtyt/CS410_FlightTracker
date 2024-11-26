package com.cryptlab.webserver.api_backends;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightOrder.Contact;
import com.amadeus.resources.FlightOrder.Document;
import com.amadeus.resources.FlightOrder.Document.DocumentType;
import com.amadeus.resources.FlightOrder.Name;
import com.amadeus.resources.FlightOrder.Phone;
import com.amadeus.resources.FlightOrder.Phone.DeviceType;
import com.amadeus.resources.FlightOrder.Traveler;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.SeatMap;
import com.google.gson.Gson;

public class FlightBookingAPI {

    Amadeus amadeus;
    Traveler traveler = new Traveler();

    public FlightBookingAPI() {
        this.amadeus = Amadeus.builder(System.getenv()).build();
    }
    
    public String[] flightOffers(String origin, String destination, String departureDate) {
        try {
            // Fetch the flight offers as an array of FlightOfferSearch objects
            FlightOfferSearch[] offers = amadeus.shopping.flightOffersSearch.get( 
                Params.with("originLocationCode", origin) // IATA code for the origin airport
                      .and("destinationLocationCode", destination) // IATA code for the destination airport
                      .and("departureDate", departureDate)// Date of departure in "YYYY-MM-DD" format
                      .and ("adults", 1)
                     );

            //check if the status code of the response is 200 (which means the request was successful).
            if (offers[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + offers[0].getResponse().getStatusCode());
            System.exit(-1);
            }

            // Convert each FlightOfferSearch object to a JSON string
            String[] jsonOffers = new String[offers.length];    
            for (int i = 0; i < offers.length; i++) {
                jsonOffers[i] = toJson(offers[i]);
            }
            return jsonOffers; // Return the array of JSON strings (FlightOffers)
        } catch (ResponseException e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }

    //seat map of given flight. 
    //returns a String[] array, where each element in the array represents a SeatMap object converted to a JSON string.
    //each element should have row info
    public String[] getSeatMap(String flightOrderId){
        try {
            SeatMap[] seatmap = amadeus.shopping.seatMaps.get(Params
            .with("flight-orderId", flightOrderId)); //Ex. "eJzTd9f3NjIJdzUGAAp%2fAiY="
           
            String[] seatMapStringFormat = new String[seatmap.length];    
            for (int i = 0; i < seatmap.length; i++) {
                seatMapStringFormat[i] = toJson(seatmap[i]);
            }
            return seatMapStringFormat;
        } catch (ResponseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public FlightOrder createOrder (String body) throws ResponseException{
        Traveler[] travelerArray = new Traveler[1];
        travelerArray[0] = traveler;
        System.out.println(travelerArray[0]);

        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.post(body);

        FlightPrice flightPricing = amadeus.shopping.flightOffersSearch.pricing.post(flightOffersSearches[0]);
        // We book the flight previously priced
        FlightOrder order = amadeus.booking.flightOrders.post(flightPricing, travelerArray);
        System.out.println(order.getResponse());
        return order;
  }

  private String toJson(Object obj) {
    return new Gson().toJson(obj);
  }


  public void createTraveler(String ID, String dob, String firstName, String lastName,
                                 String countryCallingCode, String number){
        traveler.setId(ID);
        traveler.setDateOfBirth(dob); //Ex. "2000-04-14"
        traveler.setName(new Name(firstName, lastName));

        Phone[] phone = new Phone[1];
        phone[0] = new Phone();
        phone[0].setCountryCallingCode(countryCallingCode);
        phone[0].setNumber(number);
        phone[0].setDeviceType(DeviceType.MOBILE);

        Contact contact = new Contact();
        contact.setPhones(phone);
        traveler.setContact(contact);
  }

  public void setTravelerPassportDocument(String number, String expDate, String issuedCountry, String nationality){
    Document[] document = new Document[1];
    document[0] = new Document();
    document[0].setDocumentType(DocumentType.PASSPORT);
    document[0].setNumber(number);
    document[0].setExpiryDate(expDate); //Ex. "2023-10-11"
    document[0].setIssuanceCountry(issuedCountry); //Ex. ES
    document[0].setNationality(nationality); //Ex. ES
    document[0].setHolder(true);
    traveler.setDocuments(document);
  }

}

