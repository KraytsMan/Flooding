/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.logging.Logger;

/**
 *
 * @author ������
 */
public class FTriangular {

    private double x; // function parametr
    private double a; //function constant
    private double b; //function constant
    private double c; //function constant
    private double waight;
    private static final Logger LOG = Logger.getLogger(FTriangular.class.getName());

    public FTriangular() {
    }
    

    public FTriangular(double x, double a, double b, double c, double waight) {
        this.x = x;
        this.a = a;
        this.b = b;
        this.c = c;
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

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getWaight() {
        return waight;
    }

    public void setWaight(double waight) {
        this.waight = waight;
    }  

    private double firstCase() // if x<=a
    {
        return 0.00;
    }

    private double secondCase() //if a<=x<=b
    {
        return (this.x - this.a) / (this.b - this.a);
    }

    private double thirdCase() // if b<=x<=c
    {
        return (this.c - this.x) / (this.c - this.b);
    }

    private double fourthCase() // if c<=x
    {
        return 0.00;
    }

    public double toCompute() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        DecimalFormat df2 = new DecimalFormat("#.###", symbols);
        double result = 0;
        if (this.x <= this.a) {
            result = Double.parseDouble(df2.format(this.firstCase()));
        }
        if ((this.x >= this.a) && (this.x <= this.b)) {
            result = Double.parseDouble(df2.format(this.secondCase()));
        }
        if ((this.x >= this.b) && (this.x <= this.c)) {
            result = Double.parseDouble(df2.format(this.thirdCase()));
        }
        if ((this.x >= this.c)) {
            result = Double.parseDouble(df2.format(this.fourthCase()));
        }
        result=Double.parseDouble(df2.format(result*this.waight));
        return result;
    }
}
