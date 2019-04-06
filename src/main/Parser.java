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

/**
 * Parses the given input and gives the individual parts of the string by
 * removing excess white space
 */
public class Parser {

  /**
   * Returns an array with the command and arguments by stripping all the
   * whitespace
   * 
   * @param input is the string to parse
   * @return an array of individual strings present in input
   */

  public String[] parseInput(String input) {
    // remove all white spaces after and before the string
    String trimmedInput = input.trim();

    // remove the white spaces (tabs and spaces) in between strings
    // and store the strings in an array
    String[] cmdArgs = trimmedInput.split("\\s+");

    // returns the array of strings contain the command (and it's arguments)
    return cmdArgs;
  }
}

