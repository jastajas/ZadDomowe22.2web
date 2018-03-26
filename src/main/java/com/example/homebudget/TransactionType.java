package com.example.homebudget;

public enum TransactionType {
    INCOME("Przychód"),
    EXPENDITURE("Wydatek");

    private String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    @Override
    public String toString() {
        return transactionType;
    }
}
