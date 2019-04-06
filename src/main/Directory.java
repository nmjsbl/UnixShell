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

/**
 * Represents a directory that stores subdirectories and files.
 * 
 * @param <E>
 */
public class Directory implements DirectoryTreeNode, Serializable, IDirectory {
  /**
   * This is the parent directory of the current directory
   */
  private IDirectory parentDir;
  /**
   * This is an ArrayList storing all the contents (files and directories) in
   * this directory
   */
  private ArrayList<DirectoryTreeNode> contents = new ArrayList<>();
  /**
   * this is the name of the directory
   **/
  private String name;

  /**
   * Constructs a directory object that has a parent directory, sub-directories
   * and a name
   * 
   * @param dirName is the name of the directory created
   * @param parent is the directory that this directory resides in
   */
  public Directory(String dirName, IDirectory parent) {
    this.name = dirName;
    this.parentDir = parent;
  }

  /**
   * Set the parent directory to the given directory
   * 
   * @param parent directory
   */
  @Override
  public void setParentDirectory(IDirectory parent) {
    this.parentDir = (Directory) parent;
  }

  /**
   * This returns the parent directory of this directory
   * 
   * @return the parent directory
   */

  public IDirectory getParentDirectory() {
    return this.parentDir;
  }

  /**
   * This changes the parent directory of this directory
   * 
   * @param parentDir is the new parent directory of this directory
   */
  @Override
  public void changeParent(IDirectory newParentDirectory) {
    // get the parent directory
    IDirectory curParent = this.parentDir;
    // Add this directory to be a subdirectory of the newParentDirectory
    (newParentDirectory).addItem(this);
    curParent.removeItem(this);
  }

  /**
   * This returns the name of this directory
   * 
   * @return the name of this directory
   */

  public String getName() {
    return this.name;
  }

  /**
   * Return the list of items of this directory
   * 
   * @return the array list of files and sub directories
   */

  public ArrayList<DirectoryTreeNode> getContents() {
    return this.contents;
  }

  /**
   * Return the file or directory of the given name
   * 
   * @param itemName is the name of the file or directory to fetch
   * @return the file or directory with the given name if it exists
   */

  public DirectoryTreeNode getItem(String itemName) {
    // Go through the directories and files in contents
    for (DirectoryTreeNode item : this.contents) {
      // Check if the name of sub matches the subName
      if (item.getName().equals(itemName)) {
        // Return the item
        return item;
      }
    }
    // If a file with the given name does not exist, return null
    return null;
  }

  /**
   * This adds a new item to this directory
   * 
   * @param the item to be added
   */

  public void addItem(DirectoryTreeNode item) {
    // Add this item to this directory
    this.contents.add(item);
    item.setParentDirectory(this);
  }

  /**
   * This removes the given item from this directory
   * 
   * @param item to be removed
   */

  public void removeItem(DirectoryTreeNode item) {
    // Remove the given file from this directory
    this.contents.remove(item);
  }

  /**
   * This returns the list of subdirectories of this directory
   * 
   * @return a list of subdirectories of this directory
   */
  @Override
  public ArrayList<IDirectory> getSubDirectories() {
    // Create a new arraylist to store the subdirectories
    ArrayList<IDirectory> subDir = new ArrayList<>();
    // Check each item in contents, if it is a directory add it to subDir
    for (DirectoryTreeNode dir : this.contents) {
      if (dir instanceof IDirectory) {
        subDir.add((IDirectory) dir);
      }
    }
    // Return the list of directories
    return subDir;
  }

  /**
   * This returns the list of subdirectories of this directory
   * 
   * @return a list of subdirectories of this directory
   */
  @Override
  public ArrayList<IFile> getFiles() {
    // Create a new arraylist
    ArrayList<IFile> files = new ArrayList<>();
    // Loop through the contents and add file objects to the arraylist of files
    for (DirectoryTreeNode file : this.contents) {
      if (file instanceof IFile) {
        files.add((IFile) file);
      }
    }
    // Return the list of files
    return files;
  }



  /**
   * Return the file or directory of the given name
   * 
   * @param itemName is the name of the file or directory to fetch
   * @return the file or directory with the given name if it exists
   */

  @Override
  public IFile getFile(String itemName) {
    // Go through the directories and files in contents
    for (IFile item : this.getFiles()) {
      // Check if the name of sub matches the subName
      if (item.getName().equals(itemName)) {
        // Return the item
        return item;
      }
    }
    // If a file with the given name does not exist, return null
    return null;
  }
  

}
