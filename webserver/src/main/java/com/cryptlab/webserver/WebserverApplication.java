package com.cryptlab.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Location;

@SpringBootApplication
public class WebserverApplication {

	public static void main(String[] args) {
		System.out.println("hello amadeus");

		SpringApplication.run(WebserverApplication.class, args);
		
	    Amadeus amadeus = Amadeus
	            .builder("UHYoiuW5IEBc4SmWhrqDYGw7oYAl2GNJ", "4SU1BnL9EU3Tn2sO")
	            .build();

	    Location[] locations;
			try {
				locations = amadeus.referenceData.locations.get(Params
				  .with("keyword", "LON")
				  .and("subType", Locations.ANY));

				for(var location : locations) {
					System.out.println(location);
				}
			} catch (ResponseException e) {
				e.printStackTrace();
			}

	}

	

}
