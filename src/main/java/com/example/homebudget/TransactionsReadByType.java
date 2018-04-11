package com.example.homebudget;

import java.util.InputMismatchException;

public class TransactionsReadByType extends QueriesDB {


    @Override
    protected void readUserInput() {
        try {
            System.out.println("Typy transakcji:");
            TransactionType[] types = TransactionType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.println(i + " - " + types[i]);
            }
            System.out.println("Wprowadź nr typu transakcji:");
            switch (scanner.nextInt()) {
                case 0:
                    transaction.setType(TransactionType.INCOME.toString());
                    scanner.nextLine();
                    break;
                case 1:
                    transaction.setType(TransactionType.EXPENDITURE.toString());
                    scanner.nextLine();
                    break;
                default:
                    transaction.setType(null);
                    scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            transaction.setType(null);
            System.out.println("Transaction: wrong type input");
        }
    }

    @Override
    protected void queryIntoDatabase() {
        if (transaction.getType() != null) {
            transactions = budgetDao.readByType(transaction.getType());
        } else {
            throw new UncorrectTypeException();
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
        } catch (UncorrectTypeException f) {
            System.out.println("Type wasn't read properly. Transactions not found.");
        }
    }

}
