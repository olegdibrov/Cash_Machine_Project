package com.training.model.entity;

import java.util.Objects;

/**
 * Class describing bill status
 *
 * @author Oleg Dibrov
 */
public class BillStatus {

    private Integer id;

    private String billStatusName;

    public BillStatus() {
    }

    public BillStatus(Integer id, String billStatusName) {
        this.id = id;
        this.billStatusName = billStatusName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillStatusName() {
        return billStatusName;
    }

    public void setBillStatusName(String billStatusName) {
        this.billStatusName = billStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillStatus that = (BillStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(billStatusName, that.billStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billStatusName);
    }

    @Override
    public String toString() {
        return "BillStatus{" +
                "id=" + id +
                ", billStatusName='" + billStatusName + '\'' +
                '}';
    }
}
