package com.cryptlab.webserver.api_backends;



import java.util.Iterator;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



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

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(result);
            List<String> str = node.findValuesAsText("string");
            System.out.println(str);
            Iterator<String> it = node.fieldNames();
            while(it.hasNext()) {
                
                System.out.println(it.next());
            }
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        return result;
    }

    @Override
    public void post(String arr) {
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }
    
}
