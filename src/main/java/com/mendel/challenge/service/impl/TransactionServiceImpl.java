package com.mendel.challenge.service.impl;

import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.TransactionRepository;
import com.mendel.challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction get(Long id) {
        return null;
    }

    @Override
    public Transaction getByType(String type) {
        return null;
    }

    @Override
    public Double getTotalAmount(Long id) {
        return null;
    }
}
