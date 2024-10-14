package com.cryptlab.webserver.paths;

import org.springframework.web.bind.annotation.RestController;

import com.cryptlab.webserver.api_backends.randStringAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class RandomController {
    randStringAPI api;

    public RandomController() {
        api = new randStringAPI();
    }
    
    @GetMapping("/random_strings")
    public String getRandomStrings(@RequestParam(value = "size", defaultValue = "10") String param) {
        
        return api.get();
    }
}
