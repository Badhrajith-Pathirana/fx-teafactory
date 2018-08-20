/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.BranchDAO;
import lk.beempz.tf.entity.Branch;


public class BranchDAOImpl implements BranchDAO {

    @Override
    public boolean save(Branch entity) throws Exception {
        return CrudUtil.executeUpdate("insert into branch values(?,?,?)", entity.getBranchid(),entity.getBankid(),entity.getBranchName());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from branch where branchid = ?", id);
    }

    @Override
    public boolean update(Branch entity) throws Exception {
        return CrudUtil.executeUpdate("update branch set bankid = ? , branchName = ? where branchid = ?", entity.getBankid(),entity.getBranchName(),entity.getBranchid());
    }

    @Override
    public ArrayList<Branch> getAll() throws Exception {
        ArrayList<Branch> branches = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from branch");
        while(rs.next()){
            branches.add(new Branch(rs.getInt(1), rs.getInt(2), rs.getString(3)));
        }
        return branches;
    }

    @Override
    public Branch findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from branch where branchid = ?", id);
        if(rs.next()){
            return new Branch(rs.getInt(1), rs.getInt(2), rs.getString(3));
        }
        return null;
    }

    @Override
    public Branch saveAndGetGenerated(Branch entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into branch(bankid,branchName) values(?,?)", entity.getBankid(),entity.getBranchName());
        if(rs.next()){
            return new Branch(rs.getInt(1), entity.getBankid(), entity.getBranchName());
        }
        return null;
    }
    
    
}
