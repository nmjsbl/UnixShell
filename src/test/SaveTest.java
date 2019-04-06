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
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryTree;
import main.IDirectory;
import main.Man;
import main.Save;

public class SaveTest {
  
  Save save;
  MockDirectoryTree dirTree;
  MockDirectory currentDir;
  String fileName1 = "savedFile.txt";
  String fileName2 = "savedShell.txt";
  
  @Before
  public void setUp() {
    // Create a File System and a directory Home

    save = new Save();
    dirTree = new MockDirectoryTree();
    currentDir = (MockDirectory) dirTree.getRootDirectory();
  }
  
  @Test
  public void testSaveState1() {
    boolean actualValue = false;
    boolean expectedValue = true;
    save.saveState(dirTree, currentDir, fileName1, null, null, null);
    File file = new File(fileName1);
    if(file.exists() && file.isFile())
      actualValue = true;
    assertEquals(expectedValue, actualValue);
  }
  
  @Test
  public void testSaveState2() {
    boolean actualValue = false;
    boolean expectedValue = true;
    save.saveState(dirTree, currentDir, fileName2, null, null, null);
    File file = new File(fileName2);
    if(file.exists() && file.isFile())
      actualValue = true;
    assertEquals(expectedValue, actualValue);
  }

  
  @Test
  public void testExecuteCommand() {
    String[] arguments = new String[]{fileName1};
    MockDirectory actualDir = (MockDirectory) save.executeCommand(arguments, 1,
        currentDir, dirTree, null, null, null);
    assertEquals(currentDir, actualDir);
  }

}
