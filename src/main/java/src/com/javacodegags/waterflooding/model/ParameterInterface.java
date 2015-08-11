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
public interface ParameterInterface {
    
    public Parameters get(int parameterId);
     
    public List<Parameters> getListById(int parameterId);
    
    public void deleteParams(int parameterId);

    public void insertParams(Parameters parameters);
}
