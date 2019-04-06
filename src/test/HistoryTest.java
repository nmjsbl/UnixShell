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
import main.History;
import main.IDirectory;

public class HistoryTest {
  History history;
  MockDirectoryTree dirTree;

  @Before
  public void setUp() {
    history = new History();
    dirTree = new MockDirectoryTree();
  }

  @Test
  public void testGetHistoryWhenNoCommandsEntered() {
    String actual = history.getHistory("5");
    assertEquals("", actual);
  }

  @Test
  public void testGetHistoryWhenSomeCommandsEntered() {
    // add commands to history
    history.addCommand("fjslsf");
    history.addCommand("history");
    history.addCommand("mkdir hello");
    String actual = history.getHistory("5");
    String expected = "1. fjslsf\n2. history\n3. mkdir hello\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testGetTotalNumberOfCommandsEnteredInitially() {
    int actual = history.getTotalNumberOfCommandsEntered();
    assertEquals(0, actual);
  }

  @Test
  public void testGetTotalNumberOfCommandsEntered() {
    // Add some commands
    history.addCommand("hello");
    history.addCommand("man");
    history.addCommand("history");
    history.addCommand("hi");
    int actual = history.getTotalNumberOfCommandsEntered();
    assertEquals(4, actual);
  }

  @Test
  public void testExecuteCommand() {
    IDirectory testDir = new MockDirectory("dir", dirTree.getRootDirectory());
    String[] arguments = {"history"};
    // last 3 parameters do not affect the output
    IDirectory actual = history.executeCommand(arguments, 0, testDir, dirTree,
        null, null, null);
    assertEquals(testDir, actual);
  }
}
