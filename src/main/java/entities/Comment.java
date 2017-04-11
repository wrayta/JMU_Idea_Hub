/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dbQuery.CommentQuery;
import dbQuery.SupportQuery;
import java.util.Date;

/**
 * Represents a comment
 *
 * @author root
 */
public class Comment {

    private int commentNumber;
    private int ideaNumber;
    private String comment;
    private int accountNumber;
    private int supports;
    private String date;

    /**
     * Constructor
     *
     * @param ideaNumber
     * @param accountNumber
     * @param comment
     */
    public Comment(int ideaNumber, int accountNumber, String comment) {
        this.ideaNumber = ideaNumber;
        this.accountNumber = accountNumber;
        this.comment = comment;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a");
        this.date = sdf.format(new Date()).toString();
        CommentQuery query = new CommentQuery();
        this.commentNumber = query.getNextComNum() + 1;
        SupportQuery suppQuery = new SupportQuery();
        this.supports = suppQuery.getNextSuppNum() + 1;
    }

    /**
     * Constructor
     *
     * @param commentNum
     * @param ideaNum
     * @param comment
     * @param accountNum
     * @param supports
     * @param date
     */
    public Comment(int commentNum, int ideaNum, String comment, int accountNum, int supports, String date) {
        this.commentNumber = commentNum;
        this.ideaNumber = ideaNum;
        this.comment = comment;
        this.accountNumber = accountNum;
        this.supports = supports;
        this.date = date;
    }

    /**
     * Returns commentNumber
     *
     * @return
     */
    public int getCommentNumber() {
        return commentNumber;
    }

    /**
     * Sets commentNumber
     *
     * @param commentNumber
     */
    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    /**
     * Gets ideaNumber
     *
     * @return
     */
    public int getIdeaNumber() {
        return ideaNumber;
    }

    /**
     * Sets ideaNumber
     *
     * @param ideaNumber
     */
    public void setIdeaNumber(int ideaNumber) {
        this.ideaNumber = ideaNumber;
    }

    /**
     * Gets comment
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets accountNumber
     *
     * @return
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets accountNumber
     *
     * @param accountNumber
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets supports
     *
     * @return
     */
    public int getSupports() {
        return supports;
    }

    /**
     * Sets supports
     *
     * @param supports
     */
    public void setSupports(int supports) {
        this.supports = supports;
    }

    /**
     * Gets date
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

}
