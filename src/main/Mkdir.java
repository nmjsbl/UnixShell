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
 * Represents mkdir command that creates new directories in the directory tree
 */
public class Mkdir extends Command {

  /**
   * Constructs a Mkdir object that creates new directories
   */
  public Mkdir() {
    super("\nmkdir DIR ... \n\n"
        + "Create directories, each of which maybe relative\n"
        + "to the current directory or maybe a full path\n");
  }

  /**
   * Creates new directory or directories based on the number of arguments
   * provided by the user, if they provide valid directory names and parent
   * directory.
   * 
   * @param dirTree is the directory tree to add the directory to
   * @param parentDirectory is the parent directory to create this directory in
   * @param paths is the array of full/relative paths specifying the directory
   *        or directories to be created
   */

  public void makeNewDirectories(FileSystem dirTree, IDirectory parentDirectory,
      String[] paths) {
    // loop through the array of paths given
    for (String path : paths) {
      // If path starts with /, it is a full path
      if (path.startsWith("/")) {
        // Make the directory specified by the full path
        this.makeNewDirectoryGivenFullPath(dirTree, path);
        // If path does not start with / but contains /, then it must be a
        // relative path
      } else if (path.contains("/")) {
        // Make the directory specified by the relative path
        this.makeNewDirectoryGivenRelativePath(dirTree, parentDirectory, path);
        // If path does not contain any /, then it must be the directory name
        // itself
      } else {
        // Make the directory in the given parentDirectory
        this.makeNewDirectoryGivenDirectoryName(dirTree, parentDirectory, path);
      }
    }
  }


  /**
   * Creates a directory given a relative path.
   * 
   * @param dirTree is the directory tree to add the directory to
   * @param parentDirectory the parent directory of the given path
   * @param relativePath the relative path that specifies where the directory
   *        should be made
   */
  public void makeNewDirectoryGivenRelativePath(FileSystem dirTree,
      IDirectory parentDirectory, String relativePath) {
    // Get the full path of the parentDirectory
    String pathOfParent = dirTree.getPath(parentDirectory);
    // Initialize fullPath with the parent directory's path
    String fullPath = pathOfParent;
    // If the parent directory is the root directory of the directory tree
    if (parentDirectory.equals(dirTree.getRootDirectory())) {
      // Add the relative path to the fullPath
      fullPath += relativePath;
      // If the parent directory is not the root directory
    } else {
      // Add the relative path to full path preceded by a / to get proper format
      // of the path
      fullPath += "/" + relativePath;
    }
    // Create the new directory using the full path
    this.makeNewDirectoryGivenFullPath(dirTree, fullPath);
  }

  /**
   * Creates a directory given a full path.
   * 
   * @param dirTree is the directory tree to add the directory to
   * @param fullPath specifies the path in which the directory should be added
   */
  public void makeNewDirectoryGivenFullPath(FileSystem dirTree,
      String fullPath) {
    // Get the index of the last / in the full path (this helps extract the path
    // of the parent directory of the directory to be added.
    int indexOfLastSlash = fullPath.lastIndexOf("/");
    // Get the path of the parent directory using the indexOfLastSlash
    String pathOfParent = fullPath.substring(0, indexOfLastSlash);
    // This array contains the names of all the directories in the path in order
    String[] directoryNames = pathOfParent.split("/");
    // Set the parent to reference the root directory as this is a full path
    IDirectory parent = dirTree.getRootDirectory();
    // Start index at 1 to ensure that the root is not included
    int index = 1;
    // Loop through the directories in the directoryNames and ensure that they
    // all exist and have the corresponding parent directories in the path
    while (index < directoryNames.length) {
      // Check if the directory at index does not exist in the parent directory
      if (dirTree.getItem(directoryNames[index], parent) == null
          || dirTree.isFile(parent, directoryNames[index])) {
        // Let the user know they provided an invalid path and exit the function
        System.out.println(fullPath + " is an invalid path!");
        return;
      }
      // If the directory does exist, let that be the parent directory
      parent = (IDirectory) dirTree.getItem(directoryNames[index], parent);
      // Increment index to allow all directories to be checked
      index++;
    }

    // Get the directory name using the indexOfLastSlash
    String dirName = fullPath.substring(indexOfLastSlash + 1);
    // Create the directory of the given name in the parent directory
    this.makeNewDirectoryGivenDirectoryName(dirTree, parent, dirName);
  }

  /**
   * Creates a new directory given a directory name
   * 
   * @param dirTree
   * @param parentDirectory
   * @param dirName
   */
  public void makeNewDirectoryGivenDirectoryName(FileSystem dirTree,
      IDirectory parentDirectory, String dirName) {
    // Check if the directory with the given name already exists
    if (dirTree.getItem(dirName, parentDirectory) != null) {
      System.out.println("A file or directory called " + dirName
          + " already exists in this directory!");
      // If the directory does not already exist
    } else {
      // Check if the directory name is valid
      if (Validator.validateDirectoryOrFileName(dirName)) {
        dirTree.addDirectory(dirName, parentDirectory);
        // If the provided directory name is invalid
      } else {
        // Indicate that the user provide an invalid directory name
        System.out.println(dirName + " is an invalid name for a directory!");
      }
    }
  }

  /**
   * Returns the redirect arguments if we are redirecting or just the plain
   * arguments with the ls command
   * 
   * @param arguments is the list of arguments together with ls command
   * @return the array list consisting of the arguments needed for ls command
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
        // empty list
        returnArguments.add(null);
        returnArguments.add(arguments[index]);
        returnArguments.add(arguments[index + 1]);
      } else {
        String[] tempArguments = Arrays.copyOfRange(arguments, 0, index);
        returnArguments.add(tempArguments);
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
   * This executes the mkdir command
   * 
   * @param arguments is the array of strings that contains the arguments for
   *        the command
   * @param numArgs is the integer the holds the size of arguments[]
   * @param currentDir is the current working directory
   * @param dirTree is the directory tree of the system
   * @return currentDir is the update current working directory
   */
  public IDirectory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history, Pushd pushd,
      Popd popd) {
    // Check to make sure that the user has provided arguments
    if (numArgs == 0) {
      // Indicate that no arguments were provided
      System.out.println("Error: No directory provided!");
    } else {
      ArrayList returnArguments = checkRedirectArguments(arguments);
      if ((boolean) returnArguments.remove(0)) {
        String[] newArguments = (String[]) returnArguments.remove(0);
        if (newArguments == null)
          System.out.println("Error: No directory provided!");
        else
          this.makeNewDirectories(dirTree, currentDir, newArguments);
        returnArguments.add(0, "");
        this.checkRedirection(dirTree, returnArguments, currentDir);
      } else
        // Send in the arguments to create directories
        this.makeNewDirectories(dirTree, currentDir, arguments);
    }

    return currentDir;
  }

}
