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
 * Represents a pwd command that prints the current working directory
 */
public class Pwd extends Command {

  /**
   * Constructs a Pwd object which contains the documentation of the class
   */
  public Pwd() {
    super("\npwd\n\n" + "Print the current working directory\n");
  }

  /**
   * Returns a string representation of the path of the current directory
   * 
   * @param dirTree is the directory tree that the directory is part of
   * @param currdir is the current directory the user is visiting
   * @return the path of the current directory
   */
  public String printCurWorkingDirectory(FileSystem dirTree,
      DirectoryTreeNode curDir) {
    return dirTree.getPath((IDirectory) curDir);
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
    if (givenRedirect) {
      returnArguments.add(true);
      returnArguments.add(arguments[index]);
      returnArguments.add(arguments[index + 1]);
    } else
      returnArguments.add(false);
    return returnArguments;
  }

  /**
   * This executes the pwd command
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
    // The arguments for redirection
    ArrayList returnArguments = checkRedirectArguments(arguments);
    String path =
        this.printCurWorkingDirectory(dirTree, (DirectoryTreeNode) currentDir);
    if ((boolean) returnArguments.remove(0)) {
      returnArguments.add(0, path);
      this.checkRedirection(dirTree, returnArguments, currentDir);
    } else {
      // Print out the current working directory
      System.out.println(path);
    }
    return currentDir;
  }
}
