package gradeformatter;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {
    public List<Student> readStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String studentId = data[0];
                String name = data[1];

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
                String studentId = data[0];
                String courseCode = data[1];
                double test1 = Double.parseDouble(data[2]);
                double test2 = Double.parseDouble(data[3]);
                double test3 = Double.parseDouble(data[4]);
                double finalExam = Double.parseDouble(data[5]);

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
                    writer.write(student.getStudentId() + "," + student.getName() + "," +
                                 course.getCourseCode() + "," + 
                                 String.format("%.1f", course.calculateFinalGrade()) + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}
