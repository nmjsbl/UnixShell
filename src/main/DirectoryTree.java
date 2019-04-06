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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Represents a Directory Tree that has a root directory, subdirectories and
 * files
 **/
public class DirectoryTree<E extends DirectoryTreeNode>
    implements Serializable, FileSystem {
  /**
   * this is the root directory of this file system
   **/
  private Directory rootDir;

  /**
   * Constructs a directory tree with a root directory
   */
  public DirectoryTree() {
    Directory root = new Directory("/", null);
    rootDir = root;
  }

  /**
   * Returns the root directory of this file system
   * 
   * @return the root directory
   */

  public IDirectory getRootDirectory() {
    return rootDir;
  }

  /**
   * Sets the root directory of this file system
   */
  public void setRootDirectory(IDirectory rootDir) {
    this.rootDir = (Directory) rootDir;
  }

  /**
   * Creates a new directory with the given name and adds it to the given parent
   * directory
   * 
   * @param name is the name of the new directory
   * @param parent is the parent directory
   */

  public void addDirectory(String name, IDirectory parent) {
    Directory newDir = new Directory(name, (Directory) parent);
    ((Directory) parent).addItem(newDir);
  }

  /**
   * Creates a file with the given file name and adds it to the given directory
   * 
   * @param name is the name of the new file
   * @param parent is the directory to add the file to
   */

  public void addFile(String name, IDirectory parent) {
    // Create a new file
    File newFile = new File(name, (Directory) parent);
    ((Directory) parent).addItem(newFile);
  }

  /**
   * Returns the file or directory with the given name in the given parent
   * directory.
   * 
   * @param itemName is the name of the file to fetch
   * @param parentDir is the parent directory
   */

  public DirectoryTreeNode getItem(String itemName, IDirectory parentDir) {
    return (parentDir).getItem(itemName);
  }

  /**
   * Returns the full path of the given directory.
   * 
   * @param currentNode is the directory to get the path of
   * @return the full path of currentDirectory
   */
  public String getPath(IDirectory currentNode) {
    if (currentNode == null) {
      // this directory does not exist
      return "File system is empty";
    } else if (currentNode.equals(this.rootDir)) {
      // this directory is the root
      return "/";
    } else {
      // Initialize the full path with the currentDirectory name
      String fullPath = "/" + currentNode.getName();
      // Get the parent directory of currentDirectory
      Directory parent = (Directory) currentNode.getParentDirectory();

      // Loop until parent is not the root
      while (!parent.equals(this.rootDir)) {
        // Update fullPath with the parent directory's name
        fullPath = parent.getName() + fullPath;
        fullPath = "/" + fullPath;
        // Set parent to the parent directory of the parent
        parent = (Directory) parent.getParentDirectory();
      }
      // Return the full path of the currentDirectory
      return fullPath;
    }
  }

  /**
   * Returns the directory associated to the given relative path
   * 
   * @param parentDirectory is the parent directory of the directory
   * @param relativePath represents the directory path
   * @return the directory associated to the relative path
   */
  private DirectoryTreeNode getItemGivenRelativePath(IDirectory parentDirectory,
      String relativePath) {
    // Get the full path of the parentDirectory
    String pathOfParent = this.getPath(parentDirectory);
    // Initialize fullPath with the parent directory's path
    String fullPath = pathOfParent;
    // If the parent directory is the root directory of the directory tree
    if (parentDirectory.equals(this.getRootDirectory())) {
      // Add the relative path to the fullPath
      fullPath += relativePath;
      // If the parent directory is not the root directory
    } else {
      // Add the relative path to full path preceded by a / to get proper format
      // of the path
      fullPath += "/" + relativePath;
    }
    // Return the file using the full path
    return this.getItemGivenFullPath(fullPath);
  }

  /**
   * Returns the directory associated to the given full path
   * 
   * @param fullPath represents the directory path
   * @return the directory associated to the full path
   */
  private DirectoryTreeNode getItemGivenFullPath(String fullPath) {
    String[] directoryNames = fullPath.split("/");
    // Set the parent to reference the root directory as this is a full path
    IDirectory parent = this.getRootDirectory();
    // Start index at 1 to ensure that the root is not included
    int index = 1;

    // Loop through the directories in the directoryNames and ensure that they
    // all exist and have the corresponding parent directories in the path
    while (index < directoryNames.length - 1) {
      // Check if the directory at index does not exist in the parent directory
      if ((Directory) this.getItem(directoryNames[index], parent) == null) {
        // Let the user know they provided an invalid path and exit the function
        System.out.println(fullPath + " is an invalid path!");
        // return null because an invalid path was provided
        return null;
      }
      // If the directory does exist, let that be the parent directory
      parent = (Directory) this.getItem(directoryNames[index], parent);
      // Increment index to allow all directories to be checked
      index++;
    }
    // Get the file name from the directoryNames array
    String dirName = directoryNames[directoryNames.length - 1];
    // Return the file with the given name
    return this.getItem(dirName, parent);
  }

  /**
   * Gets the file associated with the given path
   * 
   * @param parentDir is the parent directory of the file
   * @param path is the path that represents the file
   * @return the file associated with the given file
   */
  public DirectoryTreeNode getItemGivenPath(IDirectory parentDir, String path) {
    // Set file to null as default
    DirectoryTreeNode item = null;

    // Check if the path is the root
    if (path.equals("/")) {
      return this.rootDir;
      // If a full path is provided, get the file associated to the path
    } else if (path.startsWith("/")) {
      item = this.getItemGivenFullPath(path);
      // If it is a relative path, get the file associated to the path
    } else if (path.contains("/")) {
      item = this.getItemGivenRelativePath(parentDir, path);
      // If it the file name itself, get the file from the directory tree
    } else {
      item = this.getItem(path, parentDir);
    }

    // Return the item
    return item;
  }

  /**
   * Returns true if the item given by the path is a directory
   * 
   * @param parentDir is the parent directory of the given item
   * @param path is the path that represents the item given
   * @return True if the item given is a directory
   */
  public Boolean isDirectory(IDirectory parentDirectory, String path) {
    // Get the item given by the path
    DirectoryTreeNode item = this.getItemGivenPath(parentDirectory, path);
    return item != null && item instanceof IDirectory;
  }

  /**
   * Returns true if the item given by the path is a file
   * 
   * @param parentDir is the parent directory of the given item
   * @param path is the path that represents the item given
   * @return True if the item given is a file
   */
  public Boolean isFile(IDirectory parentDirectory, String path) {
    // Get the item given by the path
    DirectoryTreeNode item = this.getItemGivenPath(parentDirectory, path);
    return item != null && item instanceof IFile;
  }

  /**
   * Returns true is dirOne is a parent or grandparent directory of dirTwo
   * 
   * @param dirMove is the directory to check if its a grandparent
   * @param newParentDir is the directory to check if dirOne is its grandparent
   * @return true if dirOne is a parent or grandparent directory of dirTwo
   */
  public Boolean isGrandParent(DirectoryTreeNode dirMove,
      DirectoryTreeNode newParentDir) {
    if (dirMove.equals(this.rootDir)) {
      // this directory does not exist
      return true;
    } else if (newParentDir.equals(this.rootDir)) {
      return false;
    } else {
      // Get the parent directory of currentDirectory
      Directory parent = (Directory) newParentDir.getParentDirectory();
      // Loop until parent is not the root
      while (!parent.equals(this.rootDir)) {
        // Check if the parent is equal to the dirOne
        if (parent.equals(dirMove)) {
          return true;
        }
        // Set parent to the parent directory of the parent
        parent = (Directory) parent.getParentDirectory();
      }
      // If loop terminates that means it is not a grandparent
      return false;
    }
  }

  /**
   * Returns a representation of all the items (files and directories) in this
   * directory tree as a tree.
   * 
   * @return the tree diagram of this directory tree
   */
  public String makeTree() {
    // Create a stack to store the files/directories in the tree
    Stack<DirectoryTreeNode> itemStack = new Stack<>();
    // Initialize a string variable to store the tree diagram
    String tree = "";
    // Push the root directory into the stack
    itemStack.push(this.rootDir);

    // Loop through the items in the Stack until all the items have been
    // traversed (this is similar to the pre-order traversal of the tree)
    while (!itemStack.isEmpty()) {
      // Get the item on the top of the stack and store it in current
      DirectoryTreeNode current = (DirectoryTreeNode) itemStack.pop();
      // Get the number of tabs required in the output for this item
      int numTabs = this.getNumberOfAncestors((E) current);
      // Get the string of tabs needed to put before current's name
      String tabs = this.getNumberOfTabs(numTabs);
      // Add the required number of tabs, the current item's of name and a
      // newline
      tree += tabs + current.getName() + "\n";

      // Check if current is a Directory (in which case it may have
      // sub directories and files)
      if (current instanceof Directory) {
        // Get the list of files and directories of current directory
        ArrayList<DirectoryTreeNode> contents =
            ((Directory) current).getContents();
        // Loop through the contents of current and add them to the stack
        for (int i = contents.size() - 1; i >= 0; i--) {
          itemStack.add(contents.get(i));
        }
      }
    }
    // Return the generated tree diagram
    return tree;
  }

  /**
   * This is a helper function for the makeTree method. This returns the a
   * string of the number of tabs required
   * 
   * @param numOfTabs is the number of tabs to include in the string
   * @return a string of repeated tab characters given by numOfTabs
   */
  private String getNumberOfTabs(int numOfTabs) {
    String tabs = "";
    // If numOfTabs is 0, return an empty string
    if (numOfTabs == 0)
      return "";
    // Loop through until the number of tabs required is added to tabs
    while (numOfTabs > 0) {
      // Add a tab character to tabs
      tabs += "\t";
      // Decrement numOfTabs
      numOfTabs--;
    }
    // Return the string of numOfTabs of tabs
    return tabs;
  }

  /**
   * Given a file or directory, returns the number of ancestors it has
   * 
   * @param item is the file or directory to check
   * @return the number of ancestors of the file or directory given
   */
  private int getNumberOfAncestors(DirectoryTreeNode item) {
    // Initialize number of parents to 0
    int numAncestors;
    if (item.equals(this.rootDir)) {
      // this directory does not exist
      numAncestors = 0;
    } else {
      // Get the parent directory of given file or directory
      Directory parent = (Directory) item.getParentDirectory();
      numAncestors = 1;
      // Loop until parent is not the root
      while (!parent.equals(this.rootDir)) {
        // Increment numAncestors
        numAncestors += 1;
        // Set parent to the parent directory of the parent
        parent = (Directory) parent.getParentDirectory();
      }
      // If loop terminates that means it is not an ancestor
    }
    return numAncestors;
  }

  /**
   * Returns the data of the given file if the file exists
   * 
   * @param parentDir the directory to which the file belongs to
   * @param fileName the name of the file to extract the data of
   * @return the data of the given file
   */
  public String getContentsOfFile(IDirectory parentDir, String filePath) {
    // Check if the file with the given name does not exist
    if (this.getItemGivenPath(parentDir, filePath) == null
        || !this.isFile((Directory) parentDir, filePath)) {
      return "Error: File " + filePath + " does not exist in this directory!";
      // If the file exists
    } else {
      // Get and return the file contents
      IFile file = (IFile) this.getItemGivenPath(parentDir, filePath);
      return file.getData();
    }
  }

}
