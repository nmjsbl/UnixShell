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

/**
 * Represents a file that has a name, parent directory and stores string data.
 */
public class File implements DirectoryTreeNode, Serializable, IFile {
  /**
   * this is the data that is stored in the file
   */
  private String data = "";
  /**
   * this is the parent directory of the file
   */
  private IDirectory parentDirectory;
  /**
   * this is the name of the file
   */
  private String fileName = "";

  /**
   * Constructor creates a file with the given parent directory and name
   * 
   * @param name of the file and the parent directory
   * @param parent is the parent directory to add the file to
   */
  public File(String name, IDirectory parent) {
    this.fileName = name;
    this.parentDirectory = parent;
  }

  /**
   * Return the name of this file
   * 
   * @return the name of this file
   */

  @Override
  public String getName() {
    return this.fileName;
  }

  /**
   * Change the name of the file to the given file
   * 
   * @param newName is the new name for the file
   */
  public void changeName(String newName) {
    this.fileName = newName;
  }

  /**
   * This sets the parent directory of this file to the given parent directory.
   * 
   * @param newParentDirectory the new parent directory of this file
   */

  @Override
  public void changeParent(IDirectory newParentDirectory) {
    // 1. Remove this file from current parent directory (DO WE NEED THIS??)
    this.parentDirectory.removeItem(this);
    // 2. Add this file to the parent directory given
    newParentDirectory.addItem(this);
    // 3. Set parent as the new parent directory of this file
    this.parentDirectory = newParentDirectory;
  }

  /**
   * Return the parent directory of this file
   * 
   * @return the parent directory of this file
   */

  @Override
  public IDirectory getParentDirectory() {
    return this.parentDirectory;
  }

  /**
   * Sets the given parent as the parent directory of this file
   * 
   * @param parent is the new parent directory
   */
  @Override
  public void setParentDirectory(IDirectory parent) {
    this.parentDirectory = parent;
  }

  /**
   * This method returns the data stored in the file
   * 
   * @return data in the file
   */
  @Override
  public String getData() {
    return this.data;
  }

  /**
   * Appends the given input to the data in the file
   * 
   * @param input the string to add to the file
   */
  @Override
  public void appendData(String input) {
    this.data += input;
  }

  /**
   * Overwrite the current data with the given input
   * 
   * @param the data to overwrite in the file
   */
  @Override
  public void overwriteData(String input) {
    this.data = input;
  }

}
