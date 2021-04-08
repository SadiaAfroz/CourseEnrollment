package net.therap.model;

/**
 * @author sadia.afroz
 * @since 4/1/21
 */
public enum Days {

    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY;

    public static String getDay(int index) {
        return Days.values()[index].name();
    }

    public static void printDays() {
        int i = 1;
        System.out.println("");
        for (Days day : Days.values()) {
            System.out.println(i + " : " + day.name());
            i++;
        }
    }
}
