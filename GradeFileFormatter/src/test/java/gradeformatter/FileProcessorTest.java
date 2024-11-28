package gradeformatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class FileProcessorTest {

    @Test
    void testReadStudents_NoDuplicates() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        Path testDir = Paths.get("test");

        if (!Files.exists(testDir)) {
            Files.createDirectories(testDir);
        }

        Path studentFile = testDir.resolve("NameFileTest.txt");
        Files.writeString(studentFile, "123456789,John Hay\n123456789,John Hay\n223456789,Mary Smith");

        List<Student> students = fileProcessor.readStudents(studentFile.toString());
        assertEquals(2, students.size()); // Duplicate should not be added
        assertEquals("John Hay", students.get(0).getName());
        assertEquals("Mary Smith", students.get(1).getName());

        Files.delete(studentFile);
    }

    @Test
    void testReadCourses_NoDuplicateCourses() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        Path testDir = Paths.get("test");

        if (!Files.exists(testDir)) {
            Files.createDirectories(testDir);
        }

        Path studentFile = testDir.resolve("NameFileTest.txt");
        Files.writeString(studentFile, "123456789,John Hay");

        Path courseFile = testDir.resolve("CourseFileTest.txt");
        Files.writeString(courseFile, "123456789,CP460,60,70,80,90\n123456789,CP460,60,70,80,90");

        List<Student> students = fileProcessor.readStudents(studentFile.toString());
        fileProcessor.readCourses(courseFile.toString(), students);

        assertEquals(1, students.get(0).getCourses().size()); // No duplicate courses
        assertEquals("CP460", students.get(0).getCourses().get(0).getCourseCode());

        Files.delete(studentFile);
        Files.delete(courseFile);
    }

    @Test
    void testReadCourses_InvalidData() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        Path testDir = Paths.get("test");

        if (!Files.exists(testDir)) {
            Files.createDirectories(testDir);
        }

        Path studentFile = testDir.resolve("NameFileTest.txt");
        Files.writeString(studentFile, "123456789,John Hay");

        Path courseFile = testDir.resolve("InvalidCourseFileTest.txt");
        Files.writeString(courseFile, "123456789,CP460,INVALID,70,80,90");

        List<Student> students = fileProcessor.readStudents(studentFile.toString());

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            fileProcessor.readCourses(courseFile.toString(), students);
        });

        assertTrue(exception.getMessage().contains("For input string: \"INVALID\""));

        Files.delete(studentFile);
        Files.delete(courseFile);
    }

    @Test
    void testWriteOutput_CorrectFormatting() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        Path testDir = Paths.get("test");

        if (!Files.exists(testDir)) {
            Files.createDirectories(testDir);
        }

        Path outputFile = testDir.resolve("FormattedOutputTest.txt");
        Student student = new Student("123456789", "John Hay");
        student.addCourse(new Course("CP460", 60, 70, 80, 90));
        List<Student> students = List.of(student);

        fileProcessor.writeOutput(outputFile.toString(), students);
        String content = Files.readString(outputFile);

        assertEquals("123456789, John Hay, CP460, 78.0\n", content);

        Files.delete(outputFile);
    }

    @Test
    void testReadStudents_InvalidFileFormat() throws Exception {
        FileProcessor fileProcessor = new FileProcessor();
        Path testDir = Paths.get("test");

        if (!Files.exists(testDir)) {
            Files.createDirectories(testDir);
        }

        Path studentFile = testDir.resolve("InvalidNameFileTest.txt");
        Files.writeString(studentFile, "INVALID_FORMAT");

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            fileProcessor.readStudents(studentFile.toString());
        });

        assertTrue(exception.getMessage().contains("Index"));

        Files.delete(studentFile);
    }
}
