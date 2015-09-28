/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.model;

/**
 *
 * @author ������
 */
public class Parameters {

    private int id;
    private String name;
    private double value;
    private int foreignId;

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + name + ", value=" + value + ", foreignId=" + foreignId + '}';
    }

    public int getForeignId() {
        return foreignId;
    }

    public void setForeignId(int foreignId) {
        this.foreignId = foreignId;
    }


    public Parameters() {
    }

    public Parameters(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
}
