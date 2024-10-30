package com.cryptlab.webserver.paths;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
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


class CookieData {
    public long duration;
    public long timestamp;
    public String value;

    public CookieData (long duration, long timestamp, String value) {
        this.duration = duration;
        this.timestamp = timestamp;
        this.value = value;
    }
}


@RestController
public class CookieController {
    private Vector<CookieData> cookies;

    public CookieController () {
        cookies = new Vector<>();
        readCookies();
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
        for (CookieData rc : cookies) {
            if (rc.value.equals(cookie)) {
                return false;
            }
        }
        cookies.add( 
            new CookieData(10000, System.currentTimeMillis(), cookie)
        );
        saveCookies();
        return true;
    }

    private boolean cookieExist (String cookie) {
        for (CookieData rc : cookies) {
            if (rc.value.equals(cookie)) {
                return true;
            }
        }
        return false;
    }
    private boolean deleteCookie (String cookie) {
        int index = -1;
        for (int i = 0; i < cookies.size(); i++) {
            if (cookies.get(i).value.equals(cookie)) {
                index = i;
                break;
            }
        }
        if (index >= 0 && cookies.remove(index) != null) {
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
            for (CookieData i : cookies) {
                bw.write(i.value + "," + i.duration + "," + i.timestamp + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCookies () {
        File f = new File("cookies");
        if (!f.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("cookies"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 3) {
                    String value = arr[0];
                    long dur = Long.parseLong(arr[1]);
                    long timestamp = Long.parseLong(arr[2]);
                    cookies.add(new CookieData(dur, timestamp, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CookieData getCookie (String cookie) {
        for (CookieData i : cookies) {
            if (i.value.equals(cookie)) {
                return i;
            }
        }
        return null;
    }

    private boolean checkExpired (CookieData cookie) {
        long current = System.currentTimeMillis();
        long total = cookie.duration + cookie.timestamp;
        if (total <= current) {
            deleteCookie(cookie.value);
            return true;
        }
        return false;
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
        .maxAge(10000)
        .build();

        return ResponseEntity
        .ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .build();
    }
    @GetMapping("/set_cookie")
    public ResponseEntity<String> setCookie(@RequestParam String logincookie) {
        CookieData cookie = getCookie(logincookie);
        if (cookie != null && checkExpired(cookie)) {
            deleteCookie(logincookie);
            System.out.println("Deleted requested cookie");
        }
        else if (cookieExist(logincookie)) {
            return ResponseEntity
            .ok()
            .build();
        }
        return ResponseEntity
        .status(406)
        .build();
    }
    
}
