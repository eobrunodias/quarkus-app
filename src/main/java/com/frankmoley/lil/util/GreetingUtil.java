package com.frankmoley.lil.util;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class GreetingUtil {

    @ConfigProperty(name="greeting.name", defaultValue = "students")
    String greetingName;

    public String getGreetingName() {
        return "Hello" + greetingName;
    }
}
