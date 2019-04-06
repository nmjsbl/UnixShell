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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Represents get command that allows the content of a url be added to a file in
 * the current directory.
 */
public class Get extends Command implements IGet{

  /**
   * Constructor creates a Echo command object
   */
  public Get() {
    super("\nget URL \n\n" + " Retrieve the file at that URL and add\n"
        + "it to the current working directory.\n");
  }

  /**
   * Gets the content of the URL given
   * 
   * @param urlArgument is the URL given by the user
   * @return the string that contains the content of the url
   */
  public String getURLArgument(String urlArgument) {
    // this will be the return string
    String returnString = "";
    try {
      // create new URL object
      URL url = new URL(urlArgument);
      // get url connection instance referred by url
      URLConnection urlConnection = url.openConnection();
      // create an input stream reader
      InputStreamReader inputStream = null;
      // create a bufferedReader
      BufferedReader bufferedReader = null;
      // check if the URL is valid and if there's an inputStream
      // from the the open connection
      if (urlConnection != null && urlConnection.getInputStream() != null) {
        // instantiate input stream reader
        // Charset is the default charset of Java
        inputStream = new InputStreamReader(urlConnection.getInputStream(),
            Charset.defaultCharset());
        bufferedReader = new BufferedReader(inputStream);
        if (bufferedReader != null) {
          // read line by line
          String nextLine = bufferedReader.readLine();
          while (nextLine != null) {
            // concatenate each line
            returnString += nextLine + "\n";
            nextLine = bufferedReader.readLine();
          }
          // close bufferedReader
          bufferedReader.close();
        }
      }
      // close inputStream
      inputStream.close();
    } catch (Exception e) {
      // if url is invalid
      System.out.println("Error: Invalid URL.");
    }
    return returnString;
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
    File urlFile = new File(urlFileName, (Directory) currentDir);
    urlFile.appendData(urlContent);

    // add the file in the file list of the current directory
    ((Directory) currentDir).addItem(urlFile);
  }

  /**
   * Gets the file name from the url
   * 
   * @param argument is the argument given with the get command
   * @return the string containing the file name of the file that should be
   *         saved
   */
  public String getURLFileName(String argument) {

    // check the index of the last slash
    int indexOfLastSlash = argument.lastIndexOf("/");

    // get the filename of the url using the index of last slash and dot
    String urlFileName =
        argument.substring(indexOfLastSlash + 1, argument.length());

    // check if the url given is valid or not
    if (!checkValidURLFileName(urlFileName))
      urlFileName = "";
    else {
      // check the index of the last dot
      int indexOfLastDot = urlFileName.lastIndexOf(".");
      urlFileName = urlFileName.substring(0, indexOfLastDot);
    }

    return urlFileName;
  }

  /**
   * Checks if the given URL is valid
   * 
   * @param urlFileName is the file name from the given URL
   * @return true if the filename from the URL given is valid
   */
  public boolean checkValidURLFileName(String urlFileName) {

    boolean validURLFileName = false;
    // check the index of the last slash
    int indexOfLastPeriod = urlFileName.lastIndexOf(".");
    // get the extension of the filename using the indexOfLastPeriod
    String extension =
        urlFileName.substring(indexOfLastPeriod + 1, urlFileName.length());
    // check if the extension name of the file is valid
    if (extension.equals("html") || extension.equals("txt")) {
      validURLFileName = true;
    }
    return validURLFileName;
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
  public Directory executeCommand(String[] arguments, int numArgs,
      IDirectory currentDir, FileSystem dirTree, History history,
      Pushd pushd, Popd popd) {

    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);

    // check if there are valid arguments given with the get command
    if (arguments.length == 1 || (arguments.length == 3 && toRedirect)) {
      // get the contents of the URL
      String urlContent = this.getURLArgument(arguments[0]);
      // get the URL file name
      String urlFileName = this.getURLFileName(arguments[0]);
      // check if the file name is valid
      if (urlFileName.length() > 0)
        // save the contents of the URL to a file
        this.saveURLToFile(urlContent, urlFileName, currentDir);
    } else {
      System.out.println("Error: Invalid arguments.");
    }
    return (Directory) currentDir;
  }
}
