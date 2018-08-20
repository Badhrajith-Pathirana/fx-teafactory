/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RateDAO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;
import lk.beempz.tf.entity.Debit;
import lk.beempz.tf.entity.Rate;


public class MonthlyRateBOImpl implements MonthlyRateBO {

    RateDAO rateDAO;
   // DebitBO debitBO;
    
    public MonthlyRateBOImpl() {
        
        //this.debitBO = (DebitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEBIT);
        this.rateDAO = (RateDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RATE);
    }

    

    

    @Override
    public boolean updateMonthlyRates(MonthlyRateDTO monthlyRateDTO)throws Exception {
        return rateDAO.save(new Rate(monthlyRateDTO.getDate(), monthlyRateDTO.getaGrade(), monthlyRateDTO.getbGrade(), monthlyRateDTO.getTravelling()));
    }

    @Override
    public MonthlyRateDTO getRates(UnprocessedDebitDTO debitDTO)throws Exception{
        Rate rate = rateDAO.findById(debitDTO.getDate());
        return new MonthlyRateDTO(debitDTO.getDate(), rate.getAkgper(), rate.getBkgper(), rate.getTravelling());
    }

    @Override
    public ArrayList<MonthlyRateDTO> getAllRates() throws Exception {
        ArrayList<MonthlyRateDTO> monthlyRateDTOs = new ArrayList<>();
        ArrayList<Rate> all = rateDAO.getAll();
        for (Rate rate : all) {
            monthlyRateDTOs.add(new MonthlyRateDTO(rate.getRateMonth(), rate.getAkgper(), rate.getBkgper(), rate.getTravelling()));
        }
        return monthlyRateDTOs;
    }

    
}
