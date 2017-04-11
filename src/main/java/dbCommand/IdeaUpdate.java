package dbCommand;

import databaseinterface.DBCommandHandler;
import entities.Idea;
import entities.User;
import static java.lang.Compiler.command;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates idea table in database
 *
 * @author Joe Otis Thomas Wray
 */
public class IdeaUpdate {

    /**
     * Adds an idea to the database
     *
     * @param idea
     * @return
     */
    public boolean addIdea(Idea idea) {

        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "INSERT INTO ideas VALUES(";
        command += idea.getAccountNumber();
        command += ", '" + idea.getIdea() + "'";
        command += ", " + idea.getIdeaNumber();
        command += ", " + idea.getSupports();
        command += ", '" + idea.getDate() + "'";
        command += ", '" + idea.getIdeaTitle() + "'";
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
    
    public boolean deleteIdea(Idea idea) {
        System.out.println("Inside IdeaUpdate deleteIdea...");
        DBCommandHandler dbComHand = new DBCommandHandler();
        
        // Delete any comments attached to idea
        String commentCommand = "DELETE FROM comments WHERE ideaNumber=" + idea.getIdeaNumber();
        
        // Delete any supports attached to idea
        String supportsCommand = "DELETE FROM supports WHERE supportNumber=" + idea.getSupports();
        
        // Delete idea
        String ideaCommand = "DELETE FROM ideas WHERE ideaNumber=" + idea.getIdeaNumber();
        
        try {
            dbComHand.doCommand(commentCommand);
            dbComHand.doCommand(supportsCommand);
            int ideaResultCount = dbComHand.doCommand(ideaCommand);
            
            dbComHand.close();
            return ideaResultCount > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(IdeaUpdate.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
        
    }
    
    public boolean updateIdea(Idea idea) {
        DBCommandHandler dbComHand = new DBCommandHandler();
        
        String command = "UPDATE ideas SET accountNumber=" + idea.getAccountNumber()
                + ", " + "ideaName='" + idea.getIdea() 
                + "', " + "ideaNumber=" + idea.getIdeaNumber()
                + ", " + "supports=" + idea.getSupports()
                + ", " + "date='" + idea.getDate()
                + "', " + "ideaTitle='" + idea.getIdeaTitle()
                + "' WHERE ideaNumber=" + idea.getIdeaNumber();
                
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
