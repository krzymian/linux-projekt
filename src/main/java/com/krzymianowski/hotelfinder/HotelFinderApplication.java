package com.krzymianowski.hotelfinder;

import com.krzymianowski.hotelfinder.model.Hotel;
import com.krzymianowski.hotelfinder.model.Role;
import com.krzymianowski.hotelfinder.model.User;
import com.krzymianowski.hotelfinder.service.HotelService;
import com.krzymianowski.hotelfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Arrays;

@SpringBootApplication
public class HotelFinderApplication {

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsers() {
        User user = new User(
                "Tomasz",
                "admin@admin.com",
                passwordEncoder.encode("passw0rd"),
                null,
                Arrays.asList(
                        new Role("ROLE_USER")));
        System.out.println(user);
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
            File csvFile = new ClassPathResource("database/super_small_hotels.csv").getFile();
            br = new BufferedReader(new FileReader(csvFile));
            this.hotelService.deleteAllHotels();

            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] hotel = line.substring(1, line.length() - 1).split(splitBy);

                if (hotel[8].length() <= 255)
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelFinderApplication.class, args);
    }
}
