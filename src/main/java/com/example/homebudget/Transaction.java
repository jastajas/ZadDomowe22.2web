package com.example.homebudget;

import java.sql.Date;
import java.util.Objects;

public class Transaction {

    private long id;
    private String type;
    private String description;
    private double amount;
    private Date date;

    public Transaction() {
    }

    public Transaction(long id, String type, String description, double amount, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getId() == that.getId() &&
                Double.compare(that.getAmount(), getAmount()) == 0 &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getType(), getDescription(), getAmount(), getDate());
    }

    @Override
    public String toString() {
        return "home_budget{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
