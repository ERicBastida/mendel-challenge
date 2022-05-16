package com.mendel.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {
    @GetMapping("/ping")
    public String test(){
        return "pong";
    }
}
