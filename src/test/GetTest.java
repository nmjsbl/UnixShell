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
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.FileSystem;
import main.IDirectory;
import main.IFile;

public class GetTest {
  MockGet get;
  MockDirectoryTree dirTree;
  MockDirectory currentDir;
  String expectedURLContent =
      "<html><body>This is a content of the URL</body></html>";
  String url = "https://crunchify.com/wp-content/uploads/code/sample.txt";
  String urlFileName = "sample";

  @Before
  public void setUp() {
    // Create a Get object and FileSystem
    get = new MockGet();
    dirTree = new MockDirectoryTree();
    currentDir = (MockDirectory) dirTree.getRootDirectory();
  }

  @Test
  public void testExecuteCommand() {
    IDirectory tempDir = get.executeCommand(new String[] {}, 0,
        (IDirectory) currentDir, (FileSystem) dirTree, null, null, null);
    assertEquals(currentDir, (MockDirectory) tempDir);
  }

  @Test
  public void getURLArgument() {
    String actualURLContent = get.getURLArgument(url);
    assertEquals(expectedURLContent, actualURLContent);
  }

  @Test
  public void testSaveURLToFile() {
    get.saveURLToFile(expectedURLContent, urlFileName, currentDir);
    ArrayList<IFile> files = currentDir.getFiles();
    IFile file = files.get(files.size() - 1);
    String actualURLContent = file.getData();
    assertEquals(expectedURLContent, actualURLContent);
  }

  @Test
  public void testGetURLFileName() {
    String actualFileName = get.getURLFileName(url);
    assertEquals(urlFileName, actualFileName);
  }

  @Test
  public void testCheckValidURLFileName() {
    boolean actualValue = get.checkValidURLFileName(urlFileName);
    boolean expectedValue = true;
    assertEquals(expectedValue, actualValue);
  }


}
