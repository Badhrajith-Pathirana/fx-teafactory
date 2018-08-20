/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.beempz.tf.db.DBConnection;

/**
 *
 * @author badhr
 */
public class CrudUtil {
    private static PreparedStatement getPreparedStatement(String query, Object... args) throws ClassNotFoundException, SQLException, IOException{
        Connection conn = DBConnection.getInstance().getConnection();
        //System.out.println(conn.getAutoCommit());
        PreparedStatement pst = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i+1, args[i]);
        }
        return pst;
    }
    public static boolean executeUpdate(String query , Object... args) throws ClassNotFoundException, SQLException, IOException{
        return getPreparedStatement(query, args).executeUpdate()>0;
    }
    public static ResultSet executeQuery(String query , Object... args) throws ClassNotFoundException, SQLException, IOException{
        return getPreparedStatement(query, args).executeQuery();
    }
    public static ResultSet executeUpdateWithGeneratedKeys(String query, Object... args) throws ClassNotFoundException, SQLException, IOException{
        PreparedStatement pst = getPreparedStatement(query, args);
        int exec = pst.executeUpdate();
        if(exec>0){
            return pst.getGeneratedKeys();
        }
        return null;
    }
}
