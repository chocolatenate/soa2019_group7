package com.example.pos.api.stock.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String date;
    private String date_out;
    private int quantity;
    private double price;
    private String status;

    public Stock(){}

    public Stock(int id, String name, String date, int quantity, double price, String status, String date_out) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.date_out = date_out;
    }

    public String getDate_out() {
        return date_out;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
