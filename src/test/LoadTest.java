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
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryStack;
import main.History;
import main.IDirectory;
import main.Load;
import main.Popd;
import main.Pushd;
import main.Save;

public class LoadTest {
  
  Load load;
  MockDirectoryTree dirTree;
  MockDirectory currentDir;
  String fileName = "savedFile.txt";
  History history;
  Pushd pushd;
  Popd popd;
  DirectoryStack dirStack;
  @Before
  public void setUp() {
    // Create a File System and a directory Home

    load = new Load();
    dirTree = new MockDirectoryTree();
    currentDir = (MockDirectory) dirTree.getRootDirectory();
    history = new History();
    history.addCommand("man ls");
    pushd = new Pushd();
    dirStack = pushd.getDirStack();
    popd = new Popd();
  }
  
  @Test
  public void testLoadState1() {
    Save save = new Save();
    save.saveState(dirTree, currentDir, fileName, null, null, null);
    ArrayList returnArray = load.loadState(dirTree, currentDir,
        fileName, history, pushd, popd);
    MockDirectoryTree actualDirTree = (MockDirectoryTree) returnArray.remove(0);
    MockDirectory actualDir = (MockDirectory) returnArray.remove(0);
    History actualHistory = (History) returnArray.remove(0);
    Pushd actualPushd = (Pushd) returnArray.remove(0);
    Popd actualPopd = (Popd) returnArray.remove(0);
    String expectedOutput = dirTree.getPath(dirTree.getRootDirectory());
    String actualOutput =
        actualDirTree.getPath(actualDirTree.getRootDirectory());
    assertEquals(expectedOutput, actualOutput);
  }
  
  @Test
  public void testLoadState2() {
    Save save = new Save();
    save.saveState(dirTree, currentDir, fileName, null, null, null);
    ArrayList returnArray = load.loadState(dirTree, currentDir,
        fileName, history, pushd, popd);
    returnArray.remove(0);
    MockDirectory actualDir = (MockDirectory) returnArray.remove(0);
    String expectedOutput = currentDir.getName();
    String actualOutput = actualDir.getName();
    assertEquals(expectedOutput, actualOutput);
  }
  
  @Test
  public void testLoadState3() {
    Save save = new Save();
    save.saveState(dirTree, currentDir, fileName, null, null, null);
    ArrayList returnArray = load.loadState(dirTree, currentDir,
        fileName, history, pushd, popd);
    returnArray.remove(0);
    returnArray.remove(0);
    History actualHistory = (History) returnArray.remove(0);
    String expectedOutput = history.getHistory("1");
    String actualOutput = actualHistory.getHistory("1");
    assertEquals(expectedOutput, actualOutput);
  }
  
  @Test
  public void testLoadState4() {
    Save save = new Save();
    save.saveState(dirTree, currentDir, fileName, null, null, null);
    ArrayList returnArray = load.loadState(dirTree, currentDir,
        fileName, history, pushd, popd);
    returnArray.remove(0);
    returnArray.remove(0);
    returnArray.remove(0);
    Pushd actualPushd = (Pushd) returnArray.remove(0);
    int expectedOutput = dirStack.getStackOfDir().size();
    int actualOutput = actualPushd.getDirStack().getStackOfDir().size();
    assertEquals(expectedOutput, actualOutput);
  }
  
  @Test
  public void testLoadState5() {
    Save save = new Save();
    save.saveState(dirTree, currentDir, fileName, null, null, null);
    ArrayList returnArray = load.loadState(dirTree, currentDir,
        fileName, history, pushd, popd);
    returnArray.remove(0);
    returnArray.remove(0);
    returnArray.remove(0);
    returnArray.remove(0);
    Popd actualPopd = (Popd) returnArray.remove(0);
    MockDirectory actualOutput = (MockDirectory) popd.popDirectory(dirStack);
    assertNull(actualOutput);
  }

  @Test
  public void testExecuteCommand() {
    String[] arguments = new String[]{fileName};
    MockDirectory actualDir = (MockDirectory) load.executeCommand(arguments, 1,
        currentDir, dirTree, null, null, null);
    assertEquals(currentDir, actualDir);
  }
}
