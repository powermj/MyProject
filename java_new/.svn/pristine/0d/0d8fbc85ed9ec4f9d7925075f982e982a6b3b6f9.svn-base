package com.zhxg.yqzj.entities.v1;

import java.util.Random;

public class Node {
    
    private double size = 2;
    private double x;
    private double y;
    private String color = "#"+getRandColorCode();
    private String label;
    private String  id ;
    
    public Node(double x,double y,String label,String id,double size){
        this.x = x;
        this.y = y;
        this.label = label;
        this.id = id;
        this.size = 5 * Math.pow(size<1?0.8:size, 0.25);
    }
    
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private static String getRandColorCode(){  
        String r,g,b;  
        Random random = new Random();  
        r = Integer.toHexString(random.nextInt(240)+16).toUpperCase();  
        g = Integer.toHexString(random.nextInt(240)+16).toUpperCase();  
        b = Integer.toHexString(random.nextInt(240)+16).toUpperCase();  
           
        r = r.length()==1 ? "0" + r : r ;  
        g = g.length()==1 ? "0" + g : g ;  
        b = b.length()==1 ? "0" + b : b ;  
           
        return r+g+b;  
       }

}
