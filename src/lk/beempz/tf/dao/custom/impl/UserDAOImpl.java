/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.UserDAO;
import lk.beempz.tf.entity.User;


public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) throws Exception {
        return CrudUtil.executeUpdate("insert into user values(?,?,?)", entity.getUsername(),entity.getPassword(),entity.getAcc_type());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("delete from user where username = ?", id);
    }

    @Override
    public boolean update(User entity) throws Exception {
        return CrudUtil.executeUpdate("update into user set password = ?, acc_type = ? where username = ?", entity.getPassword(),entity.getAcc_type(),entity.getUsername());
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from user");
        while (rs.next()) {            
            users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return users;
    }

    @Override
    public User findById(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from user where username = ?", id);
        if (rs.next()) {
            return new User(rs.getString(1), rs.getString(2), rs.getString(3));
        }
        return null;
    }

    @Override
    public User saveAndGetGenerated(User entity) throws Exception {
        return null;
    }
    
}
