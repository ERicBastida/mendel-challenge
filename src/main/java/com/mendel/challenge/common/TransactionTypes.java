package com.mendel.challenge.common;

public enum TransactionTypes {
    CARS("cars"),
    SHOPPING("shipping"),
    GROCERIES("groceries");

    private final String name;

    TransactionTypes(String name){this.name = name;}
    public String getName() {return name;}
}
