package com.cryptlab.webserver.paths;

import com.cryptlab.webserver.api_backends.FlightAPI;
import com.data_objects.TripsRequest;
import com.data_objects.TripsResponse;
import com.data_objects.TripsRequest.TripRequest;
import com.data_objects.TripsResponse.TripResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ResourceController {
    private FlightAPI api;

    public ResourceController() {
      api = new FlightAPI();
    }

    @RequestMapping("/resources/trips")
    @ResponseBody
    public TripsResponse resource(@RequestBody TripsRequest tripsrequest) {
      var trips = new TripResponse[tripsrequest.trips().length];
      for(int i = 0; i < trips.length; i++)
        trips[i] = api.getTripStatus(tripsrequest.trips()[i]);

      return new TripsResponse(trips);
    }

    @RequestMapping("/resources/trip")
    @ResponseBody
    public TripResponse resource(@RequestBody TripRequest trip) {
      return api.getTripStatus(trip);
    }
}
