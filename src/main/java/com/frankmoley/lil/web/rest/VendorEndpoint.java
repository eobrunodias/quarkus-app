package com.frankmoley.lil.web.rest;

import com.frankmoley.lil.data.entity.Vendor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

public class VendorEndpoint implements PanacheRepository<Vendor> {
    public Vendor findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Vendor findByname(String name) {
        return find("lower(name)", name.toLowerCase()).firstResult();
    }

    public Vendor findByEmailAndName(String name, String email) {
        return find("lower(name) = :name and email = :email ",
             Parameters.with("name", name.toLowerCase()).and("email", email)).firstResult();
    }

}
