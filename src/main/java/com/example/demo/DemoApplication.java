package com.example.demo;

import com.example.demo.model.Hotel;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.repository.HotelRepository;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostConstruct
    public void init(){

        User user = new User(
                "Tomasz",
                "Krzymianowski",
                "admin@admin.com",
                passwordEncoder.encode("password"),
                Arrays.asList(
                        new Role("ROLE_USER"),
                        new Role("ROLE_ADMIN")));
        if (userRepository.findByEmail(user.getEmail()) == null){
            userRepository.save(user);
        }


        Hotel h1 = new Hotel(
                "Hotel 1",
                40,
                "Wrocław",
                "Polska",
                3);

        Hotel h2 = new Hotel(
                "Hotel 2",
                80,
                "Warszawa",
                "Polska",
                5);

        Hotel h3 = new Hotel(
                "Hotel 3",
                49,
                "Wrocław",
                "Polska",
                5);

        Hotel h4 = new Hotel(
                "Hotel 4",
                40,
                "Wrocław",
                "Polska",
                3);

        Hotel h5 = new Hotel(
                "Hotel 5",
                60,
                "Warszawa",
                "Polska",
                4);


        this.hotelRepository.deleteAll();

        List<Hotel> hotels = Arrays.asList(h1, h2, h3, h4, h5);
        this.hotelRepository.save(hotels);

    }


    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
