/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import MyException.NoHasRealRootsException;
import controller.dao.DAOFactory;
import controller.dao.QuadraticEquationDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.QuadraticEquation;
import view.transferObject.TransferObjectEquation;

/**
 *
 * @author Алёшечка
 */
public class QuadraticEquationController {
    private QuadraticEquationDAO quadraticEquationDAO;

    public QuadraticEquationController() throws SQLException{
        this.quadraticEquationDAO = DAOFactory.getDAOFactory().getQuadraticEquationDAO();
    }
    
    public boolean solve (QuadraticEquation quadraticEquation) throws NoHasRealRootsException{
        double a, b, c, D;        
        a = quadraticEquation.getA();
        b = quadraticEquation.getB();
        c = quadraticEquation.getC();
        
        D = b * b - 4 * a * c;
        quadraticEquation.setD(D);
        
        if (D > 0) {
            //Рівняння має два корені;
            quadraticEquation.setX1((-b - Math.sqrt(D)) / (2 * a));
            quadraticEquation.setX2((-b + Math.sqrt(D)) / (2 * a));
            return quadraticEquationDAO.insertEquation(quadraticEquation);
        }if (D == 0) {
            //Рівняння має єдиний корінь;
            quadraticEquation.setX1(-b / (2 * a));
            return quadraticEquationDAO.insertEquation(quadraticEquation);
        }  
        return true;
    }
    
    public ArrayList<QuadraticEquation> getAllEquation(){       
        return quadraticEquationDAO.getAlEquation();
    }
    
    public boolean findResultQuadraticEquation(TransferObjectEquation t) throws NoHasRealRootsException{
        //аналіз обєкта рішення від дао. якщо в базі таке є ти повертаєш це рішення наформу
        //якщо нема, контроллер вирішує це рівняння і дає команду ДАО зберегти рішення а потім віддає його вюшці
        
        QuadraticEquation eq1 =quadraticEquationDAO.findResultEquation(t.getA(), t.getB(), t.getC());
                if(eq1 == null){
                    QuadraticEquation eq = new QuadraticEquation(t.getA(), t.getB(), t.getC());
                    solve(eq);
                }
                return true;        
    }
    
    public QuadraticEquation selectEquation(TransferObjectEquation t){
        QuadraticEquation eq = new QuadraticEquation(t.getA(), t.getB(), t.getC());
        return quadraticEquationDAO.selectEquation(eq); 
    }
}
