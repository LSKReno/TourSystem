package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by LSK.Reno on 2018/1/14.
 */
public class LoginController {

    private Stage LoginStage;
    public Stage setLoginStage(Stage LoginStage) {
        this.LoginStage = LoginStage;
        return LoginStage;
    }
    @FXML
    private Label welcome_text;

    @FXML
    private TextField accountField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private Text errormessage;

    @FXML
    public void initialize(){

    }

    @FXML
    void signIn(ActionEvent event) {
        if (accountField.getText().equals("Admin") && passwordField.getText().equals("123")) {
            Stage primaryStage = Main.getStage();
            try {
                primaryStage.setTitle("Administer Mode -- Menu");
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AdministerMenu.fxml")));
                primaryStage.setScene(scene);
//                scene.getStylesheets().add(this.getClass().getResource("AdministerMenuController.css").toExternalForm());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            errormessage.setText("Wrong password or account!");
        }
    }

    /**返回到上一界面*/
    @FXML
    void back(ActionEvent event) {
        Stage primaryStage = Main.getStage();
        try {
            primaryStage.setTitle("Welcome To Tour System -- Administer login ");
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







































