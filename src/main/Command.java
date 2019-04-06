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
 * Represents a command with documentation
 */
public abstract class Command implements Serializable {
  /**
   * String to store the documentation information of this command
   */
  protected String documentation;
  protected boolean toAppendOrOverwrite = true;

  /**
   * Constructor, creates a command with the given documentation
   * 
   * @param documentation is the documentation of this command
   */
  public Command(String documentation) {
    this.documentation = documentation;
  }

  /**
   * This is a abstract method that executes the command
   * 
   * @param arguments is the array of arguments provided for the command
   * @param numArgs is the number of arguments provided for the command
   * @param currentDir is the directory in which the command was entered
   * @param history is the History command associated with current program
   * @param pushd is the Pushd command associated with current program
   * @param popd is the Popd command associated with current program
   * @param dirTree is the directory tree that we perform the command for
   */
  public abstract IDirectory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history, Pushd pushd,
      Popd popd);

  /**
   * Returns the redirect arguments if we are redirecting
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
   * Returns the documentation associated with this command
   * 
   * @return the documentation
   */
  public String toString() {
    return this.documentation;
  }

  /**
   * Check if the filename given is valid or not and returns the directory where
   * the file should be at
   * 
   * @param fileName is the path of the file
   * @param dirTree is the directory tree of the system
   * @param currDir is the current directory
   */
  public IDirectory checkFileNamePath(String fileName, FileSystem dirTree,
      IDirectory currDir) {
    int indexOfLastSlash = fileName.lastIndexOf("/");
    String fullPath = fileName.substring(0, indexOfLastSlash);
    IDirectory tempCurrDir =
        Cd.getDirectory(fullPath, new Cd(), currDir, dirTree, null, null, this);

    return tempCurrDir;
  }

  /**
   * Sets the instance variable toAppendOrOverwrite to either true or false
   * 
   * @param toAppendOrOverwrite is the boolean that tells if the should be
   *        appended or not
   */
  public void setToAppendOrOverwrite(boolean toAppendOrOverwrite) {
    this.toAppendOrOverwrite = toAppendOrOverwrite;
  }


  /**
   * Overwrites given file with given string. If given file does not exist,
   * creates a new one and adds the string.
   * 
   * @param dirTree is the directory tree in which the file exists or must be
   *        added if it does not exist
   * @param argument is an array list of strings containing the string needing
   *        to be overwritten, and file name
   * @param currdir is the current directory in which we must search for the
   *        file given or create a new file if given file does not exist
   */
  public void overwriteStringtoFile(FileSystem dirTree,
      ArrayList<String> argument, IDirectory currDir) {
    // Get the file name
    String fileName = argument.get(2);
    // temporary directory that we will manipulate here
    IDirectory tempCurrDir = currDir;
    if (fileName.contains("/")) {
      tempCurrDir = this.checkFileNamePath(fileName, dirTree, tempCurrDir);
      int indexOfLastSlash = fileName.lastIndexOf("/");
      fileName = fileName.substring(indexOfLastSlash + 1, fileName.length());
    }
    if (this.toAppendOrOverwrite) {
      // If the file does not exist, check if it is a valid file name
      if (dirTree.getItem(fileName, tempCurrDir) == null
          && Validator.validateDirectoryOrFileName(fileName)) {
        // create a new file with the given file name and add it to dirTree
        dirTree.addFile(fileName, tempCurrDir);
        // get the file that was added to the dirTree
        IFile newfile = (IFile) dirTree.getItem(fileName, tempCurrDir);
        // add string into file
        newfile.overwriteData(argument.get(0));
      } else if (dirTree.isFile(tempCurrDir, fileName)) {
        // Get the file
        IFile file = (IFile) dirTree.getItem(fileName, tempCurrDir);
        // overwrite the data
        file.overwriteData(argument.get(0));
        // Indicate that it is an invalid file name
      } else {
        System.out.println(fileName + " is an invalid file name!");
      }
    }
  }


  /**
   * Appends given string to the data of the given file. If no file is given,
   * prints the string. If the given file does not exist, creates it and appends
   * the string to the new file.
   * 
   * @param dirTree is the directory tree that we must add the file to if it
   *        does not exist already
   * @param echoArguments is an array list of strings containing the string
   *        needing to be append to file, and the file name
   * @param currDir is the current directory in which we must search for the
   *        file given or create a new file if given file does not exist
   */
  public void appendStringtoFile(FileSystem dirTree,
      ArrayList<String> echoArguments, IDirectory currDir) {
    // Get the file name from echoArguments
    String fileName = echoArguments.get(2);
    // temporary directory that we will manipulate here
    IDirectory tempCurrDir = currDir;

    if (fileName.contains("/")) {
      tempCurrDir = this.checkFileNamePath(fileName, dirTree, tempCurrDir);
      int indexOfLastSlash = fileName.lastIndexOf("/");
      fileName = fileName.substring(indexOfLastSlash + 1, fileName.length());
    }
    if (this.toAppendOrOverwrite) {
      // If the file does not exist, check if its name is valid
      if (dirTree.getItem(fileName, tempCurrDir) == null
          && Validator.validateDirectoryOrFileName(fileName)) {
        // Create and add the file with the given name in the dirTree with
        // currDir as the parent directory
        dirTree.addFile(fileName, tempCurrDir);
        // Get the file that was created
        IFile newfile = (File) dirTree.getItem(fileName, tempCurrDir);
        // add string into file
        newfile.appendData(echoArguments.get(0));
      } else if (dirTree.isFile(tempCurrDir, fileName)) {
        IFile file = (File) dirTree.getItem(fileName, tempCurrDir);
        // append string data into existing file
        file.appendData(echoArguments.get(0));
        // Indicate that the user provided an invalid file name
      } else {
        System.out.println(fileName + " is an invalid file name!");
      }
    }

  }

  /**
   * This checks if we are overwriting or appending the strings
   * 
   * @param dirTree is the directory tree
   * @param arguments is the array list of arguments needed
   * @param currentDir is the current working directory
   */
  public void checkRedirection(FileSystem dirTree, ArrayList arguments,
      IDirectory currentDir) {
    if (arguments.get(1).equals(">")) {
      // check if we need to overwrite
      this.overwriteStringtoFile(dirTree, arguments, currentDir);
    } else if (arguments.get(1).equals(">>")) {
      // check if we need to append
      this.appendStringtoFile(dirTree, arguments, currentDir);
    }
  }

}
