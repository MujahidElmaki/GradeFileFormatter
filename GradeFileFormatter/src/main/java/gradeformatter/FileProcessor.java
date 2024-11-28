package gradeformatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileProcessor {
    private final Set<String> studentIds = new HashSet<>(); // To track unique student IDs
    private final Set<String> courseEntries = new HashSet<>(); // To track unique courses per student

    public List<Student> readStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String studentId = data[0].trim();
                String name = data[1].trim();

                // Skip duplicates
                if (studentIds.contains(studentId)) {
                    continue;
                }
                studentIds.add(studentId);

                // Add student
                students.add(new Student(studentId, name));
            }
        } catch (IOException e) {
            System.err.println("Error reading student file: " + e.getMessage());
        }
        return students;
    }

    public void readCourses(String filePath, List<Student> students) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String studentId = data[0].trim();
                String courseCode = data[1].trim();
                double test1 = Double.parseDouble(data[2].trim());
                double test2 = Double.parseDouble(data[3].trim());
                double test3 = Double.parseDouble(data[4].trim());
                double finalExam = Double.parseDouble(data[5].trim());

                // Check for duplicate course entry
                String courseKey = studentId + "-" + courseCode;
                if (courseEntries.contains(courseKey)) {
                    continue; // Skip duplicate course
                }
                courseEntries.add(courseKey);

                // Add course to the respective student
                for (Student student : students) {
                    if (student.getStudentId().equals(studentId)) {
                        student.addCourse(new Course(courseCode, test1, test2, test3, finalExam));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading course file: " + e.getMessage());
        }
    }

    public void writeOutput(String filePath, List<Student> students) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Student student : students) {
                for (Course course : student.getCourses()) {
                    double finalGrade = course.calculateFinalGrade();
                    writer.write(student.getStudentId() + ", " + student.getName() + ", " +
                                 course.getCourseCode() + ", " +
                                 String.format("%.1f", finalGrade) + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}
