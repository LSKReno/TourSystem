package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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

    /**发布公告通知*/
    @FXML
    void publishAnnouncement(ActionEvent event) {
        LocalTime announcementTime = announceTimePicker.getValue();
        String announcementContent = announcementContentText.getText();
        AddAnnouncement addAnnouncement = new AddAnnouncement();
        String results = addAnnouncement.addAnnouncement(announcementContent);
//        System.out.println(results);


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