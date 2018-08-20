/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.BankDTO;

/**
 *
 * @author badhr
 */
public interface BankBO extends SuperBO{
    public BankDTO saveBank(BankDTO bank)throws Exception;
    public boolean updateBank(BankDTO bank)throws Exception;
    public boolean deleteBank(int bankid)throws Exception;
    public ArrayList<BankDTO> getAllBanks()throws Exception;
    public int getBankID(String bankName)throws Exception;
    public String findBankName(int bankid)throws Exception;
}
