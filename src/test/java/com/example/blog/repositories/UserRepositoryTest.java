package com.example.blog.repositories;

import com.example.blog.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    UserRepository userRepository;
    static User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("k@gmail.com")
                .password("1234")
                .username("k")
                .build();
        manager.persist(user);
    }

    @Test
    void findByEmail() {
        Optional<User> user1 = userRepository.findByEmail("k@gmail.com");
        assertEquals("k@gmail.com", user1.get().getEmail());
        assertEquals("k", user1.get().getUsername());
        assertEquals("k@gmail.com", user1.get().getEmail());
        assertEquals(user, user1.get());
    }

    @Test
    void findUserById() {
        Optional<User> user1 = userRepository.findUserById(1);
        assertEquals("k@gmail.com", user1.get().getEmail());
        assertEquals("k", user1.get().getUsername());
        assertEquals("k@gmail.com", user1.get().getEmail());
        assertEquals(user, user1.get());
    }

    @Test
    void findUserByEmailAndPassword() {
        Optional<User> user1 = userRepository.findUserByEmailAndPassword("k@gmail.com", "1234");
        assertEquals("k@gmail.com", user1.get().getEmail());
        assertEquals("k", user1.get().getUsername());
        assertEquals("k@gmail.com", user1.get().getEmail());
        assertEquals(user, user1.get());
    }

}