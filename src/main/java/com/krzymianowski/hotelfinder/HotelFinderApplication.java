package com.krzymianowski.hotelfinder;

import com.krzymianowski.hotelfinder.model.Country;
import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.Role;
import com.krzymianowski.hotelfinder.model.User;
import com.krzymianowski.hotelfinder.service.CountryService;
import com.krzymianowski.hotelfinder.service.HotelService;
import com.krzymianowski.hotelfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HotelFinderApplication {

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HotelFinderApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        User user = new User(
                "Tomasz",
                "admin@admin.com",
                passwordEncoder.encode("passw0rd"),
                null,
                Arrays.asList(
                        new Role("ROLE_USER")));
//        System.out.println(user);
        if (userService.findUserByEmail(user.getEmail()) == null) {
            userService.saveUser(user);
        }
    }

    @PostConstruct
    public void initDatabase() {
        String splitBy = ";";
        String line;
        BufferedReader br;

        try {
            System.out.println("Loading database from file... Please wait!");
//            File csvFile = new ClassPathResource("database/hotels.csv").getFile();
            File csvFile = new ClassPathResource("database/small_hotels.csv").getFile();
//            File csvFile = new ClassPathResource("database/super_small_hotels.csv").getFile();
            br = new BufferedReader(new FileReader(csvFile));
            this.hotelService.deleteAllHotels();

            br.readLine();
            while ((line = br.readLine()) != null) {
//				System.out.println(line);
                String[] hotel = line.substring(1, line.length() - 1).split(splitBy);

                if (hotel[8].length() >= 254) hotel[8] = "unknown";
                this.hotelService.saveHotel(new Hotel(
                        hotel[0],
                        Double.parseDouble(hotel[1]),
                        Double.parseDouble(hotel[2]),
                        hotel[3],
                        hotel[4],
                        hotel[5],
                        hotel[6],
                        hotel[7],
                        hotel[8],
                        hotel[9],
                        hotel[10]
                ));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Init countries
        List<String> distinctCountries = countryService.getDistinctCountries();

        for (String country : distinctCountries) {
            List<String> cities = countryService.getDistinctCities(country);
            countryService.addCountry(new Country(country, cities));
        }
    }
}
