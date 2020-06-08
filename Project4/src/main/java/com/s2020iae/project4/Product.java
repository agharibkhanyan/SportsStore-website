package com.s2020iae.project4;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chuon
 */
public class Product {
    private int id;
    private String name;
    private String summary;
    private String thumbnail;
    private String category;
    private String detail;
    private Float price;

    //default constructor
    public Product()
    {
     super();
    }
    
    public Product(int i, String n, String s, String t, String c, String d, Float p)
    {
        this.id = i;
        this.name = n;
        this.summary = s;
        this.thumbnail = t;
        this.category = c;
        this.detail = d;
        this.price = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
