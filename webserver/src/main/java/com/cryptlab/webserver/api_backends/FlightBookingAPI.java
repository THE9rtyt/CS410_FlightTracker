package com.cryptlab.webserver.api_backends;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

public class FlightBookingAPI {

    private final Amadeus amadeus;

    public FlightBookingAPI(String apiKey, String apiSecret) {
        this.amadeus = Amadeus.builder(apiKey, apiSecret).build();
    }
    
    //Searches for available flights based on origin, destination, and departure date
    public FlightOfferSearch[] searchFlights(String origin, String destination, String departureDate) {
        try {
            return amadeus.shopping.flightOffersSearch.get( //Return the array of FlightOffer objects containing flight options
                Params.with("originLocationCode", origin) //IATA code for the origin airport
                      .and("destinationLocationCode", destination) //IATA code for the destination airport
                      .and("departureDate", departureDate) //Date of departure in "YYYY-MM-DD" format
                      .and("adults", 1) // Example for 1 adult
            );
        } catch (ResponseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FlightOrder bookFlight(FlightOfferSearch flightOfferSearch) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bookFlight'");
    }

    /**
     * Books a flight based on the selected flight offer.
     * @param flightOffer The flight offer to book.
     * @return FlightOrder with booking details if successful.
     */
    /* public FlightOrder bookFlight(FlightOfferSearch flightOffer) {
        try {
            return amadeus.booking.flightOrders.post(flightOffer);
        } catch (ResponseException e) {
            e.printStackTrace();
            return null;
        }
    } */
}
