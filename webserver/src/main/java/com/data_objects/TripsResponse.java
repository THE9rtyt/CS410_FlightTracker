package com.data_objects;

public record TripsResponse(TripResponse[] trips) {

  public record TripResponse(
      String tripOriginCode,
      String tripDestinationCode,
      String tripOriginCity,
      String tripDestinationCity,
      String[] layoverAirports,
      String tripStartDateTime,
      String checkInLink,
      SegmentResponse[] segments
    ) {
    
    public record SegmentResponse(
        String dateTime,
        String airline,
        int flightNumber,
        String aircraftType,
        String duration,
        String origin,
        String destination,
        String originGate,
        String destinationGate,
        String originTerminal,
        String destinationTerminal,
        String onTimePrediction,
        String baggageClaim,
        Double originAirporLatitude,
        Double originAirporLongitude,
        Double destinationAirporLatitude,
        Double destinationAirporLongitude,
        String originTimeZoneOffset,
        String destinationTimeZoneOffset,
        boolean isDelayed
      ) {

    }
  }
}
