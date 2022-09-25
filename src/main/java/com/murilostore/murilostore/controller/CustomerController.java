package com.murilostore.murilostore.controller;

import javax.validation.Valid;

import com.murilostore.murilostore.dto.request.CustomerReqDTO;
import com.murilostore.murilostore.dto.response.CustomerResDTO;
import com.murilostore.murilostore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResDTO> getCustomer(@PathVariable Long id) {
        CustomerResDTO customer = customerService.getCustomer(id);

        return ResponseEntity.ok(customer);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResDTO> addCustomer(@RequestBody @Valid CustomerReqDTO customer) {

        CustomerResDTO customerResDTO = customerService.addCustomer(customer);
        return new ResponseEntity<>(customerResDTO, HttpStatus.CREATED);
    }




}
