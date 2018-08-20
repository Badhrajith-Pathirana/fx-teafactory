/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.SupplierDAO;
import lk.beempz.tf.entity.Supplier;


public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier entity) throws Exception {
        return CrudUtil.executeUpdate("insert into supplier values(?,?,?,?,?)", entity.getSupplierno(),entity.getName(),entity.getRoute(),entity.getPhone(),entity.getAddress());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from supplier where supplierno = ?", id);
    }

    @Override
    public boolean update(Supplier entity) throws Exception {
        return CrudUtil.executeUpdate("update supplier set name = ? , route = ? , phone = ? , address = ? where supplierno = ?", entity.getName(),entity.getRoute(),entity.getPhone(),entity.getAddress(),entity.getSupplierno());
    }

    @Override
    public ArrayList<Supplier> getAll() throws Exception {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from supplier");
        while (rs.next()) {            
            suppliers.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return suppliers;
    }

    @Override
    public Supplier findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("Select * from supplier where supplierno = ?", id);
        if (rs.next()) {
            return new Supplier(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
        }
        return null;
    }

    @Override
    public Supplier saveAndGetGenerated(Supplier entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into supplier(name, route, phone,address) values(?,?,?,?)", entity.getName(),entity.getRoute(),entity.getPhone(),entity.getAddress());
        if(rs.next()){
            return new Supplier(rs.getInt(1), entity.getName(), entity.getRoute(), entity.getPhone(), entity.getAddress());
        }
        return null;
    }
    
}
