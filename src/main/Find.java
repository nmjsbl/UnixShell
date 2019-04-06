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
 * Represents find command that displays all directories or files in a specific
 * directory or directories given a specific name
 * 
 * @param <E>
 */
public class Find<E> extends Command {

  /**
   * Indicates whether or not the output can be redirected
   */
  boolean canRedirect = true;

  /**
   * Constructs a Find object
   */
  public Find() {
    super("\n find path... -type [f|d] -name expression \n "
        + "if type f(file), find will display all the files named name "
        + "in the given directory/directories with paths\n"
        + "if type d(directory), find will display all the directories named"
        + "name in the given directory/directories with paths");
  }

  /**
   * Finds all the files named name in the directory in the given path. If the
   * given path is not valid, method returns an error message.
   * 
   * @param path is the absolute/relative path of the directory
   * @param name is the name of the file is being looked for
   * @param dirTree is the file system of the computer
   * @return a string representing all the files in the directory
   */
  public String findTypeF(String path, String name, FileSystem dirTree,
      IDirectory parentDir) {
    // remove quotation from name
    String newName = name.substring(1, name.length() - 1);
    // find the directory from the path given
    IDirectory dir = (IDirectory) dirTree.getItemGivenPath(parentDir, path);
    // check if dir is type directory
    if (!(dir instanceof IDirectory)) {
      canRedirect = false;
      return "invalid path";
    }
    // find all the files with name name in the directory's array list
    // and accumulate them in result string
    String result = "";
    ArrayList<IFile> files = dir.getFiles();
    for (IFile file : files) {
      if (file.getName().equals(newName)) {
        result += file.getName() + "\n";
        }
    }
    // print the files formatted in result
    if (result.equals("")) {
      canRedirect = false;
      result += "No files of the name " + name + " exists in the path " + path;
    }
    return result.trim();
  }

  /**
   * Finds all the directories named name in the directory in the given path. If
   * the given path is not valid, method returns an error message.
   * 
   * @param path is the absolute/relative path of the directory
   * @param name is the name of the file is being looked for
   * @param dirTree is the file system of the computer
   * @return a string representing all the directories in the directory
   */
  public String findTypeD(String path, String name, FileSystem dirTree,
      IDirectory parentDir) {
    // remove quotation from name
    String newName = name.substring(1, name.length() - 1);

    // find the directory from the path given
    IDirectory dir = (IDirectory) dirTree.getItemGivenPath(parentDir, path);
    // check if dir is of type Directory
    if (!(dir instanceof IDirectory) || dir == null) {
      canRedirect = false;
      return "Invalid path";
    }

    // find all the sub directories with name name in the directory's
    // array list and accumulate them in a string result
    String result = "";
    ArrayList<IDirectory> subDirectories =
        (dir).getSubDirectories();
    for (IDirectory subDir : subDirectories) {
      if (subDir.getName().equals(newName))
        result += subDir.getName() + "\n";
    }
    // print the sub directories from formatted string result
    if (result.equals("")) {
      canRedirect = false;
      result +=
          "No directories of the name " + name + " exists in the path " + path;
    }
    return result.trim();
  }

  /**
   * Returns the arguments needed to perform redirection
   * 
   * @param result is the output of command find
   * @param redirectType can be either of overwrite or append
   * @param fileName is the name of the file we want to redirect to
   * @return an array of strings containing these information
   */
  public ArrayList<String> getRedirectArgs(String result, String redirectType,
      String fileName) {
    String[] args = {result, redirectType, fileName};
    ArrayList<String> redirectionArgs =
        new ArrayList<String>(Arrays.asList(args));
    return redirectionArgs;
  }

  /**
   * Returns a string with the given directory names in the indicated paths.
   * 
   * @param paths are the paths provided by the user
   * @param dirTree is the DirectoryTree of the files and directories
   * @param currentDir is the directory the user is currently in
   * @return the output of the find command
   */
  public String findGivenItemofTyped(ArrayList<String> paths, String name,
      FileSystem dirTree, IDirectory currentDir) {
    String result = "";
    for (String path : paths) {
      result += path + ":\n" + this.findTypeD(path, name, dirTree, currentDir)
          + "\n\n";
    }
    return result.substring(0, result.length() - 2);
  }

  /**
   * Returns a string with the given file names in the indicated paths.
   * 
   * @param paths are the paths provided by the user
   * @param dirTree is the DirectoryTree of the files and directories
   * @param currentDir is the directory the user is currently in
   * @return the output of the find command
   */
  public String findGivenItemofTypef(ArrayList<String> paths, String name,
      FileSystem dirTree, IDirectory currentDir) {
    String result = "";
    for (String path : paths) {
      result += path + ":\n" + this.findTypeF(path, name, dirTree, currentDir)
          + "\n\n";
    }
    return result.substring(0, result.length() - 2);
  }

  /**
   * Return true if the command is valid
   * 
   * @param args are the arguments provided by the user for this command
   * @return true if command is valid, false otherwise.
   */
  public Boolean isValid(List<String> args) {
    // This method only checks the order of arguments
    // in order : some paths -type f|d -name "expression" >|>> Outfile
    if (!args.contains("-type"))
      return false;
    // Get the index of the -type argument
    int indexOfType = args.indexOf("-type");

    // If no paths are provided before -type, return false
    if (indexOfType == 0)
      return false;

    // Get the sublist from -type onwards
    List<String> subArgs = args.subList(indexOfType, args.size() - 1);
//    if(subArgs.size() < 4) {
//      return false;
//    }
    // Check if the arguments provided by the user are valid
    if (!(subArgs.get(1).equals("f") || subArgs.get(1).equals("d"))
        && !(subArgs.get(2).equals("-name")) && subArgs.get(3) == null)
      return false;

    if (subArgs.size() > 4
        && (!(subArgs.get(4).equals(">") || subArgs.get(4).equals(">>"))))
      return false;
    
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
    List<String> args = Arrays.asList(arguments);
    // Check validly of commands
    if (!isValid(args)) {
      System.out.println("Invalid arguments!");
      return currentDir;
    }

    // Separate the paths from args
    // assume all str before -type are path(s)
    ArrayList<String> paths = new ArrayList<String>();
    int indexOfType = args.indexOf("-type");
    for (int i = 0; i < indexOfType; i++) {
      paths.add(args.get(i));
    }
    List<String> subArg = args.subList(indexOfType, args.size());

    // Check if there is at least one path provided
    if (paths.size() == 0) {
      System.out.println("Invalid arguments!");
      return currentDir;
    }
    // Get the output of find command
    String result = "";
    if (subArg.get(1).equals("f")) {
      result +=
          this.findGivenItemofTypef(paths, subArg.get(3), dirTree, currentDir);
    } else if (subArg.get(1).equals("d")) {
      result +=
          this.findGivenItemofTyped(paths, subArg.get(3), dirTree, currentDir);
    }
    // If no redirection
    if (subArg.size() == 4 || !canRedirect) {
      System.out.println(result);
      // Check if there is redirection
    } else if (subArg.size() == 6) {
      // If there is redirection, get the arguments and redirect the output
      String fName = subArg.get(5);
      ArrayList<String> redirectArguments =
          this.getRedirectArgs(result, subArg.get(4), fName);
      this.checkRedirection(dirTree, redirectArguments, currentDir);
    } else {
      System.out.println("Error: Invalid arguments!");
    }
    return currentDir;
  }
}
