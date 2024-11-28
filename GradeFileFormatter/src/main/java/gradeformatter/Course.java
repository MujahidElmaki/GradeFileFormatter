package gradeformatter;

public class Course {
    private String courseCode;
    private double test1;
    private double test2;
    private double test3;
    private double finalExam;

    public Course(String courseCode, double test1, double test2, double test3, double finalExam) {
        validateCourseCode(courseCode);
        validateGrades(test1, test2, test3, finalExam);

        this.courseCode = courseCode;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.finalExam = finalExam;
    }

    public double calculateFinalGrade() {
        return (test1 * 0.2) + (test2 * 0.2) + (test3 * 0.2) + (finalExam * 0.4);
    }

    private void validateCourseCode(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty or null.");
        }
        if (!courseCode.matches("[A-Z]{2}\\d{3}")) { // Example format: CP460
            throw new IllegalArgumentException("Invalid course code format: " + courseCode);
        }
    }

    private void validateGrades(double... grades) {
        for (double grade : grades) {
            if (grade < 0) {
                throw new IllegalArgumentException("Grades cannot be negative: " + grade);
            }
            if (grade > 100) {
                throw new IllegalArgumentException("Grades cannot exceed 100: " + grade);
            }
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getTest1() {
        return test1;
    }

    public double getTest2() {
        return test2;
    }

    public double getTest3() {
        return test3;
    }

    public double getFinalExam() {
        return finalExam;
    }
}
