package methods;

import algorithm.Sort;
import datastructure.Critical;

import java.util.Map;

/**
 * Created by LSK.Reno on 2019/1/14 22:34.
 */
public class SortArc {
    public String sortArc( String type,String algorithm){
        //排序类型
//        String type ;
//        String algorithm ;


        Sort find = new Sort(Critical.getGraph());
        Map<String, Integer> orderResults = null;
        //按欢迎度排序
        if(type.equals("按欢迎度")){
            orderResults = find.orderByPopular(algorithm);
        }
        else if(type.equals("按景点路口数")){//按景点路口数排序
            orderResults = find.orderByPathNum(algorithm);
        }


        return toJSONString(orderResults);
    }
    private String toJSONString(Map<String, Integer> orderResults){
        String json = "[";
        for(Map.Entry<String, Integer> entry : orderResults.entrySet()){
            json  += "{\"name\":\"" + entry.getKey() + "\",\"value\":" + entry.getValue() + "},";
        }
        json = json.substring(0, json.length()-1);
        json += "]";

        return json;
    }

}
