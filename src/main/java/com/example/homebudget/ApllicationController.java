package com.example.homebudget;

import javax.swing.text.html.Option;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ApllicationController {

    private Scanner sc;

    public ApllicationController() {
        sc = new Scanner(System.in);
    }

    private void showOption() {
        UserOption[] userOptions = UserOption.values();
        for (int i = 0; i < userOptions.length; i++) {
            System.out.println(i + " - " + userOptions[i]);
        }
    }

    private void runOption(int option) {
        switch (option) {
            case 0:
                UserOption.SAVE_TRANSACTION.getQueriesDB().run();
                break;
            case 1:
                UserOption.READ_TRANSACTIONS_BY_TYPE.getQueriesDB().run();
                break;
            case 2:
                UserOption.READ_TRANSACTIONS_BY_DATE.getQueriesDB().run();
                break;
            case 3:
                UserOption.READ_TRANSACTIONS_BY_AMOUNT.getQueriesDB().run();
            default:
                break;
        }
    }


    public void runApp() {
        System.out.println("WITAJ !");
        int mainController = 0;

        do {
            showOption();
            System.out.println("Wybierz nr opcji: ");
            try {
                mainController = sc.nextInt();

                if (mainController < 0 || mainController > 4) {
                    throw new InputMismatchException();
                }

                runOption(mainController);
            } catch (InputMismatchException a) {
                mainController = 0;
                System.out.println("Wprowadzono niewłaściwy typ opcji.");
            }

        } while (mainController != 4);

        System.out.println("Dziękujemy za skorzystanie z aplikacji!");
        System.out.println("Do zobaczenia!");
    }
}
