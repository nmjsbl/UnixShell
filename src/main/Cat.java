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
 * Represents a cat command that is a type of command that prints out contents
 * of files.
 */
public class Cat extends Command {
  /**
   * Constructs a Cat object that prints contents of files.
   */
  public Cat() {
    super("\ncat FILE1 FILE2 ...\n\n"
        + "Display the contents of FILE1, FILE2 and other files\n"
        + "FILE can be a full or relative path\n");
  }

  /**
   * Returns the contents of the given files if they exist.
   * 
   * @param dirTree is the directory tree to which the files belong to
   * @param parentDirectory refers to the current directory
   * @param files is the array of file paths provided by user
   * @return the contents of the files requested
   */
  public String getFileContents(FileSystem dirTree, IDirectory parentDirectory,
      String[] files) {
    // Create a result variable to store the data of the files
    String result = "";
    String output = "";
    // loop through the array of file paths given
    for (String file : files) {
      // Get the contents of the file with the given name
      result = dirTree.getContentsOfFile(parentDirectory, file);
      // Add the result to the output
      output += result + "\n\n\n";
    }
    // Return the output without the extra new lines at the end
    return output.substring(0, output.length() - 3);
  }

  /**
   * Returns the redirect arguments if we are redirecting or just the plain
   * arguments with the cat command.
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
    // check if we are redirecting or not
    if (givenRedirect) {
      String[] tempArguments = Arrays.copyOfRange(arguments, 0, index);
      // add values to returning arraylist
      returnArguments.add(true);
      returnArguments.add(tempArguments);
      returnArguments.add(arguments[index]);
      returnArguments.add(arguments[index + 1]);
    } else {
      returnArguments.add(false);
      returnArguments.add(arguments);
    }
    return returnArguments;
  }

  /**
   * This method executes the cat command.
   * 
   * @param arguments is the array of arguments provided for the command
   * @param numArgs is the number of arguments provided for the command
   * @param currentDir is the directory in which the command was entered
   * @param dirTree is the directory tree that we perform the command for
   * @param history is the History command associated with current program
   * @param pushd is the Pushd command associated with current program
   * @param popd is the Popd command associated with current program
   * @return currentDir is the update current working directory
   */
  public IDirectory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history, Pushd pushd,
      Popd popd) {
    // If no arguments are given, indicate that no files were provided
    if (numArgs == 0) {
      System.out.println("No files provided!");
      // Else print out the contents of the files requested
    } else {
      // get the arguments if we need to redirect or not
      ArrayList returnArguments = checkRedirectArguments(arguments);
      String fileContents = "";
      // check if we are redirecting or not
      if ((boolean) returnArguments.remove(0)) {
        arguments = (String[]) returnArguments.remove(0);
        // get the contents of the files and save/append them in file
        // Loop through the file paths provided
        for (String file : arguments) {
          // If the provided file exists then add the contents to fileContents
          if (((DirectoryTree) dirTree).isFile((Directory) currentDir, file)) {
            fileContents +=
                dirTree.getContentsOfFile((Directory) currentDir, file)
                    + "\n\n\n";
            // If the provided file does not exist, display an error message
          } else {
            System.out.println(
                dirTree.getContentsOfFile((Directory) currentDir, file));
          }
        }
        if (fileContents.length() > 0) {
          returnArguments.add(0,
              fileContents.substring(0, fileContents.length() - 3));
          this.checkRedirection(dirTree, returnArguments,
              (Directory) currentDir);
        }
      } else {
        // get the contents of the files and print them
        fileContents = this.getFileContents(dirTree, currentDir, arguments);
        System.out.println(fileContents);
      }
    }
    return currentDir;
  }

}
