package net.therap.model;

/**
 * @author sadia.afroz
 * @since 3/31/21
 */
public class Trainee {
    private int traineeId;
    private String traineeName;

    public Trainee() {
    }

    public Trainee(int traineeId, String traineeName) {
        this.traineeId = traineeId;
        this.traineeName = traineeName;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public void printTrainee() {
        System.out.println("TraineeId : " + traineeId + " TraineeName : " + traineeName);
    }

}
