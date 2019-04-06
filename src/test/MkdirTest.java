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

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryTreeNode;
import main.IDirectory;
import main.Mkdir;

public class MkdirTest {

  Mkdir mkdir;
  MockDirectoryTree dirTree;

  @Before
  public void setUp() {
    mkdir = new Mkdir();
    dirTree = new MockDirectoryTree();
  }

  @Test
  public void testExecuteCommand() {
    String[] arguments = {};
    // returns a directory instead of directory tree node
    IDirectory expectedDir = dirTree.getRootDirectory();
    // execute command returns directory when we need to test mock directory
    IDirectory resultDir = mkdir.executeCommand(arguments, 0, expectedDir,
        dirTree, null, null, null);
    assertEquals(expectedDir, resultDir);
  }

  @Test
  public void testMakeNewDirectories() {
    String[] arguments = {"dir1", "dir2"};
    // get root
    IDirectory expectedDir = dirTree.getRootDirectory();
    // make a new directory
    mkdir.makeNewDirectories(dirTree, expectedDir, arguments);
    int numDirectories = expectedDir.getSubDirectories().size();
    assertEquals(0, numDirectories);
  }

  @Test
  public void testMakeNewDirectoryGivenRelativePath() {
    String arguments = "home/path";
    IDirectory expectedDir = dirTree.getRootDirectory();
    dirTree.addDirectory("home", expectedDir);
    mkdir.makeNewDirectoryGivenRelativePath(dirTree, (IDirectory) expectedDir,
        arguments);
    DirectoryTreeNode actualDir =
        dirTree.getItemGivenPath(expectedDir, arguments);
    assertEquals(expectedDir, actualDir);
  }

  @Test
  public void testMakeNewDirectoryGivenFullPath() {
    String arguments = "/parent/path";
    IDirectory expectedDir = dirTree.getRootDirectory();
    mkdir.makeNewDirectoryGivenRelativePath(dirTree, expectedDir, arguments);
    DirectoryTreeNode actualDir =
        dirTree.getItemGivenPath(expectedDir, arguments);
    assertEquals(expectedDir, actualDir);
  }

  @Test
  public void testMakeNewDirectoryGivenDirectoryName() {
    String arguments = "directory";
    IDirectory expectedDir = dirTree.getRootDirectory();
    mkdir.makeNewDirectoryGivenDirectoryName(dirTree, expectedDir, arguments);
    DirectoryTreeNode actualDir = dirTree.getItem("directory", expectedDir);
    assertEquals(expectedDir, actualDir);
  }

}
