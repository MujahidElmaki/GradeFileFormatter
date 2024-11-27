package gradeformatter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();
        List<Student> students = fileProcessor.readStudents("NameFile.txt");

        fileProcessor.readCourses("CourseFile.txt", students);
        Collections.sort(students, Comparator.comparing(Student::getStudentId));
        fileProcessor.writeOutput("FormattedOutput.txt", students);
        System.out.println("Processing complete. Output written to FormattedOutput.txt");
    }
}
