package com.example.javafxrpgmaizafares;

import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;
import com.example.javafxrpgmaizafares.isep.rpg.items.consumables.Food;
import com.example.javafxrpgmaizafares.isep.rpg.items.consumables.Potion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.javafxrpgmaizafares.CombatSceneController.chargeImage;
import static com.example.javafxrpgmaizafares.CombatSceneController.setIfComingFromMarket;
import static com.example.javafxrpgmaizafares.ListHero.listHeros;

public class MarketController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            System.out.println("Je passe dans le initialize comme une personne civilisé");
            GoldValue.setText("GOLD : "+CombatSceneController.getGold());
            //on initialises les items du market
            MarketWeapon = CombatSceneController.getMarketWeapon();
            MarketArmor = CombatSceneController.getMarketArmors();

            foodInventory = CombatSceneController.getFoodInventory();
            potionInventory = CombatSceneController.getPotionInventory();

            ArrayList<Boolean> HerosEnVie = CombatSceneController.getCliquablesHerosList();

            HashMap<String, String> heroUrlType = new HashMap<>();
            heroUrlType.put("Warrior", "src/main/resources/sprites/heros/Warrior.png");
            heroUrlType.put("Hunter", "src/main/resources/sprites/heros/Hunter.png");
            heroUrlType.put("Mage", "src/main/resources/sprites/heros/Mage.png");
            heroUrlType.put("Healer", "src/main/resources/sprites/heros/Healer.png");

            HeroTypeIndex = new HashMap<>();

            HeroTypeIndex.put("Warrior", 0);
            HeroTypeIndex.put("Hunter", 1);
            HeroTypeIndex.put("Mage", 2);
            HeroTypeIndex.put("Healer", 3);

            //initialiser les Images et les selectBox
            AvailableSections = new ArrayList<>();
            AvailableSections.add(Section1);
            AvailableSections.add(Section2);
            AvailableSections.add(Section3);
            AvailableSections.add(Section4);

            for(int i =0; i<HerosEnVie.size(); i++){
                AvailableSections.set(i, HerosEnVie.get(i));//Les sections seront ouvertes qu'aux Heros en vie
            }


            //On va créer toute les clés et les valeurs necessaires pour bien instancier les selectBox
            //AllKeys = new ArrayList<ArrayList>();
            AllWarriorKeys = new HashMap<>();
            for(int i =0; i < MarketWeapon.get(0).size(); i++){
                tempItem = (Weapon)(MarketWeapon.get(0).get(i));
                AllWarriorKeys.put(tempItem.priceInfo(), i);
            }
            AllHunterKeys = new HashMap<>();
            for(int i =0; i < MarketWeapon.get(1).size(); i++){
                tempItem = (Weapon)(MarketWeapon.get(1).get(i));
                System.out.println(tempItem.priceInfo());
                AllHunterKeys.put(tempItem.priceInfo(), i);
            }
            AllMageKeys = new HashMap<>();
            for(int i =0; i < MarketWeapon.get(2).size(); i++){
                tempItem = (Weapon)(MarketWeapon.get(2).get(i));
                AllMageKeys.put(tempItem.priceInfo(), i);
            }
            AllHealerKeys = new HashMap<>();
            for(int i =0; i < MarketWeapon.get(3).size(); i++){
                tempItem = (Weapon)(MarketWeapon.get(3).get(i));
                AllHealerKeys.put(tempItem.priceInfo(), i);
            }

            AllWeaponKeys = new ArrayList<>();
            AllWeaponKeys.add(AllWarriorKeys);
            AllWeaponKeys.add(AllHunterKeys);
            AllWeaponKeys.add(AllMageKeys);
            AllWeaponKeys.add(AllHealerKeys);

            AllArmorKeys = new HashMap<>();
            for(int i =0; i < MarketArmor.size(); i++){
                tempItem = (Item)(MarketArmor.get(i));
                AllArmorKeys.put(tempItem.getName()+"-"+(int)((((Armor)tempItem).getDefValue())*100)+"% DEF:" +tempItem.getPrice()+"Gold", i);
            }


            if(AvailableSections.get(0)){
                HeroSlotNameLabel1.setText(listHeros.get(0).getName());
                HeroImageSlot1.setImage(chargeImage(heroUrlType.get(listHeros.get(0).getWhatAmI())));
                WeaponChoiceBox1.getItems().addAll(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(0).getWhatAmI())).keySet());
                ArmorChoiceBox1.getItems().addAll(AllArmorKeys.keySet());
            }else{
                HeroSlotNameLabel1.setVisible(false);
                HeroImageSlot1.setVisible(false);
                WeaponChoiceBox1.setVisible(false);
                ArmorChoiceBox1.setVisible(false);
                BuyWeaponButton1.setVisible(false);
                BuyArmorButton1.setVisible(false);
            }

            if(AvailableSections.get(1)){
                HeroSlotNameLabel2.setText(listHeros.get(1).getName());
                HeroImageSlot2.setImage(chargeImage(heroUrlType.get(listHeros.get(1).getWhatAmI())));
                WeaponChoiceBox2.getItems().addAll(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(1).getWhatAmI())).keySet());
                ArmorChoiceBox2.getItems().addAll(AllArmorKeys.keySet());
            }else{
                HeroSlotNameLabel2.setVisible(false);
                HeroImageSlot2.setVisible(false);
                WeaponChoiceBox2.setVisible(false);
                ArmorChoiceBox2.setVisible(false);
                BuyWeaponButton2.setVisible(false);
                BuyArmorButton2.setVisible(false);
            }

            if(AvailableSections.get(2)){
                HeroSlotNameLabel3.setText(listHeros.get(2).getName());
                HeroImageSlot3.setImage(chargeImage(heroUrlType.get(listHeros.get(2).getWhatAmI())));
                WeaponChoiceBox3.getItems().addAll(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(2).getWhatAmI())).keySet());
                ArmorChoiceBox3.getItems().addAll(AllArmorKeys.keySet());
            }else{
                HeroSlotNameLabel3.setVisible(false);
                HeroImageSlot3.setVisible(false);
                WeaponChoiceBox3.setVisible(false);
                ArmorChoiceBox3.setVisible(false);
                BuyWeaponButton3.setVisible(false);
                BuyArmorButton3.setVisible(false);
            }

            if(AvailableSections.get(3)){
                HeroSlotNameLabel4.setText(listHeros.get(3).getName());
                HeroImageSlot4.setImage(chargeImage(heroUrlType.get(listHeros.get(3).getWhatAmI())));
                WeaponChoiceBox4.getItems().addAll(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(3).getWhatAmI())).keySet());
                ArmorChoiceBox4.getItems().addAll(AllArmorKeys.keySet());
            }else{
                HeroSlotNameLabel4.setVisible(false);
                HeroImageSlot4.setVisible(false);
                WeaponChoiceBox4.setVisible(false);
                ArmorChoiceBox4.setVisible(false);
                BuyWeaponButton4.setVisible(false);
                BuyArmorButton4.setVisible(false);
            }


        }catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @FXML
    public void onWeaponChoiceClick1(MouseEvent mouseEvent) {
        if(AvailableSections.get(0)){
            int heroIndex = 0;
            tempValue = (String)(WeaponChoiceBox1.getValue());
            if (tempValue != null && !tempValue.isEmpty()){
                //cette longue ligne va juste permettre de localiser l'item choisi dans les armes du market
                tempWeapon = (Weapon)MarketWeapon.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(((int)(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(tempValue))));
                if(CombatSceneController.getGold() >= tempWeapon.getPrice()){
                    listHeros.get(heroIndex).take(tempWeapon);
                    BuyPrompter.setText(tempWeapon.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempWeapon.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }
        }
    }
    @FXML
    public void onArmorChoiceClick1(MouseEvent mouseEvent) {
        if(AvailableSections.get(0)){
            int heroIndex = 0;
            tempValue = (String) ArmorChoiceBox1.getValue();
            if (tempValue != null && !tempValue.isEmpty()){
                tempArmor = MarketArmor.get(AllArmorKeys.get(tempValue));
                if(CombatSceneController.getGold() >= tempArmor.getPrice()){
                    listHeros.get(heroIndex).take(tempArmor);
                    BuyPrompter.setText(tempArmor.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempArmor.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }
        }
    }
    @FXML
    public void onWeaponChoiceClick2(MouseEvent mouseEvent) {
        if(AvailableSections.get(1)){
            int heroIndex = 1;
            tempValue = (String)(WeaponChoiceBox2.getValue());
            if (tempValue != null && !tempValue.isEmpty()){
                //cette longue ligne va juste permettre de localiser l'item choisi dans les armes du market
                tempWeapon = (Weapon)MarketWeapon.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(((int)(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(tempValue))));
                if(CombatSceneController.getGold() >= tempWeapon.getPrice()){
                    listHeros.get(heroIndex).take(tempWeapon);
                    BuyPrompter.setText(tempWeapon.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempWeapon.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }

        }
    }
    @FXML
    public void onArmorChoiceClick2(MouseEvent mouseEvent) {
        if(AvailableSections.get(1)){
            int heroIndex = 1;
            tempValue = (String) ArmorChoiceBox2.getValue();
            if (tempValue != null && !tempValue.isEmpty()){
                tempArmor = MarketArmor.get(AllArmorKeys.get(tempValue));
                if(CombatSceneController.getGold() >= tempArmor.getPrice()){
                    listHeros.get(heroIndex).take(tempArmor);
                    BuyPrompter.setText(tempArmor.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempArmor.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }

        }
    }
    @FXML
    public void onWeaponChoiceClick3(MouseEvent mouseEvent) {
        if(AvailableSections.get(2)){
            int heroIndex = 2;
            tempValue = (String)(WeaponChoiceBox3.getValue());
            if (tempValue != null && !tempValue.isEmpty()){
                //cette longue ligne va juste permettre de localiser l'item choisi dans les armes du market
                tempWeapon = (Weapon)MarketWeapon.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(((int)(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(tempValue))));
                if(CombatSceneController.getGold() >= tempWeapon.getPrice()){
                    listHeros.get(heroIndex).take(tempWeapon);
                    BuyPrompter.setText(tempWeapon.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempWeapon.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }

        }
    }
    @FXML
    public void onArmorChoiceClick3(MouseEvent mouseEvent) {
        if(AvailableSections.get(2)){
            int heroIndex = 2;
            tempValue = (String) ArmorChoiceBox3.getValue();
            if (tempValue != null && !tempValue.isEmpty()){
                tempArmor = MarketArmor.get(AllArmorKeys.get(tempValue));
                if(CombatSceneController.getGold() >= tempArmor.getPrice()){
                    listHeros.get(heroIndex).take(tempArmor);
                    BuyPrompter.setText(tempArmor.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempArmor.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }

        }
    }
    @FXML
    public void onWeaponChoiceClick4(MouseEvent mouseEvent) {
        if(AvailableSections.get(3)){
            int heroIndex = 3;
            tempValue = (String)(WeaponChoiceBox4.getValue());
            if (tempValue != null && !tempValue.isEmpty()){
                //cette longue ligne va juste permettre de localiser l'item choisi dans les armes du market
                tempWeapon = (Weapon)MarketWeapon.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(((int)(AllWeaponKeys.get(HeroTypeIndex.get(listHeros.get(heroIndex).getWhatAmI())).get(tempValue))));
                if(CombatSceneController.getGold() >= tempWeapon.getPrice()){
                    listHeros.get(heroIndex).take(tempWeapon);
                    BuyPrompter.setText(tempWeapon.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempWeapon.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }

        }
    }
    @FXML
    public void onArmorChoiceClick4(MouseEvent mouseEvent) {
        if(AvailableSections.get(3)){
            int heroIndex = 3;
            tempValue = (String) ArmorChoiceBox4.getValue();
            if (tempValue != null && !tempValue.isEmpty()){
                tempArmor = MarketArmor.get(AllArmorKeys.get(tempValue));
                if(CombatSceneController.getGold() >= tempArmor.getPrice()){
                    listHeros.get(heroIndex).take(tempArmor);
                    BuyPrompter.setText(tempArmor.getName()+" est désormais équipé par " + listHeros.get(heroIndex).getName());
                    CombatSceneController.looseMoney(tempArmor.getPrice());
                    GoldValue.setText("GOLD : "+CombatSceneController.getGold());
                }else{
                    BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
                }
            }
        }
    }

    @FXML
    public void onEndMarket(MouseEvent event) throws IOException {
        setIfComingFromMarket(true);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/CombatScene-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void BuyLembas(MouseEvent mouseEvent) {
        int foodIndex = 0;//position in the inventory
        if(CombatSceneController.getGold() >= foodInventory.get(foodIndex).getPrice()){
            foodInventory.get(foodIndex).addQuantity(1);
            BuyPrompter.setText("1 Lembas acheté, tu en as désormais " + foodInventory.get(foodIndex).getQuantity());
            CombatSceneController.looseMoney(foodInventory.get(foodIndex).getPrice());
            GoldValue.setText("GOLD : "+CombatSceneController.getGold());
        }else{
            BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
        }
    }

    public void BuyBonbon(MouseEvent mouseEvent) {
        int foodIndex = 1;//position in the inventory
        if(CombatSceneController.getGold() >= foodInventory.get(foodIndex).getPrice()){
            foodInventory.get(foodIndex).addQuantity(1);
            BuyPrompter.setText("1 Bonbon acheté, tu en as désormais " + foodInventory.get(foodIndex).getQuantity());
            CombatSceneController.looseMoney(foodInventory.get(foodIndex).getPrice());
            GoldValue.setText("GOLD : "+CombatSceneController.getGold());
        }else{
            BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
        }
    }

    public void BuyMonsterCan(MouseEvent mouseEvent) {
        int potionIndex = 0;//position in the inventory
        if(CombatSceneController.getGold() >= potionInventory.get(potionIndex).getPrice()){
            potionInventory.get(potionIndex).addQuantity(1);
            BuyPrompter.setText("1 Canette de monster acheté, tu en as désormais " + potionInventory.get(potionIndex).getQuantity());
            CombatSceneController.looseMoney(potionInventory.get(potionIndex).getPrice());
            GoldValue.setText("GOLD : "+CombatSceneController.getGold());
        }else{
            BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
        }
    }

    public void BuyWhiteSpirit(MouseEvent mouseEvent) {
        int potionIndex = 0;//position in the inventory
        if(CombatSceneController.getGold() >= potionInventory.get(potionIndex).getPrice()){
            potionInventory.get(potionIndex).addQuantity(1);
            BuyPrompter.setText("1 WhiteSpirit acheté, tu en as désormais " + potionInventory.get(potionIndex).getQuantity());
            CombatSceneController.looseMoney(potionInventory.get(potionIndex).getPrice());
            GoldValue.setText("GOLD : "+CombatSceneController.getGold());
        }else{
            BuyPrompter.setText("Tu n'as pas assez d'argent pour acheter ça.");
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    private List<ArrayList> MarketWeapon;
    private ArrayList<Armor> MarketArmor;

    private ArrayList<Food> foodInventory;
    private ArrayList<Potion> potionInventory;

    private ArrayList<Boolean> AvailableSections;
    private boolean Section1 =false;
    private boolean Section2 =false;
    private boolean Section3 =false;
    private boolean Section4 =false;

    private ArrayList<HashMap> AllWeaponKeys;
    private HashMap<String, Integer> AllWarriorKeys;
    private HashMap<String, Integer> AllHunterKeys;
    private HashMap<String, Integer> AllMageKeys;
    private HashMap<String, Integer> AllHealerKeys;
    private Item tempItem;

    private HashMap<String, Integer> AllArmorKeys;


    private HashMap<String, Integer> HeroTypeIndex;
    private String tempValue;
    private Weapon tempWeapon;
    private Armor tempArmor;

    @FXML
    public Label BuyPrompter;
    @FXML
    public ImageView HeroImageSlot1;
    @FXML
    public Label HeroSlotNameLabel1;
    @FXML
    public ImageView HeroImageSlot2;
    @FXML
    public Label HeroSlotNameLabel2;
    @FXML
    public ChoiceBox WeaponChoiceBox1;
    @FXML
    public ChoiceBox ArmorChoiceBox1;
    @FXML
    public Button BuyWeaponButton1;
    @FXML
    public Button BuyArmorButton1;
    @FXML
    public ChoiceBox WeaponChoiceBox2;
    @FXML
    public ChoiceBox ArmorChoiceBox2;
    @FXML
    public Button BuyWeaponButton2;
    @FXML
    public Button BuyArmorButton2;
    @FXML
    public ImageView HeroImageSlot3;
    @FXML
    public Label HeroSlotNameLabel3;
    @FXML
    public ChoiceBox WeaponChoiceBox3;
    @FXML
    public ChoiceBox ArmorChoiceBox3;
    @FXML
    public Button BuyWeaponButton3;
    @FXML
    public Button BuyArmorButton3;
    @FXML
    public ImageView HeroImageSlot4;
    @FXML
    public Label HeroSlotNameLabel4;
    @FXML
    public ChoiceBox WeaponChoiceBox4;
    @FXML
    public ChoiceBox ArmorChoiceBox4;
    @FXML
    public Button BuyWeaponButton4;
    @FXML
    public Button BuyArmorButton4;

    @FXML
    public Label GoldValue;



}
