package methods;

import algorithm.Prim;
import com.alibaba.fastjson.JSON;
import datastructure.VData;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSK.Reno on 2019/1/14 22:24.
 */
public class PrimRoad {
    public String primRoad(String startName){
//        String startName = request.getParameter("startName");
        Prim prim = new Prim();

        //Prim算法生成路线图
        List<VData> results = prim.prim(startName);
        Map<String, List<VData>> map = new HashMap<String, List<VData>>();
        map.put("results", results);

        return JSON.toJSONString(map);
    }

}
