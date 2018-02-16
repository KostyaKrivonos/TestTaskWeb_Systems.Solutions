/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Алёшечка
 */
public class DAOFactory {
    private static DAOFactory factory;
    private Connection con = null;
    
    private DAOFactory() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quadraticequation", "root", "1584");    
        con.setAutoCommit(false);
    }
    
    private Connection getConnection() throws SQLException {
        if (con == null) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quadraticequation", "root", "1584");
            con.setAutoCommit(false);
        }
        return con;
    }

    public static DAOFactory getDAOFactory() throws SQLException {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }
    
    public QuadraticEquationDAO getQuadraticEquationDAO() throws SQLException{
        QuadraticEquationDAO quadraticEquationDAO = new QuadraticEquationDAO(getConnection());
        return quadraticEquationDAO;
    }
}
