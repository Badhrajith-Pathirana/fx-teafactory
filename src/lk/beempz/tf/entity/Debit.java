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
public class Debit {
    private int debitid;
    private int purchaseid;
    private Date debitdate;
    private int supplierid;
    private BigDecimal amount;

    public Debit() {
    }

    public Debit(int debitid, int purchaseid, Date debitdate, int supplierid, BigDecimal amount) {
        this.debitid = debitid;
        this.purchaseid = purchaseid;
        this.debitdate = debitdate;
        this.supplierid = supplierid;
        this.amount = amount;
    }

    /**
     * @return the debitid
     */
    public int getDebitid() {
        return debitid;
    }

    /**
     * @param debitid the debitid to set
     */
    public void setDebitid(int debitid) {
        this.debitid = debitid;
    }

    /**
     * @return the purchaseid
     */
    public int getPurchaseid() {
        return purchaseid;
    }

    /**
     * @param purchaseid the purchaseid to set
     */
    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    /**
     * @return the debitdate
     */
    public Date getDebitdate() {
        return debitdate;
    }

    /**
     * @param debitdate the debitdate to set
     */
    public void setDebitdate(Date debitdate) {
        this.debitdate = debitdate;
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
