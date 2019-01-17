package datastructure;

import java.util.Date;

public class Announcement {
    private String announcement;
    private Date time;

    public Announcement(String announcement, Date time) {
        this.announcement = announcement;
        this.time = time;
    }

    public String getMessage() {
        return announcement;
    }

    public void setMessage(String message) {
        this.announcement = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }




}
