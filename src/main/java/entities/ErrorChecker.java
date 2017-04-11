/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Validates all user input
 *
 * @author Joe Otis Thomas Wray
 */
public class ErrorChecker {

    /**
     * Checks to make sure the email has an @ char
     *
     * @param email
     * @return true if valid email
     */
    public boolean validEmail(String email) {
        boolean isTrue = true;
        if (email != null) {

            if (email.trim().length() > 50 || email.trim().length() < 1) {
                isTrue = false;
            }

            if (email.indexOf('@') < 0) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(email);
            }
        } else {
            isTrue = false;
        }

        return isTrue;

    }

    /**
     * ~`!@#$%^&*()-+=<>?,;:'\"\\|[]{}/ Checks that gpa is a double to 2 decimal
     * places under 4.0
     *
     * @param gpa
     * @return true if valid gpa
     */
    public boolean validGPA(String gpa) {
        Double grade = null;
        boolean valid = true;

        if (gpa.length() < 3 || gpa.length() > 4) {
            valid = false;
        }
        try {
            grade = Double.parseDouble(gpa);
            if (gpa.indexOf(".") < 0) {
                valid = false;
            }

            if (valid = true && grade.doubleValue() <= 4.0 && grade.doubleValue() > 0.0) {
                if (grade.floatValue() >= 100 || grade.floatValue() < 1) {
                    valid = false;
                }
            }

            if (valid) {
                valid = validateInput(gpa);
            }

        } catch (Exception e) {
            valid = false;
        }
        return valid;

    }

    /**
     * Checks if academic standing is fresh, soph, junior, senior, or senior+
     *
     * @param stand
     * @return true if valid academic standing
     */
    public boolean validStanding(String stand) {
        boolean valid = true;

        if (stand.length() > 1 && stand.length() < 10) {
            if (!(stand.equalsIgnoreCase("freshman") || !(stand.equalsIgnoreCase("sophomore"))
                    || !(stand.equalsIgnoreCase("junior")) || !(stand.equalsIgnoreCase("senior"))
                    || !(stand.equalsIgnoreCase("senior+")))) {
                valid = false;
            }
        }

        if (valid) {
            valid = validateInput(stand);
        }

        return valid;
    }

    /**
     * Checks if the Grad date is valid
     *
     * @param date
     * @return
     */
    public boolean validGradDate(String date) {
        boolean isTrue = true;

        if ( date.trim().length() > 50 || date.trim().length() < 1 ) {
            isTrue = false;
        }

        if (isTrue) {
            isTrue = validateDate(date);
        }

        return isTrue;
    }

    /**
     * Checks if the occupation is valid
     *
     * @param occ
     * @return
     */
    public boolean validOccupation(String occ) {
        boolean isTrue = true;
        if (occ != null) {
            if (occ.trim().length() > 50 || occ.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(occ);
            }
        } else {
            isTrue = false;
        }

        return isTrue;
    }

    /**
     * Checks if the interests are valid
     *
     * @param interest
     * @return
     */
    public boolean validInterests(String interest) {
        boolean isTrue = true;
        if (interest != null) {
            if (interest.trim().length() > 70 || interest.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(interest);
            }
        } else {
            isTrue = false;
        }

        return isTrue;
    }

    /**
     * Checks if the website is valid
     *
     * @param web
     * @return
     */
    public boolean validWebsite(String web) {
        boolean isTrue = true;
        if (web != null) {
            if (web.trim().length() > 70 || web.trim().length() < 1) {
                isTrue = false;
            }

            if (web.indexOf(".") < 0) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(web);
            }
        } else {
            isTrue = false;
        }

        return isTrue;
    }

    public boolean validateUsername(String word) {
        
        System.out.println("The value of the word is: " + word);
        
        String regEx = "^[\\pL\\pN]+$";
        
        return ( Pattern.matches(regEx, word) && (word.length() >= 3) && (word.length() <= 12) );

    }
    /**
     * Makes sure the password is at least 8 chars and under the max amount
     *
     * @param pass
     * @return true if valid password
     */
    public boolean validateInput(String word) {
        String regEx = "^[a-zA-Z0-9!@#%&. ?,]*$";

        return Pattern.matches(regEx, word);

    }
    
    public boolean validateDate(String date) {
         String regEx = "\\d{4}-\\d{2}-\\d{2}";
         
         return Pattern.matches(regEx, date);
    }

    /**
     * Makes sure name has no illegal chars
     *
     * @param name
     * @return true if it has no illegal chars
     */
    public boolean validFirstName(String name) {

        boolean isTrue = true;

        if (name != null) {

            if (name.trim().length() > 30 || name.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(name);
            }
        } else {
            isTrue = false;
        }

        return isTrue;
    }

    /**
     * Makes sure name has no illegal chars
     *
     * @param name
     * @return true if it has no illegal chars
     */
    public boolean validLastName(String name) {
        boolean isTrue = true;

            if (name != null) {
                if (name.trim().length() > 40 || name.trim().length() < 1) {
                    isTrue = false;

                }

                if (isTrue) {
                    isTrue = validateInput(name);
                }
            } else {
                isTrue = false;

            }

        return isTrue;
    }

    /**
     * Makes sure there are no illegal email chars Email cannot have more then
     * one '.'
     *
     * @param name
     * @return true if valid
     */
    public boolean validUserName(String name) {
        {
            boolean isTrue = true;
            if (name != null) {
                if (name.trim().length() > 20 || name.trim().length() < 1) {
                    isTrue = false;
                }

                if (isTrue) {
                    isTrue = validateInput(name);
                }
            } else {
                isTrue = false;
            }

            return isTrue;
        }
    }

    /**
     * Makes sure the password is valid
     *
     * @param pwd
     * @return
     */
    public boolean validPassword(String pwd) {
        boolean isTrue = true;
        if (pwd != null) {
            if (pwd.trim().length() > 12 || pwd.trim().length() < 8) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(pwd);
            }
        } else {
            isTrue = false;
        }
        return isTrue;

    }

    /**
     * Makes sure the ideaTitle is valid
     *
     * @param title
     * @return
     */
    public boolean validIdeaTitle(String title) {
        boolean isTrue = true;
        if (title != null) {
            if (title.trim().length() > 28 || title.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(title);
            }
        } else {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * Makes sure the idea is valid
     *
     * @param idea
     * @return
     */
    public boolean validIdea(String idea) {
        boolean isTrue = true;
        if (idea != null) {
            if (idea.trim().length() > 500 || idea.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(idea);
            }
        } else {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * Makes sure the comment is valid
     *
     * @param com
     * @return
     */
    public boolean validComment(String com) {
        boolean isTrue = true;
        if (com != null) {
            if (com.trim().length() > 500 || com.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(com);
            }
        } else {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * Makes sure the Message title is valid
     *
     * @param title
     * @return
     */
    public boolean validMessageTitle(String title) {
        boolean isTrue = true;
        if (title != null) {
            if (title.trim().length() > 40 || title.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(title);
            }
        } else {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * Makes sure the message content is valid
     *
     * @param mes
     * @return
     */
    public boolean validMessage(String mes) {
        boolean isTrue = true;
        if (mes != null) {
            if (mes.trim().length() > 1000 || mes.trim().length() < 1) {
                isTrue = false;
            }

            if (isTrue) {
                isTrue = validateInput(mes);
            }
        } else {
            isTrue = false;
        }
        return isTrue;
    }
}
