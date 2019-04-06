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
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.Cd;
import main.Directory;
import main.DirectoryTree;
import main.IDirectory;
import main.Ls;

public class CdTest {
  
  Cd cd;
  DirectoryTree dirTree;
  IDirectory currentDir;
  IDirectory grandparent;
  IDirectory parent1;
  IDirectory parent2;
  IDirectory child1;
  
  @Before
  public void setUp() {
    cd = new Cd();
    dirTree = new DirectoryTree();
    currentDir = dirTree.getRootDirectory();
    // create a file system 
    grandparent = dirTree.getRootDirectory();
    dirTree.addDirectory("Work", grandparent);
    dirTree.addDirectory("Home", grandparent);

    // create sub directories of WorkLs
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
  public void testChangeDirectory1() {
    ArrayList expectedList = cd.changeDirectory(currentDir, "Work");
    expectedList.remove(0);
    boolean actual = (boolean) expectedList.remove(0);
    boolean expected = true;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testChangeDirectory2() {
    ArrayList expectedList = cd.changeDirectory(currentDir, "Work");
    Directory actualDir = (Directory) expectedList.remove(0);
    String actual = actualDir.getName();
    String expected = "Work";
    assertEquals(expected, actual);
  }

  @Test
  public void testGivenPath1() {
    String[] dirPath = {"", "Work", "Public"};
    ArrayList expectedList = cd.givenPath(dirPath, currentDir, 1);
    Directory actualDir = (Directory) expectedList.remove(0);
    String actual = actualDir.getName();
    String expected = "Public";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGivenPath2() {
    String[] dirPath = {"", "Work", "Public"};
    ArrayList expectedList = cd.givenPath(dirPath, currentDir, 1);
    expectedList.remove(0);
    boolean actual = (boolean) expectedList.remove(0);
    boolean expected = true;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGivenPath3() {
    String[] dirPath = {"Home", "User"};
    ArrayList expectedList = cd.givenPath(dirPath, currentDir, 0);
    Directory actualDir = (Directory) expectedList.remove(0);
    String actual = actualDir.getName();
    String expected = "User";
    assertEquals(expected, actual);
  }

  @Test
  public void testGivenPath4() {
    String[] dirPath = {"Home", "User"};  
    ArrayList expectedList = cd.givenPath(dirPath, currentDir, 0);
    expectedList.remove(0);
    boolean actual = (boolean) expectedList.remove(0);
    boolean expected = true;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testUpdateCurrentDir1() {
    String[] dirPath = {"", "Work", "Public"};
    ArrayList givenPathDir = cd.givenPath(dirPath, currentDir, 1);
    Directory givenDir = (Directory) givenPathDir.remove(0);
    boolean dirExist = (boolean) givenPathDir.remove(0);
    ArrayList updateCurrentDir =
        cd.updateCurrentDir(dirExist, currentDir, givenDir);
    Directory actualDir = (Directory) updateCurrentDir.remove(0);
    String actual = actualDir.getName();
    String expected = "Public";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testUpdateCurrentDir2() {
    String[] dirPath = {"Home", "User"};
    ArrayList givenPathDir = cd.givenPath(dirPath, currentDir, 0);
    Directory givenDir = (Directory) givenPathDir.remove(0);
    boolean dirExist = (boolean) givenPathDir.remove(0);
    ArrayList updateCurrentDir =
        cd.updateCurrentDir(dirExist, currentDir, givenDir);
    Directory actualDir = (Directory) updateCurrentDir.remove(0);
    String actual = actualDir.getName();
    String expected = "User";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDirectory1() {
    String argument = "..";
    currentDir = (Directory) dirTree.getItem("Private", parent1);
    Directory actualDir = (Directory) Cd.getDirectory(argument, cd, currentDir,
        dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "Work";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDirectory2() {
    String argument = "/";
    currentDir = (Directory) dirTree.getItem("Private", parent1);
    Directory actualDir = (Directory) Cd.getDirectory(argument, cd, currentDir,
        dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "/";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDirectory3() {
    String argument = ".";
    currentDir = (Directory) dirTree.getItem("Private", parent1);
    Directory actualDir = (Directory) Cd.getDirectory(argument, cd, currentDir,
        dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "Private";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDirectory4() {
    String argument = "/Home/Work";
    currentDir = (Directory) dirTree.getItem("Private", parent1);
    Directory actualDir = (Directory) Cd.getDirectory(argument, cd, currentDir,
        dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "Private";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDirectory5() {
    String argument = "Home/User";
    System.out.println(currentDir.getName());
    Directory actualDir = (Directory) Cd.getDirectory(argument, cd, currentDir,
        dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "User";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDirectory6() {
    String argument = "Protected";
    currentDir = parent1;
    Directory actualDir = (Directory) Cd.getDirectory(argument, cd, currentDir,
        dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "Protected";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testCheckPathValidity1() {
    String[] dirPath = {"Home", "User"};
    ArrayList returnDirs = cd.givenPath(dirPath, currentDir, 0);
    Directory actualDir =
        (Directory) cd.checkPathValidity(returnDirs, currentDir,
            true, null, null, null);
    String actual = actualDir.getName();
    String expected = "User";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testCheckPathValidity2() {
    String[] dirPath = {"", "Work", "Public"};
    ArrayList returnDirs = cd.givenPath(dirPath, currentDir, 1);
    Directory actualDir =
        (Directory) cd.checkPathValidity(returnDirs, currentDir,
            true, null, null, null);
    String actual = actualDir.getName();
    String expected = "Public";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testExecuteCommand1() {
    String[] arguments = {"/Work/Public"};
    Directory actualDir =
        (Directory) cd.executeCommand(arguments, 1, currentDir,
            dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "Public";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testExecuteCommand2() {
    String[] arguments = {"/Home/Public"};
    Directory actualDir =
        (Directory) cd.executeCommand(arguments, 1, currentDir,
            dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "/";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testExecuteCommand3() {
    String[] arguments = {"Work"};
    Directory actualDir =
        (Directory) cd.executeCommand(arguments, 1, currentDir,
            dirTree, null, null, null);
    String actual = actualDir.getName();
    String expected = "Work";
    assertEquals(expected, actual);
  }
}
