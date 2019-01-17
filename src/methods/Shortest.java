package methods;

import algorithm.ShortestPath;
import com.alibaba.fastjson.JSON;
import datastructure.Constants;
import datastructure.Critical;
import datastructure.MyList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSK.Reno on 2019/1/14 22:27.
 */
public class Shortest {
    public String shortest(String startName,String endName,String selectshortest) {
//        //起始点名称
//        String startName;
//        //终点名称
//        String endName ;
//        // 选择的算法
//        String selectshortest ;

        ShortestPath shortestPath = new ShortestPath(Critical.getGraph());
        String results = "";

        //两点不可达
        if(shortestPath.getPos(startName)==-1 || shortestPath.getPos(endName)==-1){
            results+= "{\"pathDis\":-1}";

        }else{//两点可达
            if(selectshortest.equals("Dijkstra"))
                shortestPath.dijkstra(startName, endName);
            else if(selectshortest.equals("Floyd"))
                shortestPath.Floyd(startName, endName);
            else if(selectshortest.equals("BellmanFord"))
                shortestPath.BellmanFord(startName, endName);
            else if(selectshortest.equals("spfa"))
                shortestPath.spfa(startName, endName);

            MyList<Integer> result = shortestPath.outputShortestPath();
            // 如果节点不可达
            if(result == null){
                results+="{\"pathDis\":" + Constants.INFINITY + ",\"nodesIndex\":" + JSON.toJSONString("") + "}";
            }else{
                List<Integer> path = new ArrayList<Integer>();
                for(int i=result.getSize()-1; i>0; i--){
                    path.add(result.getData(i));
                }
                results+="{\"pathDis\":" + result.getData(0) + ",\"nodesIndex\":"
                        + JSON.toJSONString(path) + "}";
            }
        }
        return results;
    }


}
