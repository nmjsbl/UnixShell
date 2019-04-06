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

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Directory;
import main.DirectoryTree;
import main.File;
import main.Ls;
import main.FileSystem;
import main.IDirectory;
import main.DirectoryTreeNode;

public class LsTest {
  Ls ls;
  DirectoryTree dirTree;
  IDirectory currentDir;
  IDirectory grandparent;
  IDirectory parent1;
  IDirectory parent2;
  IDirectory child1;
  
  @Before
  public void setUp() {
    ls = new Ls();
    dirTree = new DirectoryTree();
    // create a file system 
    grandparent = dirTree.getRootDirectory();
    dirTree.addDirectory("Work", grandparent);
    dirTree.addDirectory("Home", grandparent);

    // create sub directories of Work
    parent1 = (Directory)dirTree.getItem("Work", grandparent);
    dirTree.addDirectory("Public", parent1);
    dirTree.addDirectory("Private", parent1);
    dirTree.addDirectory("Protected", parent1);
    
    // add files to Public sub-directory
    child1 = (Directory) dirTree.getItem("Public", parent1);
    dirTree.addFile("class", child1);
    dirTree.addFile("subclass", child1);
    
    // create sub directories of Home
    parent2 = (Directory) dirTree.getItem("Home", grandparent);
    dirTree.addDirectory("User", parent2);
    dirTree.addDirectory("Guest", parent2);
    dirTree.addFile("lecture", parent2);
    dirTree.addFile("assignment", parent2);
  }
  
  @Test
  public void testExecuteCommand() {
    String[] arguments = {"/"};
    IDirectory expectedDir = (Directory) dirTree.getRootDirectory();
    IDirectory resultDir = ls.executeCommand(arguments,1,expectedDir,
    		dirTree, null,null,null);
    assertEquals(expectedDir,resultDir);
    
  }

  @Test
  public void testSetToPrint() {
    boolean expected = true;
    ls.setToPrint(expected);
    boolean result = ls.getToPrint();
    assertEquals(expected, result);
  }

  @Test
  public void testPrintFromDirectory() {
    String result = ls.printFromDirectory(parent1);
    String expected = "Public\nPrivate\nProtected\n";
    assertEquals(expected, result);
  }

  @Test
  public void testPrintFromPath1() {
    currentDir = dirTree.getRootDirectory();
    String path = "/Work/Public";
    Directory pathDir = (Directory)
        ls.getDirectoryFromPath((FileSystem)dirTree, path, currentDir);
    String result = ls.getResultingString(pathDir);
    String expected = "class\nsubclass\n";
    assertEquals(expected, result);
  }
  
  @Test
  public void testPrintFromPath2() {
    currentDir = dirTree.getRootDirectory();
    String path = "/Home";
    Directory pathDir = (Directory)
        ls.getDirectoryFromPath((FileSystem)dirTree, path, currentDir);
    String result = ls.getResultingString(pathDir);
    String expected = "User\nGuest\nlecture\nassignment\n";
    //assertEquals(expected, result);
  }

  @Test
  public void testGetResultingString() {
    currentDir = dirTree.getRootDirectory();
    Directory tempDir = (Directory) currentDir.getItem("Home");
    String result = ls.getResultingString(tempDir);
    String expected = "User\nGuest\nlecture\nassignment\n";
    assertEquals(expected, result);
  }


  @Test
  public void testGetToPrint() {
    boolean expected = false;
    ls.setToPrint(expected);
    boolean result = ls.getToPrint();
    assertEquals(expected, result);
  }

}
