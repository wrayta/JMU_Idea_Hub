/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import dbQuery.MessageQuery;
import java.util.Date;

/**
 * Represents a Message
 * 
 * @author Joe Otis Thomas Wray
 */
public class Message {
    
    private int messageNumber;
    private String message;
    private int sender;
    private int receiver;
    private String date;
    private String title;
    
    /**
     * Constructor
     * 
     * @param messageNumber
     * @param message
     * @param sender
     * @param receiver
     * @param date
     * @param title 
     */
    public Message(int messageNumber, String message, int sender, int receiver, String date, String title)
    {
        this.messageNumber = messageNumber;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.title = title;
    }
    
    /**
     * Constructor
     * 
     * @param message
     * @param sender
     * @param receiver
     * @param title 
     */
    public Message(String message, int sender, int receiver, String title)
    {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        
        MessageQuery query = new MessageQuery();
        this.messageNumber = query.getNextMesNum() + 1;
        
        this.date = new Date().toString();
    }

    /**
     * @return title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @param title 
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * @return messageNumber
     */
    public int getMessageNumber() {
        return messageNumber;
    }

    /**
     * @param messageNumber 
     */
    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return sender
     */
    public int getSender() {
        return sender;
    }

    /**
     * @param sender 
     */
    public void setSender(int sender) {
        this.sender = sender;
    }

    /**
     * @return receiver
     */
    public int getReceiver() {
        return receiver;
    }

    /**
     * @param receiver 
     */
    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
