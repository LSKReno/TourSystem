package methods;

import datastructure.Critical;

import java.util.Date;

/**
 * Created by LSK.Reno on 2019/1/14 22:04.
 */

//根据指定车牌号删除车辆
public class DeletePark {
    public String deletePark(String number){
        //获取车牌号
//        String number ;

        String results = Critical.getParking().leavePark(number, new Date());


        return results;
    }
}
