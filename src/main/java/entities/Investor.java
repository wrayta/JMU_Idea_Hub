package entities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an Investor
 *
 * @author Joe Otis Thomas Wray
 */
public class Investor extends User {

    private String occupation;
    private String interest;
    private String website;
    private String listName;
    private String listEmail;

    /**
     * Default Constructor
     */
    public Investor() {
        super();

        occupation = "";
        interest = "";
        website = "";
    }

    /**
     * Explicit value constructor
     *
     * @param first
     * @param last
     * @param email
     * @param password
     * @param userName
     * @param accountNumber
     * @param occupation
     * @param interest
     * @param website
     */
    public Investor(String first, String last, String email, String password,
            String userName, String occupation, String interest,
            String website) {
        super(first, last, email, password, userName);
        this.occupation = occupation;
        this.interest = interest;
        this.website = website;
    }

    /**
     * Constructor
     *
     * @param name
     * @param occupation
     * @param interest
     * @param website
     * @param accountNumber
     * @param email
     */
    public Investor(String name, String occupation, String interest, String website, int accountNumber, String email) {
        this.occupation = occupation;
        this.interest = interest;
        this.website = website;
        this.accountNumber = accountNumber;
        this.listName = name;
        this.listEmail = email;
    }

    /**
     * Constructor
     *
     * @param first
     * @param last
     * @param email
     * @param password
     * @param userName
     * @param occupation
     * @param interest
     * @param website
     * @param accountNumber
     */
    public Investor(String first, String last, String email, String password,
            String userName, String occupation, String interest,
            String website, int accountNumber) {
        super(first, last, email, password, userName, accountNumber);
        this.occupation = occupation;
        this.interest = interest;
        this.website = website;

    }

    /**
     *
     * @return listName
     */
    public String getListName() {
        return listName;
    }

    /**
     *
     * @param name
     */
    public void setListName(String name) {
        this.listName = name;
    }
    
    /*
    
    */
    public String getListEmail() {
        return listEmail;
    }
    
    /*
    
    */
    public void setListEmail(String email) {
        this.listEmail = email;
    }

    /**
     * @return occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return interests
     */
    public String getInterest() {
        return interest;
    }

    /**
     * @param interest
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website;
    }
}
