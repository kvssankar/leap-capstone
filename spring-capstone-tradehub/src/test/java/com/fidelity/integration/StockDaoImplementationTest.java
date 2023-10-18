package com.fidelity.integration;

import com.fidelity.business.Instrument;
import com.fidelity.business.Stock;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
public class StockDaoImplementationTest {
    @Autowired
    private StockDaoImpl dao;
    Instrument instrument1 = new Instrument("INST1", "Type1", "Ext1", "Category1", "Instrument 1 Description", 100, 10);

    Stock stock1 = new Stock(1L, 50,45,"2023-09-20",instrument1);


    @Test
    void testGetAllStocks() {
        List<Stock> stocks = dao.getAllStocks();
        assertEquals(stocks.get(0), stock1);
    }

    @Test
    void testGetStockById() {
        Stock stock = dao.getStockById(1L);
        assertEquals(stock1, stock);
    }

    @Test
    void testInsertStock(){
        Stock stock2 = new Stock(9L, 50,45,"2023-09-20",instrument1);
        dao.insertStock(stock2);
        Stock stock = dao.getStockById(9L);
        assertEquals(stock2, stock);
    }


    @Test
    void testDeleteStock(){
        dao.deleteStock(2L);
        Stock stock = dao.getStockById(2L);
        assertEquals(null, stock);
    }


}
