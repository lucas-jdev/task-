package br.com.logiquesistemas.task.e2e;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @LocalServerPort
    private int port;

    private final RestTemplate REST = new RestTemplate();

    @Test
    @Order(0)
    void testFindAllTasks() {
        String url = "http://localhost:" + port + "/api/task";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Collection> response = REST.exchange(url, HttpMethod.GET, request, Collection.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Order(1)
    void testCreateTask(){
        var url = "http://localhost:" + port + "/api/task";

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var body = "{\"title\":\"Imersão Code Level\",\"description\":\"Evento de desenvolvimento que ocorrerá no meet\"}";

        var request = new HttpEntity<String>(body, headers);

        ResponseEntity<Void> response = REST.exchange(url, HttpMethod.POST, request, Void.class);

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
    }

    @Test
    @Order(2)
    void testFindTaskById(){
        var url = "http://localhost:" + port + "/api/task/793e39a4-987d-456f-a339-35d205d47698";

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var request = new HttpEntity<String>(headers);

        ResponseEntity<String> response = REST.exchange(url, HttpMethod.GET, request, String.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Order(3)
    void testUpdateTask(){
        var url =  "http://localhost:" + port + "/api/task/793e39a4-987d-456f-a339-35d205d47698";

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var body = "{\"title\":\"Imersão Code Level\",\"description\":\"Evento de desenvolvimento que ocorrerá no meet\"}";

        var request = new HttpEntity<String>(body, headers);

        ResponseEntity<Void> response = REST.exchange(url, HttpMethod.PUT, request, Void.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Order(4)
    void testDeleteTask(){
        var url = "http://localhost:" + port + "/api/task/793e39a4-987d-456f-a339-35d205d47698";

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var request = new HttpEntity<String>(headers);

        ResponseEntity<Void> response = REST.exchange(url, HttpMethod.DELETE, request, Void.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }
    
    

}