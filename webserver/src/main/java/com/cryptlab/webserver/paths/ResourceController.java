package com.cryptlab.webserver.paths;

import org.springframework.web.bind.annotation.RestController;

import com.cryptlab.webserver.api_backends.FlightAPI;
import com.data_objects.TripRequest;
import com.data_objects.TripResponse;
import com.data_objects.TripResponse.SegmentResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ResourceController {
    private FlightAPI api;

    public ResourceController() {
      api = new FlightAPI();
    }

    @RequestMapping("/resources/trip")
    @ResponseBody
    public TripResponse resource(@RequestBody TripRequest trip) {

      System.out.println("new trip request: " + trip.segments().length);

      int i = 0;
      var flights = new SegmentResponse[trip.segments().length];
      for(var segment : trip.segments()) {
        int tries = 0;
        SegmentResponse temp = null;
        while((temp = api.getFlightStatus(segment)) == null) {
          tries++;
          if(tries >= 5) {
            System.out.println("failed to get flight status");
            return null;
          }
          try {
            System.err.println("Failed to get flight status, retrying");
            Thread.sleep(100*tries+50);
          } catch (InterruptedException e) {
            return null;
          }
        }
        flights[i++] = temp;
      }

      return api.createTripResponse(flights);
    }

    // @GetMapping("/resources/flight")
    // public Segment[] resource(@RequestParam(value = "carrier", defaultValue = "null") String carrier,
    //         @RequestParam(value = "flightNumber", defaultValue = "null") String number,
    //         @RequestParam(value = "scheduledDepartureDate", defaultValue = "null") String date) {

    //     //check for missing fields
    //     if(carrier.equals("null") || number.equals("null") || date.equals("null")) return null; // new Resource("error", "bad request!");
    //     // if api is not initialized
    //     if(this.api == null) return null;

    //     System.out.println("flight requested: " + carrier + number + " On: " + date);
    //     Segment[] info;
    //     try {
    //       // info = api.getFlightStatus(carrier, number, date);
    //     } catch (ResponseException e) {
    //       e.printStackTrace();
    //       return null;
    //     }
    //     System.out.println("flight response: " + info.toString());

    //     return info;
    // }

}
