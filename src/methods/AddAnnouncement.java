package methods;

import algorithm.AnnouncementManager;

/**
 * Created by LSK.Reno on 2019/1/14 21:47.
 */
public class AddAnnouncement {
    public String addAnnouncement(String message){
        String announcement = message;

        AnnouncementManager management = new AnnouncementManager();
        String results = management.addAnnouncement(announcement);
        return results;

    }





}



























