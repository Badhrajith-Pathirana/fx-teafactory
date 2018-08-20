/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.PurchaseDAO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;
import lk.beempz.tf.entity.Purchase;

public class PurchaseBOImpl implements PurchaseBO {

    PurchaseDAO purchaseDAO;
    SupplierBO supplierBO;
    DebitBO debitBO;
    

    public PurchaseBOImpl() {
        this.debitBO = (DebitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEBIT);
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        this.purchaseDAO = (PurchaseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);
    }

    @Override
    public boolean addPurchase(PurchaseDTO purchaseDTO) throws Exception {
        MonthlyRateBO monthlyRateBO = (MonthlyRateBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MONTHLY_RATE);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            MonthlyRateDTO rates = monthlyRateBO.getRates(new UnprocessedDebitDTO(purchaseDTO.getDate(), purchaseDTO.getSupplierid(), null, purchaseDTO.getaKg(), purchaseDTO.getbKg()));
            BigDecimal payforA = rates.getaGrade().multiply(purchaseDTO.getaKg());
            BigDecimal payforB = rates.getbGrade().multiply(purchaseDTO.getbKg());
            BigDecimal totalSize = purchaseDTO.getaKg().add(purchaseDTO.getbKg());
            BigDecimal payforTravel = rates.getTravelling().multiply(totalSize);
            BigDecimal totalAmount = payforA.add(payforB.subtract(payforTravel));
            Purchase purchase = purchaseDAO.saveAndGetGenerated(new Purchase(-1, purchaseDTO.getDate(), purchaseDTO.getSupplierid(), purchaseDTO.getaKg(), purchaseDTO.getbKg()));
            if (purchase == null) {
                return false;
            }
            boolean result = debitBO.insertDebit(new DebitDTO(-1, purchaseDTO.getDate(), purchase.getPurchase_id(), purchaseDTO.getSupplierid(), purchaseDTO.getSuppliername(), totalAmount));
            if (result) {
                DBConnection.getInstance().getConnection().commit();
                return true;
            } else {
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        } catch (Exception e) {
            DBConnection.getInstance().getConnection().rollback();
            throw e;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean deletePurchase(int pid, Date date) throws Exception {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Purchase result = purchaseDAO.findById(pid);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(result.getPurchase_date());
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            long diff = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (60000 * 60 * 24);
            if (diff > 1) {
                return false;
            }
            
            boolean res = debitBO.deleteByPurchase(pid);
            if(!res){
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
            res = purchaseDAO.delete(pid);
            if(!res){
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
            DBConnection.getInstance().getConnection().commit();
            return true;
                
        } catch (Exception e) {
            DBConnection.getInstance().getConnection().rollback();
            throw e;
        }
        finally{
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
        
    }

    @Override
    public ArrayList<PurchaseDTO> getAll() throws Exception {
        ArrayList<PurchaseDTO> purchaseDTOs = new ArrayList<>();
        ArrayList<Purchase> all = purchaseDAO.getAll();
        for (Purchase purchase : all) {
            purchaseDTOs.add(new PurchaseDTO(purchase.getPurchase_id(), purchase.getPurchase_date(), purchase.getSupplierid(), supplierBO.findSupplier(purchase.getSupplierid()).getName(), purchase.getAkg(), purchase.getBkg()));
        }
        return purchaseDTOs;
    }

    @Override
    public ArrayList<PurchaseDTO> getAllByMonth(Date date) throws Exception {
        ArrayList<PurchaseDTO> purchaseDTOs = new ArrayList<>();
        ArrayList<Purchase> purchases = purchaseDAO.getAllByMonth(date);
        for (Purchase purchase : purchases) {
            purchaseDTOs.add(new PurchaseDTO(purchase.getPurchase_id(), purchase.getPurchase_date(), purchase.getSupplierid(), supplierBO.findSupplier(purchase.getSupplierid()).getName(), purchase.getAkg(), purchase.getBkg()));
        }
        return purchaseDTOs;
    }

   

    

    

}
