/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

/**
 *
 * @author badhr
 */
public class Bank {
    private int bankid;
    private String bankName;

    public Bank() {
    }

    public Bank(int bankid, String bankName) {
        this.bankid = bankid;
        this.bankName = bankName;
    }

    /**
     * @return the bankid
     */
    public int getBankid() {
        return bankid;
    }

    /**
     * @param bankid the bankid to set
     */
    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    /**
     * @return the bankname
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankname the bankname to set
     */
    public void setBankname(String bankName) {
        this.bankName = bankName;
    }
    
}
