package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryTreeNode;
import main.Exit;
import main.IDirectory;

public class ExitTest {
  private MockDirectoryTree<DirectoryTreeNode> dirTree;
  private Exit exit;
  @Before
  public void setUp(){
    exit = new Exit();
    dirTree = new MockDirectoryTree<DirectoryTreeNode>();
  }

  @Test
  public void testExecuteCommand() {
    IDirectory testDir = new MockDirectory("dir", dirTree.getRootDirectory());
    String[] arguments = {};
    // last 3 parameters do not affect the output
    IDirectory actual =
        this.exit.executeCommand(arguments, 0, testDir, dirTree,
            null, null, null);
    assertEquals(testDir, actual);
  }

  @After
  public void tearDown() throws Exception {}
}
