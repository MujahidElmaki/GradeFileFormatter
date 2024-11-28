package gradeformatter;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private List<Course> courses;

    public Student(String studentId, String name) {
        validateStudentId(studentId);
        validateStudentName(name);

        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    private void validateStudentId(String studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID null");
        }
        if (studentId.startsWith("-")) {
            throw new IllegalArgumentException("Student ID cannot be negative.");
        }
        if (!studentId.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Student ID must be a 9-digit number.");
        }
        if (studentId.length() != 9) {
            throw new IllegalArgumentException("Student ID must be a 9-digit number.");
        }
    }

    private void validateStudentName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Student name cannot contain numbers.");
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
