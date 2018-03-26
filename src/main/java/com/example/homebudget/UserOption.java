package com.example.homebudget;

public enum UserOption {
    SAVE_TRANSACTION(new TransactionSave(), "Wprowadź transakcję do bazy danych."),
    READ_TRANSACTIONS_BY_TYPE(new TransactionsReadByType(), "Pokaż transakcje wg typów."),
    READ_TRANSACTIONS_BY_DATE(new TransactionsReadByDate(), "Pokaż transakcje wg dat."),
    READ_TRANSACTIONS_BY_AMOUNT(new TransactionsReadByAmount(), "Pokaż transakcje wg kwot."),
    FINISH(null, "Zakończ program.");

    private QueriesDB queriesDB;
    private String optionDescription;

    UserOption(QueriesDB queriesDB, String optionDescription) {
        this.queriesDB = queriesDB;
        this.optionDescription = optionDescription;
    }

    public QueriesDB getQueriesDB() {
        return queriesDB;
    }

    @Override
    public String toString() {
        return optionDescription;
    }
}
