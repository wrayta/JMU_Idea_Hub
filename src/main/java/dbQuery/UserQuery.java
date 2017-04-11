/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbQuery;

import databaseinterface.DBQueryHandler;
import entities.Futurepreneur;
import entities.Investor;
import entities.MajorMinor;
import entities.User;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Querys all tables related to users in the database
 *
 * @author Joe Otis Thomas Wray
 */
public class UserQuery {

    /**
     * Gets all the investors
     *
     * @return
     */
    public ArrayList<Object> getInvTable() {
        String query = "select * from investors";
        ArrayList<Object> result = new ArrayList<Object>();

        try {

            DBQueryHandler dbQue = new DBQueryHandler();
            ResultSet rs = dbQue.doQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int numOfCols = rsmd.getColumnCount();
            result.add(new Integer(numOfCols));

            while (rs.next()) {
                int i = 1;
                int accountNumber = rs.getInt(i++);
                String occupation = rs.getString(i++);
                String interests = rs.getString(i++);
                String website = rs.getString(i++);

                String name = getUserFullName(accountNumber);

                String email = getUserEmail(accountNumber);
                
                Investor inv = new Investor(name, occupation, interests, website, accountNumber, email);

                result.add(inv);
            }

            dbQue.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * Checks to make sure the user password combination exists
     *
     * @param user
     * @param pass
     * @return
     */
    public boolean checkPass(String user, String pass) {
        boolean exists = true;
        String query = "select userName from users where userName='" + user + "' and password = '" + pass + "'";
        DBQueryHandler dbQue = new DBQueryHandler();

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (!rs.next()) {
                exists = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    /**
     * Gets the accountNumber based on a userName
     *
     * @param user
     * @return
     */
    public int getAccountNum(String user) {
        int num = 0;
        String query = "select accountNumber from  users where userName = '" + user + "'";
        DBQueryHandler dbQue = new DBQueryHandler();
        try {
            ResultSet rs = dbQue.doQuery(query);
            rs.next();
            num = rs.getInt(1);

        } catch (SQLException ex) {

            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return num;
    }

    /**
     * Checks if user exists
     *
     * @param userName
     * @return
     */
    public boolean getUser(String userName) {
        boolean exists = true;
        String query = "select userName from users where userName = '";
        query += userName + "'";
        DBQueryHandler dbQue = new DBQueryHandler();

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (!rs.next()) {
                exists = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    /*
    
    */
    public String getUserEmail(int number) {
        String queryForEmail = "select email from users where accountNumber = ";
        queryForEmail += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        String email = null;
        
        try {
            ResultSet rs = dbQue.doQuery(queryForEmail);

            if (rs.next()) {
                email = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return email;
    }
    
    /**
     * Gets the full name of the user based on their accountNumber
     *
     * @param number
     * @return
     */
    public String getUserFullName(int number) {

        String query = "select * from users where accountNumber = ";
        query += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        String name = null;

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                int i = 1;
                String fName = rs.getString(i++);
                String lName = rs.getString(i++);

                name = fName + " " + lName;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;
    }
    
    /**
     * Gets the first name of the user based on their accountNumber
     *
     * @param number
     * @return
     */
    public String getUserFirstName(int number) {

        String query = "select firstName from users where accountNumber = ";
        query += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        String firstName = null;

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                int i = 1;
                firstName = rs.getString(i++);
//                String lName = rs.getString(i++);

//                name = fName + " " + lName;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return firstName;
    }


    /**
     * Gets the userName based on accountNumber
     *
     * @param number
     * @return
     */
    public String getUser(int number) {
        String query = "select userName from users where accountNumber = ";
        query += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        String name = null;

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;
    }

    /**
     * Gets accountNumber based on userName
     *
     * @param name
     * @return
     */
    public int getUserNumber(String name) {
        String query = "select accountNumber from users where userName = '";
        query += name + "'";
        DBQueryHandler dbQue = new DBQueryHandler();
        int num = -1;

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return num;
    }

    /**
     * Gets a User object based on accountNumber
     *
     * @param number
     * @return
     */
    public User getUserProfile(int number) {
        String query = "select * from users where accountNumber = ";
        query += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        DBQueryHandler dbQue2 = new DBQueryHandler();
        User user = null;
        String query2 = "select * from investors where accountNumber = ";
        query2 += number;

        String fName = "";
        String lName = "";
        String uName = "";
        String pwd = "";
        String email = "";
        String occupation = "";
        String interests = "";
        String website = "";
        String academstand = "";
        String expgrad = "";
        double gpa = 0;
        int major = 0;
        int minor = 0;

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                int i = 1;
                fName = rs.getString(i++);
                lName = rs.getString(i++);
                uName = rs.getString(i++);
                i++;
                i++;
                email = rs.getString(i++);

            }
            ResultSet rs2;

            rs2 = dbQue2.doQuery(query2);
            if (rs2.next()) {
                int j = 2;
                occupation = rs2.getString(j++);
                interests = rs2.getString(j++);
                website = rs2.getString(j++);

            }

            user = new Investor(fName, lName, email, pwd, uName, occupation, interests, website);

        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    /**
     * Gets next accountNumber
     *
     * @return
     */
    public int getNextAccountNum() {
        int max = 0;
        String query = "select max( accountNumber) from  users";
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
     * Gets Lost password
     *
     * @param email
     * @return
     */
    public String getLostPass(String email) {
        String query = "select password from users where email = '";
        query += email + "'";
        DBQueryHandler dbQue = new DBQueryHandler();
        String pass = "";

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                pass = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pass;
    }

    /**
     * Gets a Futurepreneur object based on an accountNumber
     *
     * @param number
     * @return
     */
    public Futurepreneur getFut(int number) {
        String query = "select * from users where accountNumber = ";
        query += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        DBQueryHandler dbQue2 = new DBQueryHandler();
        DBQueryHandler dbQue3 = new DBQueryHandler();
        DBQueryHandler dbQue4 = new DBQueryHandler();
        Futurepreneur user = null;
        String query3 = "select * from futurepreneurs where accountNumber = ";
        query3 += number;
        String query4 = "select majorNumber from userMajors where accountNumber=";
        query4 += number;
        String query5 = "select minorNumber from userMinors where accountNumber=";
        query5 += number;

        String fName = "";
        String lName = "";
        String uName = "";
        String pwd = "";
        String email = "";
        String academstand = "";
        String expgrad = "";
        double gpa = 0;
        int major = 0;
        int minor = 0;
        int accountNumber = 0;

        try {
            ResultSet rs = dbQue.doQuery(query);
            ResultSet rs3 = dbQue3.doQuery(query4);
            ResultSet rs4 = dbQue4.doQuery(query5);

            if (rs.next()) {
                int i = 1;
                fName = rs.getString(i++);
                lName = rs.getString(i++);
                uName = rs.getString(i++);
                accountNumber = rs.getInt(i++);
                pwd = rs.getString(i++);
                email = rs.getString(i++);

            }
            ResultSet rs2;

            rs2 = dbQue2.doQuery(query3);
            if (rs2.next()) {
                int j = 2;
                academstand = rs2.getString(j++);
                expgrad = rs2.getString(j++);
                gpa = rs2.getDouble(j++);

                if (rs3.next()) {
                    major = rs3.getInt(1);
                }

                if (rs4.next()) {
                    minor = rs4.getInt(1);
                }

                user = new Futurepreneur(fName, lName, email, pwd, uName, academstand, expgrad, gpa, major, minor, accountNumber);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    /**
     * Gets Investor object based on accountNumber
     *
     * @param number
     * @return
     */
    public Investor getInv(int number) {
        String query = "select * from users where accountNumber = ";
        query += number;
        DBQueryHandler dbQue = new DBQueryHandler();
        DBQueryHandler dbQue2 = new DBQueryHandler();
        Investor user = null;
        String query3 = "select * from investors where accountNumber = ";
        query3 += number;

        String fName = "";
        String lName = "";
        String uName = "";
        String pwd = "";
        String email = "";
        String occupation = "";
        String interest = "";
        String website = "";
        int accountNumber = 0;

        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                int i = 1;
                fName = rs.getString(i++);
                lName = rs.getString(i++);
                uName = rs.getString(i++);
                accountNumber = rs.getInt(i++);
                pwd = rs.getString(i++);
                email = rs.getString(i++);

            }
            ResultSet rs2;

            rs2 = dbQue2.doQuery(query3);
            if (rs2.next()) {
                int j = 2;
                occupation = rs2.getString(j++);
                interest = rs2.getString(j++);
                website = rs2.getString(j++);

                user = new Investor(fName, lName, email, pwd, uName, occupation, interest, website, accountNumber);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    /**
     * Checks if the user is an Investor
     *
     * @param number
     * @return
     */
    public boolean isInv(int number) {
        boolean inv = false;
        String query = "select * from investors where accountNumber = " + number;
        DBQueryHandler dbQue = new DBQueryHandler();
        try {
            ResultSet rs = dbQue.doQuery(query);

            if (rs.next()) {
                inv = true;
            }

        } catch (SQLException ex) {
            inv = false;
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inv;

    }

}
