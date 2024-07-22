//package com.home.Service;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.*;
//
////import org.json.JSONObject;
//
//@Service
//public class BitlyService {
//
//    @Value("${bitly.access.token}")
//    private String accessToken;
//
//    private final String BITLY_API_URL = "https://api-ssl.bitly.com/v4/shorten";
//
//    public String shortenUrl(String longUrl) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(accessToken);
//
//        JSONObject request = new JSONObject();
//        request.put("long_url", longUrl);
//
//        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(BITLY_API_URL, entity, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            JSONObject responseBody = new JSONObject(response.getBody());
//            return responseBody.getString("link");
//        } else {
//            throw new RuntimeException("Error shortening URL: " + response.getStatusCode());
//        }
//    }
//}
//
