package com.frankmoley.lil;

import com.frankmoley.lil.data.entity.Customer;
import com.frankmoley.lil.data.entity.Service;
import com.frankmoley.lil.data.entity.Vendor;
import com.frankmoley.lil.data.repository.CustomerRepository;
import com.frankmoley.lil.data.repository.ServiceRepository;
import com.frankmoley.lil.data.repository.VendorRepository;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

import java.util.List;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

    private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public QuarkusApp(ServiceRepository serviceRepository, CustomerRepository customerRepository,
                      VendorRepository vendorRepository) {
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    @ActivateRequestContext
    public int run(String... args) throws Exception {
        System.out.println("**\nServices**");
        List<Service> services = this.serviceRepository.listAll();
        services.forEach(System.out::println);
        Service service = this.serviceRepository.findById(2L);
        System.out.println(service);
        System.out.println("**\nVendors**");
        Vendor vendor = this.vendorRepository.findByName("DEFAULTNAME");
        System.out.println("Vendor by name: " + vendor);
        vendor = this.vendorRepository.findByEmail("vharrison1@geocities.com");
        System.out.println("Vendor by email: " + vendor);
        System.out.println("**\nCustomers**");
        Customer customer = this.customerRepository.findByEmail("montes.nascetur@semperrutrum.net");
        System.out.println("Customer by email: " + customer);

        io.quarkus.runtime.Quarkus.waitForExit();
        return 0;
    }
}
