package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class MapperTest {

    @Test
    public void test01() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        String encode01 = passwordEncoder.encode("1234");

        System.out.println(passwordEncoder.matches("1234", "$2a$10$a10TF5MQ3KGqbivP65SXJ.44L4YwDG6wzeqkBJnp/3Z6LEjBz6zSu"));
        System.out.println(passwordEncoder.matches("1234", "$2a$10$J/UvLE/Wipjw8Of8oqI4pOPCna9EMwmFJvsvPzJVH/puxgewZ5UbO"));

        System.out.println(encode);
        System.out.println(encode01);
    }

    @Test
    public void test02() {

    }
}
