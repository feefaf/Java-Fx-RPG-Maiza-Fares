package com.example.javafxrpgmaizafares;

import com.example.javafxrpgmaizafares.isep.rpg.Combatant;
import com.example.javafxrpgmaizafares.isep.rpg.gentils.*;
import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;
import com.example.javafxrpgmaizafares.isep.rpg.items.consumables.Food;
import com.example.javafxrpgmaizafares.isep.rpg.items.consumables.Potion;
import com.example.javafxrpgmaizafares.isep.rpg.mechants.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.example.javafxrpgmaizafares.ListHero.listHeros;

public class CombatSceneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println("Je passe par la :" + ROUND);
            HashMap<String, String> heroUrlType = new HashMap<>();
            heroUrlType.put("Warrior", "src/main/resources/sprites/heros/Warrior.png");
            heroUrlType.put("Hunter", "src/main/resources/sprites/heros/Hunter.png");
            heroUrlType.put("Mage", "src/main/resources/sprites/heros/Mage.png");
            heroUrlType.put("Healer", "src/main/resources/sprites/heros/Healer.png");

            HashMap<String, String> enemiesUrlType = new HashMap<>();
            enemiesUrlType.put("BersekerLion", "src/main/resources/sprites/enemies/BersekerLion.png");
            enemiesUrlType.put("Boss", "src/main/resources/sprites/enemies/Boss.png");
            enemiesUrlType.put("ItalienNotoire", "src/main/resources/sprites/enemies/ItalienNotoire.png");
            enemiesUrlType.put("Dragon", "src/main/resources/sprites/enemies/Dragon.png");
            enemiesUrlType.put("Pecheur", "src/main/resources/sprites/enemies/Pecheur.png");
            enemiesUrlType.put("SorcierMalefique", "src/main/resources/sprites/enemies/SorcierMalefique.png");

            if (comingFromMarket == false) {//après être passé au market il ne faut pas recharger tout ça.
                ROUND = 0;
                gold = 500;
                //CREATION DE TOUTES LES ARMES DISPONIBLES DU JEU!
                marketWeapon = new ArrayList<>();

                warriorWeapon = new ArrayList<>();
                hunterWeapon = new ArrayList<>();
                mageWeapon = new ArrayList<>();
                healerWeapon = new ArrayList<>();
                //ARMES DU WARRIOR
                Weapon tempWeapon;
                warriorWeapon.add(new Weapon("Eppee de FEU", "Warrior", 30, 50));
                warriorWeapon.add(new Weapon("Marteau étincelant", "Warrior", 45, 100));
                warriorWeapon.add(new Weapon("ULTRA Sabre", "Warrior", 70, 150));
                warriorWeapon.add(new Weapon("MIDNIGHTREAPER", "Warrior", 99, 300));
                //ARMES DU HUNTER
                hunterWeapon.add(new Weapon("Arbalète", "Hunter", 35, 50));
                hunterWeapon.add(new Weapon("Glock 9mm", "Hunter", 55, 100));
                hunterWeapon.add(new Weapon("AK-47", "Hunter", 80, 150));
                hunterWeapon.add(new Weapon("M82 Calibre.50", "Hunter", 120, 300));
                //ARMES DU MAGE
                mageWeapon.add(new Weapon("Baton de bois", "Mage", 5, 20));
                mageWeapon.add(new Weapon("Baton de pierre", "Mage", 10, 50));
                mageWeapon.add(new Weapon("Nokia 3310", "Mage", 12, 100));
                //ARMES DU HEALER
                healerWeapon.add(new Weapon("Sceptre ordinaire", "Healer", 10, 50));
                healerWeapon.add(new Weapon("Sceptre tranchant", "Healer", 15, 100));
                healerWeapon.add(new Weapon("Akimbo desert Eagle de Healer", "Healer", 40, 200));
                //CREATION DU MARCHEE D'ARMES
                marketWeapon = new ArrayList<>();
                marketWeapon.add(warriorWeapon);
                marketWeapon.add(hunterWeapon);
                marketWeapon.add(mageWeapon);
                marketWeapon.add(healerWeapon);

                //CREATION DES BOUCLIERS
                marketArmor = new ArrayList<>();
                marketArmor.add(new Armor("Bouclier de bois", (float) 0.10, 10));
                marketArmor.add(new Armor("Bouclier anti-émeute", (float) 0.20, 50));
                marketArmor.add(new Armor("Bouclier doré", (float) 0.30, 100));
                marketArmor.add(new Armor("Bouclier de diamant", (float) 0.50, 200));
                marketArmor.add(new Armor("YATA NO KAGAMI", (float) 0.80, 600));
                //

                //ces listes serviront d'inventaire a consommable
                foodInventory = new ArrayList<>();
                potionInventory = new ArrayList<>();

                //LISTE DES CONSUMMABLES INITIAUX
                //food
                foodInventory.add(new Food("lembas", 40, 2, 30));
                foodInventory.add(new Food("Bonbon", 100, 1, 60));
                //potions
                potionInventory.add(new Potion("Cannette Monster", 40, 2, 40));
                potionInventory.add(new Potion("white Spirit", 100, 1, 60));
                //

                //CREATION DES VAGUES D'ENNEMIS
                allEnemies = new ArrayList<>();

                //Creation de la première Vague
                Wave1 = new ArrayList<>();
                Wave1.add(new Pecheur("Sataniste", (30 * listHeros.size()), (10 * listHeros.size())));
                Wave1.add(new Pecheur("Mangeur d'âme", (20 * listHeros.size()), (15 * listHeros.size())));

                //Creation de la deuxième Vague
                Wave2 = new ArrayList<>();
                Wave2.add(new ItalienNotoire("Pizzaiolo en chef", (60 * listHeros.size()), (20 * listHeros.size())));
                Wave2.add(new ItalienNotoire("Pizzaiolo de Gauche(jlm <3)", (40 * listHeros.size()), (16 * listHeros.size())));
                Wave2.add(new ItalienNotoire("Pizzaiolo de Droite(vive le fn)", (40 * listHeros.size()), (16 * listHeros.size())));

                //Creation de la troisième Vague
                Wave3 = new ArrayList<>();
                Wave3.add(new BersekerLion("ROI LION BERSEKER", (200 * listHeros.size()), (45 * listHeros.size())));

                //Creation de la quatrième Vague
                Wave4 = new ArrayList<>();
                Wave4.add(new SorcierMalefique("Maître des dragons", (150 * listHeros.size()), (40 * listHeros.size())));
                Wave4.add(new Dragon("DRAGON ARAGNIR", (100 * listHeros.size()), (30 * listHeros.size())));
                Wave4.add(new Dragon("DRAGON NEFARIOUS", (100 * listHeros.size()), (30 * listHeros.size())));

                //Vague BOSS
                BossWave = new ArrayList<>();
                BossWave.add(new Boss("[[A P O C A L Y P S E]]", (1000 * listHeros.size()), (80 * listHeros.size())));


                //Ajout de toutes les vagues
                allEnemies.add(Wave1);
                allEnemies.add(Wave2);
                allEnemies.add(Wave3);
                allEnemies.add(Wave4);
                allEnemies.add(BossWave);


                //initialisation des images cliquables
                CliquablesHerosList = new ArrayList<>();
                IsImageHero1Cliquable =false;
                IsImageHero2Cliquable =false;
                IsImageHero3Cliquable =false;
                IsImageHero4Cliquable =false;

                CliquablesHerosList.add(IsImageHero1Cliquable);
                CliquablesHerosList.add(IsImageHero2Cliquable);
                CliquablesHerosList.add(IsImageHero3Cliquable);
                CliquablesHerosList.add(IsImageHero4Cliquable);
            }

            Marketplace = false;
            enemies = allEnemies.get(ROUND);
            ROUND +=1;//Pour que lors du prochain rechargement on recupère la vague d'ennemie suivante
            //ça peut etre une bonne idée de dire que si le tour == 5 alors *fin du jeu*

            comingFromMarket = false;

            HeroImageSlotsList = new ArrayList<>();
            HeroImageSlotsList.add(ImageHeroSlot1);
            HeroImageSlotsList.add(ImageHeroSlot2);
            HeroImageSlotsList.add(ImageHeroSlot3);
            HeroImageSlotsList.add(ImageHeroSlot4);

            HeroLabelSlotsList = new ArrayList<>();

            HeroLabelSlotsList.add(HeroNameLabel1);
            HeroLabelSlotsList.add(HeroNameLabel2);
            HeroLabelSlotsList.add(HeroNameLabel3);
            HeroLabelSlotsList.add(HeroNameLabel4);
            //Setting The correct images On the images View
            for (int i = 0; i < listHeros.size(); i++) {
                HeroImageSlotsList.get(i).setImage(chargeImage(heroUrlType.get(listHeros.get(i).getWhatAmI())));
                HeroLabelSlotsList.get(i).setText(listHeros.get(i).getName());
                CliquablesHerosList.set(i, true);
            }

            //correctly configuring the ennemies section

            EnemiesImageSlotsList = new ArrayList<>();

            EnemiesImageSlotsList.add(ImageEnnemySlotFront);
            EnemiesImageSlotsList.add(ImageEnnemySlotUp);
            EnemiesImageSlotsList.add(ImageEnnemySlotDown);

            EnnemiesLabelSlotsList = new ArrayList<>();

            EnnemiesLabelSlotsList.add(EnnemyNameLabelFront);
            EnnemiesLabelSlotsList.add(EnnemyNameLabelUp);
            EnnemiesLabelSlotsList.add(EnnemyNameLabelDown);

            CliquablesEnemiesList = new ArrayList<>();

            IsImageEnnemyFrontCliquable = false;
            IsImageEnnemyUpCliquable = false;
            IsImageEnnemyDownCliquable = false;

            CliquablesEnemiesList.add(IsImageEnnemyFrontCliquable);
            CliquablesEnemiesList.add(IsImageEnnemyUpCliquable);
            CliquablesEnemiesList.add(IsImageEnnemyDownCliquable);

            for (int i = 0; i < enemies.size(); i++) {
                System.out.println(enemiesUrlType.get(enemies.get(i).WhoAmI()));
                EnemiesImageSlotsList.get(i).setImage(chargeImage(enemiesUrlType.get(enemies.get(i).WhoAmI())));
                EnnemiesLabelSlotsList.get(i).setText(enemies.get(i).getName());
                CliquablesEnemiesList.set(i, true);

            }

            DisplayAllCombatantsInfos(listHeros, HeroHealthLabel ,enemies, EnemiesHealthLabel);


            RoundCounters(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);

        }catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

    public static void RoundCounters(Button AttackButton ,Button DefendButton, Button SpellCasterButton, Button ConsommableButton,
                                     Label ActionPrompterLabel, Label FightInfosPrompter) throws Exception {
        try{
            TOUR = 0;
            RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);

        }catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    public static void RoundProcess(Button AttackButton ,Button DefendButton, Button SpellCasterButton, Button ConsommableButton,
                                    Label ActionPrompterLabel, Label FightInfosPrompter) throws Exception {
        if(TOUR < listHeros.size()) {
            IsHerosTour = true;
            if (CliquablesHerosList.get(TOUR)){//Si le hero dont c'est le tour n'est pas mort.
                tempHeroTour = listHeros.get(TOUR);
                String message = "C'est le tour de " + listHeros.get(TOUR).getName() + " Que souhaitez vous faire ?";
                ActionPrompterLabel.setText(message);
                switch (listHeros.get(TOUR).getWhatAmI()) {
                    case "Warrior":
                        AttackButton.setText("Attaquer");
                        DefendButton.setText("Se Défendre");
                        SpellCasterButton.setText("");
                        ConsommableButton.setText("Consommable");
                        break;
                    case "Hunter":
                        AttackButton.setText("Attaquer");
                        DefendButton.setText("Se Défendre");
                        SpellCasterButton.setText("");
                        ConsommableButton.setText("Consommable,");
                        break;
                    case "Mage":
                        AttackButton.setText("Attaquer");
                        DefendButton.setText("Se Défendre");
                        SpellCasterButton.setText("Lancer Sort");
                        ConsommableButton.setText("Consommable");
                        break;
                    case "Healer":
                        AttackButton.setText("Attaquer");
                        DefendButton.setText("Se Défendre");
                        SpellCasterButton.setText("Soigner Allié");
                        ConsommableButton.setText("Consommable");
                        break;
                    default:
                        System.out.println("Il y'a un problème dans le RoundProcess");
                }
            }else{
                nextTOUR(1);
                RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
            }

        }else{
            System.out.println("c'est le tour des ennemis");
            IsHerosTour = false;
            int previousHp;
            int currentHp;
            int calculatedDamage;
            String RecapDegatsEnnemis = "";
            ActionPrompterLabel.setText("Au Tour des ennemis");

            RecapDegatsEnnemis += "BILAN DES DEGATS INFLIGES PAR L'ENNEMI:\n";
            for(int i =0; i < enemies.size();i++){
                if(CliquablesEnemiesList.get(i)){
                    int randomTarget = ThreadLocalRandom.current().nextInt(0, listHeros.size());
                    previousHp = listHeros.get(randomTarget).getHealthPoint();
                    enemies.get(i).attack(listHeros.get(randomTarget));
                    currentHp = listHeros.get(randomTarget).getHealthPoint();

                    calculatedDamage = previousHp - currentHp;
                    if (listHeros.get(randomTarget).getHealthPoint() <= 0){
                        RecapDegatsEnnemis += enemies.get(i).getName() + " a tué " + listHeros.get(randomTarget).getName()+"\n";
                        CliquablesHerosList.set(randomTarget, false);
                        HeroImageSlotsList.get(randomTarget).setImage(chargeImage("src/main/resources/sprites/heros/HeroTomb.png"));
                        boolean nullsOnly = CliquablesHerosList.stream().allMatch(x -> x == false);//Check if all heros are dead
                        if(nullsOnly){
                            ActionPrompterLabel.setText("Tous les Heros sont mort, c'est La fin du monde !");
                            //rajouter un écran de Game OVER
                        }

                    }else {
                        RecapDegatsEnnemis += enemies.get(i).getName() + " a infligé " + calculatedDamage + " DMG a " + listHeros.get(randomTarget).getName()+"\n";
                    }
                }

            }
            FightInfosPrompter.setText(RecapDegatsEnnemis);


            for(int i = 0; i < listHeros.size(); i++){
                listHeros.get(i).setIfInDefense(false);
            }
            TOUR = 0;
            RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);


            //c'est le tour des ennemis

        }
    }

    public static void DisplayAllCombatantsInfos(ArrayList <Hero> listHeros, Label HeroHealthLabel,
                                                 ArrayList <Ennemy> enemies, Label EnemiesHealthLabel){
        DisplayHerosInfo(listHeros, HeroHealthLabel);
        DisplayEnemiesInfo(enemies, EnemiesHealthLabel);
    }
    public static void DisplayHerosInfo(ArrayList <Hero> listHeros, Label HeroHealthLabel){
        Heroinfos = "";
        Heroinfos +="Les gentils\n";
        for (Combatant c : listHeros) {
            Heroinfos += ((Hero) c).getWhatAmI() + " - " + c.getName() + " : " + c.getHealthPoint() + " HP : " + c.getArmorPoint() + " DEF : " + (((c instanceof SpellCaster) ? ((SpellCaster) c).getMana() + " MANA " : "")) + (((c instanceof Hunter) ? ((Hunter) c).getAmmoCount() + " Ammo " : "")) + "\n";
        }
        HeroHealthLabel.setText(Heroinfos);

    }

    public static void DisplayEnemiesInfo(ArrayList <Ennemy> enemies, Label EnemiesHealthLabel){
        try {
            EnemyInfos = "";
            EnemyInfos += "Les Méchants\n";
            for (Combatant c : enemies) {
                EnemyInfos += c.getName() + " : " + c.getHealthPoint() + "HP : "+((Ennemy)c).getDamagePoints()+" ATK\n";
            }
                EnemiesHealthLabel.setText(EnemyInfos);

        }catch (Exception e) {
            throw new RuntimeException(e);

        }

    }


    public void Delayer(Label ActionPrompterLabel, Button SpellCasterButton) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);



    }

    //Getters pour le market
    public static List<ArrayList> getMarketWeapon(){
        return marketWeapon;
    }

    public static ArrayList<Armor> getMarketArmors(){
        return marketArmor;
    }

    public static ArrayList<Food> getFoodInventory(){
        return foodInventory;
    }

    public static ArrayList<Potion> getPotionInventory(){
        return potionInventory;
    }

    //au cas ou...
    public static ArrayList<Hero> getListHeros(){
        return listHeros;
    }

    public static ArrayList<Boolean> getCliquablesHerosList(){
        return CliquablesHerosList;
    }
    //setters pour le market
    public static void setFoodInventory(ArrayList<Food> newFoodInventory){
        foodInventory = newFoodInventory;
    }
    public static void setPotionInventory(ArrayList<Potion> newPotionInventory){
        potionInventory = newPotionInventory;
    }

    /*
    public static void UpdateListHeros(ArrayList<Hero> newHerosList){
        listHeros = newHerosList;
    }
     */

    public static int getGold() {
        return gold;
    }
    public static void looseMoney(int loss) {
        gold -= loss;
    }
    public void winMoney(int gain) {
        gold += gain;
    }

    public static String EnnemyStatus(Ennemy ennemy){
        String message = ennemy.getName()+" - "+ ennemy.getHealthPoint() +"HP :"+ennemy.getDamagePoints()+" ATK";
        return message;
    }

    public static boolean getIfComingFromMarket(){
        return comingFromMarket;
    }

    public static int getTOUR(){
        return TOUR;
    }

    public static void nextTOUR(int add){
        TOUR += add;
    }

    public static void setIfComingFromMarket(boolean isComingFromMarket){
        comingFromMarket = isComingFromMarket;
    }

    public static Image chargeImage(String url) throws Exception{
        //Image image = new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
        //System.out.println(image);
        File file = new File(url);
        Image image = new Image(file.toURI().toString());
        return image;
    }

    @FXML
    public void onClickHero1(MouseEvent mouseEvent) throws Exception {
        int heroIndex = 0;
        if(CliquablesHerosList.get(heroIndex)){
            if(IsHerosTour){
                if(canHeal){
                    FightInfosPrompter.setText(tempHeroTour.getName() + " Soigne "+ listHeros.get(heroIndex).getName()+" de "+ (((Healer)tempHeroTour).getHealPower())+" HP");
                    ((Healer)tempHeroTour).Heal(listHeros.get(heroIndex));
                    DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                    canHeal =false;
                    nextTOUR(1);
                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);

                }

            }
        }
    }

    @FXML
    public void onClickHero2(MouseEvent mouseEvent) throws Exception {
        int heroIndex = 1;
        if(CliquablesHerosList.get(heroIndex)){
            if(IsHerosTour){
                if(canHeal){
                    FightInfosPrompter.setText(tempHeroTour.getName() + " Soigne "+ listHeros.get(heroIndex).getName()+" de "+ (((Healer)tempHeroTour).getHealPower())+" HP");
                    ((Healer)tempHeroTour).Heal(listHeros.get(heroIndex));
                    DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                    canHeal =false;
                    nextTOUR(1);
                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);

                }

            }
        }
    }
    @FXML
    public void onClickHero3(MouseEvent mouseEvent) throws Exception {
        int heroIndex = 2;
        if(CliquablesHerosList.get(heroIndex)){
            if(IsHerosTour){
                if(canHeal){
                    FightInfosPrompter.setText(tempHeroTour.getName() + " Soigne "+ listHeros.get(heroIndex).getName()+" de "+ (((Healer)tempHeroTour).getHealPower())+" HP");
                    ((Healer)tempHeroTour).Heal(listHeros.get(heroIndex));
                    DisplayAllCombatantsInfos(listHeros, HeroHealthLabel, enemies, EnemiesHealthLabel);
                    canHeal =false;
                    nextTOUR(1);
                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);

                }

            }
        }
    }

    @FXML
    public void onClickHero4(MouseEvent mouseEvent) throws Exception {
        int heroIndex = 3;
        if(CliquablesHerosList.get(heroIndex)){
            if(IsHerosTour){
                if(canHeal){
                    FightInfosPrompter.setText(tempHeroTour.getName() + " Soigne "+ listHeros.get(heroIndex).getName()+" de "+ (((Healer)tempHeroTour).getHealPower())+" HP");
                    ((Healer)tempHeroTour).Heal(listHeros.get(heroIndex));
                    DisplayAllCombatantsInfos(listHeros, HeroHealthLabel, enemies, EnemiesHealthLabel);
                    canHeal =false;
                    nextTOUR(1);
                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);

                }
            }
        }
    }

    @FXML
    public void onClickEnnemyFront(MouseEvent mouseEvent) throws Exception {
            int previousHp;
            int currentHp;
            int damages;
            int enemyIndex = 0;
            DisplayHerosInfo(listHeros, HeroHealthLabel);
            if(CliquablesEnemiesList.get(enemyIndex)){
                if(IsHerosTour){
                    if(canAttack) {
                        previousHp = enemies.get(enemyIndex).getHealthPoint();
                        tempHeroTour.attack(enemies.get(enemyIndex));
                        currentHp = enemies.get(enemyIndex).getHealthPoint();
                        damages = previousHp - currentHp;
                         FightInfosPrompter.setText(tempHeroTour.getName() + " attaque " + enemies.get(enemyIndex).getName() + " et lui inflige " + damages + " DMG avec son "+tempHeroTour.getWeaponName());
                        System.out.println(tempHeroTour.getName() + " attaque " + enemies.get(enemyIndex).getName() + " et lui inflige " + damages + " DMG avec son "+tempHeroTour.getWeaponName());
                        canAttack = false;

                        DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);

                        if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                            FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                            enemies.get(enemyIndex).setHealthPoint(0);
                            CliquablesEnemiesList.set(enemyIndex, false);
                            EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                            boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                            if(nullsOnly){
                                canAttack = false;
                                canHeal =false;
                                IsHerosTour =false;
                                ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\nVous gagnez "+(500*ROUND)+" GOLDS");
                                AttackButton.setText("");
                                DefendButton.setText("");
                                SpellCasterButton.setText("MARKET !!!");
                                ConsommableButton.setText("");
                                Marketplace = true;
                            }
                        }

                        if (!Marketplace){
                            nextTOUR(1);
                            RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                        }
                    }else if(SpellMode){
                        switch (WhatSpell){
                            case "FireBall":
                                ((Mage)tempHeroTour).castFireball(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance une boule de feu sur "+ enemies.get(enemyIndex).getName() +" et inflige 25 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            case "LightningBolt":
                                ((Mage)tempHeroTour).castLightningBolt(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance un eclair sur "+ enemies.get(enemyIndex).getName() +" et inflige 40 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            case "ULTRALASER":
                                ((Mage)tempHeroTour).ultralaser(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance un ULTRALASER sur "+ enemies.get(enemyIndex).getName() +" et inflige 120 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n" +"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            default:
                                System.out.println("Il ya un problème dans le lancement des sorts");
                                break;
                        }
                    }

                }

            }
    }


    @FXML
    public void onClickEnnemyUp(MouseEvent mouseEvent) throws InterruptedException {
        try{
            int previousHp;
            int currentHp;
            int damages;
            int enemyIndex = 1;
            if(CliquablesEnemiesList.get(enemyIndex)){
                if(IsHerosTour){
                    if(canAttack) {
                        previousHp = enemies.get(enemyIndex).getHealthPoint();
                        tempHeroTour.attack(enemies.get(enemyIndex));
                        currentHp = enemies.get(enemyIndex).getHealthPoint();
                        damages = previousHp - currentHp;
                        FightInfosPrompter.setText(tempHeroTour.getName() + " attaque " + enemies.get(enemyIndex).getName() + " et lui inflige " + damages + " DMG avec son "+tempHeroTour.getWeaponName());
                        canAttack = false;

                        DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);

                        if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                            FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                            enemies.get(enemyIndex).setHealthPoint(0);
                            CliquablesEnemiesList.set(enemyIndex, false);// maintenant on ne peut plus cliquer sur cette case
                            EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));

                            boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                            if(nullsOnly){
                                canAttack = false;
                                canHeal =false;
                                IsHerosTour =false;
                                ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                AttackButton.setText("");
                                DefendButton.setText("");
                                SpellCasterButton.setText("MARKET !!!");
                                ConsommableButton.setText("");
                                Marketplace = true;
                            }
                        }
                        if (!Marketplace){
                            nextTOUR(1);
                            RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                        }

                    }else if(SpellMode){
                        switch (WhatSpell){
                            case "FireBall":
                                ((Mage)tempHeroTour).castFireball(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance une boule de feu sur "+ enemies.get(enemyIndex).getName() +" et inflige 25 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            case "LightningBolt":
                                ((Mage)tempHeroTour).castLightningBolt(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance un eclair sur "+ enemies.get(enemyIndex).getName() +" et inflige 40 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            case "ULTRALASER":
                                ((Mage)tempHeroTour).ultralaser(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance un ULTRALASER sur "+ enemies.get(enemyIndex).getName() +" et inflige 120 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            default:
                                System.out.println("Il ya un problème dans le lancement des sorts");
                                break;
                        }
                    }

                }
            }
        }catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @FXML
    public void onClickEnnemyDown(MouseEvent mouseEvent) throws Exception {
        try {
            int previousHp;
            int currentHp;
            int damages;
            int enemyIndex = 2;
            if (CliquablesEnemiesList.get(enemyIndex)) {
                if (IsHerosTour) {
                    if (canAttack) {
                        previousHp = enemies.get(enemyIndex).getHealthPoint();
                        tempHeroTour.attack(enemies.get(enemyIndex));
                        currentHp = enemies.get(enemyIndex).getHealthPoint();
                        damages = previousHp - currentHp;
                        FightInfosPrompter.setText(tempHeroTour.getName() + " attaque " + enemies.get(enemyIndex).getName() + " et lui inflige " + damages + " DMG avec son "+tempHeroTour.getWeaponName());
                        canAttack = false;

                        DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);

                        if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                            FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                            enemies.get(enemyIndex).setHealthPoint(0);
                            CliquablesEnemiesList.set(enemyIndex, false);
                            EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                            boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                            if(nullsOnly){
                                canAttack = false;
                                canHeal =false;
                                IsHerosTour =false;
                                ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                AttackButton.setText("");
                                DefendButton.setText("");
                                SpellCasterButton.setText("MARKET !!!");
                                ConsommableButton.setText("");
                                Marketplace = true;
                            }

                        }
                        if (!Marketplace){
                            nextTOUR(1);
                            RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                        }
                    }else if(SpellMode){
                        switch (WhatSpell){
                            case "FireBall":
                                ((Mage)tempHeroTour).castFireball(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance une boule de feu sur "+ enemies.get(enemyIndex).getName() +" et inflige 25 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            case "LightningBolt":
                                ((Mage)tempHeroTour).castLightningBolt(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance un eclair sur "+ enemies.get(enemyIndex).getName() +" et inflige 40 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            case "ULTRALASER":
                                ((Mage)tempHeroTour).ultralaser(enemies.get(enemyIndex));
                                FightInfosPrompter.setText(tempHeroTour.getName() + " Lance un ULTRALASER sur "+ enemies.get(enemyIndex).getName() +" et inflige 120 DMG");
                                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                                SpellMode = false;
                                canAttack = false;
                                canHeal =false;
                                WhatSpell = "";
                                if (enemies.get(enemyIndex).getHealthPoint() <= 0) {
                                    FightInfosPrompter.setText("Vous avez vaincu " + enemies.get(enemyIndex).getName());
                                    enemies.get(enemyIndex).setHealthPoint(0);
                                    CliquablesEnemiesList.set(enemyIndex, false);
                                    EnemiesImageSlotsList.get(enemyIndex).setImage(chargeImage("src/main/resources/sprites/enemies/EnemyTomb.png"));
                                    boolean nullsOnly = CliquablesEnemiesList.stream().allMatch(x -> x == false);
                                    if(nullsOnly){
                                        canAttack = false;
                                        canHeal =false;
                                        IsHerosTour =false;
                                        ActionPrompterLabel.setText("Felicitation ! Tout les méchants sont morts !\n"+"Vous gagnez"+(500*ROUND)+" GOLDS");
                                        AttackButton.setText("");
                                        DefendButton.setText("");
                                        SpellCasterButton.setText("MARKET !!!");
                                        ConsommableButton.setText("");
                                        Marketplace = true;
                                    }
                                }

                                if (!Marketplace){
                                    nextTOUR(1);
                                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                                }
                                break;
                            default:
                                System.out.println("Il ya un problème dans le lancement des sorts");
                                break;
                        }
                    }
                }
            }
        }catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    @FXML
    public void onAttackButtonClicked() throws Exception {
        if(IsHerosTour){
            if(!(SpellMode) & !(ConsumeMode)){
                ActionPrompterLabel.setText("Cliquez sur l'ennemi que vous Souhaitez attaquer");
                canAttack = true;
            }else if(SpellMode){
                ActionPrompterLabel.setText("Cliquez sur l'ennemi sur lequel vous souhaitez lancer ce sort");
                WhatSpell = "FireBall";

            }else if(ConsumeMode){
                if (foodInventory.get(0).getQuantity() > 0){
                    foodInventory.get(0).eat(tempHeroTour);
                    FightInfosPrompter.setText(tempHeroTour.getName()+" Mange un lembas et regagne 40HP");
                }else{
                    FightInfosPrompter.setText("Vous n'avez plus de lembas :(");
                }

                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                nextTOUR(1);
                ConsumeMode = false;
                SpellCasterConsumeMode = false;
                SpellMode = false;
                canAttack = false;
                canHeal =false;
                RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
            }
        }
    }

    @FXML
    public void onDefendButtonClicked(MouseEvent mouseEvent) throws Exception {
        if(IsHerosTour){
            if(!(SpellMode) & !(ConsumeMode)){
                ActionPrompterLabel.setText(tempHeroTour + " se met en garde !");
                tempHeroTour.setIfInDefense(true);
                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                nextTOUR(1);
                RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
            }else if(SpellMode){
                ActionPrompterLabel.setText("Cliquez sur l'ennemi sur lequel vous souhaitez lancer ce sort");
                WhatSpell = "LightningBolt";
            }else if(ConsumeMode){
                if (foodInventory.get(1).getQuantity() > 0){
                    foodInventory.get(1).eat(tempHeroTour);
                    FightInfosPrompter.setText(tempHeroTour.getName()+" Mange un bonbon magique et regagne 100HP");
                }else{
                    FightInfosPrompter.setText("Vous n'avez plus de Bonbon :(");
                }

                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                nextTOUR(1);
                ConsumeMode = false;
                SpellCasterConsumeMode = false;
                SpellMode = false;
                canAttack = false;
                canHeal =false;
                RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
            }

        }
    }

    @FXML
    public void OnSpellCasterButtonClicked(MouseEvent event) throws Exception {
        if(IsHerosTour){
            if(!(SpellMode) & !(ConsumeMode)){
                if (tempHeroTour instanceof Mage) {
                    SpellMode = true;
                    ActionPrompterLabel.setText("Selectionnez un Sort.");
                    AttackButton.setText("FireBall");
                    DefendButton.setText("Lightning Bolt");
                    SpellCasterButton.setText("ULTRALASER");
                    ConsommableButton.setText("Concentrate");
                } else if (tempHeroTour instanceof Healer) {
                    canHeal = true;
                    ActionPrompterLabel.setText("Cliquez sur le hero que vous souhaitez soigner.");
                }
            }else if (SpellMode){
                ActionPrompterLabel.setText("Cliquez sur l'ennemi sur lequel vous souhaitez lancer ce sort");
                WhatSpell = "ULTRALASER";
            }else if(ConsumeMode){
                if(SpellCasterConsumeMode){
                    if (potionInventory.get(0).getQuantity() > 0){
                        potionInventory.get(0).usePotion(((SpellCaster)tempHeroTour));
                        FightInfosPrompter.setText(tempHeroTour.getName()+" bois une canette de Monster et regagne 40MANA");
                    }else{
                        FightInfosPrompter.setText("Vous n'avez plus de Canette de Monster :(");
                    }

                    DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                    nextTOUR(1);
                    ConsumeMode = false;
                    SpellCasterConsumeMode = false;
                    SpellMode = false;
                    canAttack = false;
                    canHeal =false;
                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                }
            }

        }else if (Marketplace){
            System.out.println("Je passe la je!bhkvbuikzebcu:kcbezhykcvv");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxrpgmaizafares/MarketController-view.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void onConsumableButtonClicked(MouseEvent mouseEvent) throws Exception {
        if(IsHerosTour){
            if(!(SpellMode)& !(ConsumeMode)){
                ConsumeMode = true;
                ActionPrompterLabel.setText("Que souhaitez vous consommer ?");

                AttackButton.setText(foodInventory.get(0).getQuantity()+ "X Lembas:40HP");
                DefendButton.setText(foodInventory.get(1).getQuantity()+ "X Bonbon:100HP");
                if(tempHeroTour instanceof SpellCaster){
                    SpellCasterConsumeMode = true;
                    SpellCasterButton.setText(potionInventory.get(0).getQuantity()+"X CanetteMonster:\n40MANA");
                    ConsommableButton.setText(potionInventory.get(1).getQuantity()+"X White Spirit:\n100MANA");
                }

            }else if (SpellMode){
                ((Mage)tempHeroTour).concentrate();
                FightInfosPrompter.setText(tempHeroTour.getName() + " se concentre et regagne 40 MANA");
                DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                nextTOUR(1);
                SpellMode = false;
                canAttack = false;
                canHeal =false;
                RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
            }else if(ConsumeMode){
                if(SpellCasterConsumeMode){
                    if (potionInventory.get(1).getQuantity() > 0){
                        potionInventory.get(1).usePotion(((SpellCaster)tempHeroTour));
                        FightInfosPrompter.setText(tempHeroTour.getName()+" bois de la WhiteSpirit et regagne 100MANA");
                    }else{
                        FightInfosPrompter.setText("Vous n'avez plus de WhiteSpirit :(");
                    }

                    DisplayAllCombatantsInfos(listHeros, HeroHealthLabel,enemies, EnemiesHealthLabel);
                    nextTOUR(1);
                    ConsumeMode = false;
                    SpellCasterConsumeMode = false;
                    SpellMode = false;
                    canAttack = false;
                    canHeal =false;
                    RoundProcess(AttackButton, DefendButton, SpellCasterButton ,ConsommableButton , ActionPrompterLabel ,FightInfosPrompter);
                }
            }

        }
    }


    //private static boolean IfAllEnemiesDead(ArrayList<Ennemy> enemies, Button SpellCasterButton){

    //}


    //Buttons
    @FXML
    private Button AttackButton;
    @FXML
    private Button DefendButton;
    @FXML
    private Button SpellCasterButton;
    @FXML
    private Button ConsommableButton;

    //Booleans
    private static boolean IsHerosTour;
    private static boolean canAttack = false;
    private static boolean canHeal = false;
    //Modes
    private static boolean SpellMode = false;
    private static boolean ConsumeMode = false;
    private static boolean SpellCasterConsumeMode = false;
    private static boolean Marketplace = false;

    //Mage spell String
    private static String WhatSpell = "";

    //All informations label
    @FXML
    Label ActionPrompterLabel;
    @FXML
    Label FightInfosPrompter;
    @FXML
    Label HeroHealthLabel;
    @FXML
    Label EnemiesHealthLabel;

    //Images Slot for heros
    @FXML
    private ImageView ImageHeroSlot1;
    @FXML
    private ImageView ImageHeroSlot2;
    @FXML
    private ImageView ImageHeroSlot3;
    @FXML
    private ImageView ImageHeroSlot4;

    private static ArrayList<ImageView> HeroImageSlotsList;

    //Images Slot for Ennemies
    @FXML
    private ImageView ImageEnnemySlotUp;
    @FXML
    private ImageView ImageEnnemySlotFront;
    @FXML
    private ImageView ImageEnnemySlotDown;

    private static ArrayList<ImageView> EnemiesImageSlotsList;


    @FXML
    public Label HeroNameLabel1;
    @FXML
    public Label HeroNameLabel2;
    @FXML
    public Label HeroNameLabel3;
    @FXML
    public Label HeroNameLabel4;

    @FXML
    public Label EnnemyNameLabelFront;
    @FXML
    public Label EnnemyNameLabelUp;
    @FXML
    public Label EnnemyNameLabelDown;
    private  ArrayList<Label> HeroLabelSlotsList;
    private ArrayList<Label> EnnemiesLabelSlotsList;



    //Classics attributes
    private static String Heroinfos;
    private static String EnemyInfos;


    private static List<ArrayList> allEnemies;
    private static ArrayList<Ennemy> enemies;

    private ArrayList<Ennemy> Wave1;
    private ArrayList<Ennemy> Wave2;
    private ArrayList<Ennemy> Wave3;
    private ArrayList<Ennemy> Wave4;
    private ArrayList<Ennemy> BossWave;


    private static List<ArrayList> marketWeapon;
    private ArrayList<Weapon> warriorWeapon;
    private ArrayList<Weapon> hunterWeapon;
    private ArrayList<Weapon> mageWeapon;
    private ArrayList<Weapon> healerWeapon;

    private static ArrayList<Armor> marketArmor;

    private static ArrayList<Food> foodInventory;
    private static ArrayList<Potion> potionInventory;
    private static boolean comingFromMarket;
    
    private static int ROUND;

    private static int TOUR;


    //Attributs liés aux boutons et aux Images cliquables:
    private static ArrayList<Boolean> CliquablesHerosList;

    private static boolean IsImageHero1Cliquable;
    private static boolean IsImageHero2Cliquable;
    private static boolean IsImageHero3Cliquable;
    private static boolean IsImageHero4Cliquable;

    private static ArrayList<Boolean> CliquablesEnemiesList;
    private static boolean IsImageEnnemyFrontCliquable;
    private static boolean IsImageEnnemyUpCliquable;
    private static boolean IsImageEnnemyDownCliquable;

    private static Hero tempHeroTour;

    private static int gold;

    private Stage stage;
    private Scene scene;
    private Parent root;


}
