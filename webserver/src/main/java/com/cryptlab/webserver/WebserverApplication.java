package com.cryptlab.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amadeus.Amadeus;

@SpringBootApplication
public class WebserverApplication {

	private static Amadeus amadeus;

	public static void main(String[] args) {

    	amadeus = Amadeus
        .builder("G8qpyMu4vioUmUXTYhvZ0Vq9kOdzIiE9", "neAAKuCcevzchzzM")
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
