/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import lk.beempz.tf.business.custom.BankBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.BankDAO;
import lk.beempz.tf.dto.BankDTO;
import lk.beempz.tf.entity.Bank;


public class BankBOImpl implements BankBO {
    BankDAO bankDAO;

    public BankBOImpl() {
        this.bankDAO = (BankDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BANK);
    }

    @Override
    public BankDTO saveBank(BankDTO bank) throws Exception {
        Bank bankent = bankDAO.saveAndGetGenerated(new Bank(bank.getBankid(), bank.getBankName()));
        if(bankent == null)
            return null;
        return new BankDTO(bankent.getBankid(), bankent.getBankName());
    }

    @Override
    public boolean updateBank(BankDTO bank) throws Exception {
        return bankDAO.update(new Bank(bank.getBankid(), bank.getBankName()));
    }

    @Override
    public boolean deleteBank(int bankid) throws Exception {
        return bankDAO.delete(bankid);
    }

    @Override
    public ArrayList<BankDTO> getAllBanks() throws Exception {
        ArrayList<BankDTO> bankDTOs = new ArrayList<>();
        ArrayList<Bank> banks = bankDAO.getAll();
        for (Bank bank : banks) {
            bankDTOs.add(new BankDTO(bank.getBankid(), bank.getBankName()));
        }
        return bankDTOs;
    }

    @Override
    public int getBankID(String bankName) throws Exception {
        return bankDAO.getID(bankName);
    }

    @Override
    public String findBankName(int bankid) throws Exception {
        return bankDAO.findById(bankid).getBankName();
    }
    void mymethod(){
        System.out.println("This is mymethod");
    }
    

    
    
}
