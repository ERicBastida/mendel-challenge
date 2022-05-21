package com.mendel.challenge.unit.impl.service;

import com.mendel.challenge.repository.TransactionRepository;
import com.mendel.challenge.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    void getTransaction(){}
    void getTransactionByType(){}
    void getTotalAmountTransaction(){}
}
