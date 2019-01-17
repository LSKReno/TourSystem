package methods;

import datastructure.Constants;
import datastructure.Critical;

import java.io.PrintWriter;

/**
 * Created by LSK.Reno on 2019/1/14 21:42.
 */
public class CreatePark {
    public String create(){
        //停车场大小
//        String parkSize ;
//        String parkSize = "3";

        //初始化停车场大小
        Critical.setParking(Constants.PARKING_SIZE);
        String results = "{}";

        return results;
    }
}
