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
import java.util.List;

/**
 * Represents a ls command that prints the contents of a directory.
 */
public class Ls extends Command {

  /**
   * this is the boolean that indicates whether the list of subdirectories and
   * files should be printed or not
   */
  private boolean toPrint = true;

  /**
   * this is the boolean that indicates whether the list of subdirectories and
   * files that should be redirected to a file should be printed or not
   */
  private boolean toRedirect = false;

  /**
   * Constructs a Ls object that creates a new ls command with documentation
   */
  public Ls() {
    super("\nls [PATH ...] \n\n"
        + "If no paths are given, print the contents(file or directory)\n"
        + "of the current directory, with a new line following each\n"
        + "of the content(file or directory)\n");
  }

  /**
   * Returns the string representation of files and sub-directories in the
   * directory dir from the file system
   * 
   * @param currentDir is the directory whose contents should be printed
   * @return a list of directories and sub-directories
   */
  public String printFromDirectory(IDirectory currentDir) {
    // Create a result variable to store the names of the contents of the
    // directory
    String result = "";

    // Get all the sub directories in currentDir
    ArrayList<IDirectory> subDirectories = new ArrayList<>();
    subDirectories = currentDir.getSubDirectories();
    // Get all the files in the directory
    ArrayList<IFile> files = new ArrayList<>();
    files = currentDir.getFiles();

    // Add names of the sub directories to the result
    for (IDirectory subDir : subDirectories) {
      result += subDir.getName() + "\n";
    }
    // Add names of the files in the directory to the result
    for (IFile file : files) {
      result += file.getName() + "\n";
    }

    // Return the names of all the contents
    return result;
  }

  /**
   * Returns a directory given by the path givenPath.
   * 
   * @param dirTree is the directory tree of the file system
   * @param givenPath is the string that contains the path given by the user
   * @param currentDir is the current working directory
   * @return directory taken by from the path
   */
  public IDirectory getDirectoryFromPath(FileSystem dirTree, String givenPath,
      IDirectory currentDir) {
    String result = "";
    // the directory that needs to list it's sub-directories and files
    IDirectory toLsDir = null;

    // check the given path
    if (givenPath.equals("/")) { // if we want to go to the root
      toLsDir = ((DirectoryTree) dirTree).getRootDirectory();
    } else if (givenPath.equals(".")) { // if we want the current directory
      toLsDir = (Directory) currentDir;
    } else if (givenPath.equals("..")) { // if we want to go to the parent
      toLsDir = ((Directory) currentDir).getParentDirectory();
    } else { // if we are given a path (either absolute or relative)
      // use static method changingDirectoryInShell in Cd class
      toLsDir = Cd.getDirectory(givenPath, new Cd(), currentDir, dirTree, null,
          this, null);
    }
    // return the directory from path
    return toLsDir;
  }

  /**
   * Returns names of the files and sub-directories of a directory
   * 
   * @param toLsDir is the directory where we'll be getting the list of
   *        sub-directory names and file names
   * @return result is the resulting string that contains all sub-directory
   *         names and file names
   */
  public String getResultingString(IDirectory toLsDir) {
    // get sub-directories and files
    String result = "";
    // Check if the path is valid
    if (toPrint) {
      // Get the sub-directories and files
      ArrayList<DirectoryTreeNode> contents = toLsDir.getContents();

      // check if contents is empty or not
      if (!contents.isEmpty()) {
        // get the names of all the sub-directories
        for (DirectoryTreeNode content : contents) {
          result += content.getName() + "\n";
        }
      }
      // If the path is not valid then return an appropriate message
    } else {
      if (toRedirect) {
        System.out.println("Error: Cannot access path.\n");
      } else
        result = "Cannot access path \n";
    }
    // return the result
    return result;
  }

  /**
   * Sets the instance variable toPrint to either true or false
   * 
   * @param toPrint is the boolean that tells if it should be printed or not
   */
  public void setToPrint(Boolean toPrint) {
    this.toPrint = toPrint;
  }

  /**
   * Gets the instance variable toPrint to either true or false
   * 
   * @return the boolean that tells if it should be printed or not
   */
  public boolean getToPrint() {
    return this.toPrint;
  }

  /**
   * This is the recursive function that gets the lists of the names of
   * directories and files from the directory given by the user
   * 
   * @param currentDir is the directory that should list its sub-directories and
   *        files recursively
   * @return the string containing all names of sub-directories and files
   */
  public String recursivelyTraverse(IDirectory currentDir) {
    ArrayList<DirectoryTreeNode> contents =
        ((Directory) currentDir).getContents();
    ArrayList<IDirectory> subDirectories =
        ((Directory) currentDir).getSubDirectories();
    String resultList = "";

    if (contents.isEmpty()) {
      return resultList + "\n";
    } else {
      // prints the names of the sub directories and the files
      for (DirectoryTreeNode content : contents) {
        resultList += content.getName() + "\n";
      }
      resultList += "\n";
      for (IDirectory subDir : subDirectories) {
        resultList += subDir.getName() + ":\n";
        resultList += this.recursivelyTraverse(subDir);
      }
      resultList += "\n";
    }
    return resultList;
  }

  /**
   * This is a helper function that calls the recursive function for ls
   * 
   * @param dirTree is the directory tree
   * @param path is the relative or absolute path given by the user
   * @param currentDir is the current directory
   * @return the string containing all names of sub-directories and files
   */
  public String recursiveLS(FileSystem dirTree, String path,
      IDirectory currentDir) {

    Directory toLsDir =
        (Directory) this.getDirectoryFromPath(dirTree, path, currentDir);
    String result = "";
    // get resulting string using helper function
    // check if the path given is valid
    if (toPrint && (!toLsDir.getSubDirectories().isEmpty()
        || !toLsDir.getFiles().isEmpty())) {
      result += this.recursivelyTraverse(toLsDir);
    } else if (!toPrint) {
      System.out.println("Error: Cannot access path " + path + "\n");
    }

    return result;
  }

  /**
   * This is a helper function the calls the non-recursive function for ls
   * 
   * @param dirTree is the directory tree
   * @param path is the relative or absolute path given by the user
   * @param currentDir is the current directory
   * @return the string containing all names of sub-directories and files
   */
  public String nonrecursiveLS(FileSystem dirTree, String path,
      IDirectory currentDir) {

    IDirectory toLsDir = this.getDirectoryFromPath(dirTree, path, currentDir);
    String result = this.getResultingString(toLsDir);

    return result;
  }

  /**
   * This is a function that's called from the executeCommand method when we
   * need to recursively print all sub-directories and their contents
   * 
   * @param arguments is the list of arguments given with the ls command
   * @param dirTree is the directory tree
   * @param currentDir is the current directory
   * @return the string containing all names of sub-directories and files
   */
  public String recursivePrinting(String[] arguments, FileSystem dirTree,
      IDirectory currentDir) {
    List<String> newArguments =
        Arrays.asList(arguments).subList(1, arguments.length);
    String resultList = "";

    for (String path : newArguments) {
      String recursiveResult =
          this.recursiveLS(dirTree, path, currentDir) + "\n";
      if (toPrint) {
        resultList = resultList + path + ":\n" + recursiveResult;
        resultList = resultList.trim() + "\n\n";
      }
    }
    return resultList;
  }

  /**
   * This is a function that's called from the executeCommand method when we
   * don't need to recursively print all sub-directories and their contents
   * 
   * @param arguments is the list of arguments given with the ls command
   * @param dirTree is the directory tree
   * @param currentDir is the current directory
   * @return the string containing all names of sub-directories and files
   */
  public String nonrecursivePrinting(String[] arguments, FileSystem dirTree,
      IDirectory currentDir) {
    String resultList = "";
    for (String path : arguments) {
      resultList = resultList + path + ":\n";
      resultList += this.nonrecursiveLS(dirTree, path, currentDir) + "\n";
    }
    return resultList;
  }

  /**
   * Returns the redirect arguments if we are redirecting or just the plain
   * arguments with the ls command
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
      // add values to returning arraylist
      returnArguments.add(true);
      if (index == 0) {
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
   * Check if we are redirecting ls command and if the arguments given are
   * correct
   * 
   * @param dirTree is the directory tree
   * @param currentDir is the current working directory
   * @param returnArguments is the list of arguments
   */
  public void redirectingLS(FileSystem dirTree, IDirectory currentDir,
      ArrayList returnArguments) {

    String resultList = "";
    boolean incorrectRecursiveFormat = false;
    this.toRedirect = true;
    if (returnArguments.size() == 2) {
      // if there is not path or directory given with the ls command
      resultList = this.printFromDirectory(currentDir);
    } else {
      // if we are recursively listing or not
      String[] arguments = (String[]) returnArguments.remove(0);
      int numberOccurenceOfR = numberOccurenceOfR(Arrays.asList(arguments));
      int index = Arrays.asList(arguments).indexOf("-R");

      if (index == -1 && numberOccurenceOfR == 0)
        resultList = this.nonrecursivePrinting(arguments, dirTree, currentDir);
      else if (index == 0 && numberOccurenceOfR == 1)
        resultList = this.recursivePrinting(arguments, dirTree, currentDir);
      else if (numberOccurenceOfR > 1) {
        incorrectRecursiveFormat = true;
        System.out.print("Error: Incorrect format for recursive ls. \n");
      } else
        resultList =
            listNonRecursiveAndRecursive(dirTree, currentDir, arguments, index);
    }
    // only redirect the resulting list if the arguments given are valid
    if (!incorrectRecursiveFormat) {
      returnArguments.add(0, resultList);
      this.checkRedirection(dirTree, returnArguments, currentDir);
    }
  }

  /**
   * Gets the number of occurrence of -R in the list of arguments
   * 
   * @param arguments is the list of arguments
   * @return the number of occurrence of -R
   */
  private int numberOccurenceOfR(List arguments) {
    int numberOccurenceOfR = 0;

    for (int i = 0; i < arguments.size(); i++) {
      if (arguments.get(i).equals("-R"))
        numberOccurenceOfR += 1;
    }
    return numberOccurenceOfR;
  }

  /**
   * Check if we are not redirecting ls command and if the arguments given are
   * correct
   * 
   * @param dirTree is the directory tree
   * @param currentDir is the current working directory
   * @param arguments is the list of arguments
   */
  public void nonredirectingLS(FileSystem dirTree, IDirectory currentDir,
      String[] arguments) {
    String resultList = "";
    this.toRedirect = false;
    int numberOccurenceOfR = numberOccurenceOfR(Arrays.asList(arguments));
    int index = Arrays.asList(arguments).indexOf("-R");
    // check if we are listing recursively or not
    if (index == -1 && numberOccurenceOfR == 0)
      resultList = this.nonrecursivePrinting(arguments, dirTree, currentDir);
    else if (index == 0 && numberOccurenceOfR == 1)
      resultList = this.recursivePrinting(arguments, dirTree, currentDir);
    else if (numberOccurenceOfR > 1)
      resultList = "Error: Incorrect format for recursive ls.";
    else
      resultList =
          listNonRecursiveAndRecursive(dirTree, currentDir, arguments, index);
    System.out.println(resultList);
  }


  /**
   * Returns a string with the recursive and non-recursive results
   * 
   * @param dirTree is the directory tree
   * @param currentDir is the current working directory
   * @param arguments is the list of arguments
   * @param index is the index to identify where the recursive arguments are
   * @return a string with the non-recursive and recursive results
   */
  public String listNonRecursiveAndRecursive(FileSystem dirTree,
      IDirectory currentDir, String[] arguments, int index) {
    // Initialize the required arrays
    String[] nonRecursiveArgs = new String[index];
    String[] recursiveArgs = new String[arguments.length - index];

    // get non-recursive arguments
    for (int i = 0; i < index; i++) {
      nonRecursiveArgs[i] = arguments[i];
    }
    int indexRec = 0;
    // get recursive arguments
    for (int i = index; i < arguments.length; i++) {
      recursiveArgs[indexRec] = arguments[i];
      indexRec++;
    }
    // get string result for non-recursive arguments
    String resultNonRecursiveList =
        this.nonrecursivePrinting(nonRecursiveArgs, dirTree, currentDir);
    // get string result for recursive arguments
    String resultRecursiveList =
        this.recursivePrinting(recursiveArgs, dirTree, currentDir);
    // return concatenation of non-recursive and recursive results
    return resultNonRecursiveList + resultRecursiveList;
  }


  /**
   * This executes the ls command
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
    if (numArgs == 0) {
      // check there is not directory or path given
      String result = this.printFromDirectory(currentDir);
      System.out.println(result);
    } else if (numArgs > 0) {
      ArrayList returnArguments = checkRedirectArguments(arguments);
      // check if redirecting ls command result or not
      if ((boolean) returnArguments.remove(0)) {
        this.redirectingLS(dirTree, currentDir, returnArguments);
      } else {
        this.nonredirectingLS(dirTree, currentDir, arguments);
      }
    }
    return currentDir;
  }

}
