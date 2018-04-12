package com.example.homebudget;

import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BudgetDao {

    private static final String URL = "jdbc:mysql://localhost:3306/financemanager?characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASS = "admin";
    private Connection connection;

    public BudgetDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver: not found");
        } catch (SQLException e) {
            System.out.println("Connection: not established");
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection: not closed");
        }
    }

    public void save(Transaction transaction) {
        final String sql = "INSERT INTO home_budget(type, description, amount, date) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, transaction.getType());
            ps.setString(2, transaction.getDescription());
            ps.setDouble(3, transaction.getAmount());
            ps.setDate(4, transaction.getDate());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Save income: not executed");
        }
    }

		public double balanceIncome() {

        final String sql = "SELECT SUM(amount) FROM home_budget WHERE type = 'Przychód';";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();
		
            return result.getDouble("SUM(amount)");
        } catch (SQLException e) {
            System.out.println("Read by type: not executed");
        }
        return 0;
    }
	
			public double balanceExpenditure() {

        final String sql = "SELECT SUM(amount) FROM home_budget WHERE type = 'Wydatek';";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();
		
            return result.getDouble("SUM(amount)");
        } catch (SQLException e) {
            System.out.println("Read by type: not executed");
        }
        return 0;
    }
	
	public List<Transaction> readAll() {

        final String sql = "SELECT * FROM home_budget;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            return resultList(result);
        } catch (SQLException e) {
            System.out.println("Read by type: not executed");
        }
        return null;
    }
	
    public List<Transaction> readByType(String type) {

        final String sql = "SELECT * FROM home_budget WHERE type = ? ;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type);

            ResultSet result = ps.executeQuery();

            return resultList(result);
        } catch (SQLException e) {
            System.out.println("Read by type: not executed");
        }
        return null;
    }

    public List<Transaction> readByDate(Date dateStart, Date dateFinish) {

        final String sql = "SELECT * FROM home_budget WHERE date > ? AND date < ? ;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, dateStart);
            ps.setDate(2, dateFinish);
            ResultSet result = ps.executeQuery();

            return resultList(result);

        } catch (SQLException e) {
            System.out.println("Read by date: not executed");
        }
        return null;
    }

    public List<Transaction> readByAmount(double amountStart, double amountFinish) {

        final String sql = "SELECT * FROM home_budget WHERE amount > ? AND amount < ? ;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, amountStart);
            ps.setDouble(2, amountFinish);
            ResultSet result = ps.executeQuery();

            return resultList(result);
        } catch (SQLException e) {
            System.out.println("Read: not executed");
        }
        return null;
    }

    private List<Transaction> resultList(ResultSet result) throws SQLException {

        List<Transaction> transactions = new ArrayList<>();

        while (result.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(result.getLong("id"));
            transaction.setType(result.getString("type"));
            transaction.setDescription(result.getString("description"));
            transaction.setAmount(result.getDouble("amount"));
            transaction.setDate(result.getDate("date"));

            transactions.add(transaction);
        }
        return transactions;
    }
}