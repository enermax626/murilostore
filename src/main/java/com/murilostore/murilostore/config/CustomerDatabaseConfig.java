package com.murilostore.murilostore.config;

import com.murilostore.murilostore.model.Customer;
import com.murilostore.murilostore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerDatabaseConfig {

    private final CustomerRepository customerRepository;

    @Bean
    public void addCustomers(){
        Customer customer = new Customer();
        customer.setName("Murilo");
        customer.setAge(30);
        customer.setCpf("24350613820");
        customer.setEmail("murilo@gmail.com");
        customerRepository.save(customer);

        Customer customer2 = new Customer();
        customer2.setName("Cecilia");
        customer2.setAge(26);
        customer2.setCpf("96410271077");
        customer2.setEmail("cecilia@hotmail.com");
        customerRepository.save(customer2);

    }
}
