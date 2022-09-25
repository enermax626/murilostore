package com.murilostore.murilostore.service;

import com.murilostore.murilostore.dto.request.CustomerReqDTO;
import com.murilostore.murilostore.dto.response.CustomerResDTO;
import com.murilostore.murilostore.exception.CustomerAlreadyExistsException;
import com.murilostore.murilostore.exception.CustomerNotFoundException;
import com.murilostore.murilostore.mapper.CustomerMapper;
import com.murilostore.murilostore.model.Customer;
import com.murilostore.murilostore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResDTO getCustomer(Long id) {
        return customerRepository.findById(id).map(CustomerMapper::toCustomerResDTO).orElseThrow(() -> new CustomerNotFoundException("customer.not.found"));
    }

    public CustomerResDTO addCustomer(CustomerReqDTO customerReqDTO) {
        Customer customer = CustomerMapper.toCustomer(customerReqDTO);
        customerRepository.findByCpf(customer.getCpf()).ifPresent(c -> {
            throw new CustomerAlreadyExistsException("customer.already.exists");
        });

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toCustomerResDTO(savedCustomer);
    }

}
