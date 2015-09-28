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
public class Criteria {

    private int id;
    private double value;
    private double argument;
    private double weighFactor;
    private String formula;
    private int therm;

    public Criteria() {
    }

    public Criteria(int id, double value, double argument, double weighFactor, String formula, int therms) {
        this.id = id;
        this.value = value;
        this.argument = argument;
        this.weighFactor = weighFactor;
        this.formula = formula;
        this.therm = therms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getArgument() {
        return argument;
    }

    public void setArgument(double argument) {
        this.argument = argument;
    }

    public double getWeighFactor() {
        return weighFactor;
    }

    public void setWeighFactor(double weighFactor) {
        this.weighFactor = weighFactor;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getTherms() {
        return therm;
    }

    public void setTherms(int therms) {
        this.therm = therms;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", value=" + value + ", argument=" + argument + ", weighFactor=" + weighFactor + ", formula=" + formula + ", therm=" + therm + '}';
    }


    
}
