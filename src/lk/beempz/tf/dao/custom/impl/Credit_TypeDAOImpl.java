/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.Credit_TypeDAO;
import lk.beempz.tf.entity.Credit_Type;


public class Credit_TypeDAOImpl implements Credit_TypeDAO {

    @Override
    public boolean save(Credit_Type entity) throws Exception {
        return CrudUtil.executeUpdate("insert into credit_type values(?,?)", entity.getTypeid(),entity.getType_name());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from credit_type where typeid = ?", id);
    }

    @Override
    public boolean update(Credit_Type entity) throws Exception {
        return CrudUtil.executeUpdate("update credit_type set type_name = ? where typeid = ?", entity.getType_name(),entity.getTypeid());
    }

    @Override
    public ArrayList<Credit_Type> getAll() throws Exception {
        ArrayList<Credit_Type> creditTypes = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from credit_type");
        while (rs.next()) {            
            creditTypes.add(new Credit_Type(rs.getInt(1), rs.getString(2)));
        }
        return creditTypes;
    }

    @Override
    public Credit_Type findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("Select * from credit_type where typeid = ?", id);
        if (rs.next()) {
            return new Credit_Type(rs.getInt(1), rs.getString(2));
        }
        return null;
    }

    @Override
    public Credit_Type saveAndGetGenerated(Credit_Type entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into credit_type(type_name) values(?)", entity.getType_name());
        if (rs.next()) {
            return new Credit_Type(rs.getInt(1), entity.getType_name());
        }
        return null;
    }

    @Override
    public int getCreditTypeid(String creditType) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select typeid from credit_type where type_name = ?", creditType);
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }
    
}
