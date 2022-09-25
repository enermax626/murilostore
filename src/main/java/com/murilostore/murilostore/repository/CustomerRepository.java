package com.murilostore.murilostore.repository;

import java.util.Optional;

import com.murilostore.murilostore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Object> findByCpf(String cpf);
}
