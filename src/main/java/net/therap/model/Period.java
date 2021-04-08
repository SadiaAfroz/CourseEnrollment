package net.therap.model;

/**
 * @author sadia.afroz
 * @since 4/1/21
 */
public class Period {

    private int timeId;
    private String startTime;
    private String endTime;
    private int dayOfWeek;

    public Period() {
    }

    public Period(int timeId, String startTime, String endTime, int dayOfWeek) {
        this.timeId = timeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTime_id(int timeId) {
        this.timeId = timeId;
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

    @Override
    public String toString() {
        return "{Day Of work : " + Days.getDay(dayOfWeek - 1) +
                " Start Time : " + startTime + " End Time : " + endTime +
                '}';
    }
}
