package com.mendel.challenge.service.impl;

import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.TransactionRepository;
import com.mendel.challenge.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

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
        Double totalAmountResult = 0.0;

        totalAmountResult = transactionRepository.getByRelationship(id)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        return totalAmountResult;
    }
}
