package com.fidelity.controllers;

import com.fidelity.business.User;
import com.fidelity.business.UserPreference;
import com.fidelity.services.AuthService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserPreferenceController {

    @Autowired
    private Logger logger;

    @Autowired
    private AuthService service;

    @CrossOrigin(origins = "http://13.233.161.221:4200")
    @PostMapping("/addPreference")
    public ResponseEntity<Boolean> addUserPreference(@RequestBody UserPreference userPreference) {
        logger.info("User Preference added: ", userPreference);
        return ResponseEntity.ok(service.addClientPreference(userPreference));
    }
    @CrossOrigin(origins = "http://13.233.161.221:4200")
    @PutMapping("/updatePreference")
    public ResponseEntity<Boolean> updateUserPreference(@RequestBody UserPreference userPreference) {
        System.out.println(userPreference);
        logger.info("User Preference updated: ", userPreference);
        return ResponseEntity.ok(service.updateClientPreference(userPreference));
    }

    @GetMapping("/getPreference")
    public ResponseEntity<List<UserPreference>> getUserPreference() {
        logger.info("User Preference retrieved: ");
        return ResponseEntity.ok(service.getAllUserPreferences());
    }

}