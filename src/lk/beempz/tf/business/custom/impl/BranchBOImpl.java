/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.BankBO;
import lk.beempz.tf.business.custom.BranchBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.BranchDAO;
import lk.beempz.tf.dto.BranchDTO;
import lk.beempz.tf.entity.Branch;


public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO;
    BankBO bankBO;

    public BranchBOImpl() {
        this.bankBO = (BankBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BANK);
        this.branchDAO = (BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRANCH);
    }
    @Override
    public boolean saveBranch(BranchDTO branch) throws Exception {
        branch.setBankid(getBank(branch));
        Branch branchent = branchDAO.saveAndGetGenerated(new Branch(-1, branch.getBankid(), branch.getBranchName()));
        return branchent != null;
    }

    @Override
    public boolean updateBranch(BranchDTO branch) throws Exception {
        branch.setBankid(getBank(branch));
        return branchDAO.update(new Branch(branch.getBranchid(), branch.getBankid(), branch.getBranchName()));
    }

    @Override
    public ArrayList<BranchDTO> getBranches() throws Exception {
        ArrayList<Branch> branches = branchDAO.getAll();
        ArrayList<BranchDTO> branchDTOs = new ArrayList<>();
        for (Branch branch : branches) {
            branchDTOs.add(new BranchDTO(branch.getBranchid(), branch.getBankid(), branch.getBranchName(),bankBO.findBankName(branch.getBankid())));
        }
        return branchDTOs;
    }

    @Override
    public int getBank(BranchDTO branch) throws Exception {
        if(branch.getBankid()==-1){
            return bankBO.getBankID(branch.getBankName());
        }
        return branch.getBankid();
    }

    @Override
    public boolean deleteBranch(int branchid) throws Exception {
        return branchDAO.delete(branchid);
    }

    
    
}
