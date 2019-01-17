package methods;

import algorithm.AnnouncementManager;

/**
 * Created by LSK.Reno on 2019/1/14 22:00.
 */
public class DeleteAnnouncement {
    public String deleteAnnouncement(String announcement){
        int announcementInt = Integer.parseInt(announcement);

        AnnouncementManager management = new AnnouncementManager();
        String results = management.deleteAnnouncement(announcementInt);

        return results;
    }





}
