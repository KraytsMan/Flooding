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
public interface ThermInterface {
    
    public List<Therm> getStackByCaptureId(int id);
    
    public List<Therm> getList();
    
    public void insertTherm(String name);
    
    public void delteTherm(int id);
}
