package com.cryptlab.webserver.api_backends;

import com.cryptlab.webserver.WebserverApplication;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Location;
import com.amadeus.resources.CheckinLink;
import com.amadeus.resources.DatedFlight;

import com.data_objects.TripResponse;
import com.data_objects.TripResponse.SegmentResponse;
import com.data_objects.TripRequest.SegmentRequest;

public class FlightAPI {

  Amadeus amadeus;

  //string used in place of members who don't have a value currently
  private static final String noValueString ="None";

  public FlightAPI() {
    amadeus = WebserverApplication.getAmadeusObject();
  }

  //turns a Segment Request into a Segment Reponse
  public SegmentResponse getFlightStatus(SegmentRequest segment) {
    // System.out.println("[flightAPI] getting flight data for: " + segment.airlineIATA() + segment.flightNumber() + " on: " + segment.departureDate());

    DatedFlight[] flightStatus;
    try {
      flightStatus = amadeus.schedule.flights.get(
        Params.with("carrierCode", segment.airlineIATA())
        .and("flightNumber", segment.flightNumber())
        .and("scheduledDepartureDate", segment.departureDate())
      );
      if(flightStatus.length < 1) throw new Exception("Flight not found");
    } catch (Exception e) {
      System.out.println("[FlightAPI.getFlightStatus] an error has occured: " + e.getMessage());
      return null;
    }
    var flight = flightStatus[0];
    // System.out.println(flight);

    String airline = flight.getFlightDesignator().getCarrierCode();
    int flightNumber = flight.getFlightDesignator().getFlightNumber();
    String aircraftType = flight.getLegs()[0].getAircraftEquipment().getAircraftType();
    String duration = flight.getSegments()[0].getScheduledSegmentDuration();
    String origin = flight.getSegments()[0].getBoardPointIataCode();
    String destination = flight.getLegs()[0].getOffPointIataCode();
    
    var departure = flight.getFlightPoints()[0].getDeparture();
    String dateTime = departure.getTimings()[0].getValue();
    var tempGate = departure.getGate();
    String originGate = (tempGate == null) ? noValueString : tempGate.getMainGate();
    var tempTerminal = departure.getTerminal();
    String originTerminal = (tempTerminal == null) ? noValueString : tempTerminal.getCode();
    
    var arrival = flight.getFlightPoints()[1].getArrival();
    tempGate = arrival.getGate();
    String destinationGate = (tempGate == null) ? noValueString : tempGate.getMainGate();
    tempTerminal = arrival.getTerminal();
    String destinationTerminal = (tempTerminal == null) ? noValueString : tempTerminal.getCode();

    Location[] originLocation;
    Location[] destinationLocation;
    try {
      originLocation = amadeus.referenceData.locations.get(Params.with("keyword", origin).and("subType", Locations.ANY));
      destinationLocation = amadeus.referenceData.locations.get(Params.with("keyword", destination).and("subType", Locations.ANY));
      if(originLocation.length < 1 || destinationLocation.length < 1) throw new Exception("locations find failure!");
    } catch (Exception e) {
      System.out.println("[FlightAPI.getFlightStatus] an error has occured: " + e.getMessage());
      return null;
    }

    Double originAirporLatitude = originLocation[0].getGeoCode().getLatitude();
    Double originAirporLongitude = originLocation[0].getGeoCode().getLongitude();
    String originTimeZoneOffset = originLocation[0].getTimeZoneOffset();
      
    Double destinationAirporLatitude = destinationLocation[0].getGeoCode().getLatitude();
    Double destinationAirporLongitude = destinationLocation[0].getGeoCode().getLongitude();
    String destinationTimeZoneOffset = destinationLocation[0].getTimeZoneOffset();

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

  //turns a Trip Request into a Trip Reponse
  public TripResponse createTripResponse(SegmentResponse[] segments) {

    String tripOriginCode = segments[0].origin();
    String tripDestinationCode = segments[segments.length-1].destination();
    
    String tripOriginCity = null;
    String tripDestinationCity = null;


    var layoverAirports = new String[segments.length-1];
    for(int i = 0; i < segments.length-1; i++) {
      layoverAirports[i] =  segments[i+1].origin();
    }
    
    String tripStartDateTime = segments[0].dateTime();
    
    CheckinLink[] checkinLinks;
    try {
      checkinLinks = amadeus.referenceData.urls.checkinLinks.get(Params.with("airlineCode", tripOriginCode));
    } catch (ResponseException e) {
      System.out.println("[FlightAPI.createTripResponse] an error has occured: " + e.getMessage());
      return null;
    }
    String checkInLink = (checkinLinks.length == 0) ? noValueString : checkinLinks[0].getHref();

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
}
