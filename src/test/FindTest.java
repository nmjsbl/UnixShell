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
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryTreeNode;
import main.Find;
import main.IDirectory;

public class FindTest {

  MockDirectoryTree<DirectoryTreeNode> dirTree;
  Find findCommand;
  IDirectory Labs;

  @Before
  public void setUp() {
    findCommand = new Find();
    dirTree = new MockDirectoryTree<DirectoryTreeNode>();
  }

  @Test
  public void testFindTypeDOnPath() {
    setUp();

    String actual = this.findCommand.findTypeD("Valid path for parent dir",
        "\"Labs\"", dirTree, dirTree.CSCB07);
    String expected = "Labs";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFindTypeDonInvalidPath() {
    String actual = findCommand.findTypeF("Some Invalid path",
        "\"Labs\"", dirTree, dirTree.CSCB07);
    String expected = "No files of the name \"Labs\" exists in the path "
        + "Some Invalid path";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFindTypeDonNonexistingFileinPath() {
    String actual = findCommand.findTypeF("Valid path for parent dir",
        "\"blabla\"", dirTree, dirTree.CSCB07);
    String expected = "No files of the name \"blabla\" exists in the path "
        + "Valid path for parent dir";
    assertEquals(expected, actual);
  }

  @Test
  public void testFindTypeDonValidPath() {
    String actual = findCommand.findTypeF("Valid path for parent dir",
        "\"lecture1\"", dirTree, dirTree.CSCB07);
    String expected = "lecture1";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFindTypeFonInvalidPath() {
    String actual = findCommand.findTypeF("Some Invalid path",
        "\"lecture1\"", dirTree, dirTree.CSCB07);
    String expected = "No files of the name \"lecture1\" exists in the path "
        + "Some Invalid path";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testFindTypeFonNonexistingFileinPath() {
    String actual = findCommand.findTypeF("Valid path for parent dir",
        "\"lab1\"", dirTree, dirTree.CSCB07);
    String expected = "No files of the name \"lab1\" exists in the path "
        + "Valid path for parent dir";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testgetRedirectArgs() {
    ArrayList<String> actual = this.findCommand.getRedirectArgs("lecture1", 
        "append", "Record");
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("lecture1");
    expected.add("append");
    expected.add("Record");
    
    assertEquals(expected, actual);
  }
  
  // for type f
  @Test
  public void testfindGivenItemofTypefonValidandInvalid() {
    ArrayList<String> paths = new ArrayList<String>();
    paths.add("Valid path for parent dir");
    paths.add("Path to root");
    
    String actual = this.findCommand.findGivenItemofTypef(paths, 
        "\"lecture1\"", dirTree, dirTree.CSCB07);
    
    String expected = 
        "Valid path for parent dir:\n" + 
        "lecture1\n\n" + 
        "Path to root:\n" + 
        "No files of the name \"lecture1\" exists in the path Path to root";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testfindGivenItemofTypefwithMultiplepathsContainingSameFileName() {
    ArrayList<String> paths = new ArrayList<String>();
    paths.add("Valid path for parent dir");
    paths.add("Valid path for grandparent dir");
    
    String actual = this.findCommand.findGivenItemofTypef(paths, 
        "\"lecture1\"", dirTree, dirTree.CSCB07);
    
    String expected = 
        "Valid path for parent dir:\n" + 
        "lecture1\n\n" + 
        "Valid path for grandparent dir:\n" + 
        "lecture1";
    assertEquals(expected, actual);
  }
  
  // for type d
  @Test
  public void testfindGivenItemofTypedonValidandInvalid() { 
    ArrayList<String> paths = new ArrayList<String>();
    paths.add("Valid path for parent dir");
    paths.add("Path to root");
    
    String actual = this.findCommand.findGivenItemofTyped(paths, 
        "\"Labs\"", dirTree, dirTree.CSCB07);
    
    String expected = 
        "Valid path for parent dir:\n" + 
        "Labs\n\n" + 
        "Path to root:\n" + 
        "No directories of the name \"Labs\" exists in the path Path to root";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testfindGivenItemofTypedwithMultiplepathsContainingSameFileName(){
    ArrayList<String> paths = new ArrayList<String>();
    paths.add("Valid path for parent dir");
    paths.add("Valid path for grandparent dir");
    
    String actual = this.findCommand.findGivenItemofTyped(paths, 
        "\"Labs\"", dirTree, dirTree.CSCB07);
    
    String expected = 
        "Valid path for parent dir:\n" + 
        "Labs\n\n" + 
        "Valid path for grandparent dir:\n" + 
        "Labs";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testisValidonValidArgs() {
    String[] args = {"some paths","-type","f","-name "+"\"expression\""};
    List<String> arguments = Arrays.asList(args);
    
    assertTrue(this.findCommand.isValid(arguments));
    
  }
  
  @Test
  public void testisValidonInValidArgs() {
    String[] args = {"some paths","f","-type "+"\"expression\""};
    List<String> arguments = Arrays.asList(args);
    
    assertTrue(!this.findCommand.isValid(arguments));
    
  }
  
  
  @Test
  public void testExecuteCommand() {
    IDirectory testDir = new MockDirectory("dir", dirTree.getRootDirectory());
    String[] arguments = {"some paths","-type"+"f"+"-name"+"\"lecture1\""};
    // last 3 parameters do not affect the output
    IDirectory actual =
        this.findCommand.executeCommand(arguments, 5, testDir, dirTree,
            null, null, null);
    assertEquals(testDir, actual);
  }
  @After
  public void tearDown() {

  }
}
