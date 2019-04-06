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
 * Represents a pushd command that pushes directories onto the stack
 */
public class Pushd extends Command implements Serializable {
  /**
   * The directory stack to add the directories to
   */
  private DirectoryStack dirStack;
  /**
   * To check whether the directory should be pushed onto the stack or not based
   * on whether the given directory/path is valid or not
   */
  private boolean toPush = false;

  /**
   * Constructs a Pushd object with the documentation associated with it
   */
  public Pushd() {
    super("\npushd DIR \n\n"
        + "Saves the current directory by pushing it on the\n"
        + "directory stack then changes the new current working\n"
        + "directory to Dir. If DIR is not a valid path or\n"
        + "directory then the current directory will not be\n"
        + "pushed in the directory stack.\n");
    // creates an instance of DirectoryStack
    dirStack = DirectoryStack.createDirectoryStackInstance();
  }

  /**
   * Adds the current directory on the shell to the directory stack
   *
   * @param toPushDir is the directory to be pushed in the directory stack
   */
  public void pushDirectory(DirectoryTreeNode toPushDir) {
    // push toPushDir on top of directory stack
    dirStack.pushDirectory((Directory) toPushDir);
  }

  /**
   * Gets the directory stack
   *
   * @return this.dirStack is the reference to the directory stack
   */
  public DirectoryStack getDirStack() {
    return this.dirStack;
  }

  /**
   * Instantiates a new Cd object
   * 
   * @return cd is the Cd object
   * 
   */
  public Cd goToDirectory() {
    Cd cd = new Cd();
    return cd;
  }

  /**
   * Sets the value of toPush instance variable
   * 
   * @param toPush is a boolean indicating whether or not the directory should
   *        be pushed in the directory stack or not
   */
  public void setToPush(boolean toPush) {
    this.toPush = toPush;
  }

  /**
   * Gets the value of toPush instance variable
   * 
   * @return toPush is a boolean indicating whether or not the directory should
   *         be pushed in the directory stack or not
   */
  public boolean getToPush() {
    return this.toPush;
  }

  /**
   * This executes the pushd command
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

    // this will hold temporary references to directories
    IDirectory tempCurrentDir = currentDir;

    if (numArgs == 1 || (numArgs == 3 && toRedirect)) {
      // use static method changeDirectoryInShell from Cd class
      currentDir = Cd.getDirectory(arguments[0], new Cd(), currentDir, dirTree,
          this, null, null);
      // if the given directory or path to go to after pushing is valid
      if (this.getToPush())
        // push the current directory in the directory stack
        this.pushDirectory((DirectoryTreeNode) tempCurrentDir);
    } else
      // if given arguments are not valid
      System.out.println("Error: Invalid arguments.");

    return currentDir;
  }

}
