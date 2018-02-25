/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuadraticEquation;

/**
 *
 * @author Алёшечка
 */
public class QuadraticEquationDAO {
    private Connection con = null;
    private String sqlInsertResultTwoRootsOfEquation = "INSERT INTO answers (a, b, c, D, x1, x2) "
                                                        + "VALUES (?, ?, ?, ?, ?, ?)";
    private String sqSelectQuadraticEquation = "SELECT * FROM answers";
    private String sqlFindEquation = "SELECT * FROM answers an WHERE an.a = ? AND an.b = ? AND an.c = ?";

    public QuadraticEquationDAO(Connection con) {
        this.con = con;
    }
    
    public boolean insertEquation(QuadraticEquation quadraticEquation) {
        PreparedStatement state1 = null;
        try {           
            state1 = con.prepareStatement(sqlInsertResultTwoRootsOfEquation);
            state1.setDouble(1, quadraticEquation.getA());
            state1.setDouble(2, quadraticEquation.getB());
            state1.setDouble(3, quadraticEquation.getC());
            state1.setDouble(4, quadraticEquation.getD());
            state1.setDouble(5, quadraticEquation.getX1());
            state1.setDouble(6, quadraticEquation.getX2());
            
            int count = state1.executeUpdate();
            if (count == 0) {
                con.rollback();
                return false;
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally{
            try {
                state1.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    
    //Select для того чтобы получить все уравнения результатам из базы.
    public ArrayList<QuadraticEquation> getAlEquation(){
        ArrayList<QuadraticEquation> equationList = new ArrayList<>();
        
        try {
            Statement state1 = con.createStatement();
            ResultSet res = state1.executeQuery(sqSelectQuadraticEquation);

            while (res.next()) {
                double a = res.getDouble(1);
                double b = res.getDouble(2);
                double c = res.getDouble(3);
                double D = res.getDouble(4);
                double x1 = res.getDouble(5);
                double x2 = res.getDouble(6);
                QuadraticEquation quadraticEquation = new QuadraticEquation(a, b, c, D, x1, x2);
                equationList.add(quadraticEquation);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equationList;
    }
    
    /*проверка происходит в контроллере, соответственно если в базе есть такое уравнение
     оно извлекает из базы, если нет добавляет в базу новое.*/
    public QuadraticEquation findResultEquation(double a, double b, double c) {
        PreparedStatement state1 = null;
        ResultSet res = null;
        QuadraticEquation quadraticEquation = null;
        try {
            state1 = con.prepareStatement(sqlFindEquation);
            state1.setDouble(1, a);
            state1.setDouble(2, b);
            state1.setDouble(3, c);
            
            res = state1.executeQuery();
                        
            if(res.next()){
                double a1 = res.getDouble(1);
                double b1 = res.getDouble(2);
                double c1 = res.getDouble(3);
                double D = res.getDouble(4);
                double x1 = res.getDouble(5);
                double x2 = res.getDouble(6);
                quadraticEquation = new QuadraticEquation(a1, b1, c1, D, x1, x2);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                res.close();
                state1.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return quadraticEquation;
    }
    
     public QuadraticEquation selectEquation(QuadraticEquation quadratic) {
        PreparedStatement state1 = null;
        ResultSet res = null;
        QuadraticEquation quadraticEquation = null;

        try {
            state1 = con.prepareStatement(sqlFindEquation);
            state1.setDouble(1, quadratic.getA());
            state1.setDouble(2, quadratic.getB());
            state1.setDouble(3, quadratic.getC());

            res = state1.executeQuery();

            if (res.next()) {
                double a1 = res.getDouble(1);
                double b1 = res.getDouble(2);
                double c1 = res.getDouble(3);
                double D = res.getDouble(4);
                double x1 = res.getDouble(5);
                double x2 = res.getDouble(6);
                quadraticEquation = new QuadraticEquation(a1, b1, c1, D, x1, x2);
            }

        } catch (SQLException ex) {            
                Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                state1.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuadraticEquationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return quadraticEquation;
    }
}
