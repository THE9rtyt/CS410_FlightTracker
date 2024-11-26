package com.cryptlab.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amadeus.Amadeus;
import com.cryptlab.webserver.api_backends.FlightBookingAPI;

@SpringBootApplication
public class WebserverApplication {

	private static Amadeus amadeus;

	public static void main(String[] args) {

    	amadeus = Amadeus
        .builder("Add API Key Here")
        .build();

		SpringApplication.run(WebserverApplication.class, args);

		try {
        // Create an instance of the class containing the flightOffers method
        FlightBookingAPI api = new FlightBookingAPI();

        // Debugging: Print initialization status
        System.out.println("Amadeus API client initialized.");

        // Test data for flight search
        String origin = "JFK"; // Example origin airport (New York)
        String destination = "LAX"; // Example destination airport (Los Angeles)
        String departureDate = "2024-12-01"; // Example departure date

        // Debugging: Print inputs
        System.out.println("Searching for flights:");
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Departure Date: " + departureDate);

        // Call the flightOffers method
        String[] flightOffers = api.flightOffers(origin, destination, departureDate);

        // Check and print the results
        if (flightOffers != null && flightOffers.length > 0) {
            System.out.println("\nFlight Offers:");
            for (String offer : flightOffers) {
                System.out.println(offer);
            }
        } else {
            System.out.println("No flight offers found.");
        }
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
    }
	}

	public static Amadeus getAmadeusObject() throws NullPointerException{
		if(amadeus != null) {
			return amadeus;
		}
		throw new NullPointerException();
	}
}
