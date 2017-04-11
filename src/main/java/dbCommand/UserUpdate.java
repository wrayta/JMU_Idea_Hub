package dbCommand;

import control.IdeaHubControl;
import databaseinterface.DBCommandHandler;
import entities.Futurepreneur;
import entities.Investor;
import entities.User;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates all tables that have to do with a user in the database
 *
 * @author Joseph Otis Thomas Wray
 */
public class UserUpdate {

    /**
     * Updates an entry in the investors table
     *
     * @param person
     * @return
     */
    public boolean updateInv(User person) {
        boolean inv = true;
        Investor invPerson = (Investor) person;
        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "update users set firstName='" + person.getFirstName() + "'";
        command += ", lastName='" + person.getLastName() + "'";
        command += ", userName='" + person.getUserName() + "'";
        command += ", password='" + person.getPassword() + "'";
        command += ", email='" + person.getEmail() + "'";
        command += "where accountNumber=" + person.getAccountNumber();
        String invCommand = "update investors set occupation='" + invPerson.getOccupation() + "'";
        invCommand += ", interests='" + invPerson.getInterest() + "'";
        invCommand += ", website='" + invPerson.getWebsite() + "' where accountNumber=" + invPerson.getAccountNumber();

        try {
            int resultCount = dbComHand.doCommand(command);
            dbComHand.doCommand(invCommand);
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a users password
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean updatePassword(String userName, String password) {
        String newPass = IdeaHubControl.hashPassword(password);
        String command = "update users set password='" + newPass + "' where userName='" + userName + "'";
        DBCommandHandler dbComHand = new DBCommandHandler();

        try {
            int result = dbComHand.doCommand(command);
            return (result > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing entry in the the futurepreneur table
     *
     * @param person
     * @return
     */
    public boolean updateFut(User person) {
        boolean fut = true;
        Futurepreneur futPerson = (Futurepreneur) person;
        DBCommandHandler dbComHand = new DBCommandHandler();
        String command = "update users set firstName='" + person.getFirstName() + "'";
        String majCommand = "update userMajors set majorNumber=" + futPerson.getMajor()
                + " where accountNumber=" + futPerson.getAccountNumber();
        String minCommand = "update userMinors set minorNumber=" + futPerson.getMinor()
                + " where accountNumber=" + futPerson.getAccountNumber();
        command += ", lastName='" + person.getLastName() + "'";
        command += ", userName='" + person.getUserName() + "'";
        command += ", password='" + person.getPassword() + "'";
        command += ", email='" + person.getEmail() + "'";
        command += "where accountNumber=" + person.getAccountNumber();
        String futCommand = "update futurepreneurs set academicStanding='" + futPerson.getAcademic() + "'";
        futCommand += ", expectedGraduation='" + futPerson.getGradDate() + "'";
        futCommand += ", gpa=" + futPerson.getGpa() + "where accountNumber=" + futPerson.getAccountNumber();

        try {
            int resultCount = dbComHand.doCommand(command);
            dbComHand.doCommand(futCommand);
            dbComHand.doCommand(majCommand);
            dbComHand.doCommand(minCommand);
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Adds a user to the database
     *
     * @param person
     * @return
     */
    public boolean addUser(User person) {
        boolean fut = true;
        DBCommandHandler dbComHand = new DBCommandHandler();
        String futCommand = "INSERT INTO futurepreneurs VALUES(";
        String invCommand = "INSERT INTO investors VALUES(";
        String command = "INSERT INTO users VALUES(";
        String majCommand = "INSERT INTO userMajors VALUES(";
        String minCommand = "INSERT INTO userMinors VALUES(";
        command += "'" + person.getFirstName() + "'";
        command += ", '" + person.getLastName() + "'";
        command += ", '" + person.getUserName() + "'";
        command += ", " + person.getAccountNumber();
        command += ", '" + person.getPassword() + "'";
        command += ", '" + person.getEmail() + "'";
        command += ")";

        if (person instanceof Futurepreneur) {
            Futurepreneur person2 = (Futurepreneur) person;
            futCommand += person2.getAccountNumber();
            futCommand += ", '" + person2.getAcademic() + "'";
            futCommand += ", '" + person2.getGradDate() + "'";
            futCommand += ", " + person2.getGpa();
            futCommand += ")";
            majCommand += person2.getAccountNumber() + "," + person2.getMajor() + ")";
            minCommand += person2.getAccountNumber() + "," + person2.getMinor() + ")";
        } else if (person instanceof Investor) {
            fut = false;

            Investor person3 = (Investor) person;

            invCommand += person3.getAccountNumber();
            invCommand += ", '" + person3.getOccupation() + "'";
            invCommand += ", '" + person3.getInterest() + "'";
            invCommand += ", '" + person3.getWebsite() + "')";

        }

        try {
            int resultCount = dbComHand.doCommand(command);
            if (fut) {
                dbComHand.doCommand(futCommand);
                dbComHand.doCommand(majCommand);
                dbComHand.doCommand(minCommand);
            } else {
                dbComHand.doCommand(invCommand);
            }
            dbComHand.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
