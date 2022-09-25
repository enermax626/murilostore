package com.murilostore.murilostore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.murilostore.murilostore.dto.request.CustomerReqDTO;
import com.murilostore.murilostore.dto.response.CustomerResDTO;
import com.murilostore.murilostore.exception.ApplicationExceptionHandler;
import com.murilostore.murilostore.exception.CustomerAlreadyExistsException;
import com.murilostore.murilostore.exception.CustomerNotFoundException;
import com.murilostore.murilostore.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootTest(classes = {CustomerController.class, ApplicationExceptionHandler.class})
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void shouldGetCustomerWhenCustomerFoundThenReturnCustomer() throws Exception {

        CustomerResDTO customerResponse = CustomerResDTO.builder().name("murilo").age(30).cpf("24350613820").email("murilo@gmail.com").build();

        // given
        when(customerService.getCustomer(1L)).thenReturn(customerResponse);

        // then
        mockMvc.perform(get("/customer/1")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"name\":\"murilo\",\"age\":30,\"cpf\":\"24350613820\",\"email\":\"murilo@gmail.com\"}"));
    }

    @Test
    void shouldGetCustomerWhenCustomerNotFoundThenThrowNotFoundException() throws Exception {

        // given
        when(customerService.getCustomer(2L)).thenThrow(new CustomerNotFoundException("customer.not.found"));

        // then
        mockMvc.perform(get("/customer/2")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException))
                .andExpect(result -> assertEquals("customer.not.found", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldAddCustomerWhenCustomerPassedThenReturnCustomerInfo() throws Exception {

        // given
        CustomerResDTO customerResponse = CustomerResDTO.builder().name("murilo").age(30).cpf("24350613820").email("murilo@gmail.com").build();

        when(customerService.addCustomer(any(CustomerReqDTO.class))).thenReturn(customerResponse);

        // then
        mockMvc.perform(post("/customer")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\"name\":\"murilo\",\"age\":30,\"cpf\":\"24350613820\",\"email\":\"murilo@gmail.com\"}")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        "{\"name\":\"murilo\",\"age\":30,\"cpf\":\"24350613820\",\"email\":\"murilo@gmail.com\"}"));
    }

    @Test
    void shouldAddCustomerWhenCustomerAlreadyExistsThenThrowCustomerAlreadyExistsException() throws Exception {

        // given
        when(customerService.addCustomer(any(CustomerReqDTO.class))).thenThrow(new CustomerAlreadyExistsException("customer.already.exists"));

        // then
        mockMvc.perform(post("/customer")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\"name\":\"murilo\",\"age\":30,\"cpf\":\"24350613820\",\"email\":\"murilo@gmail.com\"}")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerAlreadyExistsException))
                .andExpect(result -> assertEquals("customer.already.exists", result.getResolvedException().getMessage()));
    }

}