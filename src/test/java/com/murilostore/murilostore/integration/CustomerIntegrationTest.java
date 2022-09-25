package com.murilostore.murilostore.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.murilostore.murilostore.dto.request.CustomerReqDTO;
import com.murilostore.murilostore.dto.response.CustomerResDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

    @Value("${test.base-url}")
    private String baseUrl;

    private static RestTemplate restTemplate;

    @BeforeAll
    static void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void shouldGetCustomer() {
        ResponseEntity<CustomerResDTO> response = restTemplate.getForEntity(baseUrl.concat("/customer/1"), CustomerResDTO.class);

        assertEquals("Murilo", response.getBody().getName());
        assertEquals(30, response.getBody().getAge());
        assertEquals("murilo@gmail.com", response.getBody().getEmail());
    }

    @Test
    public void shouldAddCustomerWhenCustomerNotExists() {
        CustomerReqDTO customer = CustomerReqDTO.builder().name("carlos").age(28).cpf("147.447.680-59").email("carlos@gmail.com").build();

        ResponseEntity<CustomerResDTO> response = restTemplate.postForEntity(baseUrl.concat("/customer"), customer,
                                                                             CustomerResDTO.class);
        assertEquals("carlos", response.getBody().getName());
        assertEquals(28, response.getBody().getAge());
        assertEquals("carlos@gmail.com", response.getBody().getEmail());
    }

    @Test
    public void shouldNotAddCustomerWhenCustomerAlreadyExists() {
        CustomerReqDTO customer = CustomerReqDTO.builder().name("diego").age(31).cpf("99914320007").email("diego@gmail.com").build();

        assertThrows(HttpClientErrorException.BadRequest.class,
                     () -> restTemplate.postForEntity(baseUrl.concat("/customer"), customer,
                                                      CustomerResDTO.class));
    }

}
