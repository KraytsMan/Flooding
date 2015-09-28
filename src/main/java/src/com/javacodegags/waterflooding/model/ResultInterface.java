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
public interface ResultInterface {
    
    public Result get(int criteriaId);
    
    public List<Result> getAll();
    
    public void updateResult(int id);

    public void insert();
    
}
