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
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.DirectoryStack;
import main.Echo;
import main.Pushd;

public class EchoTest {

  Echo echo;
  MockDirectoryTree dirTree;
  MockDirectory currentDir;
  String[] arguments1 = {"\"Overwrite", "file", "here\"", ">", "savedFile"};
  String[] arguments2 = {"\"Overwrite", "file", "here\"", ">>", "savedFile"};
  String expectedString = "Overwrite file here";
  String expectedOverwrite = ">";
  String expectedAppend = ">>";
  String expectedFile = "savedFile";
  
  @Before
  public void SetUp() {
    echo = new Echo();
    dirTree = new MockDirectoryTree();
    currentDir = (MockDirectory) dirTree.getRootDirectory();
  }

  @Test
  public void testCheckRedirectArguments1(){
    ArrayList expectedList = echo.checkRedirectArguments(arguments1);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedString, actual);
  }
  
  @Test
  public void testCheckRedirectArguments2(){
    ArrayList expectedList = echo.checkRedirectArguments(arguments1);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    String expected = ">";
    assertEquals(expectedOverwrite, actual);
  }
  
  @Test
  public void testCheckRedirectArguments3(){
    ArrayList expectedList = echo.checkRedirectArguments(arguments1);
    expectedList.remove(0);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedFile, actual);
  }
  
  @Test
  public void testCheckRedirectArguments4(){
    ArrayList expectedList = echo.checkRedirectArguments(arguments2);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedString, actual);
  }
  
  @Test
  public void testCheckRedirectArguments5(){
    ArrayList expectedList = echo.checkRedirectArguments(arguments2);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    String expected = ">>";
    assertEquals(expectedAppend, actual);
  }
  
  @Test
  public void testCheckRedirectArguments6(){
    ArrayList expectedList = echo.checkRedirectArguments(arguments2);
    expectedList.remove(0);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedFile, actual);
  }
  
  @Test
  public void testCheckRedirectArguments7(){
    String[] arguments = {"\"Overwrite", "file\""};
    ArrayList expectedList = echo.checkRedirectArguments(arguments);    
    String actual = (String) expectedList.remove(0);
    String expected = "Overwrite file";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testCheckRedirectArguments8(){
    String[] arguments = {};
    ArrayList expectedList = echo.checkRedirectArguments(arguments);
    boolean actual = (boolean) expectedList.remove(0);
    boolean expected = false;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testCheckRedirectArguments9(){
    String[] arguments = {">", "savedFile"};
    ArrayList expectedList = echo.checkRedirectArguments(arguments);
    boolean actual = (boolean) expectedList.remove(0);
    boolean expected = false;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetEchoArguments1(){
    ArrayList expectedList = echo.getEchoArguments(arguments1);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedString, actual);
  }
  
  @Test
  public void testGetEchoArguments2(){
    ArrayList expectedList = echo.getEchoArguments(arguments1);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedOverwrite, actual);
  }
  
  @Test
  public void testGetEchoArguments3(){
    ArrayList expectedList = echo.getEchoArguments(arguments1);
    expectedList.remove(0);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedFile, actual);
  }
  
  @Test
  public void testGetEchoArguments4(){
    String[] arguments = {"\"\""};
    ArrayList expectedList = echo.getEchoArguments(arguments);    
    String actual = (String) expectedList.remove(0);
    String expected = "";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetEchoArguments5(){
    String[] arguments = {};
    ArrayList expectedList = echo.getEchoArguments(arguments);
    boolean actual = (boolean) expectedList.remove(0);
    boolean expected = false;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetGiven3Arguments1(){
    ArrayList expectedList = echo.getGiven3Arguments(arguments1, 3);
    String actual = (String) expectedList.remove(0);
    String expected = "Overwrite file here";
    assertEquals(expectedString, actual);
  }
  
  @Test
  public void testGetGiven3Arguments2(){
    ArrayList expectedList = echo.getGiven3Arguments(arguments1, 3);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedOverwrite, actual);
  }
  
  @Test
  public void testGetGiven3Arguments3(){
    ArrayList expectedList = echo.getGiven3Arguments(arguments1, 3);
    expectedList.remove(0);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedFile, actual);
  }
  
  @Test
  public void testGetStringArgument1(){
    ArrayList expectedList = echo.getStringArgument(arguments1, 3);
    boolean actual = (boolean) expectedList.remove(0);
    assertEquals(true, actual);
  }
  
  @Test
  public void testGetStringArgument2(){
    ArrayList expectedList = echo.getStringArgument(arguments1, 3);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    assertEquals(expectedString, actual);
  }
  
  @Test
  public void testGetStringArgument3(){
    String[] arguments = {"\"\""};
    ArrayList expectedList = echo.getStringArgument(arguments, 1);
    expectedList.remove(0);
    String actual = (String) expectedList.remove(0);
    String expected = "";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testEecuteCommand(){
    MockDirectory expectedDir = (MockDirectory) echo.executeCommand(arguments1,
        5, currentDir, dirTree, null, null, null);
    assertEquals(expectedDir, currentDir);
  }
  

}
