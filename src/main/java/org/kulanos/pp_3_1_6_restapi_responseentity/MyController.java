package org.kulanos.pp_3_1_6_restapi_responseentity;

import org.kulanos.pp_3_1_6_restapi_responseentity.model.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    private String sessionId;

    private String finalCode="";


    private RestTemplate restTemplate;

    public MyController() {
        this.restTemplate = new RestTemplate();
    }

    static String baseURL = "http://94.198.50.185:7081/api/users";

    @GetMapping("/api/users")
    public ResponseEntity<String> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(baseURL, HttpMethod.GET, requestEntity, String.class);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        String cookieHeader = responseHeaders.getFirst(HttpHeaders.SET_COOKIE);
        if (cookieHeader != null) {
            sessionId = cookieHeader.split(";")[0];
        }
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> saveUser() {
        User user = new User(3L, "James", "Brown", (byte) 33);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseURL, HttpMethod.POST, requestEntity, String.class);
        finalCode += responseEntity.getBody();
        return responseEntity;
    }
    @PutMapping("/api/users")
    public ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 33);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseURL, HttpMethod.PUT, requestEntity, String.class);
        finalCode += responseEntity.getBody();
        return responseEntity;
    }

    @DeleteMapping("/api/users/3")
    public ResponseEntity<String> deleteUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseURL + "/3", HttpMethod.DELETE, requestEntity, String.class);
        finalCode += responseEntity.getBody();
        System.out.println(finalCode);
        return responseEntity;
    }
}
