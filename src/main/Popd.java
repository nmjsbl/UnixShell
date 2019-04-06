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
 * Represents a popd command that removes directories from the directory stack
 * and makes it the current working directory
 */
public class Popd extends Command {

  /**
   * Constructs a Popd object that removes the directory on the top of the
   * directory stack and makes that the current working directory.
   */
  public Popd() {
    super("\npopd\n\n" + "Removes the top most directory from the directory\n"
        + "stack and make it as the new current working directory.\n"
        + "If there is no directory in the directory stack,\n"
        + "it will give an appropriate error message.\n");
  }

  /**
   * Pops the directory at the top of the directory stack
   * 
   * @param dirStack is the reference to the DirectoryStack object
   * @return poppedDir is the directory that was popped from the directory stack
   */
  public IDirectory popDirectory(DirectoryStack dirStack) {

    IDirectory poppedDir = dirStack.popDirectory();
    return poppedDir;
  }

  /**
   * This executes the popd command
   * 
   * @param arguments is the array of strings that contains the arguments for
   *        the command
   * @param numArgs is the integer the holds the size of arguments[]
   * @param currentDir is the current working directory
   * @param dirTree is the directory tree of the system
   * @param history is the History command associated with current program
   * @param pushd is the Pushd command associated with current program
   * @param popd is the Popd command associated with current program
   * @return currentDir is the update current working directory
   */
  public IDirectory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history, Pushd pushd,
      Popd popd) {
    // check if we need to redirect
    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);
    if (toRedirect) {
      returnArguments.add(0, "");
      this.checkRedirection(dirTree, returnArguments, currentDir);
    }
    // this will hold the current directory if we need to check directories
    IDirectory tempCurrentDir = null;
    // get directory stack from pushd
    DirectoryStack dirStack = pushd.getDirStack();
    if ((numArgs == 0 || (numArgs == 2 && toRedirect)) && dirStack != null) {
      // check if we already pushed something in
      tempCurrentDir = this.popDirectory(dirStack);
      // check if directory stack is empty
      if (tempCurrentDir != null) {
        currentDir = tempCurrentDir;
      }
    } else if ((numArgs > 0 && !toRedirect) || (numArgs > 2 && toRedirect))
      // if there are arguments given and we don't need to redirect
      System.out.println("Error: Invalid arguments.");
    else
      // the directory stack is null
      System.out.println("Error: Directory stack is empty.");

    return currentDir;
  }
}
