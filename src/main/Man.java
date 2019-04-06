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
import java.util.Hashtable;

/**
 * Represents the man command that prints out the documentation of a given
 * command
 */
public class Man extends Command {
  /**
   * Hashtable to store the class names associated with the commands
   */
  Hashtable<String, String> commandHashtable = new Hashtable<String, String>();

  /**
   * this is the boolean that indicates whether the list of subdirectories and
   * files that should be redirected to a file should be printed or not
   */
  private boolean toRedirect = false;

  /**
   * Constructor-initiates Hashtable and assigns documentation
   */
  public Man() {
    super("\nman CMD \n Prints the documentation for command CMD\n");
    // Initialize commandHashtable
    commandHashtable.put("exit", "Exit");
    commandHashtable.put("man", "Man");
    commandHashtable.put("cat", "Cat");
    commandHashtable.put("cd", "Cd");
    commandHashtable.put("history", "History");
    commandHashtable.put("mkdir", "Mkdir");
    commandHashtable.put("popd", "Popd");
    commandHashtable.put("pushd", "Pushd");
    commandHashtable.put("pwd", "Pwd");
    commandHashtable.put("ls", "Ls");
    commandHashtable.put("echo", "Echo");
    commandHashtable.put("mv", "Mv");
    commandHashtable.put("get", "Get");
    commandHashtable.put("cp", "Cp");
    commandHashtable.put("tree", "Tree");
  }

  /**
   * Prints the documentation of the given command
   * 
   * @param command is the name of the command
   */
  public String printDocumentation(String command) {
    // Create string to store documentation
    String documentation = "";

    // Check if the command is in the hashtable
    if (!this.commandHashtable.containsKey(command)) {
      System.out.println("Error: " + command + " command does not exist.");
    } else {
      toRedirect = true;
      try {
        // Get the appropriate class name that belongs to command.
        String className = this.commandHashtable.get(command);

        // Get the class for the command
        Class cmd = Class.forName("main." + className);

        try {
          // create an instance of the which ever the Class
          Command instanceOfCommandClass = (Command) cmd.newInstance();
          // Get the documentation of that class
          documentation = instanceOfCommandClass.toString();
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    return documentation;
  }

  /**
   * Returns the redirect arguments if we are redirecting
   * 
   * @param arguments is the list of arguments together with man command
   * @return the array list consisting of the arguments needed for man command
   */
  public ArrayList checkRedirectArguments(String[] arguments) {
    ArrayList returnArguments = new ArrayList<>();
    // add redirection arrow and filepath to the array list
    for (int index = 1; index < arguments.length; index++) {
      returnArguments.add(arguments[index]);
    }
    return returnArguments;
  }

  /**
   * This executes the man command
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
    // Check that an argument has been provided
    String fileContents = "";
    if (numArgs == 1) {
      // Print the documentation
      fileContents = this.printDocumentation(arguments[0]);
      System.out.println(fileContents);
    } else if (numArgs == 3 && arguments[1].matches(">|>>")) {
      // get the documentation
      fileContents = printDocumentation(arguments[0]);
      // check if given command with man command is valid
      if (toRedirect) {
        ArrayList returnArguments = this.checkRedirectArguments(arguments);
        returnArguments.add(0, fileContents);
      }
    } else {
      // if the user provided 0, 2 or more than 3 argument, display an error
      // message
      System.out.println("Error: Invalid arguments.");
    }
    return currentDir;
  }
}
