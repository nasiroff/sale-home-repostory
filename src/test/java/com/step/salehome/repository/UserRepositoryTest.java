package com.step.salehome.repository;

import com.step.salehome.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void loginUser() throws Exception {
        User user = userRepository.loginUser("v.nesirov91@gmail.com");
    }

    @Test
    public void getFavoritePosts() throws Exception {
        List<String> i = Arrays.asList("salam", "sagol", "necesen");
    }


    @Test
    public void mathTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
    }

}