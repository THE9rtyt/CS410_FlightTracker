package com.cryptlab.webserver.api_backends;



import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;



public class randStringAPI extends API<String> {

    public randStringAPI() {
        super("https://qrandom.io/api/random/string");
    }

    @Override
    public String get() {
        RestClient rest_client = RestClient.create();
        String result = rest_client.get()
        .uri(url)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(String.class);

        JsonParser parse = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parse.parseMap(result);
        for (Map.Entry<String, Object> i : map.entrySet()) {
            System.out.println( i.getKey() + ":\n" + map.get(i.getKey()));
        }
        @SuppressWarnings("unchecked")
        List<String> str = (List<String>) map.get("string");
        System.out.println(str);
        return str.get(0);
    }

    @Override
    public void post(String arr) {
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }
    
}
