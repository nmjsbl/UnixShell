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

import main.DirectoryTreeNode;
import main.FileSystem;
import main.IDirectory;
import main.IFile;

/**
 * Represents a Directory Tree that has a root directory, sub directories and
 * files
 **/
public class MockDirectoryTree<E extends DirectoryTreeNode>
    implements FileSystem {
  /**
   * this is the root directory of this file system
   **/
  public IDirectory rootDir;
  // level 1
  public IDirectory C = new MockDirectory("C", rootDir);

  // level 2
  public IDirectory Documents = new MockDirectory("Documents", C);

  // level 3
  public IDirectory CSCB07 = new MockDirectory("CSCB07", Documents);
  public IDirectory CSCA08 = new MockDirectory("CSCA08", Documents);
  public IFile schedule = new MockFile("schedule", Documents);

  // level 4
  public IDirectory Labs = new MockDirectory("Labs", CSCB07);
  public IFile lecture1 = new MockFile("lecture1", CSCB07);
  public IFile lecture3 = new MockFile("lecture3", CSCB07);
  public IFile lecture2 = new MockFile("lecture2", CSCA08);


  /**
   * Constructs a directory tree with a root directory
   */
  public MockDirectoryTree() {
    MockDirectory root = new MockDirectory("/", null);
    rootDir = root;
  }

  public String makeTree() {
    return "Tree";
  }

  /**
   * Sets the root directory of this file system
   */
  public void setRootDirectory(IDirectory rootDir) {
    this.rootDir = rootDir;
  }

  /**
   * Returns the root directory of this file system
   * 
   * @return the root directory
   */

  public IDirectory getRootDirectory() {
    return this.rootDir;
  }

  /**
   * Creates a new directory with the given name and adds it to the given parent
   * directory
   * 
   * @param name is the name of the new directory
   * @param parent is the parent directory
   */

  public void addDirectory(String name, IDirectory parent) {
    IDirectory item = new MockDirectory(name, parent);
    parent.addItem((DirectoryTreeNode) item);
  }

  /**
   * Creates a file with the given file name and adds it to the given directory
   * 
   * @param name is the name of the new file
   * @param parent is the directory to add the file to
   */

  public void addFile(String name, IDirectory parent) {
    IFile item = new MockFile(name, parent);
    parent.addItem((DirectoryTreeNode) item);
  }

  /**
   * Return a DirectoryTreeNode item that you need for your tests
   * 
   * @param fileName is the name used in test case
   * @param parentDir is the parent directory used in test case
   */

  @Override
  public DirectoryTreeNode getItem(String itemName, IDirectory parentDir) {
    if ((itemName.equals("CSCB07") && parentDir.equals(this.Labs))
        || itemName.equals("/"))
      return null;
    return parentDir.getItem(itemName);
  }

  /**
   * Gets the file associated with the given path as used in your test case
   * 
   * @param parentDirectory is the parent directory of the file
   * @param path is the path that represents the file
   * @return the file associated with the given file
   */
  public DirectoryTreeNode getItemGivenPath(IDirectory parentDirectory,
      String path) {

    if (path.equals("Valid path for parent dir")) {
      return (DirectoryTreeNode) this.CSCB07;
    }
    if (path.equals("Valid path for CSCA08 dir")) {
      return (DirectoryTreeNode) this.CSCA08;
    }
    if (path.equals("Valid path for grandparent dir")) {
      return (DirectoryTreeNode) this.Documents;
    }
    if (path.equals("Valid path for Labs dir")) {
      return (DirectoryTreeNode) this.Labs;
    }
    if (path.equals("Valid path for schedule file")) {
      return (DirectoryTreeNode) this.schedule;
    }
    if (path.equals("Valid path for lecture 1 file")) {
      return (DirectoryTreeNode) this.lecture1;
    }
    if (path.equals("Valid path for lecture 2 file")) {
      return (DirectoryTreeNode) this.lecture2;
    }
    if (path.equals("Valid path for lecture 3 file")) {
      return (DirectoryTreeNode) this.lecture3;
    }
    if (path.equals("Invalid path for grandparent dir")) {
      return null;
    }

    // for absolute path
    return (DirectoryTreeNode) this.rootDir;
  }

  /**
   * Given a some path and some Parent directory return an appropriate string to
   * test your test case
   * 
   * @param parentDir
   * @param filePath
   */
  @Override
  public String getContentsOfFile(IDirectory parentDir, String filePath) {
    return "File contents";
  }

  /**
   * Given a parent directory, return a path as required by your test case
   * 
   * @param parentDirectory
   */
  @Override
  public String getPath(IDirectory parentDirectory) {
    return "path";
  }

  @Override
  public Boolean isDirectory(IDirectory parentDirectory, String path) {
    // Get the item given by the path
    DirectoryTreeNode item = this.getItemGivenPath(parentDirectory, path);
    return item != null && item instanceof IDirectory;
  }

  @Override
  public Boolean isFile(IDirectory parentDirectory, String path) {
    // Get the item given by the path
    DirectoryTreeNode item = this.getItemGivenPath(parentDirectory, path);
    return item != null && item instanceof IFile;
  }

  @Override
  public Boolean isGrandParent(DirectoryTreeNode dirOne,
      DirectoryTreeNode dirTwo) {
    if (dirOne.equals(this.CSCB07) && dirTwo.equals(this.Documents)) {
      return false;
    }
    if (!dirTwo.getParentDirectory().equals(dirOne)) {
    	return false;
    }
    return true;
  }

}
