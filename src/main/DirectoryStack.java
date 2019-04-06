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
import java.util.Stack;

/**
 * Represents a DirectoryStack that stores directory objects.
 */
public class DirectoryStack implements Serializable {
  /**
   * Represents a DirectoryStack object of this class
   */
  private static DirectoryStack dirStack = null;
  /**
   * The stack of directories of this object
   */
  private Stack<IDirectory> stackOfDirs;


  /**
   * Constructs DirectoryStack object
   */
  private DirectoryStack() {
    this.stackOfDirs = new Stack<IDirectory>();
  }

  /**
   * Creates a new DirectoryStack object
   *
   * @return dirStack is the DirectoryStack object instantiated/created or the
   *         already existing DirectoryStack object
   */
  public static DirectoryStack createDirectoryStackInstance() {
    if (dirStack == null) {
      dirStack = new DirectoryStack();
    }
    return dirStack;
  }

  /**
   * Adds the current directory on the shell to the directory stack
   *
   * @param toPushDir is the directory to be pushed in the directory stack
   */
  public void pushDirectory(IDirectory toPushDir) {
    // push toPushDir on top of directory stack
    this.stackOfDirs.push(toPushDir);
  }

  /**
   * Removes the most recent directory added on the directory stack
   *
   * @return result is the Directory that was popped from the directory stack
   */
  public IDirectory popDirectory() {
    IDirectory result;

    if (!this.stackOfDirs.isEmpty()) {
      // check if stack is not empty
      result = this.stackOfDirs.pop();
    } else {
      // print error message and return null
      System.out.println("Error: Directory stack is empty");
      result = null;
    }

    // return the resulting directory
    return result;
  }

  /**
   * Gets the stack of directories that contains the directories pushed in the
   * stack
   * 
   * @ return this.stackOfDirs is the reference to the stack of directories
   */
  public Stack<IDirectory> getStackOfDir() {
    return this.stackOfDirs;
  }

  /**
   * Overrides toString method.
   */
  public String toString() {
    return "This is a directory stack which"
        + " contains the stack of directories.";
  }
}
