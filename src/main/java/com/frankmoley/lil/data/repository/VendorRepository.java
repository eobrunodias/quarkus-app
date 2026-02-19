package com.frankmoley.lil.data.repository;

import com.frankmoley.lil.data.entity.Vendor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VendorRepository implements PanacheRepository<Vendor> {

    public Vendor findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Vendor findByName(String name) {
        return find("name = :name", Parameters.with("name", name))
                .firstResult();
    }

    public Vendor findByEmailAndName(String name, String email) {
        return find("lower(name) = :name and email = :email ",
                Parameters.with("name", name.toLowerCase()).and("email", email)).firstResult();
    }
}
