package com.fidelity.integration;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
public class TransactionDaoImplementationTest {
    @Autowired
    private TransactionDaoImpl dao;
    Instrument instrument1 = new Instrument("INST1", "Type1", "Ext1", "Category1", "Instrument 1 Description", 100, 10);

    String date = "2023-09-20";
    LocalDate localDate = LocalDate.parse(date);
    Transaction transaction1 = new Transaction(1L, instrument1, 1, 52, 10, "20-SEP-23", 1L);


    @Test
    void testGetAllTransactions() {
        List<Transaction> transactions = dao.getAllTransactions();
        assertEquals(transactions.get(0), transaction1);
    }

    @Test
    void testGetTransactionById() {
        Transaction transaction = dao.getTransactionById(1L);
        assertEquals(transaction1, transaction);
    }

    @Test
    void testInsertTransaction(){
        Transaction transaction2 = new Transaction(9L, instrument1, 1, 52, 10, "20-SEP-21", 1L);
        dao.insertTransaction(transaction2);
        Transaction transaction = dao.getTransactionById(9L);
        assertEquals(transaction2, transaction);
    }


    @Test
    void testDeleteTransaction(){
        dao.deleteTransaction(2L);
        Transaction transaction = dao.getTransactionById(2L);
        assertEquals(null, transaction);
    }


}
