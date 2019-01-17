package methods;

import algorithm.TourManager;
import datastructure.ArcNode;
import datastructure.Critical;
import datastructure.Graph;
import datastructure.VNode;

/**
 * Created by LSK.Reno on 2019/1/14 21:57.
 */
public class AddRoad {
    public String addRoad(String addRoadStartName, String addRoadEndName, int addRoadDis,int addRoadTime ){
//        String addRoadStartName ;
//        String addRoadEndName ;
//        int addRoadDis ;
//        int addRoadTime ;


        TourManager cRoad = new TourManager();
        boolean isSuccessful = cRoad.addRoad(addRoadStartName,addRoadEndName,addRoadDis,addRoadTime);


        //创建图
        Graph graph = Critical.getGraph();
        //生成图的json格式字符串
        String results = toJSONString(graph,isSuccessful);
        return results;
    }

    private String toJSONString(Graph graph, boolean isSuccessful){
        String jsonString = "{\"arcNum\":" + graph.getArcNum() + ",\"vetNum\":" + graph.getRoadNum() + ",\"isSuccessful\":" + String.valueOf(isSuccessful)
                + ",\"nodes\":[";
        int flag = 1;
        for(int i = 0; i < graph.getNodes().getSize(); i++){
            ArcNode node = graph.getNodes().getData(i);
            if(flag == 1){
                jsonString += "{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
                        + node.getPopularity()+ ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
                        + ",\"edges\":[";
                flag = 0;
            }else{
                jsonString += ",{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
                        + node.getPopularity() + ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
                        + ",\"edges\":[";
            }
            VNode tmp = null;
            if(node.getVlist().getSize()!= 0){
                tmp = node.getVlist().getData(0);
                jsonString += "{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() + ",\"time\":"
                        + tmp.getTime() + "}";
            }

//            System.out.println(node.getName());
//            System.out.println(node.getVlist().getSize());
            for(int j =1 ; j < node.getVlist().getSize(); j++){
                tmp = node.getVlist().getData(j);

                jsonString += ",{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() + ",\"time\":"
                        + tmp.getTime() + "}";
            }
            jsonString += "]}";
        }
        jsonString += "]}";

        return jsonString;
    }

}
