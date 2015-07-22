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
public interface FloodingInterface {
    
    public Flooding get(int criteriaId);
     
    public List<Flooding> getList();
}
