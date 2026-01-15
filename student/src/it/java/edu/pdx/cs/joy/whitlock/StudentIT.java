package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.InvokeMainTestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
class StudentIT extends InvokeMainTestCase {

  @Test
  void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing required student information"));
  }

  @Disabled
  @Test
  void daveStudentIsWrittenToStandardOut() {
    InvokeMainTestCase.MainMethodResult result =
      invokeMain(Student.class, "Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java.  He says \"This class is too much work\"."));
  }

  @Test
  void studentNameIsWrittenToStandardOut() {
    String name = "Student";
    InvokeMainTestCase.MainMethodResult result =
      invokeMain(Student.class, name);
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
    assertThat(result.getTextWrittenToStandardOut(), containsString(name));
  }

  @Test
  void studentGPAIsWrittenToStandardOut() {
    String gpa = "3.4";
    InvokeMainTestCase.MainMethodResult result =
      invokeMain(Student.class, "Any name", gpa);
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
    assertThat(result.getTextWrittenToStandardOut(), containsString(" has a GPA of " + gpa));
  }

  @Test
  void invalidGPAIssuesErrorToStandardError() {
    String invalidGPA = "NotAGPA";
    InvokeMainTestCase.MainMethodResult result =
      invokeMain(Student.class, "Any name", invalidGPA);
    assertThat(result.getTextWrittenToStandardError(), containsString("Invalid GPA: \"" + invalidGPA + "\""));
  }
}
