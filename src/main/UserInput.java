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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * This class prompts user for input, gets the command and arguments from the
 * user input and initializes the hashtable with commands and their
 * corresponding class names.
 */
public class UserInput {


  /**
   * Constructs an user input object
   */
  public UserInput() {}


  /**
   * Scans the user input
   * 
   * @return userInput contains the string that the user gave
   */
  public String scanUserInput() {
    String userInput = "";

    // for readability/printing, the root folder will be '/'
    System.out.print("user@userpc:/ ");

    // Create a variable to gcurrentPathcurrentPathet user input
    Scanner in = new Scanner(System.in);

    // Get the input entered by user
    userInput = in.nextLine();

    return userInput;
  }


  /**
   * Returns the command and the arguments given by the user
   * 
   * @param history is the history object
   * @return commandAndArguments is the arraylist the contains the command and
   *         and its arguments
   */
  public ArrayList getCommandAndArguments(History history) {
    String command = "";
    String userInput = "";
    String[] arguments = new String[] {};

    userInput = this.scanUserInput();

    // Add the command entered to history
    history.addCommand(userInput);

    ArrayList commandAndArguments = new ArrayList();
    // Create a Parser class object to get the command and arguments given
    Parser p = new Parser();

    String[] cmdArgs = p.parseInput(userInput);
    // Get the name of the command
    command = cmdArgs[0];

    commandAndArguments.add(command);
    // if command has arguments
    if (cmdArgs.length > 1) {
      // Get the subarray that contains the arguments given (i.e. excluding
      // index 0 because it contains the command
      arguments = Arrays.copyOfRange(cmdArgs, 1, cmdArgs.length);
      commandAndArguments.add(arguments);
      commandAndArguments.add(arguments.length);
    }

    return commandAndArguments;
  }


  /**
   * Initializes the hash table
   * 
   * @return commandHashtable is the hash table that contains the command
   */
  public Hashtable<String, String> initializeHashTable() {
    // create hash table to store commands and corresponding classes
    Hashtable<String, String> commandHashtable =
        new Hashtable<String, String>();
    // initialize commandHashtable
    commandHashtable.put("exit", "Exit");
    commandHashtable.put("man", "Man");
    commandHashtable.put("cat", "Cat");
    commandHashtable.put("cd", "Cd");
    commandHashtable.put("history", "History");
    commandHashtable.put("mkdir", "Mkdir");
    commandHashtable.put("popd", "Popd");
    commandHashtable.put("pushd", "Pushd");
    commandHashtable.put("pwd", "Pwd");
    commandHashtable.put("ls", "Ls");
    commandHashtable.put("echo", "Echo");
    commandHashtable.put("get", "Get");
    commandHashtable.put("mv", "Mv");
    commandHashtable.put("cp", "Cp");
    commandHashtable.put("tree", "Tree");
    commandHashtable.put("save", "Save");
    commandHashtable.put("load", "Load");
    commandHashtable.put("find", "Find");
    return commandHashtable;
  }
}
