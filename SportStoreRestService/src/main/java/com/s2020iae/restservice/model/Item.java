package com.s2020iae.restservice.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chuon
 */
public class Item {
    private int id;
    private int orderId;
    private int productId;

    public Item()
    {
        this.id = 0;
        this.orderId=0;
        this.productId=0;
    }

    public Item(int id, int orderId, int productId){
        this.id=id;
        this.orderId=orderId;
        this.productId=productId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId(){
        return orderId;
    }
    public void setOrderId(int orderId){
        this.orderId=orderId;
    }
    public int getProductId(){
        return productId;
    }
    public void setProductId(int productId){
        this.productId=productId;
    }
}
