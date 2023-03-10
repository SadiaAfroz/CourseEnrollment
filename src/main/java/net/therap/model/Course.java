package net.therap.model;

/**
 * @author sadia.afroz
 * @since 3/30/21
 */
public class Course {

    private int id;
    private String name;

    public Course() {
    }

    public Course(int id) {
        this.id = id;
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{ courseId=" + id +
                ", courseName='" + name + '\'' +
                '}';
    }
}
