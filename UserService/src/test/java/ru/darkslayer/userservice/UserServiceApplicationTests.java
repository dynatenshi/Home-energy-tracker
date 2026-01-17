package ru.darkslayer.userservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.darkslayer.userservice.entity.User;
import ru.darkslayer.userservice.repository.UserRepository;

@Slf4j
@SpringBootTest
class UserServiceApplicationTests {

    public static final int NUMBER_OF_USERS = 100;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void createUsers() {
        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            User user = User.builder()
                    .name("User " + i)
                    .surname("resU " + i)
                    .email("email" + i + "@gmail.com")
                    .address("Address " + i)
                    .alerting(i % 3 == 0)
                    .energyAlertingThreshold(1000 * i%10)
                    .build();
        }
        log.info("User repository updated");
    }
}
