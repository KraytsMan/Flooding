/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import com.javacodegags.waterflooding.model.Therm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ������
 */
public class RowBuilderTherm {
    
    private ArrayList<List<Therm>> arrayOfTherms;
    private static final Logger LOG = Logger.getLogger(RowBuilderTherm.class.getName());

    public RowBuilderTherm() {
        this.arrayOfTherms = new ArrayList<List<Therm>>();
    }

    public ArrayList<List<Therm>> getArrayOfTherms() {
        return arrayOfTherms;
    }

    public void setArrayOfTherms(ArrayList<List<Therm>> arrayOfTherms) {
        this.arrayOfTherms = arrayOfTherms;
    }
    
    public void addList(List<Therm> therms) {
        this.arrayOfTherms.add(therms);
    }
    
    private String putIntoRow(String name) {
        return "<td>" + name + "</td>";
    }
    
    public LinkedList<String> buildRowCriteries() {
        LinkedList<String> ls = new LinkedList<String>();
        for (int j = 0; j < this.arrayOfTherms.get(0).size(); j++) {
            String row = "";
            for (int i = 0; i < this.arrayOfTherms.size(); i++) {
                row += this.putIntoRow(this.arrayOfTherms.get(i).get(j).getName());
            }
            ls.add(row);
        }
        return ls;
    }
    
    
    
}
