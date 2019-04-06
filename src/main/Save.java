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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represents a save command that saves the current status of the JShell
 */
public class Save extends Command {

  /**
   * Constructs a Save object that creates a new save command with documentation
   */
  public Save() {
    super("\nsave filename \n\n"
        + "Saves the entire state of the JShell in the real file system"
        + "of the user's computer. Filename must be a valid path on the"
        + "computer. If the file with the same filename already exists,"
        + "then it will overwrite the file completely." + "\n"
        + "This save command uses XML.\n");
  }

  /**
   * Saves the state of the entire JShell system.
   * 
   * @param dirTree is the directory tree
   * @param currentDir is the current working directory
   * @param filename is the filename where the state of the entire JShell in the
   *        computer file system
   * @param history is the history object
   * @param pushd is the pushd objects
   * @param popd is the popd objects
   */
  public void saveState(FileSystem dirTree, IDirectory currentDir,
      String filename, History history, Pushd pushd, Popd popd) {

    try {
      // create a file - this is the real file in Java with filename given
      File file = new File(filename);
      // create file output stream with the file created
      FileOutputStream fileOS = new FileOutputStream(file);
      // create object output stream object with the file input stream created
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOS);

      // write objects
      objectOut.writeObject(dirTree);
      objectOut.writeObject(currentDir);
      objectOut.writeObject(history);
      objectOut.writeObject(pushd);
      objectOut.writeObject(popd);

      // close object output stream and file output stream
      objectOut.close();
      fileOS.close();
    } catch (IOException e) {
      System.out.println("Error: Invalid path for file.");
    }
  }

  /**
   * This executes the save command
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
      IDirectory currentDir, FileSystem dirTree, History history,
      Pushd pushd, Popd popd) {
    // check if we need to redirect
    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);

    if (numArgs == 1 || (numArgs == 3 && toRedirect)) {
      this.saveState(dirTree, currentDir, arguments[0], history, pushd, popd);
      if (toRedirect) {
        returnArguments.add(0, "");
        this.checkRedirection(dirTree, returnArguments, currentDir);
      }
    } else
      System.out.println("Error: Invalid arguments.");

    return currentDir;
  }

}
