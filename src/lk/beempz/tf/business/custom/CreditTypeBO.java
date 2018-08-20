/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.CreditTypeDTO;

/**
 *
 * @author badhr
 */
public interface CreditTypeBO extends SuperBO{
    public CreditTypeDTO getCreditType(int id)throws Exception;
    public ArrayList<CreditTypeDTO> getCredits()throws Exception;
    public int getIdByName(String type_Name)throws Exception;
}
