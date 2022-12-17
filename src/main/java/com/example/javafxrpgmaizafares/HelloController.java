package com.example.javafxrpgmaizafares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;



public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    public void onPlayButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/ChooseNbrHero-view.fxml")));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onQuitButtonClick(ActionEvent event) throws IOException{
            //je sais pas comment faire quitter
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
}