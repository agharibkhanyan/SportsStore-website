/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s2020iae.project4;

/**
 *
 * @author anon
 */
public class Order {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String billaddr;
    private String billcity;
    private String billstate;
    private int billzip;
    private String method;
    private String cardname;
    private String cardnumber;
    private int expmonth;
    private int expyear;
    private int cvv;
    private String price;
    
    public Order(){
        this.id=0;
        this.firstname=null;
        this.lastname=null;
        this.email=null;
        this.phone=null;
        this.address=null;
        this.city=null;
        this.state=null;
        this.zip=0;
        this.billaddr=null;
        this.billcity=null;
        this.billstate=null;
        this.billzip=0;
        this.method=null;
        this.cardname=null;
        this.cardnumber=null;
        this.expmonth=0;
        this.expyear=0;
        this.cvv=0;
        this.price=null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public void setEmail(String email)
    {
        this.email=email;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    
    public String getBillAddr() {
        return billaddr;
    }

    public void setBillAddr(String billaddr) {
        this.billaddr = billaddr;
    }

    public String getBillCity() {
        return billcity;
    }

    public void setBillCity(String billcity) {
        this.billcity = billcity;
    }
    
    public String getBillState() {
        return billstate;
    }

    public void setBillState(String billstate) {
        this.billstate = billstate;
    }
    
    public int getBillZip() {
        return billzip;
    }

    public void setBillZip(int billzip) {
        this.billzip = billzip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }   

    public String getCardName() {
        return cardname;
    }

    public void setCardName(String cardname) {
        this.cardname = cardname;
    }
    
    public String getCardNumber() {
        return cardnumber;
    }

    public void setCardNumber(String cardnumber) {
        this.cardnumber = cardnumber;
    } 

    public int getExpMonth() {
        return expmonth;
    }

    public void setExpMonth(int expmonth) {
        this.expmonth = expmonth;
    } 

    public int getExpYear() {
        return expyear;
    }

    public void setExpYear(int expyear) {
        this.expyear = expyear;
    }     

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
