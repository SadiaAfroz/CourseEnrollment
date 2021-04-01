package net.therap.model;

/**
 * @author sadia.afroz
 * @since 4/1/21
 */
public class Period {
    private int time_id;
    private String startTime;
    private String endTime;
    private int dayOfWeek;

    public Period() {

    }

    public Period(int time_id, String startTime, String endTime, int dayOfWeek) {
        this.time_id = time_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void printPeriod() {
        System.out.println("Day Of work : " + Days.getDay(dayOfWeek - 1) + " Start Time : " + startTime + " End Time : " + endTime);
    }
}
