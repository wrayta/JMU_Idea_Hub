package entities;

import dbQuery.UserQuery;

/**
 * Represents a User
 *
 * @author Joe Otis Thomas Wray
 */
public abstract class User {

    protected int accountNumber;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userName;

    /**
     * Constructor
     */
    public User() {
        email = "";
        firstName = "";
        lastName = "";
        password = "";
        userName = "";
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
     */
    public User(String first, String last, String email, String password,
            String userName) {
        firstName = first;
        lastName = last;
        this.email = email;
        this.password = password;
        this.userName = userName;
        UserQuery x = new UserQuery();

        this.accountNumber = x.getNextAccountNum() + 1;
    }

    /**
     * Constructor
     *
     * @param first
     * @param last
     * @param email
     * @param password
     * @param userName
     * @param accountNumber
     */
    public User(String first, String last, String email, String password, String userName, int accountNumber) {
        firstName = first;
        lastName = last;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.accountNumber = accountNumber;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param num account Number
     */
    public void setAccountNumber(int num) {
        accountNumber = num;
    }

    /**
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param name the first name
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param name the last name
     */
    public void setLastName(String name) {
        lastName = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param pass the password
     */
    public void setPassword(String pass) {
        password = pass;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param user userName
     */
    public void setUserName(String user) {
        userName = user;
    }

}
