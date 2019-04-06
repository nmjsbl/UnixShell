package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.File;
import main.FileSystem;
import main.IDirectory;

public class FileTest {
  
  FileSystem dirTree;
  IDirectory parent;
  IDirectory newParent;
  File File;
  
  @Before
  public void setUp() {
    dirTree = new MockDirectoryTree();
    parent = new MockDirectory("parent", dirTree.getRootDirectory());
    newParent = new MockDirectory("newParent", dirTree.getRootDirectory());
    File = new File("lecture", parent);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testgetName() {
    String actual = File.getName();
    String expected = "lecture";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testchangeName() {
    File.changeName("labs");
    String actual = File.getName();
    String expected = "labs";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testgetParent() {
    IDirectory actual = File.getParentDirectory();
    IDirectory expected = parent;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testchangeParent() {
    File.changeParent(newParent);
    IDirectory actual = File.getParentDirectory();
    IDirectory expected = newParent;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testsetParent() {
    File.changeParent(parent);
    IDirectory actual = File.getParentDirectory();
    IDirectory expected = parent;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testgetDatawithEmptyData() {
    String actual = File.getData();
    assertEquals("", actual);
  }
  
  @Test
  public void testgetDatawithNonEmptyData() {
    File.appendData("Welcome to the last lab");
    String actual = File.getData();
    assertEquals("Welcome to the last lab", actual);
  }
  
  @Test
  public void testappendDatatoEmptyData() {
    File.appendData("Lab for Week 11");
    String actual = File.getData();
    String expected = "Lab for Week 11";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testappendDatatoNonEmptyData() {
    File.appendData("Lab for Week 11");
    File.appendData(" and Lab for Week 12");
    String actual = File.getData();
    String expected = "Lab for Week 11 and Lab for Week 12";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testoverwriteDatawithEmptyData() {
    File.overwriteData("Final exam prep");
    String actual = File.getData();
    String expected = "Final exam prep";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testoverwriteDatawithNonEmptyData() {
    File.appendData("Welcome to last Lab");
    File.overwriteData("Final exam prep");
    String actual = File.getData();
    String expected = "Final exam prep";
    assertEquals(expected, actual);
  }
  

}
