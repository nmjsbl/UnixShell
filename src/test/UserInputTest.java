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
import java.util.Hashtable;
import org.junit.Before;
import org.junit.Test;
import main.History;
import main.UserInput;

public class UserInputTest {

  UserInput userInput;
  History history;
  String actual;
  String expected;
  
  @Before
  public void setUp() {
    userInput = new UserInput();
    history = new History();
  }
  
  @Test
  public void testInitializeHashTable1() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("exit");
    expected = "Exit";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable2() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("man");
    expected = "Man";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable3() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("cat");
    expected = "Cat";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable4() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("cd");
    expected = "Cd";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable5() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("history");
    expected = "History";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable6() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("mkdir");
    expected = "Mkdir";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable7() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("popd");
    expected = "Popd";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable8() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("pushd");
    expected = "Pushd";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable9() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("pwd");
    expected = "Pwd";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable10() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("ls");
    expected = "Ls";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable11() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("echo");
    expected = "Echo";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable12() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("get");
    expected = "Get";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable13() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("mv");
    expected = "Mv";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable14() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("cp");
    expected = "Cp";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable15() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("tree");
    expected = "Tree";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable16() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("save");
    expected = "Save";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable17() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("load");
    expected = "Load";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable18() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    actual = expectedHash.get("find");
    expected = "Find";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable19() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    boolean actual = expectedHash.contains("Command");
    boolean expected = false;
    assertEquals(expected, actual);
  }
  
  @Test
  public void testInitializeHashTable20() {
    Hashtable<String, String> expectedHash = userInput.initializeHashTable();
    boolean actual = expectedHash.contains("User");
    boolean expected = false;
    assertEquals(expected, actual);
  }
}

















