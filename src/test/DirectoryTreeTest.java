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

public class DirectoryTreeTest {

  Directory grandparent;
  Directory parent1;
  Directory parent2;
  Directory child1;
  DirectoryTree dirTree;
  String tree;
  
  
  @Before
  public void setUp() {
    dirTree = new DirectoryTree();
    grandparent = (Directory) dirTree.getRootDirectory();
    dirTree.addDirectory("Work", grandparent);
    dirTree.addDirectory("Home", grandparent);
    
    // create sub directories of Work
    parent1 = (Directory) dirTree.getItem("Work", grandparent);
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
    
    tree =
        "/\n"+
        "\tWork\n" +
        "\t\tPublic\n" +
        "\t\t\tclass\n" +
        "\t\t\tsubclass\n" +
        "\t\tPrivate\n" +
        "\t\tProtected\n" +
        "\tHome\n" +
        "\t\tUser\n" +
        "\t\tGuest\n" +
        "\t\tlecture\n" +
        "\t\tassignment\n";
  }
  
  @Test
  public void testGetRootDirectory() {
    Directory root = (Directory) dirTree.getRootDirectory();
    assertEquals(grandparent, root);
  }
  
  @Test
  public void testSetRootDirectory1() {
    Directory root = new Directory("root", null);
    dirTree.setRootDirectory(root);
    Directory actualRoot = (Directory) dirTree.getRootDirectory();
    String expected = "root";
    String actual = actualRoot.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSetRootDirectory2() {
    Directory root = new Directory("/", null);
    dirTree.setRootDirectory(root);
    Directory actualRoot = (Directory) dirTree.getRootDirectory();
    String expected = "/";
    String actual = actualRoot.getName();
    assertEquals(expected, actual);
  }
  
  
  @Test
  public void testAddDirectory1() {
    dirTree.addDirectory("School", grandparent);
    int actual = grandparent.getContents().size();
    int expected = 3;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAddDirectory2() {
    dirTree.addDirectory("Static", parent1);
    int actual = parent1.getContents().size();
    int expected = 4;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAddFile1() {
    dirTree.addFile("quiz", parent2);
    int actual = parent2.getContents().size();
    int expected = 5;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAddFile2() {
    dirTree.addFile("test", child1);
    int actual = child1.getContents().size();
    int expected = 3;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetItem1() {
    Directory expectedDir = (Directory) dirTree.getItem("Work", grandparent);
    assertEquals(expectedDir, parent1);
  }
  
  @Test
  public void testGetItem2() {
    Directory expectedDir = (Directory) dirTree.getItem("Public", parent1);
    assertEquals(expectedDir, child1);
  }
  
  @Test
  public void testGetItem3() {
    Directory expectedDir = (Directory) dirTree.getItem("Home", grandparent);
    assertEquals(expectedDir, parent2);
  }
  
  @Test
  public void testGetItem4() {
    Directory expectedDir = (Directory) dirTree.getItem("Some", grandparent);
    assertNull(expectedDir);
  }
  
  @Test
  public void testGetPath1() {
    String expected = "/Work/Public";
    String actual = dirTree.getPath(child1);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetPath2() {
    String expected = "/Home";
    String actual = dirTree.getPath(parent2);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetPath3() {
    String expected = "/";
    String actual = dirTree.getPath(grandparent);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetPath4() {
    String expected = "File system is empty";
    String actual = dirTree.getPath(null);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetItemGivenPath1() {
    String path = "/Work/Private";
    Directory expectedDir = (Directory) dirTree.getItem("Private", parent1); 
    Directory actualDir = (Directory) dirTree.getItemGivenPath(child1, path);
    assertEquals(expectedDir, actualDir);
  }

  @Test
  public void testGetItemGivenPath2() {
    String path = "/Work/Public/subclass";
    File expectedDir = (File) dirTree.getItem("subclass", child1); 
    File actualDir = (File) dirTree.getItemGivenPath(parent2, path);
    assertEquals(expectedDir, actualDir);
  }
  
  @Test
  public void testIsDirectory1() {
    boolean expected = true;
    boolean actual = dirTree.isDirectory(child1, "/Work/Public");
    assertEquals(expected, actual);
  }

  @Test
  public void testIsDirectory2() {
    boolean expected = false;
    boolean actual = dirTree.isDirectory(child1, "/Work/Public/class");
    assertEquals(expected, actual);
  }
  
  @Test
  public void testIsFile1() {
    boolean expected = false;
    boolean actual = dirTree.isFile(parent2, "/Home");
    assertEquals(expected, actual);
  }

  @Test
  public void testIsFile2() {
    boolean expected = true;
    boolean actual = dirTree.isFile(parent2, "/Home/lecture");
    assertEquals(expected, actual);
  }
  
  
  @Test
  public void testIsGrandParent1() {
    boolean expected = false;
    boolean actual = dirTree.isGrandParent(parent2, parent1);
    assertEquals(expected, actual);
  }

  @Test
  public void testIsGrandParent2() {
    boolean expected = true;
    boolean actual = dirTree.isGrandParent(grandparent, parent2);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testMakeTree() {
    String actualTree = dirTree.makeTree();
    assertEquals(tree, actualTree);
  }
  
  @Test
  public void testGetContentsOfFile1() {
    File file = (File) dirTree.getItem("lecture", parent2);
    String expected = "This is the content of the file.";
    file.appendData(expected);
    String actual = dirTree.getContentsOfFile(parent2, "/Home/lecture");
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetContentsOfFile2() {
    File file = (File) dirTree.getItem("class", child1);
    String expected = "This is the content of the file.";
    file.appendData(expected);
    String actual = dirTree.getContentsOfFile(parent2, "/Work/Public/class");
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetContentsOfFile3() {
    String expected = "Error: File /Work/Protected does"
        + " not exist in this directory!";
    String actual = dirTree.getContentsOfFile(parent2, "/Work/Protected");
    assertEquals(expected, actual);
  }
  
}