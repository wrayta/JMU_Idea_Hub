/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbQuery;

import databaseinterface.DBQueryHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Querys the support table in database
 * 
 * @author Joe Otis Thomas Wray
 */
public class SupportQuery {
    
    /**
     * Checks to see if a user already supported something
     * 
     * @param number
     * @param suppNumber
     * @return 
     */
    public boolean notAlreadySupp(int number, int suppNumber)
    {
        boolean exists = true;
        String query = "select * from supports where accountNumber=" + number
                + " && supportNumber=" + suppNumber;
        DBQueryHandler dbQue = new DBQueryHandler();
        
        try {
            ResultSet rs = dbQue.doQuery(query);
            
            if (rs.next())
                exists = false;
                
        } catch (SQLException ex) {
            exists = false;
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
    }
    
    /**
     * Gets the number of supports a support number has
     * 
     * @param suppId
     * @return 
     */
    public int getSupps(int suppId)
    {
        String query = "select count(supportNumber) from supports where supportNumber=" + suppId;
        DBQueryHandler dbQue = new DBQueryHandler();
        int i = 0;
        try {
            ResultSet rs = dbQue.doQuery(query);
            
            if (rs.next())
                i = rs.getInt(1);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SupportQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return i;
    }
    
    /**
     * Gets the next support number
     * 
     * @return 
     */
    public int getNextSuppNum()
    {
        int max = 0;
        int maxIdeaSuppNum = 0;
        int maxComSuppNum = 0;
        String query = "select max( supports ) from  ideas";
        String query2 = "select max( supports ) from  comments";
        DBQueryHandler dbQue = new DBQueryHandler();
        DBQueryHandler dbQue2 = new DBQueryHandler();
        try {      
            ResultSet rs = dbQue.doQuery(query);
            ResultSet rs2 = dbQue2.doQuery(query2);
            
            rs.next();
            maxIdeaSuppNum = rs.getInt(1);
            
            rs2.next();
            maxComSuppNum = rs2.getInt(1);
            
            if (maxIdeaSuppNum > maxComSuppNum)
                max = maxIdeaSuppNum;
            else
                max = maxComSuppNum;

        } catch (SQLException ex) {
            max = 0;
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return max;
    }
    
}
