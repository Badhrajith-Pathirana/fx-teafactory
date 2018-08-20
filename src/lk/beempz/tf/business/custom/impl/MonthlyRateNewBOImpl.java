/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateNewBO;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RateDAO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.entity.Rate;


public class MonthlyRateNewBOImpl implements MonthlyRateNewBO {
    DebitBO debitBO;
    RateDAO rateDAO;

    public MonthlyRateNewBOImpl() {
        this.rateDAO = (RateDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RATE);
        this.debitBO = (DebitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEBIT);
    }

    @Override
    public boolean insertMonthlyRates(MonthlyRateDTO debitDTO) throws Exception {
             PurchaseBO purchaseBO = (PurchaseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean result = rateDAO.update(new Rate(debitDTO.getDate(), debitDTO.getaGrade(), debitDTO.getbGrade(), debitDTO.getTravelling()));
            if(!result){
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
            ArrayList<PurchaseDTO> purchaseDTOs = purchaseBO.getAllByMonth(debitDTO.getDate());
            for (PurchaseDTO purchaseDTO : purchaseDTOs) {
                BigDecimal aPrice = purchaseDTO.getaKg().multiply(debitDTO.getaGrade());
                BigDecimal bPrice = purchaseDTO.getbKg().multiply(debitDTO.getbGrade());
                BigDecimal totalTea = purchaseDTO.getaKg().add(purchaseDTO.getbKg());
                BigDecimal travellingPrice = debitDTO.getTravelling().multiply(totalTea);
                BigDecimal totalPricee = aPrice.add(bPrice.subtract(travellingPrice));
                DebitDTO purchase = debitBO.getAllByPurchaseID(purchaseDTO.getPurchaseid());
                boolean res = debitBO.updateDebit(new DebitDTO(purchase.getDebitid(), purchaseDTO.getDate(), purchaseDTO.getPurchaseid(), purchaseDTO.getSupplierid(), null, totalPricee));
                if(!res){
                    DBConnection.getInstance().getConnection().rollback();
                    return false;
                }
                
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
    public MonthlyRateDTO findByID(Date date) throws Exception{
        Rate findById = rateDAO.findById(date);
        if(findById == null)
            return null;
        return new MonthlyRateDTO(findById.getRateMonth(), findById.getAkgper(), findById.getBkgper(), findById.getTravelling());
    }
    
}
