package com.fidelity.integration;

import com.fidelity.business.User;
import com.fidelity.business.UserPreference;
import org.junit.jupiter.api.AfterEach;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
public class UserPreferenceDaoImplementationTest {

    @Autowired
    private UserPreferenceDaoImpl dao;


    UserPreference preference1 = new UserPreference(1L, "Purpose1", "High", "IncomeCat1", "Long", true, false);

    List<UserPreference> allUserPreferences = new ArrayList<>();

    @BeforeEach
    void setUp(){
        allUserPreferences.add(preference1);
    }


    @Test
    void testGetAllUserPreferences() {
        List<UserPreference> userPreferences = dao.getAllUserPreferences();
        assertEquals(allUserPreferences.get(0), userPreferences.get(0));
    }

    @Test
    void testGetUserById() {
        UserPreference userPreference = dao.getUserPreferenceById(1L);
        assertEquals(preference1, userPreference);
    }

    @Test
    void testInsertUserPreference(){
        UserPreference preference2 = new UserPreference(6L, "Purpose2", "Low", "IncomeCat2", "Short", false, true);
        dao.insertUserPreference(preference2);
        UserPreference userPreference = dao.getUserPreferenceById(6L);
        assertEquals(preference2, userPreference);
    }

    @Test
    void testUpdateUserPreference(){
        UserPreference preference2 = new UserPreference(6L, "Purpose2", "Low", "IncomeCat2", "Short", false, true);
        dao.insertUserPreference(preference2);
        UserPreference userPreference = dao.getUserPreferenceById(6L);
        assertEquals(preference2, userPreference);
        preference2.setInvestmentPurpose("Purpose3");
        dao.updateUserPreference(preference2);
        userPreference = dao.getUserPreferenceById(6L);
        assertEquals(preference2, userPreference);
    }
}
