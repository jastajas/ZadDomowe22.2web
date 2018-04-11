package com.example.homebudget;

import java.util.InputMismatchException;
import java.util.Locale;

public class TransactionsReadByAmount extends QueriesDB {

    double start;
    double finish;

    @Override
    protected void readUserInput() {
        scanner.useLocale(Locale.US);
        try {
            System.out.println("Wprowadź kwotę początkową:");
            start = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("=====================================================");
            System.out.println("Wprowadź kwotę końcową:");
            finish = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            start = -1;
            System.out.println("Amount: wrong type input");
        }
    }

    @Override
    protected void queryIntoDatabase() {
        if (start >= 0 && finish >= 0) {
            transactions = budgetDao.readByAmount(start, finish);
            budgetDao.closeConnection();
        } else {
            throw new UncorrectAmountException();
        }
    }

    @Override
    public void run() {
        try {
            readUserInput();
            queryIntoDatabase();
            if (transactions != null) {
                for (Transaction transaction1 : transactions) {
                    System.out.println(transaction1.toString());
                }
            } else {
                System.out.println("Brak szukanych pozycji w bazie.");
            }
        } catch (UncorrectAmountException f) {
            System.out.println("Amount wasn't read properly. Transactions not found.");
        }
    }
}
