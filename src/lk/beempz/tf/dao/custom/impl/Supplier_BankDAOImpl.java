/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.Supplier_BankDAO;
import lk.beempz.tf.entity.Supplier_Bank;
import lk.beempz.tf.entity.Supplier_Bank_PK;


public class Supplier_BankDAOImpl implements Supplier_BankDAO {

    @Override
    public boolean save(Supplier_Bank entity) throws Exception {
        return CrudUtil.executeUpdate("insert into supplier_bank values(?,?,?)", entity.getSupplier_Bank_PK().getBranchid(),entity.getSupplier_Bank_PK().getSupplierid(),entity.getAcc_no());
    }

    @Override
    public boolean delete(Supplier_Bank_PK id) throws Exception {
        return CrudUtil.executeUpdate("delete from supplier_bank where branchid = ? and supplierid = ?", id.getBranchid(),id.getSupplierid());
    }

    @Override
    public boolean update(Supplier_Bank entity) throws Exception {
        return CrudUtil.executeUpdate("update supplier_bank set acc_no = ? where branchid = ? and supplierid = ?", entity.getAcc_no(),entity.getSupplier_Bank_PK().getBranchid(),entity.getSupplier_Bank_PK().getSupplierid());
    }

    @Override
    public ArrayList<Supplier_Bank> getAll() throws Exception {
        ArrayList<Supplier_Bank> supplier_banks = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from supplier_bank");
        while (rs.next()) {            
            supplier_banks.add(new Supplier_Bank(new Supplier_Bank_PK(rs.getInt(1), rs.getInt(2)), rs.getString(3)));
        }
        return supplier_banks;
    }

    @Override
    public Supplier_Bank findById(Supplier_Bank_PK id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from supplier_bank where branchid = ? and supplierid = ?", id.getBranchid(),id.getSupplierid());
        if (rs.next()) {
            return new Supplier_Bank(new Supplier_Bank_PK(rs.getInt(1), rs.getInt(2)), rs.getString(3));
        }
        return null;
    }

    @Override
    public Supplier_Bank saveAndGetGenerated(Supplier_Bank entity) throws Exception {
        return null;
    }
    
}
