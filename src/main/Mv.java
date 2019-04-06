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
 * Represents Mv command that moves a file or directory to another directory in
 * the directory tree
 */
public class Mv extends Command {

  /**
   * Constructs a Mv object that moves an item (file or directory) to another
   * directory
   */
  public Mv() {
    super("\nmv OLDPATH NEWPATH \n\n" + "Move item OLDPATH to NEWPATH.\n"
        + "Both OLDPATH and NEWPATH may be relative to\n"
        + "the current directory or maybe full paths.\n"
        + "If NEWPATH is a directory, move the item into\n"
        + "the directory.\n");
  }

  /**
   * Moves the file given by oldPath to the directory given by newPath
   * 
   * @param dirTree is the directory tree to which the file belongs
   * @param parentDirectory is the parent directory of the file
   * @param oldPath is the path of the file
   * @param newPath is the path of the new directory to move the file to
   */
  public void moveFileGivenPath(FileSystem dirTree,
      IDirectory parentDirectory, String oldPath, String newPath) {
    // Get the file associated with oldPath and newPath
    IFile fileMove = (IFile)dirTree.getItemGivenPath((IDirectory) parentDirectory, oldPath);

    // Check if newPath is a file
    if (dirTree.isFile((IDirectory) parentDirectory, newPath)) {
      IFile fileDestination = (IFile) (dirTree)
          .getItemGivenPath((IDirectory) parentDirectory, newPath);
      // Overwrite the data of fileSource to destinationFile
      String contents = fileMove.getData();
      fileDestination.overwriteData(contents);
      ((IDirectory) parentDirectory).removeItem((DirectoryTreeNode)fileMove);
      // Otherwise, newPath must be directory
    } else {
      // Check if newPath is a directory
      IDirectory newParent = (IDirectory) dirTree
          .getItemGivenPath((IDirectory) parentDirectory, newPath);
      if (newParent == null) {
        System.out.println(newPath + " is an invalid NEWPATH!");
      } else {
        // Move the file to the newParent directory
        this.moveFileToDirectoryGivenFile(dirTree, fileMove,
            newParent);
      }
    }
  }

  /**
   * Moves the directory given by oldPath to the directory given by newPath
   * 
   * @param dirTree is the directory tree to which the directory belongs
   * @param parentDirectory is the parent directory of the directory
   * @param oldPath is the path of the directory
   * @param newPath is the path of the new directory to move the directory to
   */
  public void moveDirectoryGivenPath(FileSystem dirTree,
      IDirectory parentDirectory, String oldPath, String newPath) {
    // Get the directories associated with oldPath and newPath
    IDirectory dirMove = (IDirectory)(dirTree)
        .getItemGivenPath((IDirectory) parentDirectory, oldPath);
    IDirectory newParent = null;
    // Check if newPath is a directory
    if (dirTree.isDirectory((IDirectory) parentDirectory, newPath)) {
      newParent = (IDirectory) dirTree
          .getItemGivenPath((IDirectory) parentDirectory, newPath);
    }

    // Check if dirMove and newParent are valid directories
    if (dirMove == null) {
      System.out.println(oldPath + " is an invalid OLDPATH!");
    } else if (newParent == null) {
      System.out.println(newPath + " is an invalid NEWPATH!");
      // If they are valid copy the directory
    } else {
      // Move the dirMove directory to the newParent directory
      this.moveDirectoryGivenDirectory(dirTree, dirMove, newParent);
    }
  }

  /**
   * Moves the given file to the given directory
   * 
   * @param dirTree is the directory tree to which the directory belongs
   * @param parentDirectory is the parent directory of the directory
   * @param fileMove represents the file to move
   * @param newParentDir is the new directory to move the file to
   */
  public void moveFileToDirectoryGivenFile(FileSystem dirTree,
      IFile fileMove, IDirectory newParentDir) {
    // Check if newParentDir already contains an item with the name of fileMove
    if (dirTree.getItem(fileMove.getName(),
        (IDirectory) newParentDir) != null) {
      // Get the item
      DirectoryTreeNode itemRemove =
          dirTree.getItem(fileMove.getName(), (IDirectory) newParentDir);
      // Remove the item
      ((IDirectory) newParentDir).removeItem(itemRemove);
    }
    // Move fileMove to newParentDir
    ((IFile) fileMove).changeParent((IDirectory) newParentDir);
  }

  /**
   * Moves the given directory to the new directory
   * 
   * @param dirTree is the directory tree to which the directories belong
   * @param dirMove is the directory to move
   * @param newParentDir is the directory to move dirMove to
   */
  public void moveDirectoryGivenDirectory(FileSystem dirTree,
      IDirectory dirMove, IDirectory newParentDir) {
    // Check if the given directories are valid
    if (dirTree.isGrandParent((DirectoryTreeNode) dirMove,
        (DirectoryTreeNode) newParentDir)) {
      System.out.println("Error: Invalid path.");
      // If the parent directory with dirName exists
    } else {
      // Check if a directory named dirMove already exists in newParentDir
      if (dirTree.getItem(dirMove.getName(), newParentDir) != null) {
        newParentDir
            .removeItem(dirTree.getItem(dirMove.getName(), newParentDir));
      }
      // Change the parent directory of this directory
      dirMove.changeParent(newParentDir);
    }
  }

  /**
   * This executes the mv command
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
    // check if we need to redirect
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
      // Check if oldPath is a file, move it to the newPath
      if (dirTree.isFile((IDirectory) currentDir, oldPath))
        this.moveFileGivenPath(dirTree,currentDir, oldPath,
            newPath);
      // If oldPath is a directory, move the directory to the newPath
      else
        this.moveDirectoryGivenPath(dirTree,currentDir,oldPath, newPath);
    }
    return currentDir;
  }
}
