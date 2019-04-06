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
import main.IDirectory;
import main.DirectoryTreeNode;
import main.FileSystem;
import main.IFile;
import main.Mv;

public class MvTest {
  Mv mv;
  MockDirectoryTree<DirectoryTreeNode> dirTree;
  IDirectory testD;

  @Before
  public void setUp() {
    mv = new Mv();
    dirTree = new MockDirectoryTree<DirectoryTreeNode>();
    IDirectory testD = new MockDirectory("test", dirTree.getRootDirectory());
    dirTree.lecture1.overwriteData("hello"); 
    dirTree.lecture3.overwriteData("goodbye"); 
  }

  @Test
  public void testExecuteCommand() {
    String[] arguments = {"history"};
    // last 3 parameters do not affect the output
    IDirectory actual =
        mv.executeCommand(arguments, 0, testD, dirTree, null, null, null);
    assertEquals(testD, actual);
  }
  
  @Test
  public void testmoveFileGivenPathFor2Files() {
	  String oldpath = "Valid path for lecture 1 file"; // lec1
	  String newpath = "Valid path for lecture 3 file"; //lec3
	  mv.moveFileGivenPath(dirTree,dirTree.CSCB07, oldpath,newpath);
	  String expected = "hello";
	  String result = dirTree.lecture3.getData();
	  assertEquals(expected,result);
  }
 
  @Test
  public void testmoveFileGivenPathForFileAndDirectory() {
	  String newpath = "Valid path for Labs dir";
	  String oldpath = "Valid path for lecture 1 file";
	  mv.moveFileGivenPath(dirTree,dirTree.CSCB07, oldpath,newpath);
	  IDirectory expected = dirTree.Labs;
	  MockFile lecture1mock = (MockFile)dirTree.lecture1;
	  IDirectory result = lecture1mock.getParentDirectory();
	  assertEquals(expected, result);
  }
  
  @Test
  public void testmoveDirectoryGivenPathFor2Directories() {
	  String oldpath = "Valid path for CSCA08 dir";
	  String newpath = "Valid path for parent dir";
	  mv.moveDirectoryGivenPath(dirTree,dirTree.CSCB07, oldpath,newpath);
	  IDirectory expected = dirTree.CSCB07;
	  IDirectory result = dirTree.CSCA08.getParentDirectory();
	  assertEquals(expected, result);
  }
  
  @Test
  public void testmoveFileToDirectoryGivenFile() {
	  mv.moveFileToDirectoryGivenFile(dirTree, dirTree.lecture1,dirTree.Labs);
	  IDirectory expected = dirTree.Labs;
	  MockFile lecture1mock = (MockFile)dirTree.lecture1;
	  IDirectory result = lecture1mock.getParentDirectory();
	  assertEquals(expected, result);
  }
  
  @Test
  public void testmoveDirectoryGivenDirectory() {
	  mv.moveDirectoryGivenDirectory(dirTree,dirTree.CSCA08,dirTree.CSCB07);
	  IDirectory expected = dirTree.CSCB07;
	  IDirectory result = dirTree.CSCA08.getParentDirectory();
	  assertEquals(expected, result);
  }
}
