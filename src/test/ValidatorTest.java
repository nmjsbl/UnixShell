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
import org.junit.Test;
import main.Validator;

public class ValidatorTest {

  @Test
  public void testValidateDirectoryOrFileNameInvalid() {
    String invalidName = "434-*lf.fsd";
    assertEquals(false, Validator.validateDirectoryOrFileName(invalidName));
  }

  @Test
  public void testValidateDirectoryOrFileNameValid() {
    String validName = "fileName_dirName";
    assertEquals(true, Validator.validateDirectoryOrFileName(validName));
  }

  @Test
  public void testValidateStringArgumentsValid() {
    String validString = "\"kfsdl\"";
    assertEquals(true, Validator.validateStringArgument(validString));
  }

  @Test
  public void testValidateStringArgumentsInValid() {
    String invalidString = "\"kfsdl";
    assertEquals(false, Validator.validateStringArgument(invalidString));
  }
}
