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
}
