package dbQuery;

import databaseinterface.DBQueryHandler;
import entities.Idea;
import entities.Message;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Querys the message table;
 *
 * @author Joseph Otis Thomas Wray
 */
public class MessageQuery {

    /**
     * Gets the messages associated with an account number
     *
     * @param accountNum
     * @return
     */
    public ArrayList<Object> getMessages(int accountNum) {
        String query = "select * from messages where receiverNumber=" + accountNum;
        ArrayList<Object> result = new ArrayList<Object>(); //Why is this OBJECT???????

        try {

            DBQueryHandler dbQue = new DBQueryHandler();
            ResultSet rs = dbQue.doQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int numOfCols = rsmd.getColumnCount();
            result.add(new Integer(numOfCols));

            while (rs.next()) {
                int i = 1;
                int messageNum = rs.getInt(i++);
                String message = rs.getString(i++);
                int sender = rs.getInt(i++);
                int receiver = rs.getInt(i++);
                String date = rs.getString(i++);
                String title = rs.getString(i++);

                Message mes = new Message(messageNum, message, sender, receiver, date, title);

                result.add(mes);
            }

            dbQue.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * Gets the next message number
     *
     * @return
     */
    public int getNextMesNum() {
        int max = 0;
        String query = "select max( messageNumber ) from  messages";
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
     * Gets a message based on the message number
     *
     * @param mesNum
     * @return
     */
    public Message getMessage(int mesNum) {
        String query = "select * from messages where messageNumber=" + mesNum;
        Message mes = null;

        DBQueryHandler dbQue = new DBQueryHandler();
        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                int i = 1;
                int messageNumber = rs.getInt(i++);
                String message = rs.getString(i++);
                int sender = rs.getInt(i++);
                int receiver = rs.getInt(i++);
                String date = rs.getString(i++);
                String title = rs.getString(i++);
                mes = new Message(messageNumber, message, sender, receiver, date, title);
            }

        } catch (SQLException ex) {
            Logger.getLogger(IdeaQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mes;

    }

}
