package com.cryptlab.webserver.paths;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.cryptlab.webserver.api_backends.FlightBookingAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightBookingController {

    private final FlightBookingAPI flightBookingAPI;

    public FlightBookingController() {
        this.flightBookingAPI = new FlightBookingAPI("YOUR_API_KEY", "YOUR_API_SECRET");
    }

    @GetMapping("/searchFlights")
    public FlightOfferSearch[] searchFlights(
            @RequestParam String origin, // IATA code of the origin airport.
            @RequestParam String destination, //IATA code of the destination airport.
            @RequestParam String departureDate) { //Date of departure in "YYYY-MM-DD" format.
        return flightBookingAPI.searchFlights(origin, destination, departureDate); //return a list of flight offers.
    }

    /**
     * Books a flight based on a flight offer.
     * @param offerIndex The index of the selected flight offer (for simplicity).
     * @return The flight order confirmation.
     */
    @PostMapping("/bookFlight")
    public FlightOrder bookFlight(@RequestParam int offerIndex) {
        FlightOfferSearch[] offers = flightBookingAPI.searchFlights("JFK", "LAX", "2024-11-20"); // Example values
        if (offers != null && offerIndex < offers.length) {
            return flightBookingAPI.bookFlight(offers[offerIndex]);
        }
        return null;
    }
}
