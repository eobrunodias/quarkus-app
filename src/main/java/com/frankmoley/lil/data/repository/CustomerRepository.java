package com.frankmoley.lil.data.repository;

import com.frankmoley.lil.data.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public Customer findByEmail(String email) {
       return find("email", email).firstResult();
    }

}
