package com.cryptlab.webserver.paths;

import org.springframework.web.bind.annotation.RestController;

import com.data_objects.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ResourceController {
    private static final String template = "Hell %s";
    private final int fff = 10000;

    @GetMapping("/resource")
    public Resource resource(@RequestParam(value = "name", defaultValue = "resource") String param) {
        Resource r = new Resource(fff, template);
        System.out.println(r);
        return r;
    }
    
}
