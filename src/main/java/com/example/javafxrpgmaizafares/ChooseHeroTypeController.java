package com.example.javafxrpgmaizafares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;



public class ChooseHeroTypeController extends ChooseNbrHeroController{

    public void Initialize(URL url, ResourceBundle resourceBundle) throws Exception {

    }


    @FXML
    public void onWarriorButtonClick(ActionEvent event) throws IOException{
        setHeroTypeClicked("Warrior");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/ConfigureHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onHunterButtonClick(ActionEvent event) throws IOException{
        setHeroTypeClicked("Hunter");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/ConfigureHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onMageButtonClick(ActionEvent event) throws IOException{
        setHeroTypeClicked("Mage");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/ConfigureHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onHealerButtonClick(ActionEvent event) throws IOException{
        setHeroTypeClicked("Healer");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/ConfigureHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static String getHeroTypeClicked() throws IOException{
        return HeroTypeClicked;
    }

    public void setHeroTypeClicked(String heroTypeClicked) {
        this.HeroTypeClicked = heroTypeClicked;
    }

    private static String HeroTypeClicked;


    private Stage stage;
    private Scene scene;
    private Parent root;
}
