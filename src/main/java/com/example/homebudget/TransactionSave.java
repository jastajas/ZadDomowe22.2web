package com.example.homebudget;

import java.util.InputMismatchException;
import java.util.Locale;
import java.time.LocalDate;
import java.sql.Date;

public class TransactionSave extends QueriesDB {

    @Override
    protected void readUserInput() {
        scanner.useLocale(Locale.US);
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
            System.out.println("=====================================================");
            System.out.println("Wprowadź opis transakcji:");
            transaction.setDescription(scanner.nextLine());
            System.out.println("=====================================================");
            System.out.println("Wprowadź kwotę:");
            transaction.setAmount(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("=====================================================");
            System.out.println("Wprowadź datę");
            System.out.println("Wprowadź rok:");
            int year = scanner.nextInt();
            System.out.println("Wprowadź miesiąc:");
            int month = scanner.nextInt();
            System.out.println("Wprowadź dzień:");
            int day = scanner.nextInt();
            LocalDate tempDate = LocalDate.of(year, month, day);
            transaction.setDate(Date.valueOf(tempDate));
        } catch (InputMismatchException e) {
            System.out.println("Transaction: wrong type input");
        } catch (java.time.DateTimeException g) {
            System.out.println("Date: wrong data input");
        }

    }

    @Override
    protected void queryIntoDatabase() {
        if (!transaction.getType().equals("") && transaction.getType() != null &&
                !transaction.getDescription().equals("") && transaction.getDescription() != null &&
                transaction.getAmount() >= 0) {
            budgetDao.save(transaction);
        } else {
            throw new UncorrectTransactionException();
        }
    }

    @Override
    public void run() {
        try {
            readUserInput();
            queryIntoDatabase();
            System.out.println("Poprawnie zapisano: " + transaction.toString() + " w bazie danych.");
        } catch (UncorrectTransactionException f) {
            System.out.println("Transaction wasn't written.");
        }
    }
}
