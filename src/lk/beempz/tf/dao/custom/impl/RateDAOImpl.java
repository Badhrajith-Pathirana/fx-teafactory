/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.RateDAO;
import lk.beempz.tf.entity.Rate;


public class RateDAOImpl implements RateDAO {

    @Override
    public boolean save(Rate entity) throws Exception {
        return CrudUtil.executeUpdate("insert into rate values(?,?,?,?)", entity.getRateMonth(),entity.getAkgper(),entity.getBkgper(),entity.getTravelling());
        
    }

    @Override
    public boolean delete(Date id) throws Exception {
        return CrudUtil.executeUpdate("delete from rate where rateMonth = ?", id);
    }

    @Override
    public boolean update(Rate entity) throws Exception {
        return CrudUtil.executeUpdate("update rate set akgper = ? , bkgper = ? , travelling = ? where rateMonth = ?", entity.getAkgper(),entity.getBkgper(),entity.getTravelling(),entity.getRateMonth());
    }

    @Override
    public ArrayList<Rate> getAll() throws Exception {
        ArrayList<Rate> rates = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from rate");
        while (rs.next()) {            
            rates.add(new Rate(rs.getDate(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getBigDecimal(4)));
        }
        return rates;
    }

    @Override
    public Rate findById(Date id) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(id);
        System.out.println(c.get(Calendar.MONTH));
        ResultSet rs = CrudUtil.executeQuery("select * from rate where MONTH(rateMonth) = ?", c.get(Calendar.MONTH)+1);
        if (rs.next()) {
            return new Rate(rs.getDate(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getBigDecimal(4));
        }
        return null;
    }

    @Override
    public Rate saveAndGetGenerated(Rate entity) throws Exception {
        return null;
    }
    
}
