/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.SupplierDAO;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.dto.SupplierDTO;
import lk.beempz.tf.entity.Supplier;


public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO;
    RouteBO routeBO;

    public SupplierBOImpl() {
        this.routeBO = (RouteBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROUTE);
        this.supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    }
    @Override
    public ArrayList<SupplierDTO> getSuppliers() throws Exception {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOs = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            RouteDTO route = routeBO.findRoute(supplier.getRoute());
            supplierDTOs.add(new SupplierDTO(supplier.getSupplierno(), supplier.getName(), supplier.getRoute(), route.getRoute(), supplier.getPhone(), supplier.getAddress()));
            
        }
        return supplierDTOs;
    }

    @Override
    public SupplierDTO findSupplier(int Id) throws Exception {
        Supplier supplier = supplierDAO.findById(Id);
        return new SupplierDTO(supplier.getSupplierno(), supplier.getName(), supplier.getRoute(), routeBO.findRoute(supplier.getRoute()).getRoute(), supplier.getPhone(), supplier.getAddress());
        
    }

    @Override
    public boolean addNewSupplier(SupplierDTO supplier) throws Exception {
        if(supplier.getRouteid() == -1){
            supplier.setRouteid(routeBO.getRouteByName(supplier.getRoute()).getRouteid());
        }
        if(supplier.getSupplierid() == -1){
            Supplier id = supplierDAO.saveAndGetGenerated(new Supplier(-1, supplier.getName(), supplier.getRouteid(), supplier.getContact(), supplier.getAddress()));
            if(id == null)
                return false;
            return true;
        }
        return supplierDAO.save(new Supplier(supplier.getSupplierid(), supplier.getName(), supplier.getRouteid(), supplier.getContact(), supplier.getAddress()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        if(supplier.getRouteid() == -1){
            supplier.setRouteid(routeBO.getRouteByName(supplier.getRoute()).getRouteid());
        }
        return supplierDAO.update(new Supplier(supplier.getSupplierid(), supplier.getName(), supplier.getRouteid(), supplier.getContact(), supplier.getAddress()));
    }

    @Override
    public boolean deleteSupplier(int id)throws Exception{
        return supplierDAO.delete(id);
    }

    @Override
    public int addAndReturnGenerated(SupplierDTO supplier) throws Exception{
        if(supplier.getRouteid() == -1){
            supplier.setRouteid(routeBO.getRouteByName(supplier.getRoute()).getRouteid());
        }
        if(supplier.getSupplierid() == -1){
            Supplier id = supplierDAO.saveAndGetGenerated(new Supplier(-1, supplier.getName(), supplier.getRouteid(), supplier.getContact(), supplier.getAddress()));
            if(id == null)
                return -1;
            return id.getSupplierno();
        }
        return -1;
    }
}
