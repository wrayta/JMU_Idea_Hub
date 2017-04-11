package databaseinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;

/*
 * An abstract parent class for database handlers. This class loads
 * database access parameters from web.xml and loads the database
 * driver class.
 * @author Dr. Grove used by Team 3
 */
public abstract class DBHandler {

    protected String driverName = null, url = null, userId = null, password = null;
    protected Connection con = null;
    protected Statement stmt = null;
    protected boolean isOpen = false;

    /*
     * Get parameters required to open DBMS connection.
     */
    public DBHandler() {
        try {

            driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            url = "jdbc:mysql://localhost:2010/jmu_idea_hub";
            userId = "root";
            password = "admin";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Open the DB connection
     */
    public void open() throws SQLException {
        con = DriverManager.getConnection(url, userId, password);
        if (con != null) {
	    System.out.println("You made it, take control your database now!");
	} else {
	    System.out.println("Failed to make connection!");
	}
        stmt = con.createStatement();
        isOpen = true;
    }

    /*
     * Close the DB connection
     */
    public void close() throws SQLException {
        stmt.close();
        con.close();
        isOpen = false;
    }

}
