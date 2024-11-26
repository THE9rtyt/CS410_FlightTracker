package com.cryptlab.webserver.paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOrder;
import com.cryptlab.webserver.api_backends.FlightBookingAPI;


// handling HTTP requests
@RestController
public class FlightBookingController {

    private final FlightBookingAPI flightBookingAPI;

    public FlightBookingController() {
        this.flightBookingAPI = new FlightBookingAPI();
    }

    //A client sends a GET request like this: GET /shopping/flight-offers?origin=JFK&destination=LAX&departureDate=2024-12-15
    @GetMapping("/shopping/flight-offers") 
    public String[] flightOffers(
            @RequestParam String origin, // IATA code of the origin airport.
            @RequestParam String destination, //IATA code of the destination airport.
            @RequestParam String departureDate) { //Date of departure in "YYYY-MM-DD" format.
        return flightBookingAPI.flightOffers(origin, destination, departureDate); //return a list of flight offers.
    }

    // GET /shopping/seatmaps?flight-orderid=qyuwoqhhuhhyuuby
    @GetMapping("/shopping/seatmaps") 
    public String[] seatmap(@RequestParam String flightorderId) { 
      return flightBookingAPI.getSeatMap(flightorderId);
    }

    @PostMapping("/booking/flight-orders") 
    public FlightOrder createOrder(String Id) throws ResponseException{ //selected index of FlightOffers
        return flightBookingAPI.createOrder(Id);
    }
}

