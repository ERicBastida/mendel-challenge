package com.mendel.challenge.service.impl;

import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.TransactionRepository;
import com.mendel.challenge.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction get(Long id) {
        return transactionRepository.get(id);
    }

    @Override
    public List<Long> getByType(String type) {

        return transactionRepository.getByType(type)
                .stream()
                .map(Transaction::getId)
                .collect(Collectors.toList());
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
