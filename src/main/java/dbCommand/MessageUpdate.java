package dbCommand;

import databaseinterface.DBCommandHandler;
import entities.Idea;
import entities.Message;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates the message table in the database
 *
 * @author Joe Otis Thomas Wray
 */
public class MessageUpdate {

    /**
     * Adds a message to the database
     *
     * @param mes
     * @return
     */
    public boolean addIdea(Message mes) {

        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "INSERT INTO messages VALUES(";
        command += mes.getMessageNumber();
        command += ", '" + mes.getMessage() + "'";
        command += ", " + mes.getSender();
        command += ", " + mes.getReceiver();
        command += ", '" + mes.getDate() + "'";
        command += ", '" + mes.getTitle() + "'";
        command += ")";
        try {
            int resultCount = dbComHand.doCommand(command);
            dbComHand.close();
            return resultCount > 0;
        } catch (SQLException ex) {
            Logger.getLogger(IdeaUpdate.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
