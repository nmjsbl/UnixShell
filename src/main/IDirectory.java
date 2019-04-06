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
 * This interface represents a Directory and contains methods that are relevant
 * to Directory objects.
 */
public interface IDirectory {
  public IFile getFile(String itemName);

  public ArrayList<IFile> getFiles();

  public ArrayList<IDirectory> getSubDirectories();

  public ArrayList<DirectoryTreeNode> getContents();

  public void removeItem(DirectoryTreeNode item);

  public void addItem(DirectoryTreeNode item);

  public String getName();

  public IDirectory getParentDirectory();

  public DirectoryTreeNode getItem(String itemName);

  void setParentDirectory(IDirectory parent);

  void changeParent(IDirectory newParentDir);
}
