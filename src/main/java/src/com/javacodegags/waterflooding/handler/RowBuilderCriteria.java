/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import com.javacodegags.waterflooding.model.Criteria;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ������
 */
public class RowBuilderCriteria {
    private static final Logger LOG = Logger.getLogger(RowBuilderCriteria.class.getName());

    private ArrayList<List<Criteria>> arrayOfCriteria;

    public RowBuilderCriteria() {
        arrayOfCriteria = new ArrayList<List<Criteria>>();
    }

    public ArrayList<List<Criteria>> getArrayOfCriteria() {
        return arrayOfCriteria;
    }

    public void setArrayOfCriteria(ArrayList<List<Criteria>> arrayOfCriteria) {
        this.arrayOfCriteria = arrayOfCriteria;
    }

    public void addList(List<Criteria> criterias) {
        this.arrayOfCriteria.add(criterias);
    }

    private String putIntoRow(double value, int id) {
        return "<td onclick=\"criteriaClick("+id+")\">" + value + "</td>";
    }

    public LinkedList<String> buildRowCriteries() {
        LinkedList<String> ls = new LinkedList<String>();
        for (int j = 0; j < this.arrayOfCriteria.get(0).size(); j++) {
            String row = "";
            for (int i = 0; i < this.arrayOfCriteria.size(); i++) {
                row += this.putIntoRow(this.arrayOfCriteria.get(i).get(j).getValue(), this.arrayOfCriteria.get(i).get(j).getId());
            }
            ls.add(row);
        }
        return ls;
    }
    
    private String putIntoRowParams(String value, int id) {
        return "<td height=58px onclick=\"criteriaParamsClick("+id+");\">" + value + "</td>";
    }

    public LinkedList<String> buildRowCriteriesParams() {
        LinkedList<String> ls = new LinkedList<String>();
        for (int j = 0; j < this.arrayOfCriteria.get(0).size(); j++) {
            String row = "";
            for (int i = 0; i < this.arrayOfCriteria.size(); i++) {
                row += this.putIntoRowParams(this.arrayOfCriteria.get(i).get(j).getFormula(), this.arrayOfCriteria.get(i).get(j).getId());
            }
            ls.add(row);
        }
        return ls;
    }
}
