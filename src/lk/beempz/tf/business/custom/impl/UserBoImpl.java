/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.UserDAO;
import lk.beempz.tf.dto.UserDTO;
import lk.beempz.tf.entity.User;
import lk.beempz.tf.business.custom.UserBO;


public class UserBoImpl implements UserBO {

    UserDAO userDAO;

    public UserBoImpl() {
        this.userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    }
    @Override
    public boolean loginSuccess(UserDTO user)throws Exception {
        User result = userDAO.findById(user.getUsername());
        if(result.getPassword().equals(user.getPassword()))
            return true;
        return false;
    }
    
}
