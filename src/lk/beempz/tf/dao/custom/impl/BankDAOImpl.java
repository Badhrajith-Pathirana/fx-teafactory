/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.BankDAO;
import lk.beempz.tf.entity.Bank;

public class BankDAOImpl implements BankDAO {

    @Override
    public boolean save(Bank entity) throws Exception {
        return CrudUtil.executeUpdate("insert into bank values(?,?)", entity.getBankid(),entity.getBankName());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from bank where bankid = ?", id);
    }

    @Override
    public boolean update(Bank entity) throws Exception {
        return CrudUtil.executeUpdate("update bank set bankName = ? where bankid = ?", entity.getBankName(), entity.getBankid());
    }

    @Override
    public ArrayList<Bank> getAll() throws Exception {
        ArrayList<Bank> banks = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from bank");
        while(rs.next()){
            banks.add(new Bank(rs.getInt(1), rs.getString(2)));
        }
        return banks;
    }

    @Override
    public Bank findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from bank where bankid = ?", id);
        if(rs.next()){
            return new Bank(rs.getInt(1), rs.getString(2));
        }
        return null;
    }

    @Override
    public Bank saveAndGetGenerated(Bank entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into bank(bankName) values(?)", entity.getBankName());
        if(rs.next()){
            return new Bank(rs.getInt(1), entity.getBankName());
        }
        return null;
    }

    @Override
    public int getID(String bankName) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select bankid from bank where bankName = ?", bankName);
        if(rs.next()){
            return rs.getInt(1);
        }
            return -1;
        
    }

}
