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
public class Supplier {
    private int supplierno;
    private String name;
    private int route;
    private String phone;
    private String address;

    public Supplier() {
    }

    public Supplier(int supplierno, String name, int route, String phone, String address) {
        this.supplierno = supplierno;
        this.name = name;
        this.route = route;
        this.phone = phone;
        this.address = address;
    }

    /**
     * @return the supplierno
     */
    public int getSupplierno() {
        return supplierno;
    }

    /**
     * @param supplierno the supplierno to set
     */
    public void setSupplierno(int supplierno) {
        this.supplierno = supplierno;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the route
     */
    public int getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(int route) {
        this.route = route;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
}
