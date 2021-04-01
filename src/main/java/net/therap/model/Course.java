package net.therap.model;

/**
 * @author sadia.afroz
 * @since 3/30/21
 */
public class Course {
    private int courseId;
    private String courseName;

    public Course() {
    }

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void printCourse() {
        System.out.println("CourseId : " + courseId + " CourseName : " + courseName);
    }
}
