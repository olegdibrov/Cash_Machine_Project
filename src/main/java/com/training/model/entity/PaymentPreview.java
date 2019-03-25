package com.training.model.entity;

import java.util.List;
import java.util.Objects;

public class PaymentPreview {
    private Integer idPayment;
    private Product product;
    private Integer quantity;

    public PaymentPreview() {
    }

    public PaymentPreview(Integer idPayment, Product product, Integer quantity) {
        this.idPayment = idPayment;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        PaymentPreview that = (PaymentPreview) o;
        return Objects.equals(idPayment, that.idPayment) &&
                Objects.equals(product, that.product) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPayment, product, quantity);
    }

    @Override
    public String toString() {
        return "PaymentPreview{" +
                "idPayment=" + idPayment +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
