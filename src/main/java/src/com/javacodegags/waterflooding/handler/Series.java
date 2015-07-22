/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import java.util.ArrayList;

/**
 *
 * @author ������
 */
public class Series {
    
   private String name;
   private ArrayList data;

    public Series() {
    }

    public Series(String name) {
        this.name = name;
        this.data = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + "name=" + name + ", data=" + data + '}';
    }
    
   

    
}
