package com.example.javafxrpgmaizafares;

import com.example.javafxrpgmaizafares.isep.rpg.gentils.*;
import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.javafxrpgmaizafares.ListHero.listHeros;

public class ConfigureHero extends ChooseHeroTypeController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            HashMap<String, String> heroUrlType = new HashMap<>();
            heroUrlType.put("Warrior", "src/main/resources/sprites/heros/Warrior.png");
            heroUrlType.put("Hunter", "src/main/resources/sprites/heros/Hunter.png");
            heroUrlType.put("Mage", "src/main/resources/sprites/heros/Mage.png");
            heroUrlType.put("Healer", "src/main/resources/sprites/heros/Healer.png");

            HeroSlot.setImage(chargeImage(heroUrlType.get(getHeroTypeClicked())));

        }catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @FXML
    public void ValidateHero(ActionEvent event) throws IOException {
        name = EnteredName.getText();
        switch(getHeroTypeClicked()){
            case "Warrior":
                tempWarrior = new Warrior(name);
                tempWarrior.take(new Weapon("Eppee en bois", "Warrior", 20, 10));
                tempWarrior.take(new Armor("Pull ordinaire", 0, 10));
                listHeros.add(tempWarrior);
                break;
            case "Hunter":
                tempHunter = new Hunter(name);
                tempHunter.take(new Weapon("Arc", "Hunter", 25, 10));
                tempHunter.take(new Armor("Veste ordinaire", 0, 10));
                listHeros.add(tempHunter);
                break;
            case "Mage":
                tempMage = new Mage(name, 100);
                tempMage.take(new Weapon("Baton en carton", "Mage", 3, 10));
                tempMage.take(new Armor("Robe ordinaire",  0, 10));
                listHeros.add(tempMage);
                break;
            case "Healer":
                tempHealer = new Healer(name, 100);
                tempHealer.take(new Weapon("cure dent", "Healer", 5, 10));
                tempHealer.take(new Armor("Tunique de soie",  0, 10));
                listHeros.add(tempHealer);
                break;
            default:
                System.out.println("Y'a un gros problème dans Validate Hero");
        }
        if (getNbrHero() <= 1){//Si on a fini d'ajouter les héros.
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/CombatScene-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{//Si il reste des heros a rajouter
            minusNbrHero(1);//on retire 1 au compteur de héros a rajouter
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/chooseHeroType.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public static Image chargeImage(String url) throws Exception{
        //Image image = new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
        //System.out.println(image);
        File file = new File(url);
        Image image = new Image(file.toURI().toString());
        return image;
    }

    @FXML
    private ImageView HeroSlot;
    @FXML
    private TextField EnteredName;

    private String HeroTypeClicked;

    private Hero tempHero;
    private Warrior tempWarrior;
    private Hunter tempHunter;
    private Mage tempMage;
    private Healer tempHealer;

    private String name;

    private Stage stage;
    private Scene scene;
    private Parent root;
}
