package com.data_objects;

public record TripsRequest(TripRequest[] trips) {

  public record TripRequest(SegmentRequest[] segments) {

    public record SegmentRequest(String airlineIATA, int flightNumber, String departureDate) {

    }
  }
}
