package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author CS 2420 course staff and Benjamin Faerber and David Chen
 * @version 2025-05-15
 */
public class CS2420ClassTest {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass;

	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();

		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass
				.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");

		largeClass = new CS2420Class();
		for (int i = 0; i < 100; i++) {
			String fakeFirstName = "Joe" + i;
			String fakeLastName = "Smith" + i;
			String fakeEmailDomain = "gmail" + i + ".com";

			EmailAddress fakeContactInfo = new EmailAddress(fakeFirstName + fakeLastName, fakeEmailDomain);

			CS2420Student student = new CS2420Student(fakeFirstName, fakeLastName, i * 1000, fakeContactInfo);
			student.addScore(i, "exam");
			student.addScore(i, "lab");
			student.addScore(i, "quiz");
			student.addScore(i, "assignment");

			largeClass.addStudent(student);
		}

		// Add a fake duplicate
		EmailAddress fakeDupEmail = new EmailAddress("Joe10Smith10", "gmail10.com");
		largeClass.addStudent(new CS2420Student("Sally", "Smith", 12342234, fakeDupEmail));
	}

	// Empty CS 2420 class tests
	// --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}

	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}

	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}

	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests
	// --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass
				.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}

	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass
				.addStudent(new CS2420Student("Jane", "Doe", 1010100, new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}

	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(72.59625, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B-", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");
		assertEquals(77.09625, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}

	// Small CS 2420 class tests
	// -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}

	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.58272, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(77.4857, smallClass.computeClassAverage(), 0.001);
	}

	// Large CS 2420 class tests
	// -------------------------------------------------------------------------

	@Test
	public void testLargeLookupContactInfo() {

		UofUStudent expectedStudent1 = new UofUStudent("Joe10", "Smith10", 10 * 1000);
		UofUStudent expectedStudent2 = new UofUStudent("Joe58", "Smith58", 58 * 1000);

		UofUStudent expectedSally = new UofUStudent("Sally", "Smith", 12342234);

		// With the fake duplicate we added for Joe10 and Sally using the same email
		ArrayList<CS2420Student> actualStudentsWithDup = largeClass
				.lookup(new EmailAddress("Joe10Smith10", "gmail10.com"));
		assertEquals(2, actualStudentsWithDup.size());

		// For Joe 58
		ArrayList<CS2420Student> actualStudentWithoutDup = largeClass
				.lookup(new EmailAddress("Joe58Smith58", "gmail58.com"));
		assertEquals(1, actualStudentWithoutDup.size());

		assertTrue(actualStudentsWithDup.contains(expectedStudent1));
		assertTrue(actualStudentsWithDup.contains(expectedSally));

		assertTrue(actualStudentWithoutDup.contains(expectedStudent2));
	}

	@Test
	public void testLargeGetContactList() {
		ArrayList<EmailAddress> actual = largeClass.getContactList();
		assertEquals(100, actual.size());
	}

	@Test
	public void testLargeStudentFinalScore() {
		CS2420Student student = largeClass.lookup(58_000);	
		assertEquals(58.0, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testLargeComputeClassAverage() {
		assertEquals(49.0, largeClass.computeClassAverage(), 0.1);
	}
	
	@Test
	public void testLargeComputeFinalGrade() {
		CS2420Student student = largeClass.lookup(58 * 1000);
		student.addScore(58.0, "assignment");
		student.addScore(58.0, "exam");
		student.addScore(58.0, "lab");
		student.addScore(58.0, "quiz");

		assertEquals("E", student.computeFinalGrade());
	}
}
