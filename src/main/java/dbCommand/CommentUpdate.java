package dbCommand;

import databaseinterface.DBCommandHandler;
import entities.Comment;
import entities.Idea;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates the comment table in the database
 *
 * @author Joe Otis Thomas Wray
 */
public class CommentUpdate {

    /**
     * Adds a comment to the database
     * @param com
     * @return true if added successfully
     */
    public boolean addComment(Comment com) {

        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "INSERT INTO comments VALUES(";
        command += com.getCommentNumber();
        command += ", " + com.getIdeaNumber();
        command += ", '" + com.getComment() + "'";
        command += ", " + com.getAccountNumber();
        command += ", " + com.getSupports();
        command += ", '" + com.getDate() + "'";
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
