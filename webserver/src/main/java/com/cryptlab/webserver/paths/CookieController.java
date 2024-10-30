package com.cryptlab.webserver.paths;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


//switch to using responsecookie in vector
//read cookies
//delete expired cookies
//send new generated cookie if cookie expired

@RestController
public class CookieController {
    private Vector<String> cookies;

    public CookieController () {
        cookies = new Vector<>();

    }

    private String generateCookie () {
        Random r = new Random();
        String str = "";
        for (int i = 0; i < 23; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            str = str + c;
        }
        return str;
    }

    private boolean addCookie (String cookie) {
        if (cookies.contains(cookie)) {
            return false;
        }
        cookies.add(cookie);
        saveCookies();
        return true;
    }

    private boolean cookieExist (String cookie) {
        return cookies.contains(cookie);
    }
    private boolean deleteCookie (String cookie) {
        if (cookies.remove(cookie)) {
            File f = new File("cookies");
            if (f.exists()) {
                f.delete();
            }
            saveCookies();
            return true;
        }
        return false;
    }

    private void saveCookies () {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cookies"))) {
            for (String i : cookies) {
                bw.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCookies () {

    }

    @GetMapping("/new_cookie")
    public ResponseEntity<String> newCookie() {
        String cookie_string = "";
        while (true) {
            cookie_string = generateCookie();
            if (!cookieExist(cookie_string)) {
                addCookie(cookie_string);
                break;
            }
        }
        ResponseCookie cookie = ResponseCookie.from("login-cookie", cookie_string)
        .httpOnly(true)
        .secure(true)
        .maxAge(100)
        .build();

        return ResponseEntity
        .ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .build();
    }
    @GetMapping("/set_cookie")
    public ResponseEntity<String> setCookie(@RequestParam String param) {
        if (cookieExist(param)) {
            return ResponseEntity
            .ok()
            .build();
        }
        return ResponseEntity
        .notFound()
        .build();
    }
    
}
