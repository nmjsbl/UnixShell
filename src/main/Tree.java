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

/**
 * Represents the tree command that when prompted, displays the entire file
 * system as a tree
 */

public class Tree extends Command {
  /**
   * Constructs a Tree object that prints the entire file system
   */
  public Tree() {
    super("\ntree \n\n"
        + "Displays the the entire file system starting from the\n"
        + "root directory\n");
  }

  /**
   * Returns a tree diagram of the given directory tree
   * 
   * @param dirTree is the directory tree to generate a diagram of
   * @return a tree diagram of dirTree
   */
  public String getTree(FileSystem dirTree) {
    // Return the tree diagram of the given directory tree
    return dirTree.makeTree();
  }

  /**
   * This executes the tree command
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
    // Get the tree of the given directory tree
    String tree = this.getTree(dirTree);

    // Check if the number of arguments is 0
    if (numArgs == 0) {
      // Print out the tree diagram
      System.out.println(tree);
      // Check for redirection
    } else if (numArgs == 2
        && (arguments[0].equals(">") || arguments[0].equals(">"))) {
      ArrayList returnArguments = new ArrayList(Arrays.asList(arguments));
      returnArguments.add(0, tree);
      this.checkRedirection(dirTree, returnArguments, currentDir);
      // If invalid arguments are provided
    } else {
      System.out.println("Error: Invalid arguments!");
    }
    return currentDir;
  }
}

