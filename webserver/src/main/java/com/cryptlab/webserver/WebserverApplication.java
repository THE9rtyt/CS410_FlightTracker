package com.cryptlab.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amadeus.Amadeus;

@SpringBootApplication
public class WebserverApplication {

	private static Amadeus amadeus;

	public static void main(String[] args) {
<<<<<<< HEAD
    amadeus = Amadeus
        .builder("G8qpyMu4vioUmUXTYhvZ0Vq9kOdzIiE9", "neAAKuCcevzchzzM")
=======

    	amadeus = Amadeus
        .builder(System.getenv())
>>>>>>> origin
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
