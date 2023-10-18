package com.fidelity.integration;


import com.fidelity.business.User;
import com.fidelity.business.UserPreference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
public class UserDaoImplementationTest {
    @Autowired
    private UserDaoImpl dao;

    UserPreference preference1 = new UserPreference(1L, "Purpose1", "High", "IncomeCat1", "Long", true, false);

    User user1 = new User(1L, "user1@example.com", "User 1", "Country1", "1990-01-01", "High", 0L ,preference1);

    List<User> allUsers = new ArrayList<>();


    @BeforeEach
    void setUp(){
        allUsers.add(user1);

    }

    @Test
    void testGetAllUsers() {
        List<User> users = dao.getAllUsers();
        assertEquals(allUsers.get(0), users.get(0));
        assertFalse(users.isEmpty());
    }

    @Test
    void testGetUserById() {
        User user = dao.getUserById(1L);
        assertEquals(allUsers.get(0), user);
    }

    @Test
    void testInsertUser(){
        User user2 = new User(6L, "user7@gmail.com", "User 7", "Country7", "1997-07-07", "Low",0L,null);
        dao.insertUser(user2);
        User user = dao.getUserByEmail("user7@gmail.com");
        assertEquals(user2.getEmail(), user.getEmail());
    }

    @Test
    void testGetUserByEmail() {
        User user = dao.getUserByEmail("user1@example.com");
        assertEquals(user1, user);
    }

    @Test
    void testUpdateUser(){
        User updatedUser = new User(1L, "updatedUser@example.com", "UpadtedUser", "Country1", "1990-01-01", "High", 1000L ,preference1);
        dao.updateUser(updatedUser);
        User user = dao.getUserById(1L);
        assertEquals(updatedUser.getEmail(), user.getEmail());
        assertEquals(updatedUser.getPersonalName(),user.getPersonalName());
    }
}
