/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.model;

import java.util.List;

/**
 *
 * @author ������
 */
public interface CriteriaInterface {

    public Criteria get(int criteriaId);

    public List<Criteria> getListById(int criteriaId);

    public List<Criteria> getAll();

    public void updateFunction(int id, double value);

    public void updateCriteria(Criteria c);

    public int insertCriteria(int id);

    public void insertIntermediateToCaption(int criteria, int caption);
    
    public void insertIntermediateToFlooding(int criteria, int flooding);
}
