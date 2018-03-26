package com.example.homebudget;

import java.util.List;
import java.util.Scanner;

public abstract class QueriesDB {

    Transaction transaction;
    Scanner scanner;
    BudgetDao budgetDao;
    List<Transaction> transactions;

    public QueriesDB() {
        transaction = new Transaction();
        scanner = new Scanner(System.in);
        budgetDao = new BudgetDao();
    }

    protected abstract void readUserInput();

    protected abstract void queryIntoDatabase();

    public abstract void run();
}
