package datastructure;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class AnnouncementContainer {

    static MyList<Announcement> announcementList = new MyList<>();

    public static MyList<Announcement> getMessageList() {
        return announcementList;
    }

    public static void setMessageList(MyList<Announcement> messageList) {
        announcementList = messageList;
    }


}
