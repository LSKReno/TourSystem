package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
//老虎山 朝日峰 20 12
//老虎山 花卉园 30 15
//老虎山 北门 12 5

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Main extends Application {

    static private Stage primaryStage;

    /**
     * all the controller can call this method.
     * @return get the primaryStage.
     */

    //准备开始运行程序的初始化
    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Welcome To Tour System -- Menu");
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root, 1122, 745);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //得到初始的菜单界面
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    public static Stage getStage() {
        return primaryStage;
    }

    //运行程序
    public static void main(String[] args) {
        launch(args);
    }

}






















