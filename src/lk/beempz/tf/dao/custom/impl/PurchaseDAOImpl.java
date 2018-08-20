/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.PurchaseDAO;
import lk.beempz.tf.entity.Purchase;


public class PurchaseDAOImpl implements PurchaseDAO {

    @Override
    public boolean save(Purchase entity) throws Exception {
        return CrudUtil.executeUpdate("insert into purchase values(?,?,?,?,?)", entity.getPurchase_id(),entity.getPurchase_date(),entity.getSupplierid(),entity.getAkg(),entity.getBkg());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from purchase where purchase_id = ?", id);
    }

    @Override
    public boolean update(Purchase entity) throws Exception {
        return CrudUtil.executeUpdate("update purchase set purchase_date = ? , supplierid = ? , akg = ? , bkg = ? where purchase_id = ?", entity.getPurchase_date(),entity.getSupplierid(),entity.getAkg(),entity.getBkg(),entity.getPurchase_id());
    }

    @Override
    public ArrayList<Purchase> getAll() throws Exception {
        ArrayList<Purchase> purchases = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from purchase");
        while (rs.next()) {            
            purchases.add(new Purchase(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getBigDecimal(5)));
        }
        return purchases;
    }

    @Override
    public Purchase findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from purchase where purchase_id = ?", id);
        if (rs.next()) {
            return new Purchase(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getBigDecimal(5));
        }
        return null;
    }

    @Override
    public Purchase saveAndGetGenerated(Purchase entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into purchase(purchase_date,supplierid,akg,bkg) values(?,?,?,?)", entity.getPurchase_date(),entity.getSupplierid(),entity.getAkg(),entity.getBkg());
        if (rs.next()) {
            return new Purchase(rs.getInt(1), entity.getPurchase_date(), entity.getSupplierid(), entity.getAkg(), entity.getBkg());
        }
        return null;
    }

    @Override
    public ArrayList<Purchase> getAllByMonth(Date date) throws Exception {
        ArrayList<Purchase> purchases = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from purchase where MONTH(purchase_date) = ?", date.getMonth()+1);
        while (rs.next()) {            
            purchases.add(new Purchase(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getBigDecimal(5)));
        }
        return purchases;
    }
    
}
