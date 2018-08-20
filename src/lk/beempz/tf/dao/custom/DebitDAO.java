/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom;

import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.dao.CrudDAO;
import lk.beempz.tf.entity.Debit;

/**
 *
 * @author badhr
 */
public interface DebitDAO extends CrudDAO<Debit, Integer>{
    public ArrayList<Debit> getSortAndFiltered(Date from,Date to)throws Exception;
    public boolean deleteByPurchaseid(int purchaseid)throws Exception;
    public Debit selectByPurchaseID(int purchaseid)throws Exception;
}
