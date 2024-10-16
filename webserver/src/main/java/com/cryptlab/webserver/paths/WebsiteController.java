package com.cryptlab.webserver.paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//feeds the index of webpage

@RestController
public class WebsiteController {
    String website;


    WebsiteController () {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(
                "website.html"
            ));
            while(sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append('\n');
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        website = sb.toString();
    }

    @GetMapping("/")
    public String getIndex(@RequestParam(value = "path", defaultValue = "/") String param) {
        return website;
    }
}
