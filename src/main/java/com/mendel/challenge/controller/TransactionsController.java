package com.mendel.challenge.controller;

import com.mendel.challenge.dto.TransactionSumDTO;
import com.mendel.challenge.dto.TransactionDTO;
import com.mendel.challenge.service.TransactionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long transactionId){

        TransactionDTO resultTransaction = transactionService.get(transactionId);

        return  ResponseEntity.status(ObjectUtils.isEmpty(resultTransaction)? HttpStatus.NOT_FOUND : HttpStatus.OK ).body(resultTransaction);
    }
    @GetMapping("/types/{transactionType}")
    public ResponseEntity<List<Long>> getTransactionTypes(@PathVariable String transactionType){
        List<Long> resultTransaction = transactionService.getByType(transactionType);
        return  ResponseEntity.status(ObjectUtils.isEmpty(resultTransaction)? HttpStatus.NOT_FOUND : HttpStatus.OK ).body(resultTransaction);
    }
    @GetMapping("/sum/{transactionId}")
    public ResponseEntity<TransactionSumDTO> getTransactionSum(@PathVariable Long transactionId){

        TransactionSumDTO resultTransaction = transactionService.getTotalAmount(transactionId);

        return  ResponseEntity.status( ObjectUtils.isEmpty(resultTransaction) ? HttpStatus.NOT_FOUND :  HttpStatus.OK ).body(resultTransaction);
    }
    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> newTransaction(@PathVariable Long transactionId, @RequestBody @NotNull TransactionDTO transaction){
        transaction.setId(transactionId);
        TransactionDTO result = transactionService.setTransaction(transaction);

        return  ResponseEntity
                .status(!ObjectUtils.isEmpty(result) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT)
                .body(result);
    }

    @GetMapping("/ping")
    public String test(){
        return "pong";
    }
}
