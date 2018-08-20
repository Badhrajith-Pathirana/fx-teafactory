/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.beempz.tf.dao.CrudUtil;
import lk.beempz.tf.dao.custom.RouteDAO;
import lk.beempz.tf.entity.Route;


public class RouteDAOImpl implements RouteDAO {

    @Override
    public boolean save(Route entity) throws Exception {
        return CrudUtil.executeUpdate("insert into route values(?,?)", entity.getRouteid(),entity.getRoutename());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from route where routeid  =?", id);
    }

    @Override
    public boolean update(Route entity) throws Exception {
        return CrudUtil.executeUpdate("update route set routename = ? where routeid = ?", entity.getRoutename(),entity.getRouteid());
    }

    @Override
    public ArrayList<Route> getAll() throws Exception {
        ArrayList<Route> routes = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from route");
        while (rs.next()) {            
            routes.add(new Route(rs.getInt(1), rs.getString(2)));
        }
        return routes;
    }

    @Override
    public Route findById(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from route where routeid = ?", id);
        if (rs.next()) {
            return new Route(rs.getInt(1), rs.getString(2));
        }
        return null;
    }

    @Override
    public Route saveAndGetGenerated(Route entity) throws Exception {
        ResultSet rs = CrudUtil.executeUpdateWithGeneratedKeys("insert into route(routename) values(?)", entity.getRoutename());
        if (rs.next()) {
            return new Route(rs.getInt(1),entity.getRoutename());
        }
        return null;
    }

    @Override
    public Route getRouteID(String name) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from route where routename = ?", name);
        if (rs.next()) {
            return new Route(rs.getInt(1), name);
        }
        return null;
    }
    
}
