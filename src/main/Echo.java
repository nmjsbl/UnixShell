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
 * Represents an echo command that allows a string to be added to a file,
 * overwritten into a file or allows string to be printed if no file is given.
 */
public class Echo extends Command {

  // this is the boolean that indicates whether the file should be
  // appended or overwritten or not
  private boolean toAppendOrOverwrite = true;


  /**
   * Constructor creates a Echo command object
   */
  public Echo() {
    super("\necho STRING[ > or >> OUTFILE] \n\n"
        + "Appends or overwrites a string into outfile\n"
        + "or makes a new file to append a string to if outfile does not exist.\n"
        + "Also can print a string onto JShell.\n");
  }


  /**
   * Check the validity of the arguments given by the user together with the
   * echo command
   * 
   * @param arguments is the list of arguments parsed
   * @return echoArguments is the array list that consist of the arguments of
   *         echo command
   */
  public ArrayList checkRedirectArguments(String[] arguments) {
    // array list that consist upto 3 elements [STRING, > or >>, OUTFILE]
    ArrayList echoArguments = new ArrayList();
    if (arguments.length > 0 && arguments[0].startsWith("\"")
        && arguments[0].length() > 0) {
      echoArguments = this.getEchoArguments(arguments);
    } else
      echoArguments.add(false);
    return echoArguments;
  }


  /**
   * Returns the arguments needed for echo command
   * 
   * @param arguments is the list of arguments parsed
   * @return echoArguments is the array list that consist of the arguments of
   *         echo command
   */
  public ArrayList getEchoArguments(String[] arguments) {
    // array list that consist upto 3 elements [STRING, > or >>, OUTFILE]
    ArrayList echoArguments = new ArrayList<String>();
    ArrayList echoStringArguments = null;
    // declare index variable
    int index;
    // initialize boolean that check how many arguments are given by the user
    boolean given3Arguments = false;
    // look for > or >> in the argument string array
    for (index = 0; index < arguments.length; index++) {
      if (arguments[index].equals(">") || arguments[index].equals(">>")) {
        given3Arguments = true;
        break;
      }
    }
    // we are given 3 arguments
    if (given3Arguments) {
      echoArguments = this.getGiven3Arguments(arguments, index);
    } else {
      // if we only have a string argument
      echoStringArguments = this.getStringArgument(arguments, arguments.length);
      if ((boolean) echoStringArguments.remove(0))
        // check if the string argument given is valid
        echoArguments = echoStringArguments;
      else
        echoArguments.add(false);
    }
    return echoArguments;
  }


  /**
   * Get the 3 arguments given together by the user
   * 
   * @param arguments is the list of arguments parsed
   * @param index indicates where the > or >> is
   * @return return3Arguments is the array list that consists for 3 elements the
   *         STRING[0], > or >> [1] and OUTFILE[2]
   */
  public ArrayList getGiven3Arguments(String[] arguments, int index) {
    // there should be the 3 arguments for the echo command
    ArrayList return3Arguments = new ArrayList();
    ArrayList tempReturnStringArgument;
    Boolean valid3Arguments = false;
    Boolean validStringArgument = true;

    // for STRING argument
    tempReturnStringArgument = this.getStringArgument(arguments, index);
    if ((boolean) tempReturnStringArgument.remove(0)) {
      // if string argument is valid then
      // for either > or >> argument
      tempReturnStringArgument.add(arguments[index]);

      // for OUTFILE argument
      if (index == arguments.length - 2) {
        String filePath = arguments[index + 1];
        int indexOfLastSlash = filePath.lastIndexOf("/");
        // check if the filename given in the path is valid
        String filename =
            filePath.substring(indexOfLastSlash + 1, filePath.length());
        if (Validator.validateDirectoryOrFileName(filename)) {
          tempReturnStringArgument.add(filePath);
          // update returnStringArgument array list
          return3Arguments = tempReturnStringArgument;
          // arguments are valid
          valid3Arguments = true;
        } else
          valid3Arguments = false;
      } else
        valid3Arguments = false;
    } else // if string argument is not valid
      validStringArgument = false;
    // if one of the arguments fail to be valid
    if (!valid3Arguments || !validStringArgument)
      return3Arguments.add(false);

    return return3Arguments;
  }


  /**
   * Returns the whole STRING argument for the echo command
   * 
   * @param arguments is the list of arguments parsed
   * @param index indicates where the string arguments are from the the
   *        arguments string list
   * @return returnStringArgument is the array list that contains the
   *         stringArgument or the boolean that tells if the string argument is
   *         valid or not
   */
  public ArrayList getStringArgument(String[] arguments, int index) {

    ArrayList returnStringArgument = new ArrayList();
    String stringArgument = "";
    // contains validity of stringArgument
    boolean isStringArgumentValid;
    // counter
    int i = 0;
    // make all elements on the left of arguments[index] to be a string
    for (i = 0; i < index; i++) {
      if (i == index - 1)
        stringArgument += arguments[i];
      else
        stringArgument += arguments[i] + " ";
    }
    // check if stringArgument is a valid string
    isStringArgumentValid = Validator.validateStringArgument(stringArgument);
    returnStringArgument.add(isStringArgumentValid);
    // if is a valid string argument add the stringArgument to the array list
    if (isStringArgumentValid) {
      // remove qoutes from the strings
      stringArgument = stringArgument.substring(1, stringArgument.length() - 1);
      returnStringArgument.add(stringArgument);
    }

    return returnStringArgument;
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
    // arraylist that contains the arguments of echo command

    ArrayList echoArguments = this.checkRedirectArguments(arguments);
    // check size of echoArguments
    if (echoArguments.size() > 1) {
      if (echoArguments.get(1).equals(">")) {
        // check if we need to overwrite
        this.overwriteStringtoFile(dirTree, echoArguments, currentDir);
      } else if (echoArguments.get(1).equals(">>")) {
        // check if we need to append
        this.appendStringtoFile(dirTree, echoArguments, currentDir);
      }
    } else {
      if (echoArguments.get(0).equals(false)) {
        // check if there are no arguments given
        System.out.println("Error: Invalid arguments");
      } else
        // if only string arguments are given
        System.out.println(echoArguments.get(0));
    }
    return currentDir;
  }

}
