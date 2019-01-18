package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import methods.*;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Created by LSK.Reno on 2019/1/14 17:04.
 */
public class AdministerMenuController {

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton backToMenuButton;

    @FXML
    private Tab insertScenicSpotTAB;

    @FXML
    private AnchorPane insertScenicSpotPane;

    @FXML
    private JFXButton insertScenicSpotButton;

    @FXML
    private JFXTextField addScenicNameText;

    @FXML
    private JFXTextField addScenicDesText;

    @FXML
    private JFXTextField addScenicPopText;

    @FXML
    private JFXComboBox<String> addScenicHasRestComboBox;

    @FXML
    private JFXComboBox<String> addScenicHasToiletComboBox;

    @FXML
    private Tab deleteScenicSpotTAB;

    @FXML
    private AnchorPane deleteScenicSpotPane;

    @FXML
    private JFXButton deleteScenicSpotButton;

    @FXML
    private JFXTextField deleteScenicNameText;

    @FXML
    private JFXTextField deleteScenicIndexText;

    @FXML
    private Tab insertRoadTAB;

    @FXML
    private AnchorPane shortestPathPane;

    @FXML
    private JFXButton inertRoadButton;

    @FXML
    private Tab deleteRoadTAB;

    @FXML
    private AnchorPane deleteRoadPane;

    @FXML
    private JFXButton deleteRoadButton;

    @FXML
    private Tab searchAndSortTAB;

    @FXML
    private JFXButton publishAnnouncementButton;

    @FXML
    private JFXTimePicker announceTimePicker;

    @FXML
    private JFXTextField announcementContentText;

    @FXML
    private JFXTextField addStartScenicNameText;

    @FXML
    private JFXTextField addEndScenicNameText;

    @FXML
    private JFXTextField addDisText;

    @FXML
    private JFXTextField addTimeText;

    @FXML
    private JFXTextField deleteStartScenicNameText;

    @FXML
    private JFXTextField deleteEndScenicNameText;

    @FXML
    private AnchorPane announcementSuccessPane;

    @FXML
    private JFXButton notVisibleButton;

    @FXML
    private JFXButton planRoadKruskalButton;

    @FXML
    private JFXTextField planRoadStartScenicNameText;

    @FXML
    private JFXButton planRoadPrimButton;

    @FXML
    private JFXTextArea roadPlanTextArea;

    /**界面初始化*/
    @FXML
    public void initialize() {
        JFXComboBox addScenicHasRestBox = (JFXComboBox) insertScenicSpotPane.lookup("#addScenicHasRestComboBox");
        ObservableList<String> addScenicHasRestList = FXCollections.observableArrayList("有", "无");
        addScenicHasRestBox.setItems(addScenicHasRestList);

        JFXComboBox addScenicHasToiletBox = (JFXComboBox) insertScenicSpotPane.lookup("#addScenicHasToiletComboBox");
        ObservableList<String> addScenicHasToiletList = FXCollections.observableArrayList("有", "无");
        addScenicHasToiletBox.setItems(addScenicHasToiletList);
    }

    /**增加景点*/
    @FXML
    void insertScenicSpot(ActionEvent event) {
        String addNodeName = addScenicNameText.getText();
        String addNodeDes = addScenicDesText.getText();
        String nodePop = addScenicPopText.getText();
        String nodeRest = addScenicHasRestComboBox.getValue();
        String nodeToilet = addScenicHasToiletComboBox.getValue();

        AddNode addNode = new AddNode();
        String results = addNode.addNode(addNodeName, addNodeDes, nodePop, nodeRest, nodeToilet);
        System.out.println(results);


    }


    /**删除景点*/
    @FXML
    void deleteScenicSpot(ActionEvent event) {
        String deleteNodeName = deleteScenicNameText.getText();
        int deleteIndex = Integer.valueOf(deleteScenicIndexText.getText());

        DeleteNode deleteNode = new DeleteNode();
        String results = deleteNode.deleteNode(deleteNodeName,deleteIndex );
        System.out.println(results);
    }

    /**增加路*/
    @FXML
    void inertRoad(ActionEvent event) {
        String addRoadStartName = addStartScenicNameText.getText();
        String addRoadEndName = addEndScenicNameText.getText();
        int addRoadDis = Integer.parseInt(addDisText.getText());
        int addRoadTime = Integer.parseInt(addTimeText.getText());

        AddRoad addRoad = new AddRoad();
        String results = addRoad.addRoad(addRoadStartName, addRoadEndName, addRoadDis, addRoadTime);
        System.out.println(results);

    }

    /**删除路*/
    @FXML
    void deleteRoad(ActionEvent event) {
        String deleteRoadStartName = deleteStartScenicNameText.getText();
        String deleteRoadEndName = deleteEndScenicNameText.getText();
        DeleteRoad deleteRoad = new DeleteRoad();
        String results = deleteRoad.deleteRoad(deleteRoadStartName, deleteRoadEndName);
        System.out.println(results);
    }

    /**使用Prim道路规划建议*/
    @FXML
    void planRoadPrim(ActionEvent event) {
        roadPlanTextArea.setVisible(true);
        PrimRoad primRoad = new PrimRoad();
        String results = primRoad.primRoad(planRoadStartScenicNameText.getText());
//        System.out.println(results);
//  狮子山 {"results":[{"end":4,"start":1,"weight":6},{"end":7,"start":4,"weight":3},{"end":3,"start":1,"weight":7},{"end":0,"start":1,"weight":9},{"end":2,"start":0,"weight":8},{"end":5,"start":2,"weight":4},{"end":6,"start":2,"weight":5},{"end":9,"start":3,"weight":10},{"end":10,"start":9,"weight":9},{"end":11,"start":10,"weight":10},{"end":8,"start":6,"weight":15},{"end":12,"start":11,"weight":30}]}
//  老虎山 {"results":[{"end":11,"start":12,"weight":30},{"end":10,"start":11,"weight":10},{"end":9,"start":10,"weight":9},{"end":3,"start":9,"weight":10},{"end":1,"start":3,"weight":7},{"end":4,"start":1,"weight":6},{"end":7,"start":4,"weight":3},{"end":0,"start":1,"weight":9},{"end":2,"start":0,"weight":8},{"end":5,"start":2,"weight":4},{"end":6,"start":2,"weight":5},{"end":8,"start":6,"weight":15}]}

        CreateGraph createGraph = new CreateGraph();
        String displayGraph = null;
        try {
            displayGraph = createGraph.create();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("景点地图初始化错误");
        }
        JSONObject jsonGraph = JSON.parseObject(displayGraph);
        JSONArray nodes = jsonGraph.getJSONArray("nodes");

        String roadPlan = "";
        JSONObject jsonPrim = JSON.parseObject(results);
        JSONArray resultsList = jsonPrim.getJSONArray("results");
        for (int i = 0; i < resultsList.size(); i++) {
            JSONObject road = resultsList.getJSONObject(i);
            String start = nodes.getJSONObject(road.getInteger("start")).getString("name");
            String end = nodes.getJSONObject(road.getInteger("end")).getString("name");
            String weight = road.getString("weight");
            roadPlan += start+"-->"+end+", 路长: "+weight+"\n";

        }
        roadPlanTextArea.setText(roadPlan);

    }

    /**使用Kruskal道路规划建议*/
    @FXML
    void planRoadKruskal(ActionEvent event) {
        roadPlanTextArea.setVisible(true);
        KruskalRoad kruskalRoad = new KruskalRoad();
        String results = kruskalRoad.kruskalRoad();
//        System.out.println(results);
//        {"results":[
//                {"end":7,"start":4,"weight":3},
//            {"end":5,"start":2,"weight":4},
//            {"end":6,"start":2,"weight":5},
//            {"end":4,"start":1,"weight":6},
//            {"end":3,"start":1,"weight":7},
//            {"end":2,"start":0,"weight":8},
//            {"end":1,"start":0,"weight":9},
//            {"end":10,"start":9,"weight":9},
//            {"end":9,"start":3,"weight":10},
//            {"end":11,"start":10,"weight":10},
//            {"end":8,"start":6,"weight":15},
//            {"end":12,"start":11,"weight":30}]}
        CreateGraph createGraph = new CreateGraph();
        String displayGraph = null;
        try {
            displayGraph = createGraph.create();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("景点地图初始化错误");
        }
//        {"arcNum":13,"vetNum":22,"xpos":[280,170,430,85,250,350,550,230,330,150,300,450,200,350,500],"ypos":[70,130,150,200,205,200,270,300,310,380,440,460,500,500,500],
//      "nodes":[
//          {"name":"北门","des":"景点北入口","pop":80,"hasRest":true,"hasToilet":true,"edges":[{"index":1,"dist":9,"time":15},{"index":2,"dist":8,"time":10}]},
//          {"name":"狮子山","des":"一座山","pop":70,"hasRest":true,"hasToilet":false,"edges":[{"index":0,"dist":9,"time":15},{"index":2,"dist":22,"time":32},{"index":3,"dist":7,"time":8},{"index":4,"dist":6,"time":7}]},{"name":"仙云石","des":"一块石头","pop":75,"hasRest":false,"hasToilet":false,"edges":[{"index":0,"dist":8,"time":10},{"index":1,"dist":22,"time":32},{"index":5,"dist":4,"time":5},{"index":6,"dist":5,"time":6}]},{"name":"一线天","des":"悬崖","pop":90,"hasRest":false,"hasToilet":false,"edges":[{"index":1,"dist":7,"time":8},{"index":7,"dist":11,"time":20},{"index":9,"dist":10,"time":18},{"index":10,"dist":19,"time":28}]},{"name":"飞流瀑","des":"瀑布","pop":75,"hasRest":false,"hasToilet":false,"edges":[{"index":1,"dist":6,"time":7},{"index":7,"dist":3,"time":4}]},{"name":"仙武湖","des":"湖","pop":90,"hasRest":true,"hasToilet":true,"edges":[{"index":2,"dist":4,"time":5},{"index":6,"dist":7,"time":8},{"index":8,"dist":20,"time":50},{"index":11,"dist":30,"time":44}]},{"name":"九曲桥","des":"桥","pop":85,"hasRest":false,"hasToilet":false,"edges":[{"index":2,"dist":5,"time":6},{"index":5,"dist":7,"time":8},{"index":8,"dist":15,"time":20},{"index":11,"dist":20,"time":44}]},{"name":"观云台","des":"楼阁","pop":80,"hasRest":true,"hasToilet":true,"edges":[{"index":3,"dist":11,"time":20},{"index":4,"dist":3,"time":4},{"index":8,"dist":16,"time":32},{"index":10,"dist":15,"time":29}]},{"name":"碧水潭","des":"一个水潭","pop":100,"hasRest":true,"hasToilet":false,"edges":[{"index":7,"dist":16,"time":32},{"index":5,"dist":20,"time":50},{"index":6,"dist":15,"time":20},{"index":11,"dist":17,"time":44}]},{"name":"花卉园","des":"一个花园","pop":82,"hasRest":true,"hasToilet":true,"edges":[{"index":3,"dist":10,"time":18},{"index":10,"dist":9,"time":12}]},{"name":"红叶亭","des":"一个亭子","pop":70,"hasRest":false,"hasToilet":false,"edges":[{"index":7,"dist":15,"time":29},{"index":9,"dist":9,"time":12},{"index":3,"dist":19,"time":28},{"index":11,"dist":10,"time":22}]},{"name":"朝日峰","des":"一个山峰","pop":120,"hasRest":true,"hasToilet":true,"edges":[{"index":8,"dist":17,"time":44},{"index":10,"dist":10,"time":22},{"index":6,"dist":20,"time":44},{"index":5,"dist":30,"time":44},{"index":12,"dist":30,"time":15}]},{"name":"老虎山","des":"一座像老虎的山","pop":90,"hasRest":true,"hasToilet":false,"edges":[{"index":11,"dist":30,"time":15}]}]}

        JSONObject jsonGraph = JSON.parseObject(displayGraph);
        JSONArray nodes = jsonGraph.getJSONArray("nodes");

        String roadPlan = "";
        JSONObject jsonKruskal = JSON.parseObject(results);
        JSONArray resultsList = jsonKruskal.getJSONArray("results");
        for (int i = 0; i < resultsList.size(); i++) {
            JSONObject road = resultsList.getJSONObject(i);
            String start = nodes.getJSONObject(road.getInteger("start")).getString("name");
            String end = nodes.getJSONObject(road.getInteger("end")).getString("name");
            String weight = road.getString("weight");
            roadPlan += start+"-->"+end+", 路长: "+weight+"\n";

        }
        roadPlanTextArea.setText(roadPlan);
    }



    /**发布公告通知*/
    @FXML
    void publishAnnouncement(ActionEvent event) {
        LocalTime announcementTime = announceTimePicker.getValue();
        String announcementContent = announcementContentText.getText();
        AddAnnouncement addAnnouncement = new AddAnnouncement();
        String results = addAnnouncement.addAnnouncement(announcementContent);
//        System.out.println(results);
        announcementSuccessPane.setVisible(true);
    }

    /**隐藏发布公告成功Pane*/
    @FXML
    void notVisible(ActionEvent event) {
        announcementSuccessPane.setVisible(false);
    }



    /**
     * 返回主菜单
     * */
    @FXML
    void backToMenu(ActionEvent event) {
        Stage primaryStage = Main.getStage();
        try {
            primaryStage.setTitle("Welcome To Tour System -- Menu");
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Menu.fxml")));
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(1);
    }



}