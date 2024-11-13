package com.data_objects;

public record TripRequest(SegmentRequest[] segments) {

  public record SegmentRequest(String airlineIATA, int flightNumber, String departureDate) {

  }
}
