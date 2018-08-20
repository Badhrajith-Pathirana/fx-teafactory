/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author badhr
 */
public class Credit {
    private int creditid;
    private int supplierid;
    private int credit_type;
    private Date date;
    private BigDecimal amount;

    public Credit() {
    }

    public Credit(int creditid, int supplierid, int credit_type, Date date, BigDecimal amount) {
        this.creditid = creditid;
        this.supplierid = supplierid;
        this.credit_type = credit_type;
        this.date = date;
        this.amount = amount;
    }

    /**
     * @return the creditid
     */
    public int getCreditid() {
        return creditid;
    }

    /**
     * @param creditid the creditid to set
     */
    public void setCreditid(int creditid) {
        this.creditid = creditid;
    }

    /**
     * @return the supplierid
     */
    public int getSupplierid() {
        return supplierid;
    }

    /**
     * @param supplierid the supplierid to set
     */
    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    /**
     * @return the credit_type
     */
    public int getCredit_type() {
        return credit_type;
    }

    /**
     * @param credit_type the credit_type to set
     */
    public void setCredit_type(int credit_type) {
        this.credit_type = credit_type;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
