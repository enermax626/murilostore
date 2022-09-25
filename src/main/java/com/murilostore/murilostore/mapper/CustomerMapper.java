package com.murilostore.murilostore.mapper;

import com.murilostore.murilostore.dto.request.CustomerReqDTO;
import com.murilostore.murilostore.dto.response.CustomerResDTO;
import com.murilostore.murilostore.model.Customer;
import util.CpfUtils;

public class CustomerMapper {

    public static CustomerResDTO toCustomerResDTO(Customer customer){
        CustomerResDTO customerResDTO = new CustomerResDTO();
        customerResDTO.setName(customer.getName());
        customerResDTO.setAge(customer.getAge());
        customerResDTO.setCpf(customer.getCpf());
        customerResDTO.setEmail(customer.getEmail());
        return customerResDTO;
    }

    public static Customer toCustomer(CustomerReqDTO customerReqDTO){
        Customer customer = new Customer();
        customer.setName(customerReqDTO.getName().toLowerCase());
        customer.setAge(customerReqDTO.getAge());
        customer.setCpf(CpfUtils.filterCpf(customerReqDTO.getCpf()));
        customer.setEmail(customerReqDTO.getEmail().toLowerCase());
        return customer;
    }
}
