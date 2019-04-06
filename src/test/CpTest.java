package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.Cp;
import main.DirectoryTreeNode;
import main.IDirectory;

public class CpTest {
  Cp cp;
  MockDirectoryTree dirTree;

  @Before
  public void setUp() {
    cp = new Cp();
    dirTree = new MockDirectoryTree();
  }

  @Test
  public void testCopyFileGivenPath1() {
    IDirectory parent = dirTree.getRootDirectory();
    String oldPath = "Valid path for schedule file";
    String newPath = "Valid path for lecture 1 file";
    cp.copyFileGivenPath(dirTree, parent, oldPath, newPath);
    String expected = dirTree.getContentsOfFile(parent, oldPath);
    String actual = dirTree.getContentsOfFile(parent, newPath);
    assertEquals(expected, actual);
  }

  @Test
  public void testCopyFileGivenPath2() {
    IDirectory parent = dirTree.getRootDirectory();
    String oldPath = "Valid path for lecture 2 file";
    String newPath = "Valid path for grandparent dir";
    cp.copyFileGivenPath(dirTree, parent, oldPath, newPath);
    assertTrue(dirTree.getItem("lecture 2", dirTree.Documents) != null);
  }

  @Test
  public void testCopyFileGivenPath3() {
    IDirectory parent = dirTree.getRootDirectory();
    String oldPath = "Valid path for lecture 1 file";
    String newPath = "Invalid path for grandparent dir";
    cp.copyFileGivenPath(dirTree, parent, oldPath, newPath);
    assertTrue(dirTree.getItem("invalid lecture 1", dirTree.Documents) == null);
  }

  @Test
  public void testCopyDirectoryGivenPath1() {
    IDirectory parent = dirTree.getRootDirectory();
    String oldPath = "Valid path for Labs dir";
    String newPath = "Valid path for grandparent dir";
    cp.copyDirectoryGivenPath(dirTree, parent, oldPath, newPath);
    assertTrue(dirTree.getItem("Labs", dirTree.Documents) != null);
  }

  @Test
  public void testCopyDirectoryGivenPath2() {
    IDirectory parent = dirTree.getRootDirectory();
    String oldPath = "Valid path for Labs dir";
    String newPath = "Invalid path for grandparent dir";
    ArrayList<DirectoryTreeNode> expected = dirTree.Documents.getContents();
    cp.copyDirectoryGivenPath(dirTree, parent, oldPath, newPath);
    // Shouldn't be added so the contents must remain the same
    assertEquals(expected, dirTree.Documents.getContents());
  }

  @Test
  public void testCopyFileGivenFile1() {
    cp.copyFileGivenFile(dirTree, (DirectoryTreeNode) dirTree.schedule,
        dirTree.CSCA08);
    assertTrue(dirTree.getItem("schedule", dirTree.CSCA08) != null);
  }

  @Test
  public void testCopyFileGivenFile2() {
    cp.copyFileGivenFile(dirTree, (DirectoryTreeNode) dirTree.lecture2,
        dirTree.CSCB07);
    assertTrue(dirTree.getItem("lecture2", dirTree.CSCB07) != null);
  }

  @Test
  public void testCopyFileGivenFile3() {
    cp.copyFileGivenFile(dirTree, (DirectoryTreeNode) dirTree.lecture1,
        dirTree.C);
    assertTrue(dirTree.getItem("lecture1", dirTree.C) != null);
  }

  @Test
  public void testCopyDirectoryGivenDirectory1() {
    cp.copyDirectoryGivenDirectory(dirTree, dirTree.Labs, dirTree.Documents);
    assertTrue(dirTree.getItem("Labs", dirTree.Documents) != null);
  }

  @Test
  public void testCopyDirectoryGivenDirectory2() {
    cp.copyDirectoryGivenDirectory(dirTree, dirTree.CSCB07, dirTree.Labs);
    assertTrue(dirTree.getItem("CSCB07", dirTree.Labs) == null);
  }

  @Test
  public void testCopyDirectoryGivenDirectory3() {
    cp.copyDirectoryGivenDirectory(dirTree, dirTree.rootDir, dirTree.C);
    assertTrue(dirTree.getItem("/", dirTree.C) == null);
  }

  @Test
  public void testExecuteCommand() {
    IDirectory testDir = new MockDirectory("dir", dirTree.getRootDirectory());
    String[] arguments = {"history"};
    // last 3 parameters do not affect the output
    IDirectory actual =
        cp.executeCommand(arguments, 0, testDir, dirTree, null, null, null);
    assertEquals(testDir, actual);
  }
}
