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
import java.util.Stack;

/**
 * Represents Cp command that copies a file or directory to another directory in
 * the directory tree
 */
public class Cp extends Command {
  /**
   * Constructs a Cp object that copies an item (file or directory) to another
   * directory
   */
  public Cp() {
    super("\ncp OLDPATH NEWPATH \n\n" + "Copies item OLDPATH to NEWPATH.\n"
        + "Both OLDPATH and NEWPATH may be relative to\n"
        + "the current directory or maybe full paths.\n"
        + "If OLDPATH is a directory, recursively copies\n"
        + "all the contents. If NEWPATH is a directory, \n"
        + "copies the item into the directory.\n");
  }

  /**
   * Copies the file given by oldPath to the directory given by newPath
   * 
   * @param dirTree is the directory tree to which the file belongs
   * @param parentDirectory is the parent directory of the file
   * @param oldPath is the path of the file
   * @param newPath is the path of the new directory to move the file to
   */
  public void copyFileGivenPath(FileSystem dirTree, IDirectory parentDirectory,
      String oldPath, String newPath) {
    // Get the file and directory associated with oldPath and newPath
    IFile fileCopy = (IFile) dirTree.getItemGivenPath(parentDirectory, oldPath);

    // Check if newPath is a file
    if (dirTree.isFile(parentDirectory, newPath)) {
      IFile fileDestination =
          (IFile) dirTree.getItemGivenPath(parentDirectory, newPath);
      // Overwrite the data of fileSource to destinationFile
      String contents = fileCopy.getData();
      fileDestination.overwriteData(contents);
      // Otherwise, newPath must be directory
    } else {
      IDirectory newParent =
          (IDirectory) dirTree.getItemGivenPath(parentDirectory, newPath);

      if (newParent == null) {
        System.out.println(newPath + " is an invalid path!");
      } else {
        // Copy the file to the newParent directory
        this.copyFileGivenFile(dirTree, (DirectoryTreeNode) fileCopy,
            newParent);
      }
    }
  }

  /**
   * Copies the directory given by oldPath to the directory given by newPath
   * 
   * @param dirTree is the directory tree to which the directory belongs
   * @param parentDirectory is the parent directory of the directory
   * @param oldPath is the path of the directory
   * @param newPath is the path of the new directory to move the directory to
   */
  public void copyDirectoryGivenPath(FileSystem dirTree,
      IDirectory parentDirectory, String oldPath, String newPath) {
    // Get the directories associated with oldPath and newPath
    IDirectory dirMove =
        (IDirectory) dirTree.getItemGivenPath(parentDirectory, oldPath);
    IDirectory newParent = null;

    // Check if newPath is a directory
    if (dirTree.isDirectory(parentDirectory, newPath)) {
      newParent =
          (IDirectory) dirTree.getItemGivenPath(parentDirectory, newPath);
    }

    // Check if dirMove and newParent are valid directories
    if (dirMove == null) {
      System.out.println(oldPath + " is invalid!");
    } else if (newParent == null) {
      System.out.println(newPath + " is invalid!");
      // If they are valid copy the directory
    } else {
      // Copy the dirMove directory to the newParent directory
      this.copyDirectoryGivenDirectory(dirTree, dirMove, newParent);
    }
  }

  /**
   * Copies the given file to the given directory
   * 
   * @param parentDirectory is the parent directory of the directory
   * @param fileCopy represents the file to copy
   * @param newParentDir is the new directory to copy the file to
   */
  public void copyFileGivenFile(FileSystem dirTree, DirectoryTreeNode fileCopy,
      IDirectory newParentDir) {
    // Get the item with the name of the file
    DirectoryTreeNode item = dirTree.getItem(fileCopy.getName(), newParentDir);
    // Check if a file or directory with this name already exists in
    // newParentDir
    if (item != null) {
      // Replace that item with fileCopy
      ((IDirectory) newParentDir).removeItem(item);
      // If a file with that name doesn't exist
    }
    // Create a new file with the given name
    dirTree.addFile(fileCopy.getName(), newParentDir);
    // Get the new file
    IFile newFile = (IFile) dirTree.getItem(fileCopy.getName(), newParentDir);
    // Add the contents of fileCopy to newFile
    newFile.overwriteData(((IFile) fileCopy).getData());
  }

  /**
   * Copies the given directory to the new directory
   * 
   * @param dirTree is the directory tree to which the directories belong
   * @param dirMove is the directory to copy
   * @param newParent is the directory to copy dirCopy to
   */
  public void copyDirectoryGivenDirectory(FileSystem dirTree,
      IDirectory dirMove, IDirectory newParent) {
    // Get the root directory of dirTree
    IDirectory root = dirTree.getRootDirectory();
    // Check if the given directories are valid
    if (dirMove.equals(root) || dirTree.isGrandParent(
        (DirectoryTreeNode) dirMove, (DirectoryTreeNode) newParent)) {
      System.out.println("Please provide a valid path!");
      // If the parent directory with dirName exists
    } else {
      // Copy the dirMove to newParent recursively
      this.copyDirectoryRecursively(dirTree, dirMove, newParent);
    }
  }

  /**
   * Copies the given directory recursively to the new directory
   * 
   * @param dirTree is the directory tree to which the directories belong
   * @param dirMove is the directory to copy
   * @param newParent is the directory to copy dirCopy to
   */
  public void copyDirectoryRecursively(FileSystem dirTree, IDirectory dirMove,
      IDirectory newParent) {
    // Create a stack to store the directories and files
    Stack<DirectoryTreeNode> itemStack = new Stack<>();
    Hashtable<String, IDirectory> parentMap = new Hashtable();
    // Push dirMove to the Stack
    itemStack.push((DirectoryTreeNode) dirMove);
    // Initialize a new variabe to store the parent directory
    IDirectory parent = null;
    // Loop until the itemStack is empty
    while (!itemStack.isEmpty()) {
      // Set current to be the directory or file on the top of stack
      DirectoryTreeNode current = itemStack.pop();
      String currentName = current.getName();
      // Check if current is dirMove
      if (currentName.equals(dirMove.getName())) {
        // Set the parent to newParent
        parent = newParent;
        // If it is not equal to dirMove, get the parent directory of the copy
        // of current
      } else {
        parent = (IDirectory) parentMap.get(currentName);
      }
      //System.out.println("\t" + dirTree.getItem(name, parent).getName());
      // If the directory already exists then remove it
      if (dirTree.getItem(currentName, parent) != null)
        parent.removeItem(dirTree.getItem(currentName, parent));
      // Check if current is a directory
      if (current instanceof IDirectory) {
        // Add a new directory with the name of current and overwrite its
        // contents
        dirTree.addDirectory(currentName, parent);
        // Get the contents of the directory
        ArrayList<DirectoryTreeNode> contents =
            ((IDirectory) current).getContents();
        // Loop through the contents of current and add them to the stack
        for (int i = contents.size() - 1; i >= 0; i--) {
          itemStack.add(contents.get(i));
          parentMap.put(contents.get(i).getName(),
              (IDirectory) dirTree.getItem(currentName, parent));
        }
        // If current is not a directory
      } else {
        // Add a new file with the name of current and overwrite its contents
        dirTree.addFile(currentName, parent);
        IFile newFile = (IFile) dirTree.getItem(currentName, parent);
        newFile.overwriteData(((IFile) current).getData());
      }
    }
  }

  /**
   * This executes the cp command
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

    // check if we need to redirect based on the arguments
    // but we don't need to save the file
    ArrayList returnArguments = checkRedirectArguments(arguments);
    boolean toRedirect = (boolean) returnArguments.remove(0);

    // Check to make sure that the user has provided arguments
    if ((numArgs != 2 && !toRedirect) || (toRedirect && numArgs > 4)) {
      // Indicate that no arguments were provided
      System.out.println("Error: Invalid arguments.");
    } else {
      // Get the individual components of the arguments
      String oldPath = arguments[0];
      String newPath = arguments[1];
      // Check if oldPath is a file, copy it to the newPath
      if (dirTree.getItemGivenPath(currentDir, oldPath) != null
          && dirTree.getItemGivenPath(currentDir, oldPath) instanceof File) {
        this.copyFileGivenPath(dirTree, currentDir, oldPath, newPath);
        // If oldPath is a directory, copy the directory to the newPath
      } else
        this.copyDirectoryGivenPath(dirTree, currentDir, oldPath, newPath);
    }
    return currentDir;
  }
}
