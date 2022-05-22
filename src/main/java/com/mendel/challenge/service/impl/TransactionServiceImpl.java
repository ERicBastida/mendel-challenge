package com.mendel.challenge.service.impl;

import com.mendel.challenge.dto.TransactionDTO;
import com.mendel.challenge.dto.TransactionSumDTO;
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
    public TransactionDTO get(Long id) {
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
    public TransactionSumDTO getTotalAmount(Long id) {
        Double totalAmountResult = 0.0;

        List<TransactionDTO> relatedTransactions = transactionRepository.getByRelationship(id);

        totalAmountResult = relatedTransactions
                .stream()
                .mapToDouble(TransactionDTO::getAmount)
                .sum();

        return relatedTransactions.size() > 0 ? TransactionSumDTO.builder().sum(totalAmountResult).build() : null;
    }

    @Override
    public TransactionDTO setTransaction(TransactionDTO transaction) {
        return transactionRepository.save(transaction) ? transaction : null;
    }
}
