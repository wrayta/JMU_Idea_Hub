package dbQuery;

import databaseinterface.DBQueryHandler;
import entities.Comment;
import entities.Idea;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Querys the comment table
 *
 * @author Joseph Otis Thomas Wray
 */
public class CommentQuery {

    /**
     * Gets the next comment number
     *
     * @return
     */
    public int getNextComNum() {
        int max = 0;
        String query = "select max( commentNumber ) from  comments";
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
     * Gets the comments associated with the idea number
     *
     * @param ideaNumber
     * @return
     */
    public ArrayList<Object> getComs(int ideaNumber) {
        String query = "select * from comments where ideaNumber=" + ideaNumber;
        ArrayList<Object> result = new ArrayList<Object>(); //Why is this OBJECT???????

        try {

            DBQueryHandler dbQue = new DBQueryHandler();
            ResultSet rs = dbQue.doQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int numOfCols = rsmd.getColumnCount();
            result.add(new Integer(numOfCols));

            while (rs.next()) {
                int i = 1;
                int commentNum = rs.getInt(i++);
                int ideaNum = rs.getInt(i++);
                String comment = rs.getString(i++);
                int accountNum = rs.getInt(i++);
                int supports = rs.getInt(i++);
                String date = rs.getString(i++);

                Comment com = new Comment(commentNum, ideaNum, comment, accountNum, supports, date);

                result.add(com);
            }

            dbQue.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
