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
public class Rate {
    private Date rateMonth;
    private BigDecimal akgper;
    private BigDecimal bkgper;
    private BigDecimal travelling;

    public Rate() {
    }

    public Rate(Date rateMonth, BigDecimal akgper, BigDecimal bkgper, BigDecimal travelling) {
        this.rateMonth = rateMonth;
        this.akgper = akgper;
        this.bkgper = bkgper;
        this.travelling = travelling;
    }

    /**
     * @return the rateMonth
     */
    public Date getRateMonth() {
        return rateMonth;
    }

    /**
     * @param rateMonth the rateMonth to set
     */
    public void setRateMonth(Date rateMonth) {
        this.rateMonth = rateMonth;
    }

    /**
     * @return the akgper
     */
    public BigDecimal getAkgper() {
        return akgper;
    }

    /**
     * @param akgper the akgper to set
     */
    public void setAkgper(BigDecimal akgper) {
        this.akgper = akgper;
    }

    /**
     * @return the bkgper
     */
    public BigDecimal getBkgper() {
        return bkgper;
    }

    /**
     * @param bkgper the bkgper to set
     */
    public void setBkgper(BigDecimal bkgper) {
        this.bkgper = bkgper;
    }

    /**
     * @return the travelling
     */
    public BigDecimal getTravelling() {
        return travelling;
    }

    /**
     * @param travelling the travelling to set
     */
    public void setTravelling(BigDecimal travelling) {
        this.travelling = travelling;
    }
    
}
