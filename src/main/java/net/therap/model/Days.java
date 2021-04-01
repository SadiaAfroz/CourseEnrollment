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
}
