package com.example.homebudget;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class TransactionsReadByDate extends QueriesDB {

    Date start;
    Date finish;

    @Override
    protected void readUserInput() {
        try {
            int year = 0;
            int month = 0;
            int day = 0;
            LocalDate tempDate = null;

            System.out.println("=====================================================");
            System.out.println("Wprowadź datę początkową");
            System.out.println("Wprowadź rok:");
            year = scanner.nextInt();
            System.out.println("Wprowadź miesiąc:");
            month = scanner.nextInt();
            System.out.println("Wprowadź dzień:");
            day = scanner.nextInt();
            tempDate = LocalDate.of(year, month, day);
            start = (Date.valueOf(tempDate));

            System.out.println("=====================================================");
            System.out.println("Wprowadź datę końcową");
            System.out.println("Wprowadź rok:");
            year = scanner.nextInt();
            System.out.println("Wprowadź miesiąc:");
            month = scanner.nextInt();
            System.out.println("Wprowadź dzień:");
            day = scanner.nextInt();
            tempDate = LocalDate.of(year, month, day);
            finish = (Date.valueOf(tempDate));

        } catch (InputMismatchException e) {
            scanner.nextLine();

            System.out.println("TransactionDate: wrong type input");
        }
    }

    @Override
    protected void queryIntoDatabase() {
        if (start != null && finish != null) {
            transactions = budgetDao.readByDate(start, finish);
            budgetDao.closeConnection();
        } else {
            throw new UncorrectDateException();
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
        } catch (UncorrectDateException f) {
            System.out.println("Date wasn't read properly. Transactions not found.");
        }
    }
}
