/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.model;

import java.util.logging.Logger;

/**
 *
 * @author ������
 */
public class Flooding {

    private int id;
    private String name;
    private String image;
    private String description;

    public Flooding() {
    }

    public Flooding(int id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }
    private static final Logger LOG = Logger.getLogger(Flooding.class.getName());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + '}';
    }
    
    
}
