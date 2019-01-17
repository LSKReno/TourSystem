package methods;

import algorithm.AnnouncementManager;

import java.io.IOException;

/**
 * Created by LSK.Reno on 2019/1/14 22:32.
 */
public class ShowAnnouncement {
    public String ShowAnnouncement()  {
        AnnouncementManager management = new AnnouncementManager();
        String results = null;
        try {
            results = management.showAnnouncement();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
