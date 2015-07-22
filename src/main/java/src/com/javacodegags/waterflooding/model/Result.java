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
public class Result {
    
    private int id;
    private double minimum;
    private double average;

    public Result() {
    }

    public Result(int id, double minimum, double average) {
        this.id = id;
        this.minimum = minimum;
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", minimum=" + minimum + ", average=" + average + '}';
    }
    
    
    
}
