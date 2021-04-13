package net.therap.model;

/**
 * @author sadia.afroz
 * @since 4/12/21
 */
public class CourseEnrollment {

    private int id;
    private Course course;
    private Trainee trainee;

    public CourseEnrollment(Course course, Trainee trainee) {
        this.course = course;
        this.trainee = trainee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }
}
