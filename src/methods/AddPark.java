package methods;

import datastructure.Critical;

/**
 * Created by LSK.Reno on 2019/1/14 21:55.
 */

//根据车牌名称向停车场添加车辆
public class AddPark {
    public String addPark(String number){
        //获取车牌名称
//        String number = request.getParameter("number");
        String results = "";
        if (number.equals("")){
            results = "请输入正确的车牌号码";
        }
        else {
            results = Critical.getParking().arrivePark(number);
        }

        return results;
    }

}
