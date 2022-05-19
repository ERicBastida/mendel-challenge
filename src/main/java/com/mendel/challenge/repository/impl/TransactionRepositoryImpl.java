package com.mendel.challenge.repository.impl;

import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

    Map<Long, Transaction> transactions = new HashMap<Long, Transaction>();

    @Override
    public Transaction get(Long id) {
        return transactions.get(id);
    }

    @Override
    public void save(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
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
                        transaction -> transaction.getValue().getParentId().equals(id))
                .map(item -> item.getValue())
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Transaction> getByRelationship(Long id) {
        List<Transaction> result = Arrays.asList();
        if (transactions.containsKey(id)) {
            Transaction relation = getByParentId(id);
            if (ObjectUtils.isEmpty(relation)) {
                return Arrays.asList(transactions.get(id));
            }else{
                result = getByRelationship(relation.getId());
                result.add(transactions.get(id));
                return result;
            }
        } else {
            return result;
        }
    }

}
