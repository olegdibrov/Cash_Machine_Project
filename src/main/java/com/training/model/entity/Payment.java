package com.training.model.entity;

import java.util.Objects;

public class Payment {
    private Integer idPayment;
    private Integer idBill;
    private Integer idProduct;
    private Integer quantity;

    public Payment() {
    }

    public Payment(Integer idPayment, Integer idBill, Integer idProduct, Integer quantity) {
        this.idPayment = idPayment;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(idPayment, payment.idPayment) &&
                Objects.equals(idBill, payment.idBill) &&
                Objects.equals(idProduct, payment.idProduct) &&
                Objects.equals(quantity, payment.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPayment, idBill, idProduct, quantity);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", idBill=" + idBill +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                '}';
    }
}

