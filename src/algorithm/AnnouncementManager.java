package algorithm;

import com.alibaba.fastjson.JSON;
import datastructure.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AnnouncementManager {

    public String addAnnouncement(String message) {
        //当前发布的消息过多
        if (AnnouncementContainer.getMessageList().getSize() >= 5) {
            deleteAnnouncement(2);
        }
        Announcement announcement = new Announcement(message, new Date());
        AnnouncementContainer.getMessageList().add(announcement);
        try {
            saveAnnouncement();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"list\":" + generateListJSON(AnnouncementContainer.getMessageList()) + "}";
    }

    public String deleteAnnouncement(int index) {
        if(AnnouncementContainer.getMessageList().getSize() - index < 0)
            return "{\"list\":" + generateListJSON(AnnouncementContainer.getMessageList()) + ",\"success\":" + false + "}";
        AnnouncementContainer.getMessageList().delete(AnnouncementContainer.getMessageList().getSize() - index);
        try {
            saveAnnouncement();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"list\":" + generateListJSON(AnnouncementContainer.getMessageList()) + ",\"success\":" + true + "}";

    }

    public String showAnnouncement() throws IOException{
        if(AnnouncementContainer.getMessageList().getSize() == 0){
            File file = new File("D:\\TourSystem\\TourSystem\\Announcement.txt");
            Scanner reader;
            try{
                reader = new Scanner(file);
            }catch (FileNotFoundException e){
                return "no Announcement.txt file";
            }

            while (reader.hasNext()) {
                String time = reader.nextLine();
                String message = reader.nextLine();
                Date date = new Date(time);
                AnnouncementContainer.getMessageList().add(new Announcement(message,date));
            }
            return "{\"list\":" + generateListJSON(AnnouncementContainer.getMessageList()) + "}";

        }else{
            return "{\"list\":" + generateListJSON(AnnouncementContainer.getMessageList()) + "}";
        }
    }

    private String generateListJSON(MyList<Announcement> announcementMyList) {
        List<Announcement> list = new ArrayList<>();
        for(int i = 0; i < announcementMyList.getSize(); i++){
            Announcement node = announcementMyList.getData(i);
            list.add(node);
        }

        return JSON.toJSONString(list);
    }

    public void saveAnnouncement ()  throws IOException {
        File fileOutput = new File("D:\\TourSystem\\TourSystem\\Announcement.txt");
        FileOutputStream fos = new FileOutputStream(fileOutput,false);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < AnnouncementContainer.getMessageList().getSize(); i++) {
            String time = String.valueOf(AnnouncementContainer.getMessageList().getData(i).getTime());
            String message = AnnouncementContainer.getMessageList().getData(i).getMessage();

            bw.write(time);
            bw.newLine();
            bw.write(message);
            bw.newLine();
        }


        bw.close();


    }
}