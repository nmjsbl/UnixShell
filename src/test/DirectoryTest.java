package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.Directory;
import main.DirectoryTree;
import main.DirectoryTreeNode;
import main.IFile;

public class DirectoryTest {

  Directory grandparent;
  Directory parent1;
  Directory parent2;
  Directory child1;
  Directory newDir1;
  Directory newDir2;
  Directory newDir3;
  DirectoryTree dirTree;
  
  
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
    
    newDir1 = new Directory("New", parent1);
    newDir2 = new Directory("Main", child1);
    newDir3= new Directory("Lib", parent2);
  }
  
  @Test
  public void testGetName1() {
    String actual = parent1.getName();
    String expected = "Work";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetName2() {
    String actual = parent2.getName();
    String expected = "Home";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetName3() {
    String actual = child1.getName();
    String expected = "Public";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetContents1() {
    ArrayList expectedList = parent2.getContents();
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "User";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetContents2() {
    ArrayList expectedList = parent2.getContents();
    expectedList.remove(0);
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "Guest";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetContents3() {
    ArrayList expectedList = parent2.getContents();
    expectedList.remove(0);
    expectedList.remove(0);
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "lecture";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetContents4() {
    ArrayList expectedList = parent2.getContents();
    expectedList.remove(0);
    expectedList.remove(0);
    expectedList.remove(0);
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "assignment";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetItem1() {
    DirectoryTreeNode item = parent1.getItem("Public");
    String actual = item.getName();
    String expected = "Public";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetItem2() {
    DirectoryTreeNode item = parent1.getItem("Private");
    String actual = item.getName();
    String expected = "Private";
    assertEquals(expected, actual);
  }

  @Test
  public void testGetItem3() {
    DirectoryTreeNode item = parent1.getItem("Protected");
    String actual = item.getName();
    String expected = "Protected";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetItem4() {
    DirectoryTreeNode item = parent1.getItem("Encapsulate");
    assertNull(item);
  }
  
  @Test
  public void testAddItem1() {
    parent1.addItem(newDir1);
    int actual = parent1.getContents().size();
    int expected = 4;
    assertEquals(expected, actual);
  }
 
  @Test
  public void testAddItem2() {
    child1.addItem(newDir2);
    int actual = child1.getContents().size();
    int expected = 3;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAddItem3() {
    parent2.addItem(newDir3);
    int actual = parent2.getContents().size();
    int expected = 5;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testRemoveItem1() {
    parent1.removeItem(newDir1);
    int actual = parent1.getContents().size();
    int expected = 3;
    assertEquals(expected, actual);
  }
 
  @Test
  public void testRemoveItem2() {
    child1.removeItem(newDir2);
    int actual = child1.getContents().size();
    int expected = 2;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testRemoveItem3() {
    parent2.removeItem(newDir3);
    int actual = parent2.getContents().size();
    int expected = 4;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetSubDirectories1() {
    ArrayList expectedList = parent2.getSubDirectories();
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "User";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetSubDirectories2() {
    ArrayList expectedList = parent2.getSubDirectories();
    expectedList.remove(0);
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "Guest";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetFiles1() {
    ArrayList expectedList = parent2.getFiles();
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "lecture";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetFiles2() {
    ArrayList expectedList = parent2.getFiles();
    expectedList.remove(0);
    String actual = ((DirectoryTreeNode) expectedList.remove(0)).getName();
    String expected = "assignment";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetFile1() {
    IFile file = child1.getFile("class");
    String actual = file.getName();
    String expected = "class";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetFile2() {
    IFile file = child1.getFile("subclass");
    String actual = file.getName();
    String expected = "subclass";
    assertEquals(expected, actual);
  }
}






















