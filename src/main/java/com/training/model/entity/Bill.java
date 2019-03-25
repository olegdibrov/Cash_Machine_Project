package com.training.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Bill {
    private Integer idBill;
    private LocalDate date;
    private Integer idUser;
    private Integer idBillStatus;
    private List<Payment> payments;

    public Bill() {
    }

    public Bill(Integer idBill, LocalDate date, Integer idUser, Integer idBillStatus, List<Payment> payments) {
        this.idBill = idBill;
        this.date = date;
        this.idUser = idUser;
        this.idBillStatus = idBillStatus;
        this.payments = payments;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdBillStatus() {
        return idBillStatus;
    }

    public void setIdBillStatus(Integer idBillStatus) {
        this.idBillStatus = idBillStatus;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(idBill, bill.idBill) &&
                Objects.equals(date, bill.date) &&
                Objects.equals(idUser, bill.idUser) &&
                Objects.equals(idBillStatus, bill.idBillStatus) &&
                Objects.equals(payments, bill.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBill, date, idUser, idBillStatus, payments);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", date=" + date +
                ", idUser=" + idUser +
                ", idBillStatus=" + idBillStatus +
                ", payments=" + payments +
                '}';
    }
}
