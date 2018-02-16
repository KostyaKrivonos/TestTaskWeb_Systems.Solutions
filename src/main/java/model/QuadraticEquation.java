/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Алёшечка
 */
public class QuadraticEquation {
    private double a, b, c;
    private double D;
    private double x1, x2;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation(double a, double b, double c, double D, double x1, double x2) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.D = D;
        this.x1 = x1;
        this.x2 = x2;
    }

    public QuadraticEquation(double a, double b, double c, double D, double x1) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.D = D;
        this.x1 = x1;
    }
    
    
   
    public void setD(double D) {
        this.D = D;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getD() {
        return D;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "a: " + a + ", b: " + b + ", c: " + c + ", D= " + D + ", x1= " + getX1() + ", x2= " + getX2();
    }
    
}
