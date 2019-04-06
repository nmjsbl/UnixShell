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

/**
 * This interface represents a file system and has the relevant methods that
 * should be implemented in any file system.
 */
public interface FileSystem {

  public IDirectory getRootDirectory();

  public void setRootDirectory(IDirectory rootDir);

  public void addDirectory(String name, IDirectory parent);

  public void addFile(String name, IDirectory parent);

  public DirectoryTreeNode getItem(String itemName, IDirectory parentDir);

  public String getPath(IDirectory parentDirectory);

  public DirectoryTreeNode getItemGivenPath(IDirectory parentDir, String path);

  public Boolean isDirectory(IDirectory parentDirectory, String path);

  public Boolean isFile(IDirectory parentDirectory, String path);

  public Boolean isGrandParent(DirectoryTreeNode dirOne,
      DirectoryTreeNode dirTwo);

  public String makeTree();

  public String getContentsOfFile(IDirectory parentDir, String filePath);
}
