package gradeformatter;

public class Course {
    private String courseCode;
    private double test1;
    private double test2;
    private double test3;
    private double finalExam;

    public Course(String courseCode, double test1, double test2, double test3, double finalExam) {
        this.courseCode = courseCode;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.finalExam = finalExam;
    }

    public double calculateFinalGrade() {
        return (test1 * 0.2) + (test2 * 0.2) + (test3 * 0.2) + (finalExam * 0.4);
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
