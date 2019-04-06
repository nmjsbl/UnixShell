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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the history command that stores the commands entered by user and
 * prints them out when requested
 */
public class History extends Command implements Serializable {
  /**
   * This is a list to store all the commands entered by user
   */
  private ArrayList<String> commandLog = new ArrayList<String>();

  /**
   * Constructor, creates a history command object
   */

  public History() {
    super("\nhistory [number] \n\n"
        + "Prints out the recent commands (one on each line).\n"
        + "The output will contain 2 columns, the first one is\n"
        + "numbered such that the highest number is the most recent\n"
        + "command and the second column contains the actual command.\n"
        + "The output will also include any syntactical errors entered by\n"
        + "the user. This number of commands this command will print is"
        + "given by number.\n");
  }

  /**
   * Adds the command to the history of commands entered by user. Also includes
   * any invalid commands entered.
   * 
   * @param command is the command to insert into the history
   */

  public void addCommand(String command) {
    // Adds the command to the Arraylist of commands
    commandLog.add(command);
  }

  /**
   * Prints out the number of commands requested by the user
   * 
   * @param numOfCommands specifies the number of commands wanted
   * @return the number of recently entered commands requested
   */

  public String getHistory(String numOfCommandsRequested) {
    // Get the total number of commands in cmdLog
    int totalNumOfCommands = this.getTotalNumberOfCommandsEntered();
    // Get the number of commands wanted by user
    int numOfCommands = this.getNumberOfCommands(numOfCommandsRequested);
    // To store the commands requested
    String result = "";
    /*
     * If the user provides a number greater than the number of commands in the
     * set the number of commands to the size of the commandLog
     */
    if (numOfCommands > commandLog.size())
      numOfCommands = commandLog.size();

    // Get the start index of the first command wanted by the user
    int startIndex = totalNumOfCommands - numOfCommands;

    // Loop through the commands and add numOfCommands to result
    for (int i = startIndex; i < totalNumOfCommands; i++) {
      // Add the the commands to result
      result += (i + 1) + ". " + commandLog.get(i) + "\n";
    }
    return result;
  }

  /**
   * Gets the total number of commands entered
   * 
   * @return the total number of commands entered
   */
  public int getTotalNumberOfCommandsEntered() {
    return this.commandLog.size();
  }

  /**
   * Returns the number of commands wanted if a valid integer value is provided.
   * 
   * @param numOfCommands is the number of commands requested by user
   * @return integer value of the number of commands
   */
  private int getNumberOfCommands(String numOfCommands) {
    /*
     * create an integer to store the integer value of the number of commands
     * intialize it with the size of the cmdlog as if no arguments are provided
     * all the commands have to be printed
     */
    int numberCommands = this.getTotalNumberOfCommandsEntered();
    // Check if the numOfCommands is an integer and return it
    try {
      // Check if the user provided an empty string (i.e. no arguments)
      numberCommands = Integer.parseInt(numOfCommands);
    } catch (NumberFormatException e) {
      // numOfCommands is not an integer
    }
    // return the number of commands
    return numberCommands;
  }

  /**
   * Returns the redirect arguments if we are redirecting or just the plain
   * arguments with the cat command
   * 
   * @param arguments is the list of arguments together with cat command
   * @return the array list consisting of the arguments needed for cat command
   */
  public ArrayList checkRedirectArguments(String[] arguments) {
    ArrayList returnArguments = new ArrayList<>();
    int index;
    boolean givenRedirect = false;
    // look for > or >> in the argument string array
    for (index = 0; index < arguments.length; index++) {
      if ((arguments[index].equals(">") || arguments[index].equals(">>"))
          && index == arguments.length - 2) {
        givenRedirect = true;
        break;
      }
    }
    // check if we are redirecting or not
    if (givenRedirect) {
      // add values to returning arraylist
      returnArguments.add(true);
      if (index == 0) {
        returnArguments.add(arguments[index]);
        returnArguments.add(arguments[index + 1]);
      } else if (index == 1) {
        returnArguments.add(arguments[index - 1]);
        returnArguments.add(arguments[index]);
        returnArguments.add(arguments[index + 1]);
      }
    } else {
      returnArguments.add(false);
      returnArguments.add(arguments);
    }
    return returnArguments;
  }

  /**
   * Check if the arguments given together with the history command is valid
   * 
   * @param numArgs is number of arguments given
   * @param arguments is the arguments given
   * @return the boolean which indicates whether the given arguments are valid
   *         or not
   */
  public boolean checkValidArguments(int numArgs, String[] arguments) {
    // initialize bolean value
    boolean isNotValid = true;
    if (numArgs == 2 && arguments[0].matches(">|>>")) {
      // check if there are 2 arguments given and in the right order
      isNotValid = false;
    } else if (numArgs == 3 && arguments[0].matches("[0-9]+")
        && arguments[1].matches(">|>>")) {
      // check if there are 3 arguments given and in the right order
      isNotValid = false;
    } else if (numArgs == 1 && arguments[0].matches("[0-9]+"))
      isNotValid = false;

    return isNotValid;
  }

  /**
   * This method executes the command
   * 
   * @param arguments is the array of arguments provided for the command
   * @param numArgs is the number of arguments provided for the command
   * @param currentDir is the directory in which the command was entered
   * @param dirTree is the directory tree that we perform the command for
   * @param history is the History command associated with current program
   * @param pushd is the Pushd command associated with current program
   * @param popd is the Popd command associated with current program
   * @return currentDir is the update current working directory
   */
  public IDirectory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history, Pushd pushd,
      Popd popd) {
    // Check if no arguments were provided
    if (numArgs == 0) {
      // Send in an empty string to indicate no arguments were provided
      System.out.println(this.getHistory(""));
      // Check if more than 1 argument was provided or if the argument provided
      // is non-numeric
    } else if (this.checkValidArguments(numArgs, arguments)) {
      System.out.println("Error: Invalid arguments.");
      // Send in the number of arguments entered by user
    } else {
      // get the arguments if we need to redirect or not
      ArrayList returnArguments = checkRedirectArguments(arguments);
      String fileContents = "";
      if ((boolean) returnArguments.remove(0)) {
        // check if we redirect all contents or only a number of history
        if (returnArguments.size() == 2) {
          fileContents = this.getHistory("");
        } else
          fileContents = this.getHistory((String) returnArguments.remove(0));
        returnArguments.add(0, fileContents);
        this.checkRedirection(dirTree, returnArguments, currentDir);
      } else {
        fileContents = this.getHistory(arguments[0]);
        System.out.println(fileContents);
      }
    }
    return currentDir;
  }
}
