package com.formos.test.model;

import com.formos.test.enums.Product;

import java.time.LocalDateTime;

public class SaleReport {
    private Integer id;
    private String productName;
    private Integer quantity;
    private double totalPrice;
    private double costPrice;
    private LocalDateTime dateTime;

    public SaleReport() {
    }

    public SaleReport(Integer id, Product product, Integer quantity, double totalPrice, double costPrice, LocalDateTime dateTime) {
        this.id = id;
        this.productName = product.getName();
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.costPrice = costPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(Product product) {
        this.productName = product.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", name=" + productName +
                ", quantity=" + quantity +
                ", total price=" + totalPrice +
                ", cost price=" + costPrice +
                ", dateTime=" + dateTime +
                ']';
    }
}
