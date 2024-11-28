package gradeformatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class StudentTest {

    @Test
    void testAddCourse() {
        Student student = new Student("123456789", "John Hay");
        Course course = new Course("CP460", 60.5, 70.6, 80.6, 80.6);
        student.addCourse(course);

        List<Course> courses = student.getCourses();
        assertEquals(1, courses.size());
        assertEquals("CP460", courses.get(0).getCourseCode());
    }

    @Test
    void testValidStudent() {
        Student student = new Student("123456789", "Jane Doe");
        assertEquals("123456789", student.getStudentId());
        assertEquals("Jane Doe", student.getName());
    }

    @Test
    void testInvalidStudentId_TooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Student("12345", "John Hay")
        );
        assertEquals("Student ID must be a 9-digit number.", exception.getMessage());
    }

    @Test
    void testInvalidStudentId_Negative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Student("-123456789", "John Hay")
        );
        assertEquals("Student ID cannot be negative.", exception.getMessage());
    }

    @Test
    void testInvalidStudentId_NonNumeric() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Student("12345ABCD", "John Hay")
        );
        assertEquals("Student ID must be a 9-digit number.", exception.getMessage());
    }

    @Test
    void testInvalidStudentName_WithNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Student("123456789", "John123")
        );
        assertEquals("Student name cannot contain numbers.", exception.getMessage());
    }

    @Test
    void testInvalidStudentName_Empty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Student("123456789", "")
        );
        assertEquals("Student name cannot be empty.", exception.getMessage());
    }
}
