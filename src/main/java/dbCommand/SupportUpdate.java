package dbCommand;

import databaseinterface.DBCommandHandler;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates the support table in the database
 *
 * @author Joseph Otis Thomas Wray
 */
public class SupportUpdate {

    /**
     * Adds that a person supported something
     *
     * @param suppNum
     * @param accountNum
     */
    public void incrementSupps(int suppNum, int accountNum) {
        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "INSERT INTO supports VALUES(" + suppNum + "," + accountNum + ")";

        try {
            int resultCount = dbComHand.doCommand(command);
            dbComHand.close();
        } catch (SQLException ex) {
            Logger.getLogger(IdeaUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lets a person "undo" their support
     */
    public void decrementSupps(int suppNum, int accountNum) {
        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "DELETE FROM supports WHERE supportNumber=" + suppNum + " AND accountNumber="+ accountNum;

        try {
            int resultCount = dbComHand.doCommand(command);
            dbComHand.close();
        } catch (SQLException ex) {
            Logger.getLogger(IdeaUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
