package com.cryptlab.webserver.api_backends;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.DatedFlight;
import com.cryptlab.webserver.WebserverApplication;
import com.data_objects.TripResponse;
import com.data_objects.TripResponse.SegmentResponse;
import com.data_objects.TripRequest.SegmentRequest;

public class FlightAPI {

  Amadeus amadeus;

  public FlightAPI() {
    amadeus = WebserverApplication.getAmadeusObject();
  }

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

    String dateTime = flight.getScheduledDepartureDate();
    String airline = flight.getFlightDesignator().getCarrierCode();
    int flightNumber = flight.getFlightDesignator().getFlightNumber();
    String aircraftType = flight.getLegs()[0].getAircraftEquipment().getAircraftType();
    String duration = flight.getSegments()[0].getScheduledSegmentDuration();
    String origin = flight.getSegments()[0].getBoardPointIataCode();
    String destination = flight.getLegs()[0].getOffPointIataCode();

    var departure = flight.getFlightPoints()[0].getDeparture();
    var tempGate = departure.getGate();
    String originGate = (tempGate == null) ? "N/A" : tempGate.getMainGate();
    var tempTerminal = departure.getTerminal();
    String originTerminal = (tempTerminal == null) ? "N/A" : tempTerminal.getCode();

    String destinationGate, destinationTerminal;
    var arrival = flight.getFlightPoints()[1].getArrival();
    if(arrival == null) {
      destinationGate = "N/A2";
      destinationTerminal = "N/A2";
    } else {
      tempGate = arrival.getGate();
      destinationGate = (tempGate == null) ? "N/A" : tempGate.getMainGate();
      tempTerminal = arrival.getTerminal();
      destinationTerminal = (tempTerminal == null) ? "N/A" : tempTerminal.getCode();
    }

    String onTimePrediction = null;
      // segment.addProperty("onTimePrediction", flight.getLegs()[0].getOnTimePrediction());
      
    String baggageClaim = null;
      // segment.addProperty("baggageClaim", flight.getLegs()[0].getBaggageClaim());
    String originAirporLatitude = null;
      // segment.addProperty("originAirporLatitude", flight.getLegs()[0].getOrigin().getLatitude());
    String originAirporLongitude = null;
      // segment.addProperty("originAirporLongitude", flight.getLegs()[0].getOrigin().getLongitude());
      
    String destinationAirporLatitude = null;
      // segment.addProperty("destinationAirporLatitude", flight.getLegs()[0].getDestination().getLatitude());
    String destinationAirporLongitude = null;
      // segment.addProperty("destinationAirporLongitude", flight.getLegs()[0].getDestination().getLongitude());
      
    String originTimeZoneOffset = null;
      // segment.addProperty("originTimeZoneOffset", flight.);
    String destinationTimeZoneOffset = null;
      // segment.addProperty("destinationTimeZoneOffset", flight.getLegs()[0].getDestination().getTimeZoneOffset());
    boolean isDelayed = false;
      // segment.addProperty("isDelayed", flight.getLegs()[0].getIsDelayed());
      
      
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

  public TripResponse createTripResponse(SegmentResponse[] segments) {

    String tripOriginCode = segments[0].origin();
    String tripDestinationCode = segments[segments.length-1].destination();
    
    String tripOriginCity = null;
    String tripDestinationCity = null;
    // try {
    //   var originLocation = amadeus.referenceData.location(tripOriginCode).get();
    //   tripOriginCity = originLocation.getDetailedName();

    //   var destinationLocation = amadeus.referenceData.location(tripDestinationCode).get();
    //   tripDestinationCity = destinationLocation.getDetailedName();
    // } catch (ResponseException e) {
    //   System.out.println("[FlightAPI.TripResponse] an error has occured: " + e.getDescription());
    //   return null;
    // }


    var layoverAirports = new String[segments.length-1];
    for(int i = 0; i < segments.length-1; i++) {
      layoverAirports[i] =  segments[i+1].origin();
    }

    String tripStartDateTime = segments[0].dateTime();
    String checkInLink = null;

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
