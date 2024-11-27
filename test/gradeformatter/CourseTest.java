package gradeformatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testCalculateFinalGrade() {
        Course course = new Course("CP460", 60.5, 70.6, 80.6, 80.6);
        double expectedGrade = (60.5 * 0.2) + (70.6 * 0.2) + (80.6 * 0.2) + (80.6 * 0.4);
        assertEquals(expectedGrade, course.calculateFinalGrade(), 0.1);
    }
}
