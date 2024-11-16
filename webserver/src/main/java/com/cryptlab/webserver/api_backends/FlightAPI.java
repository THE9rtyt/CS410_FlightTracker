package com.cryptlab.webserver.api_backends;

import com.cryptlab.webserver.WebserverApplication;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Location;
import com.amadeus.resources.CheckinLink;
import com.amadeus.resources.DatedFlight;

import com.data_objects.TripsResponse.TripResponse;
import com.data_objects.TripsResponse.TripResponse.SegmentResponse;
import com.data_objects.TripsRequest.TripRequest;
import com.data_objects.TripsRequest.TripRequest.SegmentRequest;

public class FlightAPI {

  Amadeus amadeus;

  // string used in place of members who don't have a value currently
  private static final String noValueString ="None";
  // to pause between amadeus requests in milliseconds
  private static final long pauseTime = 50;

  public FlightAPI() {
    amadeus = WebserverApplication.getAmadeusObject();
  }

  public TripResponse getTripStatus(TripRequest trip) {
    // flights for each segment
    DatedFlight[] flights = new DatedFlight[trip.segments().length];

    //airports that will be traveled
    Location[] airports = new Location[trip.segments().length+1];

    // try getting checkinLinks
    String checkInLink = null;

    try { //try amadeus API stuff
      // gather the flight from each segment
      for(int i = 0; i < flights.length; i++) {
        // obtain flight data from each flight being taken
        flights[i] = getFlightStatus(trip.segments()[i]);
        Thread.sleep(pauseTime);
      }

      // build airport list from segments
      // get origin from first segment
      airports[0] = getAirportFromIata(flights[0].getSegments()[flights[0].getSegments().length-1].getBoardPointIataCode());
      Thread.sleep(pauseTime);

      // get destination from all the segments
      for(int i = 0; i < flights.length; i++) {
        airports[i+1] = getAirportFromIata(flights[i].getSegments()[flights[i].getSegments().length-1].getOffPointIataCode());
        Thread.sleep(pauseTime);
      }

      CheckinLink[] checkInLinks = getCheckInLink(flights[0].getFlightDesignator().getCarrierCode());
      checkInLink = (checkInLinks.length != 0) ? checkInLinks[0].getHref() : noValueString;
    } catch (ResponseException e) {
      System.out.println("[flightAPI] an error has occured: " + e.getDescription());
      //print only the last 5 calls of the stack trace
      int calls = 8;
      System.err.println("[flightAPI] last " + calls + " calls of the stack trace:");
      for(int i = 0; i < calls; i++)
        System.err.println(e.getStackTrace()[i]);

      return null;
    } catch (InterruptedException e) {
      System.err.println("[flightAPI] interrupted");
      return null;
    } //try catch amadeus API stuff


    // trip origin and destination information
    String tripOriginCode = airports[0].getIataCode();
    String tripDestinationCode = airports[airports.length-1].getIataCode();
    String tripOriginCity = airports[0].getDetailedName();
    String tripDestinationCity = airports[airports.length-1].getDetailedName();

    // build layover array from airports but only the inside ones
    var layoverAirports = new String[airports.length-2];
    for(int i = 0; i < layoverAirports.length; i++) {
      layoverAirports[i] =  airports[i+1].getIataCode();
    }
    
    // get start time from first flight's departure time
    String tripStartDateTime = flights[0].getFlightPoints()[0].getDeparture().getTimings()[0].getValue();

    // build segments array from flight and airport data
    var segments = new SegmentResponse[trip.segments().length];
    for(int i = 0; i < segments.length; i++) {
      segments[i] = createSegmentResponse(flights[i], airports[i], airports[i+1]);
    }

    return new TripResponse(
      tripOriginCode,
      tripDestinationCode,
      tripOriginCity,
      tripDestinationCity,
      layoverAirports,
      tripStartDateTime,
      checkInLink,
      segments
    );
  }

  // amadeus api helper function for on demand flight status
  private DatedFlight getFlightStatus(SegmentRequest segment) throws ResponseException {
    System.out.println("Getting flight status for: " + segment.airlineIATA() + segment.flightNumber() + " on " + segment.departureDate());

    int tries = 0;
    DatedFlight ret = null;
    while(ret == null) {
      try {
        ret = amadeus.schedule.flights.get(
            Params.with("carrierCode", segment.airlineIATA())
            .and("flightNumber", segment.flightNumber())
            .and("scheduledDepartureDate", segment.departureDate())
          )[0];
      } catch(ResponseException e) {
        tries++;
        checkResponseException(e, tries);
      }
    }

    return ret;
  }

  // amadeus api helper function for airport & city search
  private Location getAirportFromIata(String Iata) throws ResponseException {
    System.out.println("Getting airport from IATA: " + Iata);

    int tries = 0;
    Location ret = null;
    while(ret == null) {
      try {
        ret = amadeus.referenceData.locations.get(
          Params.with("keyword", Iata)
          .and("subType", Locations.ANY))[0];
      } catch(ResponseException e) {
        tries++;
        checkResponseException(e, tries);
      }
    }

    return ret;
  }

  // amadeus api helper function for flight check in links
  private CheckinLink[] getCheckInLink(String carrierCode) throws ResponseException {
    System.out.println("Getting checkin link for carrier: " + carrierCode);

    int tries = 0;
    CheckinLink[] ret = null;
    while(ret == null) {
      try {
        ret = amadeus.referenceData.urls.checkinLinks.get(Params.with("airlineCode", carrierCode));
      } catch(ResponseException e) {
        tries++;
        checkResponseException(e, tries);
      }
    }

    return ret;
  }

  private void checkResponseException(ResponseException e, int tries) throws ResponseException{
    var time = pauseTime;
    if(e.getDescription().startsWith("[500]") && tries < 5) { // 500 amadeus internal error occured
      System.out.println("Got 500 response, retrying. tries: " + tries);
    } else if(e.getDescription().startsWith("[429]") &&  tries < 5) { // 429 amadeus rate limit hit
      System.out.println("Got 429 response, retrying after " + time + " ms");
      time = pauseTime*tries + 50; //adjust pause time to slow down
    } else {
      throw e;
    }
    try {
      Thread.sleep(time);
    } catch (InterruptedException e1) {
      System.out.println("429 response wait was interrupted");
      throw e;
    }
  }

  private SegmentResponse createSegmentResponse(DatedFlight flight, Location originLocation, Location destinationLocation) {
    // basic flight information
    String airline = flight.getFlightDesignator().getCarrierCode();
    int flightNumber = flight.getFlightDesignator().getFlightNumber();
    String aircraftType = flight.getLegs()[0].getAircraftEquipment().getAircraftType();
    String duration = flight.getSegments()[0].getScheduledSegmentDuration();
    
    // origin flight information
    String origin = originLocation.getIataCode();
    var departure = flight.getFlightPoints()[0].getDeparture();
    String dateTime = departure.getTimings()[0].getValue();
    String originGate = (departure.getGate() == null) ? noValueString : departure.getGate().getMainGate();
    String originTerminal = (departure.getTerminal() == null) ? noValueString : departure.getTerminal().getCode();

    Double originAirporLatitude = originLocation.getGeoCode().getLatitude();
    Double originAirporLongitude = originLocation.getGeoCode().getLongitude();
    String originTimeZoneOffset = originLocation.getTimeZoneOffset();
    
    // destination flight information
    String destination = destinationLocation.getIataCode();
    var arrival = flight.getFlightPoints()[1].getArrival();
    String destinationGate = (arrival.getGate() == null) ? noValueString : arrival.getGate().getMainGate();
    String destinationTerminal = (arrival.getTerminal() == null) ? noValueString : arrival.getTerminal().getCode();
      
    Double destinationAirporLatitude = destinationLocation.getGeoCode().getLatitude();
    Double destinationAirporLongitude = destinationLocation.getGeoCode().getLongitude();
    String destinationTimeZoneOffset = destinationLocation.getTimeZoneOffset();

    // miscellaneous information
    String onTimePrediction = null;   
    String baggageClaim = null;
    boolean isDelayed = false;
    
    return new SegmentResponse(
      dateTime,
      airline,
      flightNumber,
      aircraftType,
      duration,
      origin,
      destination,
      originGate,
      destinationGate,
      originTerminal,
      destinationTerminal,
      onTimePrediction,
      baggageClaim,
      originAirporLatitude,
      originAirporLongitude,
      destinationAirporLatitude,
      destinationAirporLongitude,
      originTimeZoneOffset,
      destinationTimeZoneOffset,
      isDelayed
    );
  }
}
