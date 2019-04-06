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

import java.util.ArrayList;
import main.DirectoryTreeNode;
import main.IDirectory;
import main.IFile;


public class MockDirectory implements DirectoryTreeNode, IDirectory {

  /**
   * This is the parent directory of the current directory
   */
  private IDirectory parentDir;

  // Some directory and files to be returned by methods in this class
  // private MockDirectory CSCB07 = new MockDirectory("CSCB07", this);
  // private MockDirectory CSCA08 = new MockDirectory("CSCA08", this);
  // private MockFile schedule = new MockFile("schedule", this);

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
   * @param rootDir is the directory that this directory resides in
   */
  public MockDirectory(String dirName, IDirectory parentDir) {
    this.name = dirName;
    this.parentDir = parentDir;
  }

  /**
   * Return the name of this directory
   * 
   * @return name of the directory
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Set the parent directory to the given directory
   * 
   * @param parent directory
   */
  @Override
  public void setParentDirectory(IDirectory parent) {
    this.parentDir = parent;

  }

  /**
   * This changes the parent directory of this directory
   * 
   * @param parentDir is the new parent directory of this directory
   */
  @Override
  public void changeParent(IDirectory newParentDirectory) {
    this.setParentDirectory(newParentDirectory);

  }

  /**
   * This returns the parent directory of this directory
   * 
   * @return the parent directory
   */
  @Override
  public IDirectory getParentDirectory() {
    return this.parentDir;
  }

  /**
   * Return the list of items of this directory
   * 
   * @return the array list of files and sub directories
   */
  @Override
  public ArrayList<DirectoryTreeNode> getContents() {
    return this.contents;
  }

  /**
   * Return the Mockfile or directory of the given name
   * 
   * @param itemName is the name of the file or directory to fetch
   * @return the file or directory with the given name if it exists
   */

  @Override
  public IFile getFile(String itemName) {
    return new MockFile(itemName, this);
  }

  /**
   * Return the file or directory of the given name
   * 
   * @param itemName is the name of the file or directory to fetch
   * @return the file or directory with the given name if it exists
   */

  @Override
  public DirectoryTreeNode getItem(String itemName) {
    if (itemName.equals("schedule"))
      return new MockFile("schedule", this);
    if (itemName.equals("lecture2"))
      return new MockFile("lecture2", this);
    if (itemName.equals("lecture1"))
      return new MockFile("lecture1", this);
    if (itemName.equals("CSCA08"))
      return new MockDirectory("CSCA08", this);
    if (itemName.equals("invalid lecture 1")) {
      return null;
    }

    return new MockDirectory("CSCB07", this);
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
   * This returns the list of sub directories of this directory
   * 
   * @return a list of sub directories of this directory
   */

  @Override
  public ArrayList<IDirectory> getSubDirectories() {

    // Create an array list to be returned
    ArrayList<IDirectory> subDirs = new ArrayList<>();
    // Add some directories
    if (this.name.equals("CSCB07")) {
      IDirectory Labs = new MockDirectory("Labs", this);
      subDirs.add(Labs);
      return subDirs;
    } else if (this.name.equals("Documents")) {
      IDirectory Labs = new MockDirectory("Labs", this);
      subDirs.add(Labs);
      return subDirs;
    }

    // Return the list of directories
    return subDirs;

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
    if (this.name.equals("CSCB07")) {
      IFile lecture1 = new MockFile("lecture1", this);
      files.add(lecture1);
      return files;
    }

    else if (this.name.equals("Documents")) {
      IFile lecture1 = new MockFile("lecture1", this);
      files.add(lecture1);
      return files;
    }
    String expectedURLContent =
        "<html><body>This is a content of the URL</body></html>";
    // Add some files to Array list to be returned
    MockFile getFile = new MockFile("sample", this);
    getFile.appendData(expectedURLContent);
    // Return the list of files
    files.add(getFile);
    return files;
  }

}
