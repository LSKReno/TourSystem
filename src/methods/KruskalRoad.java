package methods;

import algorithm.Kruskal;
import com.alibaba.fastjson.JSON;
import datastructure.Critical;
import datastructure.VData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSK.Reno on 2019/1/14 22:21.
 */
public class KruskalRoad {
    public String KruskalRoad(){
        Kruskal kruskal = new Kruskal(Critical.getGraph());
        //克鲁斯卡尔算法生成路线图
        List<VData> results = kruskal.kruskal();
        Map<String, List<VData>> map = new HashMap<String, List<VData>>();
        map.put("results", results);

        return JSON.toJSONString(map);
    }


}
