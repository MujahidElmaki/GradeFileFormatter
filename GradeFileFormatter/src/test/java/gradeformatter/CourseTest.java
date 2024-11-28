package gradeformatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testValidCourseAndGrades() {
        Course course = new Course("CP460", 60.5, 70.6, 80.6, 80.6);
        double expectedGrade = (60.5 * 0.2) + (70.6 * 0.2) + (80.6 * 0.2) + (80.6 * 0.4);
        assertEquals(expectedGrade, course.calculateFinalGrade(), 0.1);
    }

    @Test
    void testGradesOver100() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Course("CP460", 120.0, 85.0, 95.0, 90.0)
        );
        assertTrue(exception.getMessage().contains("Grades cannot exceed 100"));
    }

    @Test
    void testNegativeGrades() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Course("CP460", -10.0, 70.0, 80.0, 90.0)
        );
        assertTrue(exception.getMessage().contains("Grades cannot be negative"));
    }

    @Test
    void testInvalidCourseCode_Empty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Course("", 60.0, 70.0, 80.0, 90.0)
        );
        assertEquals("Course code cannot be empty or null.", exception.getMessage());
    }

    @Test
    void testInvalidCourseCode_Format() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            new Course("INVALID", 60.0, 70.0, 80.0, 90.0)
        );
        assertTrue(exception.getMessage().contains("Invalid course code format"));
    }
}
