package IO;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by LSK.Reno on 2019/1/15 10:25.
 */
public class IO {
    public void outputFile(String fileName,String content){
        FileWriter fw  = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
//            fw = new FileWriter(fileName, true);
            fw = new FileWriter(fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void loadAnnouncement(String filename){
//        try {
//            File file = new File(filename);
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String temp;
//            int i = 1;
//            while((temp = reader.readLine()) != null) {
//                if(i >= 2 ){
//                    String type = temp.split(",")[0];
//                    String hireStatus = temp.split(",")[5];
//                    if (type.equals("Car") && hireStatus.equals("Available")){
//                        String registrationID = temp.split(",")[1];
//                        String modelName = temp.split(",")[2];
//
//                        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd-HH:mm:ss");
//                        String return_time = temp.split(",")[7];
//
//                        Vehicle car = new Car(registrationID,modelName , yearOfRegistration,
//                                fuelType ,hireStatus , Double.parseDouble(engineCapacity ),return_time);
//                        availableVehicleList.add(car);
//                    }
//                    else if(type.equals("Van") && hireStatus.equals("Available")){
//                        String registrationID = temp.split(",")[1];
//                        String modelName = temp.split(",")[2];
//
//                        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd-HH:mm:ss");
//                        String return_time = temp.split(",")[7];
//
//                        Vehicle van = new Van(registrationID,modelName , yearOfRegistration,
//                                fuelType ,hireStatus , Double.parseDouble(maxLoadCapacity),return_time);
//                        availableVehicleList.add(van);
//                    }
//                }
//                i++;
//            }
//            reader.close();
//        }
//        catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (NumberFormatException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}
