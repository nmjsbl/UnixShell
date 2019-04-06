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

package test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.Command;
import main.Directory;
import main.File;
import main.FileSystem;
import main.History;
import main.IDirectory;
import main.IGet;
import main.Popd;
import main.Pushd;

public class MockGet extends MockCommand implements IGet{
  
  /**
   * Gets the content of the URL given
   * 
   * @param urlArgument is the URL given by the user
   * @return the string that contains the content of the url
   */
  public String getURLArgument(String urlArgument) {
    String result = "<html><body>This is a content of the URL</body></html>";
    return result;
  }
  
  /**
   * Saves the content of the URL to a file
   * 
   * @param urlContent is the content of the url
   * @param urlFileName is the filename of the file to be saved
   * @param currentDir is the current working directory
   */
  public void saveURLToFile(String urlContent, String urlFileName,
      IDirectory currentDir) {
    // create a file containing the contents of the URL
    MockFile urlFile = new MockFile(urlFileName, currentDir);
    urlFile.appendData(urlContent);

    // add the file in the file list of the current directory
    currentDir.addItem(urlFile);
  }
  
  /**
   * Gets the file name from the url
   * 
   * @param argument is the argument given with the get command
   * @return the string containing the file name of the file that should be
   *         saved
   */
  public String getURLFileName(String argument) {
    return "sample";
  }
  
  /**
   * Checks if the given URL is valid
   * 
   * @param urlFileName is the file name from the given URL
   * @return true if the filename from the URL given is valid
   */
  public boolean checkValidURLFileName(String urlFileName) {
    return true;
  }
  
  /**
   * This method executes the command
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
      IDirectory currentDir, FileSystem dirTree, History history,
      Pushd pushd, Popd popd) {
    return currentDir;
  }
}

