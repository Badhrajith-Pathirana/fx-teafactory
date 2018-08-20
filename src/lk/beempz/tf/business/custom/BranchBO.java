/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.BranchDTO;

/**
 *
 * @author badhr
 */
public interface BranchBO extends SuperBO{
    public boolean saveBranch(BranchDTO branch)throws Exception;
    public boolean updateBranch(BranchDTO branch)throws Exception;
    public ArrayList<BranchDTO> getBranches()throws Exception;
    public int getBank(BranchDTO branch)throws Exception;
    public boolean deleteBranch(int branchid)throws Exception;
}
