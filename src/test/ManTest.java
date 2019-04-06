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
import main.Man;

public class ManTest {
  Man man;
  MockDirectoryTree dirTree;

  @Before
  public void setUp() {
    // Create a File System and a directory Home

    man = new Man();
    dirTree = new MockDirectoryTree();
  }

  @Test
  public void testPrintDocumentation1() {
    String expected = "";
    String actual = man.printDocumentation("this");
    assertEquals(expected, actual);
  }
  @Test
  public void testPrintDocumentation2() {
    String expected = "\nexit CMD \n Exits the program\n";
    String actual = man.printDocumentation("exit");
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteCommand() {
    IDirectory testDir = new MockDirectory("dir", dirTree.getRootDirectory());
    String[] arguments = {"history"};
    // last 3 parameters do not affect the output
    IDirectory actual =
        man.executeCommand(arguments, 0, testDir, dirTree, null, null, null);
    assertEquals(testDir, actual);
  }

}
