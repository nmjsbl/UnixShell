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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Load extends Command {

  /**
   * Constructs a Load object that creates a new load command with documentation
   */
  public Load() {
    super("\nsave filename \n\n"
        + "Saves the entire state of the JShell in the real file system"
        + "of the user's computer. Filename must be a valid path on the"
        + "computer. If the file with the same filename already exists,"
        + "then it will overwrite the file completely." + "\n"
        + "This save command uses XML.\n");
  }

  /**
   * Loads the JShell state that was saved by the user.
   * 
   * @param dirTree is the directory tree
   * @param currentDir is the current working directory
   * @param filename is the filename where the state of the entire JShell in the
   *        computer file system
   * @param history is the history object
   * @param pushd is the pushd objects
   * @param popd is the popd objects
   * @return the arraylist containing the objects
   */
  public ArrayList loadState(FileSystem dirTree, IDirectory currentDir,
      String filename, History history, Pushd pushd, Popd popd) {

    // this the return array list
    ArrayList returnRequirements = new ArrayList<>();
    try {
      // create a file - this is the real file in Java with filename given
      File file = new File(filename);
      // create file input stream with the file created
      FileInputStream fileIS = new FileInputStream(file);
      // create object input stream object with the file input stream created
      ObjectInputStream objectInput = new ObjectInputStream(fileIS);

      // read objects and type casting them
      dirTree = (DirectoryTree) objectInput.readObject();
      currentDir = (Directory) objectInput.readObject();
      history = (History) objectInput.readObject();
      pushd = (Pushd) objectInput.readObject();
      popd = (Popd) objectInput.readObject();

      // close object input stream and file input stream
      objectInput.close();
      fileIS.close();
    } catch (IOException e) {
      System.out.println("Error: File/Path not found.");
    } catch (ClassNotFoundException e) {
      System.out.println("Error: Class not found.");
    }
    // add the objects read to the array list
    returnRequirements.add(dirTree);
    returnRequirements.add(currentDir);
    returnRequirements.add(history);
    returnRequirements.add(pushd);
    returnRequirements.add(popd);

    return returnRequirements;
  }

  /**
   * This executes the load command
   * 
   * @param arguments is the array of strings that contains the arguments for
   *        the command
   * @param numArgs is the integer the holds the size of arguments[]
   * @param currentDir is the current working directory
   * @param dirTree is the directory tree of the system
   * @return currentDir is the update current working directory
   */
  public IDirectory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history,
      Pushd pushd, Popd popd) {
    return currentDir;
  }

  /**
   * This executes the load command
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
  public ArrayList executeLoadCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history,
      Pushd pushd, Popd popd) {
    ArrayList returnRequirements;

    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);

    if ((arguments.length == 1 || (arguments.length == 3 && toRedirect))
        && history.getTotalNumberOfCommandsEntered() == 1) {
      returnRequirements = this.loadState(dirTree, currentDir, arguments[0],
          history, pushd, popd);
    } else {
      if (history.getTotalNumberOfCommandsEntered() > 1)
        System.out.println("Error: Cannot load now.");
      else
        System.out.println("Error: Invalid arguments.");

      returnRequirements = new ArrayList<>();
      returnRequirements.add(dirTree);
      returnRequirements.add(currentDir);
      returnRequirements.add(history);
      returnRequirements.add(pushd);
      returnRequirements.add(popd);
    }
    return returnRequirements;
  }
}
