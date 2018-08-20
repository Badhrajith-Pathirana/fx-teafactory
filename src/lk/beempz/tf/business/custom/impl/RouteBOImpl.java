/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RouteDAO;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.entity.Route;


public class RouteBOImpl implements RouteBO {

    RouteDAO routeDAO;

    public RouteBOImpl() {
        this.routeDAO = (RouteDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROUTE);
    }
    @Override
    public RouteDTO findRoute(int routeid)throws Exception{
        Route routeResult = routeDAO.findById(routeid);
        if(routeResult == null){
            return null;
        }
        return new RouteDTO(routeResult.getRouteid(), routeResult.getRoutename());
    }

    @Override
    public ArrayList<RouteDTO> getRoutes() throws Exception {
        ArrayList<RouteDTO> routeDTOs = new ArrayList<>();
        ArrayList<Route> routes = routeDAO.getAll();
        for (Route route : routes) {
            routeDTOs.add(new RouteDTO(route.getRouteid(), route.getRoutename()));
        }
        return routeDTOs;
    }

    @Override
    public RouteDTO getRouteByName(String name) throws Exception {
        Route routeID = routeDAO.getRouteID(name);
        return new RouteDTO(routeID.getRouteid(), name);
    }

    @Override
    public boolean saveRoute(RouteDTO routeDTO) throws Exception {
        return routeDAO.saveAndGetGenerated(new Route(routeDTO.getRouteid(), routeDTO.getRoute())) != null;
    }

    @Override
    public boolean updateRoute(RouteDTO routeDTO) throws Exception {
        return routeDAO.update(new Route(routeDTO.getRouteid(), routeDTO.getRoute()));
    }

    @Override
    public boolean deleteRoute(int routeid) throws Exception {
        return routeDAO.delete(routeid);
    }

    

    

    
}
