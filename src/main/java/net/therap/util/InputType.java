package net.therap.util;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public interface InputType {

    int COURSE_DETAILS_BY_COURSEID = 1;
    int TRAINEES_DETAILS_BY_COURSEID = 2;
    int ADD_NEW_COURSE = 3;
    int ADD_NEW_TRAINEE = 4;
    int ADD_NEW_ENROLLMENT = 5;
    int REMOVE_TRAINEE_FROM_COURSE = 6;
    int UPDATE_TRAINEE_ON_COURSE = 7;
    int TIMESLOTS_BY_COURSEID = 8;
    int ALLOCATE_TIMESLOT = 9;
    int EXIT = 10;
}
