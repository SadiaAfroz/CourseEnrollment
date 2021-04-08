package net.therap.model;

/**
 * @author sadia.afroz
 * @since 3/31/21
 */
public class Trainee {

    private int id;
    private String name;

    public Trainee() {
    }

    public Trainee(int id, String name) {
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
        return "{id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
