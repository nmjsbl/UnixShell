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
package driver;

import java.util.ArrayList;
import java.util.Hashtable;
import main.Command;
import main.Directory;
import main.DirectoryTree;
import main.DirectoryTreeNode;
import main.History;
import main.Load;
import main.Popd;
import main.Pushd;
import main.UserInput;

/**
 * Represents a JShell, a simulation of a command line interface or terminal
 * where commands can be entered.
 */
public class JShell {

  /**
   * Allows user to enter commands and executes the commands if they are valid
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Create variables to store the command and arguments entered by user
    String command = "";
    String[] arguments = new String[] {};
    // Stores the command and arguments entered in an Arraylist
    ArrayList commandAndArgs;
    // Create a DirectoryTree object when the JShell is opened
    // DirectoryTree always starts at root \
    DirectoryTree<DirectoryTreeNode> dirTree = new DirectoryTree<>();
    // the directory we manipulate
    Directory currentDir = (Directory) dirTree.getRootDirectory();
    // Create a history class object to keep track of the commands entered
    History history = new History();
    // Create a pushd class object
    Pushd pushd = new Pushd();
    Popd popd = new Popd();
    // Create UserInput object to get user input and a Hashtable that maps
    // command names with their class names
    UserInput userInputObject = new UserInput();
    Hashtable commandHashTable = userInputObject.initializeHashTable();

    // Keep prompting the user for input until they enter the exit command
    while (!command.trim().equals("exit")) {
      // to store number of arguments entered by user
      int numArgs = 0;
      // Get the commands and arguments from user input
      commandAndArgs = userInputObject.getCommandAndArguments(history);
      // Get the command name
      command = (String) commandAndArgs.remove(0);
      // Get the arguments and number of arguments
      if (commandAndArgs.size() > 0) {
        arguments = (String[]) commandAndArgs.remove(0);
        numArgs = (int) commandAndArgs.remove(0);
      } else {
        arguments = new String[] {};
        numArgs = 0;
      }
      // If the command is not in the hashtable, it must be invalid
      if (!commandHashTable.containsKey(command)) {
        System.out.println(command + " is an invalid command!");
        // If the command is history execute history command
      } else if (command.equals("history")) {
        history.executeCommand(arguments, numArgs, currentDir, dirTree, history,
            pushd, popd);
        // If the command is in the hash table
      } else if (command.equals("pushd")) {
        currentDir = (Directory) pushd.executeCommand(arguments, numArgs, currentDir,
            dirTree, history, pushd, popd);
        // If the command is in the hash table
      } else if (command.equals("load")) {
        // check if we are loading
        ArrayList returnRequirements = null;
        // create load object
        Load load = new Load();
        // execute load command
        returnRequirements = load.executeLoadCommand(arguments, numArgs,
            currentDir, dirTree, history, pushd, popd);
        // update dirTree, currentDir, history and pushd
        dirTree = (DirectoryTree) returnRequirements.remove(0);
        currentDir = (Directory) returnRequirements.remove(0);
        history = (History) returnRequirements.remove(0);
        pushd = (Pushd) returnRequirements.remove(0);
        popd = (Popd) returnRequirements.remove(0);
      } else {
        // Get the class name
        String className = "main." + (String) commandHashTable.get(command);
        Class tempClass;
        try {
          // Create instance of command class
          tempClass = Class.forName(className);
          Command instanceOfCommandClass = (Command) tempClass.newInstance();
          // execute the command
          currentDir =
              (Directory) instanceOfCommandClass.executeCommand(arguments,
                  numArgs, currentDir, dirTree, history, pushd, popd);
          // Exceptions
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (InstantiationException e1) {
          e1.printStackTrace();
        } catch (IllegalAccessException e1) {
          e1.printStackTrace();
        }
      }
    } // end of while loop
  }
}
