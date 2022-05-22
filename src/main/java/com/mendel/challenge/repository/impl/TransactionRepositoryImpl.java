package com.mendel.challenge.repository.impl;

import com.mendel.challenge.dto.TransactionDTO;
import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

    Map<Long, Transaction> transactions = new HashMap<>();

    @Override
    public TransactionDTO get(Long id) {
        Transaction result = transactions.get(id);
        return ObjectUtils.isEmpty(result) ? null :  result.toDto();
    }

    @Override
    public Boolean save(TransactionDTO transaction) {
        Boolean result =  !transactions.containsKey(transaction.getId());
        Transaction transactionToSave = Transaction.builder().build();
        transactionToSave.fromDto(transaction);
        transactions.put(transaction.getId(), transactionToSave);
        return result;
    }

    @Override
    public List<Transaction> getByType(String type) {

        return transactions.entrySet().stream().filter(
                        transaction -> transaction.getValue().getType().equals(type))
                .map(item -> item.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public Transaction getByParentId(Long id) {

        return transactions.entrySet().stream().filter(
                        transaction -> id.equals(transaction.getValue().getParentId()))
                .map(item -> item.getValue())
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<TransactionDTO> getByRelationship(Long id) {
        List<TransactionDTO> result = new ArrayList<>();
        if (transactions.containsKey(id)) {
            Transaction relation = getByParentId(id);
            if (ObjectUtils.isEmpty(relation)) {
                result.add(transactions.get(id).toDto());
            } else {
                result = getByRelationship(relation.getId());
                result.add(transactions.get(id).toDto());
            }
        }
        return result;
    }

}
