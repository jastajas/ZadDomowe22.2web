package com.example.homebudget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApllicationController {

    private BudgetDao bdao;

    public ApllicationController(BudgetDao budgetDao) {
        bdao = budgetDao;
    }


    @RequestMapping("/main")
    public String showTransactionList(Model model, @RequestParam(required = false) String param1,
                                      @RequestParam(required = false) String param2, @RequestParam(required = false) String param3) {

        List<Transaction> transactions = new ArrayList<>();
        if (param3 == null) {
            transactions = bdao.readAll();
        } else if (param3.equals("byType")) {
            transactions = bdao.readByType(param1);
        } else if (param3.equals("byDate")) {
            transactions = bdao.readByDate(Date.valueOf(param1), Date.valueOf(param2));
        } else if (param3.equals("byAmount")) {
            transactions = bdao.readByAmount(Double.valueOf(param1), Double.valueOf(param2));
        }
        TransactionType[] transactionTypes = TransactionType.values();

        if (!transactions.isEmpty()) {
            model.addAttribute("newTransaction", new Transaction());
            model.addAttribute("transactionTypes", transactionTypes);
            model.addAttribute("transactions", transactions);
        }
        return "index";
    }

    @PostMapping("/addTransaction")
    public String showTransactionList(Model model, Transaction transaction) {

        if (transaction != null) {
            bdao.save(transaction);
        }
        return "redirect:/main";
    }
}