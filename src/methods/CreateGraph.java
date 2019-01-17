package methods;

import algorithm.CreateNewGraph;
import datastructure.*;
import java.io.PrintWriter;

/**
 * Created by LSK.Reno on 2019/1/14 21:29.
 */
public class CreateGraph {

    public String create(){
        //创建图
        CreateNewGraph graph = new CreateNewGraph(0,0);
        graph.createGraph();

        //生成图的json格式字符串
        String results = toJSONString(graph.getGraph());
        return results;
    }



    private String toJSONString(Graph graph){
        MyList<Integer> xpos = Position.getxPos();
        MyList<Integer> ypos = Position.getyPos();

        String x = convertToJson(xpos);
        String y = convertToJson(ypos);

        String jsonString = "{\"arcNum\":" + graph.getArcNum() + ",\"vetNum\":" + graph.getRoadNum()  + ",\"xpos\":" + x  + ",\"ypos\":" + y
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
                        + node.getPopularity()+ ",\"hasRest\":" + node.isHasRest() + ",\"hasToilet\":" + node.isHasToilet()
                        + ",\"edges\":[";
            }

            VNode tmp = node.getVlist().getData(0);
            jsonString += "{\"index\":" + tmp.getIndex() + ",\"dist\":" + tmp.getDist() + ",\"time\":"
                    + tmp.getTime() + "}";

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

    private String convertToJson(MyList<Integer> pos){
        String s = "[";
        for(int i = 0; i < pos.getSize(); i++){
            s += pos.getData(i);
            if(i == pos.getSize()-1) {
                s += "]";
            }else{
                s+=",";
            }
        }
        return  s;
    }


}
