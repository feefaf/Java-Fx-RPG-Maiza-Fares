package com.example.javafxrpgmaizafares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;




public class ChooseNbrHeroController {
    @FXML
    public void oneHeroChosen (ActionEvent event) throws IOException {
        nbrHero = 1;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/chooseHeroType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void twoHeroChosen (ActionEvent event) throws IOException {
        nbrHero = 2;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/chooseHeroType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void threeHeroChosen (ActionEvent event) throws IOException {
        nbrHero = 3;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/chooseHeroType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void fourHeroChosen (ActionEvent event) throws IOException {
        nbrHero = 4;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/chooseHeroType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public static int getNbrHero() {
        return nbrHero;
    }

    public static void minusNbrHero(int minus) {
        nbrHero -= minus;
    }

    static public int nbrHero;
    private Stage stage;
    private Scene scene;
    private Parent root;
}

