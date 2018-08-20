/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

/**
 *
 * @author badhr
 */
public class Credit_Type {
    private int typeid;
    private String type_name;

    public Credit_Type() {
    }

    public Credit_Type(int typeid, String type_name) {
        this.typeid = typeid;
        this.type_name = type_name;
    }

    /**
     * @return the typeid
     */
    public int getTypeid() {
        return typeid;
    }

    /**
     * @param typeid the typeid to set
     */
    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    /**
     * @return the type_name
     */
    public String getType_name() {
        return type_name;
    }

    /**
     * @param type_name the type_name to set
     */
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    
}
