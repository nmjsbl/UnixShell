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
 * Represents a cd command that allows users to switch between different
 * directories in the directory tree.
 */
public class Cd extends Command {

  /**
   * Constructs a Cd object with documentation
   */
  public Cd() {
    super("\ncd DIR \n\n" + "Change directory to DIR, which maybe relative\n"
        + "to the current directory or maybe a full path\n"
        + ".. means a parent directory,\n" + ". means the current directory\n"
        + "The directory must be / (forward slash).\n"
        + "The foot of the file system is a single slash:/.\n");
  }

  /**
   * Changes the current directory to the directory provided by the user
   * 
   * @param currentDir is the current directory in the shell
   * @param toDirectory is the directory where they want to go
   * 
   * @return returnDirectories is the list that contains the directories needed
   *         to update the new current working directory and a boolean value
   *         that indicates if the toDirectory given is valid
   */
  public ArrayList changeDirectory(IDirectory currentDir, String toDirectory) {
    // this array list will hold all the sub-directories of currentDir
    ArrayList<IDirectory> currSubDir = currentDir.getSubDirectories();
    // will contain the return directories needed to update the shell
    ArrayList returnDirectories = new ArrayList();
    // check if it is a sub-directory of the current directory
    boolean dirExist = false;

    if (!currSubDir.isEmpty()) {
      // check if the current directory has sub-directories
      for (IDirectory childDirectory : currSubDir) {
        // iterate through the sub-directories
        if (childDirectory.getName().equals(toDirectory)) {
          // given path directory is a sub-directory of the current directory
          dirExist = true;
          // then move reference of the current directory
          currentDir = childDirectory;
        }
      }
    }
    // add current directory and dirExist in the array list
    returnDirectories.add(currentDir);
    returnDirectories.add(dirExist);

    return returnDirectories;
  }

  /**
   * Changes the current directory to the directory from the path provided by
   * the user
   * 
   * @param pathDirs is the array that contains the path that we need to
   *        traverse
   * @param currentDir is the current directory in the shell
   * @param index is where we want to start in the pathDir array
   * 
   * @return returnDirectories is the list that contains the directories needed
   *         to update the new current working directory and a boolean value
   *         that indicates if the pathDirs given is valid
   */
  public ArrayList givenPath(String[] pathDirs, IDirectory currentDir,
      int index) {
    // use tempCurrentDir to use during iteration of the directories
    Directory tempCurrentDir = (Directory) currentDir;
    // this will contain all the sub-directories of the current directory
    ArrayList<IDirectory> currSubDir = tempCurrentDir.getSubDirectories();

    // initialize booleans used for the while loop and return variable
    boolean dirExist = true;
    boolean isASubDir = false;

    while (dirExist && index < pathDirs.length) {
      // iterate while the directory exist in the sub-directory list
      for (IDirectory childDirectory : currSubDir) {
        if (childDirectory.getName().equals(pathDirs[index])) {
          // check if directory is a sub directory and update tempCurrentDir
          isASubDir = true;
          tempCurrentDir = (Directory) childDirectory;
          break;
        }
      }
      // check if we got enter the if statement inside the for loop
      if (!isASubDir)
        dirExist = false;

      // increment index and reset isSubDir to false
      index++;
      isASubDir = false;
      // get the sub directories
      currSubDir = tempCurrentDir.getSubDirectories();

      if (currSubDir.size() == 0 && index != pathDirs.length)
        // check if we are still not in the last directory in the path
        dirExist = false;
    }
    // update current directory if given path is valid using helper function
    ArrayList returnDirectories =
        this.updateCurrentDir(dirExist, currentDir, tempCurrentDir);

    return returnDirectories;
  }

  /**
   * Update the current working directory
   * 
   * @param dirExist is the boolean value that contains whether the directory
   *        exists or not
   * @param currentDir is the current working directory
   * @param tempCurrentDir is the temporary current directory
   * @return returnDirectories is the list that contains the directories needed
   *         to update the new current working directory and a boolean value
   *         that indicates if the pathDirs given is valid
   */
  public ArrayList updateCurrentDir(boolean dirExist, IDirectory currentDir,
      IDirectory tempCurrentDir) {
    // contain the return directories needed to update the shell
    ArrayList returnDirectories = new ArrayList();

    // if directory exist then we update the current directory
    if (dirExist) {
      currentDir = tempCurrentDir;
    }
    // add current directory and isValid in the array list
    returnDirectories.add(currentDir);
    returnDirectories.add(dirExist);

    return returnDirectories;
  }


  /**
   * Get the current working directory from the given path
   * 
   * @param argument is the string the represents the argument given by the user
   *        together with the command
   * @param currentDir is the current directory in the shell
   * @param dirTree is the directory tree of the whole system
   * @param pushd is the instance of Pushd object
   * 
   * @return currentDir is the current working directory
   */
  public static IDirectory getDirectory(String argument, Cd cd,
      IDirectory currentDir, FileSystem dirTree, Pushd pushd, Ls ls,
      Command command) {
    // this will contain the directories returned by the methods called
    ArrayList returnDirs = null;
    // holds value if given directory or path is valid or not
    boolean isValid = false;

    if (argument.equals("..")) {
      isValid = true; // valid path
      if (currentDir.getParentDirectory() != null)
        // check if we are not in the root directory
        currentDir = currentDir.getParentDirectory();
    } else if (argument.equals(".")) {
      // remain at current directory
      isValid = true; // valid path
    } else if (argument.equals("/") || argument.equals("")) {
      // if user wants to go to root directory
      isValid = true; // valid path
      currentDir = dirTree.getRootDirectory();
    } else { // if we are given a path
      String[] directoryPath = argument.split("/");
      if (directoryPath.length == 1) {
        // if we are given only a directory
        returnDirs = cd.changeDirectory(currentDir, argument);
      } else if (directoryPath.length != 0 && directoryPath[0].equals("")) {
        // given full path, starts at /
        returnDirs = cd.givenPath(directoryPath, dirTree.getRootDirectory(), 1);
      } else // if we are given relative path
        returnDirs = cd.givenPath(directoryPath, currentDir, 0);
    }
    currentDir = cd.checkPathValidity(returnDirs, currentDir, isValid, pushd,
        ls, command);
    return currentDir;
  }


  /**
   * Checks path validity for pushd and ls commands
   * 
   * @param returnDirs
   * @param isValid is the boolean value that contains whether the path is valid
   *        or not
   * @param pushd is the Pushd object
   * @param ls is the Popd object
   * @return currentDir is the current working directory
   */
  public IDirectory checkPathValidity(ArrayList returnDirs,
      IDirectory currentDir, boolean isValid, Pushd pushd, Ls ls,
      Command command) {
    // check if the path given is valid then we update the current directory
    // if the user happened to input a path for the argument
    if (returnDirs != null
        && (boolean) returnDirs.remove(returnDirs.size() - 1)) {
      isValid = true; // valid path
      // update current directory
      currentDir = (Directory) returnDirs.remove(returnDirs.size() - 1);
    }
    // for pushd and ls command
    // to check if we need to push the directory in the directory stack
    // or to print the sub-directories and files for ls command
    if (isValid) {
      if (pushd != null) {
        pushd.setToPush(true);
      } else if (ls != null) {
        ls.setToPrint(true);
      } else if (command != null) {
        command.setToAppendOrOverwrite(true);
      }
    } else if (!isValid && ls != null) {
      ls.setToPrint(false);
    } else if (!isValid) {
      System.out.println("Error: No such directory.");
      if (pushd != null) {
        pushd.setToPush(false);
      } else if (command != null) {
        command.setToAppendOrOverwrite(false);
      }
    }
    return currentDir;
  }


  /**
   * This executes the cd command
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
    // check if we need to redirect based on the arguments
    // but we don't need to save the file
    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);

    if (numArgs == 1 || (numArgs == 3 && toRedirect)) {
      // check if we have valid arguments
      currentDir = Cd.getDirectory(arguments[0], this, currentDir, dirTree,
          null, null, null);
      // parentDir = currentDir.getParentDirectory();
    } else
      System.out.println("Error: Invalid arguments.");

    return currentDir;
  }

}
