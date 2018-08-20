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
public class Route {
    private int routeid;
    private String routename;

    public Route() {
    }

    public Route(int routeid, String routename) {
        this.routeid = routeid;
        this.routename = routename;
    }

    /**
     * @return the routeid
     */
    public int getRouteid() {
        return routeid;
    }

    /**
     * @param routeid the routeid to set
     */
    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    /**
     * @return the routename
     */
    public String getRoutename() {
        return routename;
    }

    /**
     * @param routename the routename to set
     */
    public void setRoutename(String routename) {
        this.routename = routename;
    }
    
}
