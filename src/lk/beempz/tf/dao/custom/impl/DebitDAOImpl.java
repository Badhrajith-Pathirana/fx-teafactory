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
import lk.beempz.tf.dao.custom.DebitDAO;
import lk.beempz.tf.entity.Debit;

public class DebitDAOImpl implements DebitDAO {

    @Override
    public boolean save(Debit entity) throws Exception {
        return CrudUtil.executeUpdate("insert into debit values(?,?,?,?,?)", entity.getDebitid(), entity.getPurchaseid(), entity.getDebitdate(), entity.getSupplierid(), entity.getAmount());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from debit where debitid = ?", id);
    }

    @Override
    public boolean update(Debit entity) throws Exception {
        return CrudUtil.executeUpdate("update debit set detail = ? , purchaseid = ? , supplierid = ? , amount = ? where debitid = ?", entity.getPurchaseid(), entity.getDebitdate(), entity.getSupplierid(), entity.getAmount(), entity.getDebitid());
    }

    @Override
    public ArrayList<Debit> getAll() throws Exception {
        ArrayList<Debit> debits = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from debit");
        while (rs.next()) {
            debits.add(new Debit(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getBigDecimal(5)));
        }
        return debits;
    }

    @Override
    public Debit findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from debit where debitid = ?", id);
        if (rs.next()) {
            return new Debit(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getBigDecimal(5));
        }
        return null;
    }

    @Override
    public Debit saveAndGetGenerated(Debit entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into debit(purchaseid,debitdate,supplierid,amount) values(?,?,?,?)", entity.getPurchaseid(), entity.getDebitdate(), entity.getSupplierid(), entity.getAmount());
        if (rs.next()) {
            return new Debit(rs.getInt(1), entity.getPurchaseid(), entity.getDebitdate(), entity.getSupplierid(), entity.getAmount());
        }
        return null;
    }

    @Override
    public ArrayList<Debit> getSortAndFiltered(Date from, Date to) throws Exception {
        ArrayList<Debit> debits = new ArrayList<>();
        String query = "select * from debit where debitdate between ? and ? ";
        ResultSet rs = CrudUtil.executeQuery(query, from, to);
        while (rs.next()) {
            debits.add(new Debit(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getBigDecimal(5)));
        }
        return debits;
    }

    @Override
    public boolean deleteByPurchaseid(int purchaseid) throws Exception {
        return CrudUtil.executeUpdate("delete from debit where purchaseid = ?", purchaseid);
    }

    @Override
    public Debit selectByPurchaseID(int purchaseid) throws Exception {
        
        ResultSet rs = CrudUtil.executeQuery("select * from debit where purchaseid = ?", purchaseid);
        if (rs.next()) {
            return new Debit(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getBigDecimal(5));
        }
        return null;
    }

    

}
