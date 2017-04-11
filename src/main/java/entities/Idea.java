package entities;

import dbQuery.IdeaQuery;
import dbQuery.SupportQuery;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Represents an Idea
 *
 * @author Joe Otis Thomas Wray
 */
public class Idea {

    private int ideaNumber;
    private int supports;
    private int accountNumber;
    private String ideaTitle;

    private String idea;
    private String date;

    /**
     * Default Constructor
     */
    public Idea() {
        supports = 0;

        idea = "";
        date = new Date().toString();
    }

    /**
     * Constructor
     *
     * @param title
     * @param idea
     */
    public Idea(String title, String idea) {
        this.ideaTitle = title;
        this.idea = idea;
    }

    /**
     * Constructor
     *
     * @param accountNumber
     * @param idea
     * @param ideaTitle
     */
    public Idea(int accountNumber, String idea, String ideaTitle) {
        this.accountNumber = accountNumber;
        this.idea = idea;
        this.ideaTitle = ideaTitle;
        IdeaQuery query = new IdeaQuery();
        this.ideaNumber = query.getNextIdeaNum() + 1;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a");
        this.date = sdf.format(new Date()).toString();
        SupportQuery suppQuery = new SupportQuery();
        this.supports = suppQuery.getNextSuppNum() + 1;
    }

    /**
     * Constructor
     *
     * @param accountNumber
     * @param idea
     * @param ideaNumber
     * @param supports
     * @param date
     * @param ideaTitle
     */
    public Idea(int accountNumber, String idea, int ideaNumber, int supports, String date, String ideaTitle) {
        this.accountNumber = accountNumber;
        this.idea = idea;
        this.supports = supports;
        this.date = date;
        this.ideaTitle = ideaTitle;
        this.ideaNumber = ideaNumber;
    }

    /**
     * @return accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     *
     * @param accountNum
     */
    public void setAccountNumber(int accountNum) {
        this.accountNumber = accountNum;
    }

    /**
     *
     * @return ideaTitle
     */
    public String getIdeaTitle() {
        return ideaTitle;
    }

    /**
     *
     * @param title
     */
    public void setIdeaTitle(String title) {
        ideaTitle = title;
    }

    /**
     * @return idea number
     */
    public int getIdeaNumber() {
        return ideaNumber;
    }

    /**
     * Increments the supports
     */
    public void incrementSupport() {
        supports++;
    }

    /**
     * @param ideaNumber
     */
    public void setIdeaNumber(int ideaNumber) {
        this.ideaNumber = ideaNumber;
    }

    /**
     * @return number of supports
     */
    public int getSupports() {
        return supports;
    }

    /**
     * @param supports
     */
    public void setSupports(int supports) {
        this.supports = supports;
    }

    /**
     * @return idea
     */
    public String getIdea() {
        return idea;
    }

    /**
     * @param idea
     */
    public void setIdea(String idea) {
        this.idea = idea;
    }

    /**
     * @return date created
     */
    public String getDate() {
        
        return date;
    }

    /**
     * @param date date created
     */
    public void setDate(String date) {
        this.date = date;
    }

}
