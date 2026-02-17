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

//    GreetingUtil greetingUtil;
//
//    public QuarkusApp(GreetingUtil greetingUtil) {
//        super();
//        this.greetingUtil =  greetingUtil;
//    }

    private final ServiceRepository serviceRepository;
    private final VendorRepository vendorRepository;
    private final CustomerRepository customerRepository;

    public QuarkusApp(ServiceRepository serviceRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.serviceRepository = serviceRepository;
        System.out.println("teste");
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
    }


    public static void main(String... args) {
        io.quarkus.runtime.Quarkus.run(QuarkusApp.class, args);
    }


    @Override
    @ActivateRequestContext
    public int run(String... args) throws Exception {
        List<Service> services = this.serviceRepository.getAllServices();
        services.forEach(System.out::println);

        Vendor vendor = this.vendorRepository.findByEmail("vendor@email.com");
        System.out.println(vendor);

        Customer customer = this.customerRepository.findByEmail("customer@email.com");
        System.out.println(customer);
        System.out.println("teste");
        System.out.println("teste2");

        Service service = this.serviceRepository.findById(2L);
        System.out.println(service);

        System.out.println("Services");
        System.out.println("Vendors");
        System.out.println("Customers");

        return 0;
    }

}
