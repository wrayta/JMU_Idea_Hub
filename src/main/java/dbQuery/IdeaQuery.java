package dbQuery;

import databaseinterface.DBQueryHandler;
import entities.Idea;
import entities.MajorMinor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Querys the idea table
 *
 * @author Joe Otis Thomas Wray
 */
public class IdeaQuery {

    /**
     * Gets next idea number
     *
     * @return
     */
    public int getNextIdeaNum() {
        int max = 0;
        String query = "select max( ideaNumber ) from  ideas";
        DBQueryHandler dbQue = new DBQueryHandler();
        try {
            ResultSet rs = dbQue.doQuery(query);
            rs.next();
            max = rs.getInt(1);

        } catch (SQLException ex) {

            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return max;
    }

    /**
     * Gets an idea
     *
     * @param ideaNum
     * @return
     */
    public Idea getIdea(int ideaNum) {
        String query = "select * from ideas where ideaNumber=" + ideaNum;
        Idea idea = null;

        DBQueryHandler dbQue = new DBQueryHandler();
        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                int i = 1;
                int accountNum = rs.getInt(i++);
                String ideaName = rs.getString(i++);
                int ideaNumber = rs.getInt(i++);
                int supps = rs.getInt(i++);
                String date = rs.getString(i++);
                String ideaTitle = rs.getString(i++);
                idea = new Idea(accountNum, ideaName, ideaNumber, supps, date, ideaTitle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(IdeaQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idea;

    }

    /**
     * 
     */
    public ArrayList<Object> getIdeasForMonth(String month) {
        
        System.out.println("The month: " + month);
        
        String query = "SELECT * FROM ideas WHERE date LIKE '%" + month + "%' ORDER BY ideaNumber DESC";
        ArrayList<Object> result = new ArrayList<Object>(); //Why is this OBJECT???????

        try {

            DBQueryHandler dbQue = new DBQueryHandler();
            ResultSet rs = dbQue.doQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int numOfCols = rsmd.getColumnCount();
            result.add(new Integer(numOfCols));

            while (rs.next()) {
                int i = 1;
                int accountNum = rs.getInt(i++);
                String ideaName = rs.getString(i++);
                int ideaNum = rs.getInt(i++);
                int supps = rs.getInt(i++);
                String date = rs.getString(i++);
                String ideaTitle = rs.getString(i++);

                Idea id = new Idea(accountNum, ideaName, ideaNum, supps, date, ideaTitle);

                result.add(id);
            }

            dbQue.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
    /**
     * Gets all the ideas
     *
     * @return
     */
    public ArrayList<Object> getIdeas() {
        String query = "SELECT * FROM ideas ORDER BY ideaNumber DESC";
        ArrayList<Object> result = new ArrayList<Object>(); //Why is this OBJECT???????

        try {

            DBQueryHandler dbQue = new DBQueryHandler();
            ResultSet rs = dbQue.doQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int numOfCols = rsmd.getColumnCount();
            result.add(new Integer(numOfCols));

            while (rs.next()) {
                int i = 1;
                int accountNum = rs.getInt(i++);
                String ideaName = rs.getString(i++);
                int ideaNum = rs.getInt(i++);
                int supps = rs.getInt(i++);
                String date = rs.getString(i++);
                String ideaTitle = rs.getString(i++);

                Idea id = new Idea(accountNum, ideaName, ideaNum, supps, date, ideaTitle);

                result.add(id);
            }

            dbQue.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
