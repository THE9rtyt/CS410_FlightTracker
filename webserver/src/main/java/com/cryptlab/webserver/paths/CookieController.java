package com.cryptlab.webserver.paths;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {
    private ConcurrentHashMap<String, String> cookies;

    public CookieController () {
        cookies = new ConcurrentHashMap<>();

    }

    private String generateCookie () {

        return "";
    }

    private boolean addCookie (String cookie) {

        return false;
    }

    private boolean cookieExist (String cookie) {

        return false;
    }
    private boolean deleteCookie (String cookie) {

        return false;
    }

    @GetMapping("/new_cookie")
    public ResponseEntity<String> newCookie() {
        ResponseCookie cookie = ResponseCookie.from("test", generateCookie())
        .httpOnly(true)
        .secure(true)
        .maxAge(100)
        .build();

        return ResponseEntity
        .ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .build();
    }
    
}
