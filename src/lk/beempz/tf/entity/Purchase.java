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
public class Purchase {
    private int purchase_id;
    private Date purchase_date;
    private int supplierid;
    private BigDecimal akg;
    private BigDecimal bkg;

    public Purchase() {
    }

    public Purchase(int purchase_id, Date purchase_date, int supplierid, BigDecimal akg, BigDecimal bkg) {
        this.purchase_id = purchase_id;
        this.purchase_date = purchase_date;
        this.supplierid = supplierid;
        this.akg = akg;
        this.bkg = bkg;
    }

    /**
     * @return the purchase_id
     */
    public int getPurchase_id() {
        return purchase_id;
    }

    /**
     * @param purchase_id the purchase_id to set
     */
    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    /**
     * @return the purchase_date
     */
    public Date getPurchase_date() {
        return purchase_date;
    }

    /**
     * @param purchase_date the purchase_date to set
     */
    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
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
     * @return the akg
     */
    public BigDecimal getAkg() {
        return akg;
    }

    /**
     * @param akg the akg to set
     */
    public void setAkg(BigDecimal akg) {
        this.akg = akg;
    }

    /**
     * @return the bkg
     */
    public BigDecimal getBkg() {
        return bkg;
    }

    /**
     * @param bkg the bkg to set
     */
    public void setBkg(BigDecimal bkg) {
        this.bkg = bkg;
    }
    
}
