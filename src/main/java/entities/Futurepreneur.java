/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import java.util.Date;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a Futureprenuer
 *
 * @author Joe Otis Thomas Wray
 */
public class Futurepreneur extends User {

    private String academic;
    private int major;
    private int minor;
    private String gradDate; // Should we use SQL version of Date
    private double gpa;

    /**
     * Default Constructor
     */
    public Futurepreneur() {
        super();

        academic = "Freshman";
        gradDate = "Spring 2017";
        gpa = 4.0;
    }

    /**
     * Explicit value constructor
     *
     * @param first
     * @param last
     * @param email
     * @param password
     * @param userName
     * @param academic
     * @param gradDate
     * @param gpa
     * @param major
     * @param minor
     */
    public Futurepreneur(String first, String last, String email, String password,
            String userName, String academic, String gradDate,
            double gpa, int major, int minor) {
        super(first, last, email, password, userName);
        this.academic = academic;
        this.gradDate = gradDate;
        this.gpa = gpa;
        this.major = major;
        this.minor = minor;
    }

    /**
     * Constructor
     *
     * @param first
     * @param last
     * @param email
     * @param password
     * @param userName
     * @param academic
     * @param gradDate
     * @param gpa
     * @param major
     * @param minor
     * @param accountNumber
     */
    public Futurepreneur(String first, String last, String email, String password,
            String userName, String academic, String gradDate,
            double gpa, int major, int minor, int accountNumber) {
        super(first, last, email, password, userName, accountNumber);
        this.academic = academic;
        this.gradDate = gradDate;
        this.gpa = gpa;
        this.major = major;
        this.minor = minor;
    }

    /**
     * @return academic standing
     */
    public String getAcademic() {
        return academic;
    }

    /**
     * Must be "Freshman", "Sophomore", "Junior", "Senior" or "Senior+"
     *
     * @param academic academic standing
     */
    public void setAcademic(String academic) {
        if (academic.equalsIgnoreCase("freshman")
                || academic.equalsIgnoreCase("sophomore")
                || academic.equalsIgnoreCase("junior")
                || academic.equalsIgnoreCase("senior")
                || academic.equalsIgnoreCase("senior+")) {
            this.academic = academic;
        } else {
            academic = "Senior+";
        }
    }

    /**
     * @return major
     */
    public int getMajor() {
        return major;
    }

    /**
     * @param major
     */
    public void setMajor(int major) {
        this.major = major;
    }

    /**
     * @return minor
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @param minor
     */
    public void setMinor(int minor) {
        this.minor = minor;
    }

    /**
     * @return expected graduation date
     */
    public String getGradDate() {
        return gradDate;
    }

    /**
     * @param gradDate expected graduation date
     */
    public void setGradDate(String gradDate) {
        this.gradDate = gradDate;
    }

    /**
     * @return gpa
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * @param gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
