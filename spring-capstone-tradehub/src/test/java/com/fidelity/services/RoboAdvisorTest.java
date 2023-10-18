//package com.fidelity.services;
//
//import com.fidelity.business.RoboAdvisor;
//import com.fidelity.business.UserPreference;
//import com.fidelity.services.RoboAdvisorService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:beans.xml")
//@Transactional
//public class RoboAdvisorTest {
//
//    @Autowired
//    private RoboAdvisorService roboAdvisorService;
//
//    @BeforeEach
//    public void setUp() throws IOException {
//        List<RoboAdvisor> mockInvestmentOptions = new ArrayList<>();
//        mockInvestmentOptions.add(new RoboAdvisor("Option1", 150, 5, 12, 4, "buy"));
//        mockInvestmentOptions.add(new RoboAdvisor("Option2", 130, 6, 9, 6, "sell"));
//
//        roboAdvisorService = new RoboAdvisorService();
//    }
//
//    @Test
//    public void testSuggestTopBuyOrSellOptionsLowRisk() {
//        UserPreference userPreference = new UserPreference();
//        userPreference.setRiskTolerance("low");
//
//        List<String> suggestions = roboAdvisorService.suggestTopBuyOrSellOptions(userPreference);
//        assertEquals(5, suggestions.size());
//    }
//
//    @Test
//    public void testSuggestTopBuyOrSellOptionsMediumRisk() {
//        UserPreference userPreference = new UserPreference();
//        userPreference.setRiskTolerance("medium");
//
//        List<String> suggestions = roboAdvisorService.suggestTopBuyOrSellOptions(userPreference);
//        assertEquals(5, suggestions.size());
//    }
//
//    @Test
//    public void testSuggestTopBuyOrSellOptionsHighRisk() {
//        UserPreference userPreference = new UserPreference();
//        userPreference.setRiskTolerance("high");
//
//        List<String> suggestions = roboAdvisorService.suggestTopBuyOrSellOptions(userPreference);
//        assertEquals(5, suggestions.size());
//    }
//
//    @Test
//    public void testSuggestTopBuyOrSellOptionsInvalidRiskTolerance() {
//        UserPreference userPreference = new UserPreference();
//        userPreference.setRiskTolerance("invalid");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            roboAdvisorService.suggestTopBuyOrSellOptions(userPreference);
//        });
//    }
//
//}