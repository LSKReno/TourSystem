package algorithm;

import datastructure.*;
import javafx.scene.shape.Arc;

import java.io.*;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class TourManager {
    Graph graph = Critical.getGraph();

    //删除节点
    public boolean deleteNode(String node){
        int delete = getPos(node);

        if(delete == -1)
            return false;
        int count = 0;
        for(int i = 0 ; i < graph.getNodes().getSize(); i++){
            for (int j = 0; j < graph.getNodes().getData(i).getVlist().getSize(); j++){
                if(graph.getNodes().getData(i).getVlist().getData(j).getIndex() == delete){
                    graph.getNodes().getData(i).getVlist().delete(j);
                    count++;
                }
            }
        }

        //减少边的值
        graph.setRoadNum(graph.getRoadNum()-count);

        //改变索引值
        for(int i = 0 ; i < graph.getNodes().getSize(); i++) {
            for (int j = 0; j < graph.getNodes().getData(i).getVlist().getSize(); j++) {
                int tempIndex = graph.getNodes().getData(i).getVlist().getData(j).getIndex();
                if(tempIndex > delete){
                    graph.getNodes().getData(i).getVlist().getData(j).setIndex(tempIndex-1);
                }
            }
        }

        //删除节点
        graph.getNodes().delete(delete);
        graph.setArcNum(graph.getArcNum()-1);

        Position.getxPos().delete(delete);
        Position.getyPos().delete(delete);

        // 文件存储
        try {
            saveGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean addNode(String name, String des, int pop, boolean hasRest, boolean hasToilet){
        ArcNode acrNode =new ArcNode(name,des,pop,hasRest,hasToilet);
        graph.getNodes().add(acrNode);
        graph.setArcNum(graph.getArcNum()+1);

        // 文件存储
        try {
            saveGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  true;
    }


    public boolean deleteRoad(String start, String end){
        int startIndex = getPos(start);
        int endIndex = getPos(end);

        if(startIndex == -1 || endIndex == -1)
            return false;

        MyList<VNode> list = graph.getNodes().getData(startIndex).getVlist();

        for(int i = 0; i < list.getSize(); i++){
            if(list.getData(i).getIndex() == endIndex){
                list.delete(i);
            }
        }

        list = graph.getNodes().getData(endIndex).getVlist();

        for(int i = 0; i < list.getSize(); i++){
            if(list.getData(i).getIndex() == startIndex){
                list.delete(i);
            }
        }
        graph.setRoadNum(graph.getRoadNum()-1);

        // 文件存储
        try {
            saveGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean addRoad(String start, String end, int dis , int time){
        int startIndex = getPos(start);
        int endIndex = getPos(end);

        if(startIndex == -1 || endIndex == -1)
            return false;

        MyList<VNode> list = graph.getNodes().getData(startIndex).getVlist();

        list.add(new VNode(endIndex,dis, time));

        list = graph.getNodes().getData(endIndex).getVlist();
        list.add(new VNode(startIndex,dis, time));
        graph.setRoadNum(graph.getRoadNum()+1);

        // 文件存储
        try {
            saveGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getPos(String name){
        int pos = -1;
        for(int i=0; i<graph.getArcNum(); i++){
            if(name.equals(graph.getNodes().getData(i).getName())){
                pos = i;
                break;
            }
        }

        return pos;
    }

    public void saveGraph ()  throws IOException {
        File fout = new File("D:\\TourSystem\\TourSystem\\Info.txt");
        FileOutputStream fos = new FileOutputStream(fout,false);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        // 写入节点信息
        for (int i = 0; i < graph.getNodes().getSize(); i++) {
            ArcNode node = graph.getNodes().getData(i);
            String data = node.getName() + " " + node.getDes() + " " + node.getPopularity() + " ";
            data += node.isHasRest()? "1 ":"0 ";
            data += node.isHasToilet()? "1":"0";
            bw.write(data);
            bw.newLine();
        }

        bw.write("");
        bw.newLine();

        for (int i = 0; i < graph.getNodes().getSize(); i++) {
            for(int j = 0 ; j < graph.getNodes().getData(i).getVlist().getSize(); j++){
                VNode node = graph.getNodes().getData(i).getVlist().getData(j);
                if(node.getIndex() < i){
                    String data = graph.getNodes().getData(i).getName() + " " + graph.getNodes().getData(node.getIndex()).getName() +
                            " " + node.getDist() + " " + node.getTime();
                    bw.write(data);
                    bw.newLine();
                }
            }
        }

        bw.close();

        File file = new File("D:\\TourSystem\\TourSystem\\position.txt");
        FileOutputStream fospos = new FileOutputStream(file,false);

        BufferedWriter bwpos = new BufferedWriter(new OutputStreamWriter(fospos));


        String xpos = convertToString(Position.getxPos());
        String ypos = convertToString(Position.getyPos());

        bwpos.write(xpos);
        bwpos.newLine();
        bwpos.write(ypos);
        bwpos.close();

    }

    private String convertToString(MyList<Integer> pos){
        String data="";
        for(int i = 0; i < pos.getSize(); i++){
            data += pos.getData(i) + " ";
        }
        return data;
    }



}
