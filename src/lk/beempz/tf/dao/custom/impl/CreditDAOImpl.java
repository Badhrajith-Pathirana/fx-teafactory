/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.CreditDAO;
import lk.beempz.tf.entity.Credit;


public class CreditDAOImpl implements CreditDAO {

    @Override
    public boolean save(Credit entity) throws Exception {
        return CrudUtil.executeUpdate("insert into credit values(?,?,?,?,?)", entity.getCreditid(),entity.getSupplierid(),entity.getCredit_type(),entity.getDate(),entity.getAmount());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from credit where creditid = ?", id);
    }

    @Override
    public boolean update(Credit entity) throws Exception {
        return CrudUtil.executeUpdate("update credit set supplierid = ?,credit_type = ?, date = ?, amount = ? where creditid = ?", entity.getSupplierid(),entity.getCredit_type(),entity.getDate(),entity.getAmount(),entity.getCreditid());
    }

    @Override
    public ArrayList<Credit> getAll() throws Exception {
        ArrayList<Credit> credits = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from credit");
        while (rs.next()) {            
            credits.add(new Credit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getBigDecimal(5)));
        }
        return credits;
    }

    @Override
    public Credit findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from credit where creditid = ?", id);
        if (rs.next()) {
            return new Credit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getBigDecimal(5));
        }
        return null;
    }

    @Override
    public Credit saveAndGetGenerated(Credit entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into credit(supplierid,credit_type,date,amount) values(?,?,?,?)", entity.getSupplierid(),entity.getCredit_type(),entity.getDate(),entity.getAmount());
        if (rs.next()) {
            return new Credit(rs.getInt(1), entity.getSupplierid(), entity.getCredit_type(), entity.getDate(), entity.getAmount());
        }
        return null;
    }

    @Override
    public ArrayList<Credit> getFromTo(Date from, Date to)throws Exception{
        ArrayList<Credit> credits = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from credit where date between ? and ?", from,to);
        while (rs.next()) {            
            credits.add(new Credit(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getBigDecimal(5)));
        }
        return credits;
    }
    
}
