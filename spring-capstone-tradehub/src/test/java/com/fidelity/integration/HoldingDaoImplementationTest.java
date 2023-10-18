package com.fidelity.integration;

import com.fidelity.business.Holding;
import com.fidelity.business.Instrument;
import com.fidelity.business.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
public class HoldingDaoImplementationTest {

    @Autowired
    private HoldingDaoImpl dao;
    Instrument instrument1 = new Instrument("INST1", "Type1", "Ext1", "Category1", "Instrument 1 Description", 100, 10);

    String date = "2023-09-20";
    LocalDate localDate = LocalDate.parse(date);
    Holding holding1 = new Holding(1L, instrument1, 100,50, 1L);


    @Test
    void testGetAllHoldings() {
        List<Holding> holdings = dao.getAllHoldings();
        assertEquals(holdings.get(0), holding1);
    }

    @Test
    void testGetHoldingById() {
        Holding holding = dao.getHoldingById(1L);
        assertEquals(holding1, holding);
    }

    @Test
    void testInsertHolding(){
        Holding holding2 = new Holding(10L, instrument1, 100,50, 1L);
        dao.insertHolding(holding2);
        Holding holding = dao.getHoldingById(10L);
    }

    @Test
    void testDeleteTransaction(){
        dao.deleteHolding(2L);
        Holding holding = dao.getHoldingById(2L);
        assertEquals(null, holding);
    }

}
