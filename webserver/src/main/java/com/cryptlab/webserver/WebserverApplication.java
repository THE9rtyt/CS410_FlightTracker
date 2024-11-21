package com.cryptlab.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amadeus.Amadeus;

@SpringBootApplication
public class WebserverApplication {

	private static Amadeus amadeus;

	public static void main(String[] args) {

    	amadeus = Amadeus
        .builder("Add API Key Here")
        .build();

		SpringApplication.run(WebserverApplication.class, args);
	}

	public static Amadeus getAmadeusObject() throws NullPointerException{
		if(amadeus != null) {
			return amadeus;
		}
		throw new NullPointerException();
	}
}
