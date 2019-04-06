// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: toletima
// UT Student #: 1004323172
// Author: Maunica Toleti
//
// Student2:
// UTORID user_name: sibalnao
// UT Student #: 1003939786
// Author: Naomi Joy Sibal
//
// Student3:
// UTORID user_name: jeyaku89
// UT Student #: 100 393 7323
// Author: Nikisha Jeyakumar
//
// Student4:
// UTORID user_name: tarannu7
// UT Student #: 1003940471
// Author: Tahasun Tarannum
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks whether directory names are properly formatted and if the paths are
 * valid.
 */
public class Validator {

  /**
   * Returns true only if the given input contains alphanumeric and _, -
   * characters
   * 
   * @param directoryName is the string to check
   * @return boolean value depending on if the argument is valid or not
   */
  public static boolean validateDirectoryOrFileName(String directoryName) {
    // Create a regex to specify the allowed characters
    String regex = "^[,a-zA-Z0-9_-]+$";
    // Create a pattern to compile the regex
    Pattern pattern = Pattern.compile(regex);
    // Check if the argument follows the pattern
    Matcher matcher = pattern.matcher(directoryName);


    // return whether the given string is valid
    return matcher.matches();
  }

  /**
   * Returns true if the string argument given is valid
   * 
   * @param stringArguments is the user given string
   * @return isStringArgumentValid is the boolean that hold the value whether
   *         the string argument is valid or not
   */
  public static boolean validateStringArgument(String stringArguments) {

    boolean isStringArgumentValid = true;
    if (!stringArguments.startsWith("\"") || !stringArguments.endsWith("\"")) {
      // check if stringArguments doesn't start and end with "
      isStringArgumentValid = false;
    } else {
      // check the occurrence of double quote
      int occurrence =
          stringArguments.length() - stringArguments.replace("\"", "").length();
      if (occurrence > 2) {
        // if there are more than 2 quotes
        isStringArgumentValid = false;
      }
    }
    return isStringArgumentValid;
  }

}

