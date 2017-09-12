package cn.xujiajun.tastjava.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

public class HelloResourceTest {
    private static final String REST_URI = "http://localhost:8080/tastjava/hi";

    private Client client = ClientBuilder.newClient();

    @Test
    public void sayHelloWordTest() {
        WebTarget target = client.target(REST_URI);
        String response = target.request(MediaType.TEXT_PLAIN_TYPE).get(String.class);
        assertEquals("Hello world!", response);
    }

    @Test
    public void sayHelloNameTest() {
        WebTarget target = client.target(REST_URI);
        WebTarget targetUpdated = target.path("/tastjava");
        String response = targetUpdated.request(MediaType.TEXT_PLAIN_TYPE).get(String.class);
        assertEquals("Hello,tastjava", response);
    }
}
