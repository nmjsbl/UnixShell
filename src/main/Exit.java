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

/**
 * Represents an exit command that terminates the program
 */
public class Exit extends Command {


  /**
   * Constructs an exit object
   */
  public Exit() {
    super("\nexit CMD \n Exits the program\n");
  }


  /**
   * Exits the program
   */
  private void exit() {
    // Exit the program
    System.exit(0);
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
      IDirectory currentDir, FileSystem dirTree, History history,
      Pushd pushd, Popd popd) {
    // check if we need to redirect
    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);

    if (numArgs == 0 || numArgs == 2 && toRedirect)
      this.exit();
    else
      System.out.println("Error: Invalid argumets.");
    return currentDir;
  }
}
