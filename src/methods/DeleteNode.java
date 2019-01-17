package methods;

import algorithm.TourManager;
import datastructure.ArcNode;
import datastructure.Critical;
import datastructure.Graph;
import datastructure.VNode;

/**
 * Created by LSK.Reno on 2019/1/14 22:02.
 */
public class DeleteNode {
    public String deleteNode(String deleteNodeName, int deleteIndex){
//        String deleteNodeName ;
//        int deleteIndex ;
        TourManager cRoad = new TourManager();
        boolean isSuccessful = cRoad.deleteNode(deleteNodeName);


        //创建图
        Graph graph = Critical.getGraph();
        //生成图的json格式字符串
        String results = toJSONString(graph,isSuccessful, deleteIndex);
        return results;
    }

    private String toJSONString(Graph graph, boolean isSuccessful, int index){
        String jsonString = "{\"arcNum\":" + graph.getArcNum() + ",\"vetNum\":" + graph.getRoadNum() + ",\"index\":" + index
                + ",\"isSuccessful\":" + String.valueOf(isSuccessful)
                + ",\"nodes\":[";
        int flag = 1;
        for(int i = 0; i < graph.getNodes().getSize(); i++){
            ArcNode node = graph.getNodes().getData(i);
            if(flag == 1){
                jsonString += "{\"name\":\"" + node.getName() + "\",\"des\":\"" + node.getDes() + "\",\"pop\":"
                        + node.getPopularity() + ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
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

    public int getPos(String name){
        Graph graph = Critical.getGraph();
        int pos = -1;
        for(int i=0; i<graph.getArcNum(); i++){
            if(name.equals(graph.getNodes().getData(i).getName())){
                pos = i;
                break;
            }
        }

        return pos;
    }

}
