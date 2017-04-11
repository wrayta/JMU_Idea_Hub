package databaseinterface;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This class handles the DB command interface.
 * @author Dr. Grove used by Team 3
 */
public class DBCommandHandler extends DBHandler implements Serializable {

    /*
     * Execute a command and return a result count.
     */

    public int doCommand(String command) throws SQLException {
        if (!isOpen) {
            open();
        }

        int resultCount = stmt.executeUpdate(command);
        return resultCount;
    }
}
