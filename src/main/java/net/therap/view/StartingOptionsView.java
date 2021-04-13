package net.therap.view;

/**
 * @author sadia.afroz
 * @since 4/12/21
 */
public class StartingOptionsView {

    public static void viewOptions() {
        System.out.println();
        System.out.println("Choose the option :");
        System.out.println("1. Get course details by course id : (course id)");
        System.out.println("2. Get trainess details by course id : (course id)");
        System.out.println("3. Add new course (course name)");
        System.out.println("4. Add new trainee (trainee name)");
        System.out.println("5. Add new enrollment (course id, trainee id)");
        System.out.println("6. Remove trainee from course (trainee id, course id)");
        System.out.println("7. Update trainee of a course (old trainee name, new trainee name, course id)");
        System.out.println("8. Get allocated time slot by course id");
        System.out.println("9. Allocate a time for specific course-trainee by the enrollment id");
        System.out.println("10. Update trainee name (trainee new name, trainee id)");
        System.out.println("11. Update course name (course new name, course id)");
        System.out.println("12. Delete trainee  (trainee id)");
        System.out.println("13. Delete course (courseid)");
        System.out.println("14. EXIT ");
    }
}
