package methods;

import algorithm.TourMap;
import com.alibaba.fastjson.JSON;
import datastructure.Critical;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSK.Reno on 2019/1/14 22:36.
 */
public class TourMaps {
    public String tourMaps(String startName,String endName,String selectTourMap){
//        String startName;
//        String endName ;
//        String selectTourMap ;

        TourMap tourMap = new TourMap(Critical.getGraph());
        String results = "";
        //判断是否存在指定的起始点
        List<Integer> tourIndexList = new ArrayList<>();
        if(tourMap.getPos(startName) != -1){
            //若存在，生成旅游路线图和是否有回路
            if(selectTourMap.equals("Hamilton"))
                tourIndexList = tourMap.getHamilton(startName,endName);
            else if (selectTourMap.equals("DFS"))
                tourIndexList = tourMap.DFS(startName,endName);
            else if (selectTourMap.equals("Euler"))
                tourIndexList = tourMap.EulerTour(startName);
            else if (selectTourMap.equals("BDFS plus"))
                tourIndexList = tourMap.bdfsplus(startName,endName);

            if(tourIndexList == null){
                results+="{\"tourList\":[], \"tourCycle\":[], \"pathLength\":-1}";
            }else{
                results+="{\"tourList\":"+ JSON.toJSONString(tourIndexList) + ",\"pathLength\":"+tourMap.getPathLength() +"}";
            }
        }else{
            //若不存在，返回空数组
            results+="{\"tourList\":[], \"tourCycle\":[]}";
        }
    return results;
    }

}
