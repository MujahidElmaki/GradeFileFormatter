package gradeformatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

class FileProcessorTest {

    @Test
    void testReadStudents() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        String studentFile = "test/NameFileTest.txt";

        // Create a temporary test file
        Files.writeString(Path.of(studentFile), "123456789,John Hay\n223456789,Mary Smith");

        List<Student> students = fileProcessor.readStudents(studentFile);
        assertEquals(2, students.size());
        assertEquals("John Hay", students.get(0).getName());

        // Clean up
        Files.delete(Path.of(studentFile));
    }

    @Test
    void testWriteOutput() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        String outputFile = "test/FormattedOutputTest.txt";

        Student student = new Student("123456789", "John Hay");
        student.addCourse(new Course("CP460", 60.5, 70.6, 80.6, 80.6));
        List<Student> students = List.of(student);

        fileProcessor.writeOutput(outputFile, students);

        String content = Files.readString(Path.of(outputFile));
        assertTrue(content.contains("123456789,John Hay,CP460,66.7"));

        // Clean up
        Files.delete(Path.of(outputFile));
    }
}
