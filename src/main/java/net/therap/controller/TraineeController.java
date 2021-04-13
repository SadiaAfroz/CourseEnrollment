package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Trainee;
import net.therap.service.CourseService;
import net.therap.service.TraineeService;
import net.therap.validator.TraineeValidator;

import java.util.List;
import java.util.Scanner;

/**
 * @author sadia.afroz
 * @since 4/12/21
 */
public class TraineeController {

    public void getTraineesByCourseId() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Trainee Id: ");
        int courseId = input.nextInt();

        TraineeService traineeProcessor = new TraineeService();
        List<Trainee> trainees = traineeProcessor.getTrainees(courseId);
        traineeProcessor.processTrainees(trainees);
    }

    public void insertTrainee() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Trainee Name: ");
        String traineeName = input.nextLine();
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.isValidName(traineeName)) {
            Trainee trainee = new Trainee();
            trainee.setName(traineeName);

            TraineeService traineeService = new TraineeService();
            traineeService.insertTrainee(trainee);
        } else {
            System.out.println("Not a valid Trainee Name ");
        }
    }

    public void updateTrainee() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter new Trainee Name: ");
        String newName= input.nextLine();
        System.out.println("Enter Trainee id: ");
        int traineeId= input.nextInt();

        TraineeService traineeService=new TraineeService();
        Trainee trainee= new Trainee(traineeId, newName);
        traineeService.updateTrainee(trainee);
    }

    public void deleteTrainee() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Trainee id: ");
        int traineeId= input.nextInt();

        TraineeService traineeService=new TraineeService();
        Trainee trainee= new Trainee(traineeId);
        traineeService.deleteTrainee(trainee);
    }
}
