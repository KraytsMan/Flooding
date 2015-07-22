/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author ������
 */
public class FSViewed {

    private double x; // function parametr
    private double a; //function constant
    private double b; //function constant
    private double waight;

    public FSViewed() {
    }

    public FSViewed(double x, double a, double b, double waight) {
        this.x = x;
        this.a = a;
        this.b = b;
        this.waight = waight;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    private double firstCase() // if x<=a
    {
        return 0.00;
    }

    private double secondCase() //if a<=x<=b
    {
        return 2 * Math.pow((this.x - this.a) / (this.b - this.a), 2);
    }

    private double thirdCase() // if b<x
    {
        return 1 - 2 * Math.pow((this.b - this.x) / (this.b - this.a), 2);
    }

    private double fourthCase() // if c<=x
    {
        return 1.00;
    }

    public double toCompute() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        DecimalFormat df2 = new DecimalFormat("#.###", symbols);
        double result = 0;
        if (this.x <= this.a) {
            result = Double.parseDouble(df2.format(this.firstCase()));
        }
        if ((this.x > this.a) && (this.x <= ((this.a + this.b) / 2))) {
            result = Double.parseDouble(df2.format(this.secondCase()));
        }
        if ((this.x > ((this.a + this.b) / 2)) && (this.x < this.b)) {
            result = Double.parseDouble(df2.format(this.thirdCase()));
        }
        if (this.x >= this.b) {
            result = Double.parseDouble(df2.format(this.fourthCase()));
        }
        result = Double.parseDouble(df2.format(result * this.waight));
        return result;
    }

}
