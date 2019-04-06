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
import static org.junit.Assert.assertNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryStack;
import main.IDirectory;
import main.Popd;
import main.Pushd;

public class PopdTest {
  MockDirectoryTree dirTree;
  DirectoryStack dirStack;
  Pushd pushdCommand;
  Popd popdCommand;
  IDirectory currentDir;

  @Before
  public void SetUp() {
    dirTree = new MockDirectoryTree();
    dirStack = DirectoryStack.createDirectoryStackInstance();
    popdCommand = new Popd();
    currentDir = dirTree.getRootDirectory();
  }

  @Test
  public void popDirectoryonEmptyStacktest() {
    IDirectory result = popdCommand.popDirectory(dirStack);
    assertNull("Stack is Empty", result);
  }

  @Test
  public void popDirectoryonNonEmptyStacktest() {
    dirStack.pushDirectory((IDirectory) currentDir);
    IDirectory result = popdCommand.popDirectory(dirStack);
    assertEquals(result, currentDir);
  }

  @Test
  public void testExecuteCommand() {
    String[] arguments = {};
    pushdCommand = new Pushd();
    // last 3 parameters do not affect the output
    MockDirectory actual = (MockDirectory) popdCommand.executeCommand(arguments,
        0, currentDir, dirTree, null, pushdCommand, null);
    assertEquals(currentDir, actual);
  }

  @After
  public void resetStack() {
    dirStack = null;
  }

}
