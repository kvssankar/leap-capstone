package com.fidelity.services;



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



import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class AuthServiceTest {


    @Autowired
    private AuthService authService;



    UserPreference preference1 = new UserPreference(1L, "Purpose1", "High", "IncomeCat1", "Long", true, false);
    User user1 = new User(1L, "user1@example.com", "User 1", "Country1", "1990-01-01", "High", 0L, preference1);
    List<UserPreference> allUserPreferences = new ArrayList<>();

    @BeforeEach
    void setUp(){
        allUserPreferences.add(preference1);
    }





    @Test
    void registerTest_Success() {
        User user2 = new User(12L, "usern@example.com", "User n", "Country1", "1990-01-01", "High", 1000L, preference1);
        assertNotNull(authService.register(user2));
    }



    @Test
    void registerTest_Failure_NullUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(null);
        });
    }



    @Test
    void registerTest_Failure_NullEmail() {
        User user = new User(1L, "", "User 1", "Country1", "1990-01-01", "High", 0L, preference1);
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(user);
        });
    }



    @Test
    void registerTest_Failure_NullCountry() {
        User user = new User(1L, "user1@example.com", "User 1", "", "1990-01-01", "High", 0L, preference1);
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(user);
        });
    }



    @Test
    void registerTest_Failure_NullDOB() {
        User user = new User(1L, "user1@example.com", "User 1", "", "", "High", 0L, preference1);
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(user);
        });
    }



    @Test
    void registerTest_Failure_EmailAlreadyExists() {
        User user2 = new User(1L, "user1@example.com", "User 1", "", "1990-01-01", "High", 0L, preference1);
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(user2);
        });
    }



    @Test
    void loginTest_Success() {
        User user = authService.login("user1@example.com");
        assertEquals(user1, user);
    }



    @Test
    void loginTest_Failure_EmptyEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.login("");
        });
    }



    @Test
    void loginTest_Failure_InvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.login("userx@email.com");
        });
    }



    @Test
    void getAllUserPreferencesTest_Success() {
        List<UserPreference> userPreferences = authService.getAllUserPreferences();
        assertFalse(userPreferences.isEmpty());
    }



    @Test
    void getUserPreferenceByIdTest_Success() {
        UserPreference userPreference = authService.getUserPreferenceId(1L);
        assertNotNull(userPreference);
//        assertEquals(1L, userPreference.getId());
    }



    @Test
    void getUserPreferenceByIdTest_Failure_IDNotFound() {
        assertThrows(IllegalStateException.class, () -> {
            authService.getUserPreferenceId(1001L);
        });
    }



    @Test
    void getUserPreferenceByIdTest_Failure_InvalidID() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.getUserPreferenceId(0L);
        });
    }



    @Test
    void addClientPreferenceTest_Success() {
        UserPreference userPreference = new UserPreference(999L, "Purpose1", "High", "IncomeCat1", "Long", true, false);
        assertTrue(authService.addClientPreference(userPreference));
    }



    @Test
    void addClientPreferenceTest_Failure_NullUserPreference() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.addClientPreference(null);
        });
    }



    @Test
    void addClientPreferenceTest_Failure_ExistingUser() {
        UserPreference userPreference = new UserPreference(1L, "Purpose1", "High", "IncomeCat1", "Long", true, false);
        assertThrows(IllegalArgumentException.class, () -> {
            authService.addClientPreference(userPreference);
        });
    }



    @Test
    void updateClientPreferenceTest_Success() {
        UserPreference userPreference = new UserPreference(1L, "Purposen", "Low", "IncomeCat1", "Long", true, false);
        assertTrue(authService.updateClientPreference(userPreference));
    }



    @Test
    void updateClientPreferenceTest_Failure_NullUserPreference() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.updateClientPreference(null);
        });
    }



    @Test
    void updateClientPreferenceTest_Failure_InvalidUserID() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.addClientPreference(null);
        });
    }



    @Test
    void updateClientPreferenceTest_Failure_EmptyInvestmentPurpose() {
        UserPreference userPreference = new UserPreference(1L, "", "Low", "IncomeCat1", "Long", true, false);
        assertThrows(IllegalArgumentException.class, () -> {
            authService.addClientPreference(null);
        });
    }



}