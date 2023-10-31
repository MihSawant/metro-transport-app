package tech.foxlo.bookingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tech.foxlo.bookingservice.repository.StationRepository;
import tech.foxlo.bookingservice.service.StationService;

@SpringBootApplication
@EnableDiscoveryClient
public class BookingServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }

    @Autowired
    private StationService service;


    @Bean
    public CommandLineRunner runner(){
        return args -> {
            };
    }
}
