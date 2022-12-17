package com.example.javafxrpgmaizafares.isep.rpg;



import com.example.javafxrpgmaizafares.isep.rpg.gentils.*;
import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;
import com.example.javafxrpgmaizafares.isep.rpg.items.consumables.Food;
import com.example.javafxrpgmaizafares.isep.rpg.items.consumables.Potion;
import com.example.javafxrpgmaizafares.isep.rpg.mechants.*;
import com.example.javafxrpgmaizafares.isep.utils.InputParser;
import com.example.javafxrpgmaizafares.isep.rpg.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Game {

    public Game(InputParser inputParser) {
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
        marketArmor.add(new Armor("Bouclier de diamant", (float)0.50, 200));
        marketArmor.add(new Armor("YATA NO KAGAMI", (float)0.80, 600));
        //

        //ces listes serviront d'inventaire a consommable
        foodInventory = new ArrayList<>();
        potionInventory = new ArrayList<>();

        //LISTE DES CONSUMMABLES INITIAUX
        //food
        foodInventory.add(new Food("Couscous", 30, 2, 20));
        foodInventory.add(new Food("lembas", 40, 2, 30));
        foodInventory.add(new Food("Bonbon", 100, 1, 60));
        //potions
        potionInventory.add(new Potion("Potion de Mana", 20, 2, 20));
        potionInventory.add(new Potion("Canette de Monster", 40, 2, 40));
        potionInventory.add(new Potion("white Spirit", 100, 1, 60));
        //


        System.out.println("La fin du monde approche à grands pas. Apocalypse, la créature la plus terrifiante et\n" +
                        " la plus puissante jamais vue, s'apprête à dévaster tout sur son passage.\n"+
                "Mais tout espoir n'est pas perdu. Vous êtes le leader d'une équipe de héros, déterminés à combattre\n" +
                        " cette menace et à sauver le monde.\n" +
                        "Ensemble, vous affronterez tous les ennemis qui se dresseront sur votre chemin et \n" +
                        "vous aurez l'opportunité de vaincre Apocalypse une fois pour toutes. \n" +
                "La bataille finale a commencé. Préparez-vous à sauver le monde de l'Apocalypse.");

        this.inputParser = inputParser;

        // Il faut normalement 4 héros de types différents...
        heros = new ArrayList<>();
        Scanner sc = new Scanner (System.in);
        do {
            System.out.println("Avec combien de héros voulez vous partir a l'aventure? : ");
            nbrHero = sc.nextInt(); // On lit la réponse de l'utilisateur
            // Si l'utilisateur a choisi un nombre qui n'est pas entre 1 et 4,
            // on affiche un message d'erreur et on recommence la boucle
            if (nbrHero < 1 || nbrHero > 4) {
                System.out.println("Vous ne pouvez avoir qu'1 a 4 héros maximum !");
            }
        } while (nbrHero < 1 || nbrHero > 4);

        Scanner sc2 = new Scanner (System.in);

        for (int i = 0; i<nbrHero; i++) {//cette boucle de test va juste nous permettre de donner un nom et des pv aux guerriers
            System.out.println("Hero n°"+(i+1)+" - Quel Héros souhaitez vous ajouter a votre équipe? :\n- Warrior\n- Hunter\n- Mage\n- Healer\n");
            typeHero = sc2.nextLine();
            switch (typeHero.toLowerCase()) {
                case "warrior":
                    System.out.println("C'est quoi son p'tit nom? : ");
                    name = sc2.nextLine();
                    tempWarrior = new Warrior(name);
                    tempWarrior.take(new Weapon("Eppee en bois", "Warrior", 20, 10));
                    tempWarrior.take(new Armor("Pull ordinaire", 0, 10));
                    System.out.println(tempWarrior.getName()+" rejoint l'aventure\nIl possède une "+
                            tempWarrior.getWeaponName()+" : "+tempWarrior.getWeaponDamage()+" DMG"+
                            "\nEt un "+tempWarrior.getArmorName()+" : "+tempWarrior.getArmorPoint()+" DEF\n");
                    heros.add(tempWarrior);

                    break;
                case "hunter":
                    System.out.println("C'est quoi son p'tit nom? : ");
                    name = sc2.nextLine();
                    tempHunter = new Hunter(name);
                    tempHunter.take(new Weapon("Arc", "Hunter", 25, 10));
                    tempHunter.take(new Armor("Veste ordinaire", 0, 10));
                    System.out.println(tempHunter.getName()+" rejoint l'aventure\nIl possède un "+
                            tempHunter.getWeaponName()+" : "+tempHunter.getWeaponDamage()+" DMG"+
                            "\nEt un "+tempHunter.getArmorName()+" : "+tempHunter.getArmorPoint()+" DEF\n");
                    heros.add(tempHunter);

                    break;
                case "mage":
                    System.out.println("C'est quoi son p'tit nom? : ");
                    name = sc2.nextLine();
                    tempMage = new Mage(name, 100);
                    tempMage.take(new Weapon("Baton en carton", "Mage", 3, 10));
                    tempMage.take(new Armor("Robe ordinaire",  0, 10));
                    System.out.println(tempMage.getName()+" rejoint l'aventure\nIl possède un "+
                            tempMage.getWeaponName()+" : "+tempMage.getWeaponDamage()+" DMG"+
                            "\nEt un "+tempMage.getArmorName()+" : "+tempMage.getArmorPoint()+" DEF\n");
                    heros.add(tempMage);

                    break;
                case "healer":
                    System.out.println("C'est quoi son p'tit nom? : ");
                    name = sc2.nextLine();
                    tempHealer = new Healer(name, 100);
                    tempHealer.take(new Weapon("cure dent", "Healer", 5, 10));
                    tempHealer.take(new Armor("Tunique de soie",  0, 10));
                    System.out.println(tempHealer.getName()+" rejoint l'aventure\nIl possède un "+
                            tempHealer.getWeaponName()+" : "+tempHealer.getWeaponDamage()+" DMG"+
                            "\nEt un "+tempHealer.getArmorName()+" : "+tempHealer.getArmorPoint()+" DEF\n");
                    heros.add(tempHealer);

                    break;
                default:
                    System.out.println("C'est pas un type de héros ça, recommence");
                    i -= 1;//on recommence l'itérarion de la boucle.
            }
        }

        for (int i = 0; i < heros.size(); i++){
            heros.get(i).Status();
        }

        //CREATION DES VAGUES D'ENNEMIS
        allEnemies = new ArrayList<>();

        //Creation de la première Vague
        Wave1 = new ArrayList<>();
        Wave1.add(new Pecheur("Sataniste", (30 * heros.size()), (10* heros.size())));
        Wave1.add(new Pecheur("Mangeur d'âme", (20 * heros.size()), (15* heros.size())));

        //Creation de la deuxième Vague
        Wave2 = new ArrayList<>();
        Wave2.add(new ItalienNotoire("Pizzaiolo de Gauche(jlm <3)", (40 * heros.size()), (16* heros.size())));
        Wave2.add(new ItalienNotoire("Pizzaiolo en chef", (60 * heros.size()), (20* heros.size())));
        Wave2.add(new ItalienNotoire("Pizzaiolo de Droite(vive le fn)", (40 * heros.size()), (16* heros.size())));

        //Creation de la troisième Vague
        Wave3 = new ArrayList<>();
        Wave3.add(new BersekerLion("ROI LION BERSEKER", (200 * heros.size()), (45* heros.size())));

        //Creation de la quatrième Vague
        Wave4 = new ArrayList<>();
        Wave4.add(new Dragon("DRAGON ARAGNIR", (100 * heros.size()), (30* heros.size())));
        Wave4.add(new SorcierMalefique("Maître des dragons", (150 * heros.size()), (40* heros.size())));
        Wave4.add(new Dragon("DRAGON NEFARIOUS", (100 * heros.size()), (30* heros.size())));

        //Vague BOSS
        BossWave = new ArrayList<>();
        BossWave.add(new Boss("[[A P O C A L Y P S E]]", (1000*heros.size()), (80*heros.size())));


        //Ajout de toutes les vagues
        allEnemies.add(Wave1);
        allEnemies.add(Wave2);
        allEnemies.add(Wave3);
        allEnemies.add(Wave4);
        allEnemies.add(BossWave);


    }


    public void start() {
        int ixHero = 0;
        for (int i =0; i < allEnemies.size(); i++) {
            enemies = allEnemies.get(i);
            displayMessage(
                    "================================================================\n" +
                    "                          NOUVELLE VAGUE                        \n" +
                    "================================================================");
            for (Ennemy ennemy : enemies){
                EnnemyStatus(ennemy);
            }

            // Boucle de jeu
            while (true) {
                displayStatus(heros, enemies);
                displayMessage(
                                "=================================\n" +
                                "      Au tour des Heros !\n" +
                                "=================================\n");
                for (int h = 0; h < heros.size();h++){
                    if (enemies.size() == 0){break;}//pour passer la boucle quand les ennemies son mort
                    displayStatus(heros, enemies);
                    displayMessage("C'est le tour de "+heros.get(h).getName()+"\n" +
                                        "------------------------------------------");
                    choixAction(heros.get(h));

                    if (enemies.size() == 0){break;}//au cas ou la dernière action a tué le dernier ennemis
                    for(int e = 0; e < enemies.size();e++){//on verifie qu'aucun ennemis est mort
                        if (enemies.get(e).getHealthPoint() <=0){
                            displayMessage(enemies.get(e).getName()+ " Est vaincu !");
                            enemies.remove(e);
                            Delay();
                        }
                    }

                }
                displayMessage(
                                "=================================\n" +
                                "      Au tour des Ennemis !\n" +
                                "=================================\n");
                if (enemies.size() != 0) {
                for (Ennemy mechant: enemies){
                        displayStatus(heros, enemies);
                        displayMessage("C'est le tour de " + mechant.getName() + "\n" +
                                "------------------------------------------");
                        int randomTarget = ThreadLocalRandom.current().nextInt(0, heros.size());
                        mechant.attack(heros.get(randomTarget));
                        if (heros.get(randomTarget).getHealthPoint() <= 0) {
                            displayMessage
                                    ("Le pauvre " + heros.get(randomTarget).getName() + " a été vaincu...");
                            heros.remove(randomTarget);
                            Delay();
                        }
                        Delay();
                }

                for (int h = 0; h < heros.size();h++){
                    heros.get(h).heal(10);
                    heros.get(h).setIfInDefense(false);
                }
                displayMessage(
                                "=================================\n" +
                                "        Fin du tour !\n" +
                                        "     tout vos héros on regagné 10hp" +
                                "=================================\n");
                }

                // Tests de fin du jeu
                if (heros.size() == 0) {
                    displayMessage("Les héros ont perdu, c'est la fin du monde...");
                    break;
                }
                if (enemies.size() == 0) {
                    displayMessage("BRAVO, les héros ont gagné ce tour !!!");
                    break;
                }
            }
            if (heros.size() == 0) {
                break;
            }

            displayMessage(
                            "============================================================\n" +
                            "      FELICITATION - Vous avez gagné "+ (1000*(i+1)) +" gold!\n" +
                            "============================================================\n");

            winMoney((1000*(i+1)));//le +1 est la car le i commence a 0
            Delay();
            displayMessage(
                            "============================================================\n" +
                            "      MarketPlace - Equipez vous pour la prochaine vague !\n" +
                            "============================================================\n"+
                                    "\n" +
                                    "Gold :"+getGold());

            //LANCEMENT DU MARKET

            Marketplace();

            //FIN DU MARKET
            displayMessage(
                            "============================================================\n" +
                            "      Fin des Achats - Début de La Vague suivante !\n" +
                            "============================================================\n");
            Delay();
            for (int h = 0; h < heros.size();h++){
                heros.get(h).heal(100);
            }
            displayMessage(
                    "=================================\n" +
                            "        Vague N°"+i +" !\n" +
                            "     tout vos héros on regagné 100hp" +
                            "=================================\n");
        }
        displayMessage(
                        "============================================================\n" +
                        "                         FIN DU JEU !\n" +
                        "============================================================\n");
    }

    public void choixAction(Hero hero){//cette methode va permettre en bref de traduire le choix de l'utilisateur
        int choix = hero.actionChoice();
        String returnedChoice = hero.actionMethodSwithcher(choix);

        actionMaker(returnedChoice, hero);//l'action va être effectuée dans cette methode
    }

    public Combatant actionMaker(String choice, Hero combatant){
        //déclarations de variables pour provoquer une action
        String menuEnnemis;
        String menuHeros;
        String menuConsumables;
        int nbreEnnemy = enemies.size();
        int nbreHeros = heros.size();
        int choixAttaque;
        int choixAllie;
        int choixConsumable;
        //Debut du switch donnant permettant d'effectuer l'action choisie
        switch(choice){
            // si le héro choisis d'attaquer
            case "attack":
                Scanner sc2 = new Scanner (System.in);
                do{
                    menuEnnemis = "";
                    displayMessage("Qui souhaitez vous attaquer? :");
                    for (int i = 0; i < nbreEnnemy;i++){
                        menuEnnemis += "["+(i+1)+"] - "+enemies.get(i).getName()+"\n";
                    }
                    displayMessage(menuEnnemis);
                    choixAttaque = sc2.nextInt();
                    if (choixAttaque < 1 || choixAttaque > nbreEnnemy) {
                        displayMessage("Ennemis selectionné non existant. Recommmencez");
                    }
                }while(choixAttaque < 1 || choixAttaque > nbreEnnemy);

                tempHarmedEnnemy = enemies.get((choixAttaque-1));//Je prend l'ennemi, je le met dans une variable
                combatant.attack(tempHarmedEnnemy);//je le frappe
                enemies.set((choixAttaque-1), tempHarmedEnnemy);//puis je le remet dans sa liste.
                break;
            // si le héro choisis de se défendre
            case "defend":
                combatant.setIfInDefense(true);
                displayMessage(combatant.getName()+" se met en garde!\n");
                break;
            // si un Mage décide de lancer un sort
            case "spell":
                int spellChoice;
                Scanner sc3 = new Scanner (System.in);
                do{
                displayMessage("Quel sort souhaitez vous lancer ? :\n" +
                        "[1] - FireBall : -20 MANA\n" +
                        "[2] - LightningBolt : -30 MANA\n" +
                        "[3] - ULTRALASER : -100 MANA\n" +
                        "[4] - Concentrate : +40 MANA\n");

                    spellChoice = sc3.nextInt();
                    if (spellChoice < 1 || spellChoice > 4) {
                        displayMessage("Sort selectionné non existant. Recommmencez");
                    }
                }while(spellChoice < 1 || spellChoice > 4);

                switch (spellChoice) {
                    case 1:
                        do{
                            menuEnnemis = "";
                            displayMessage("Sur qui souhaitez vous lancer ce sort ? :");
                            for (int i = 0; i < nbreEnnemy;i++){
                                menuEnnemis += "["+(i+1)+"] - "+enemies.get(i).getName()+"\n";
                            }
                            displayMessage(menuEnnemis);
                            choixAttaque = sc3.nextInt();
                            if (choixAttaque < 1 || choixAttaque > nbreEnnemy) {
                                displayMessage("Ennemis selectionné non existant. Recommmencez");
                            }
                        }while(choixAttaque < 1 || choixAttaque > nbreEnnemy);

                        tempHarmedEnnemy = enemies.get((choixAttaque-1));//Je prend l'ennemi, je le met dans une variable
                        ((Mage)combatant).castFireball(tempHarmedEnnemy);//je lui lance mon sort
                        enemies.set((choixAttaque-1), tempHarmedEnnemy);//puis je le remet dans sa liste.
                        break;
                    case 2:
                        do{
                            menuEnnemis = "";
                            displayMessage("Sur qui souhaitez vous lancer ce sort ? :");
                            for (int i = 0; i < nbreEnnemy;i++){
                                menuEnnemis += "["+(i+1)+"] - "+enemies.get(i).getName()+"\n";
                            }
                            displayMessage(menuEnnemis);
                            choixAttaque = sc3.nextInt();
                            if (choixAttaque < 1 || choixAttaque > nbreEnnemy) {
                                displayMessage("Ennemis selectionné non existant. Recommmencez");
                            }
                        }while(choixAttaque < 1 || choixAttaque > nbreEnnemy);

                        tempHarmedEnnemy = enemies.get((choixAttaque-1));//Je prend l'ennemi, je le met dans une variable
                        ((Mage)combatant).castLightningBolt(tempHarmedEnnemy);//je lui lance mon sort
                        enemies.set((choixAttaque-1), tempHarmedEnnemy);//puis je le remet dans sa liste.

                        break;
                    case 3:
                        do{
                            menuEnnemis = "";
                            displayMessage("Sur qui souhaitez vous lancer ce sort ? :");
                            for (int i = 0; i < nbreEnnemy;i++){
                                menuEnnemis += "["+(i+1)+"] - "+enemies.get(i).getName()+"\n";
                            }
                            displayMessage(menuEnnemis);
                            choixAttaque = sc3.nextInt();
                            if (choixAttaque < 1 || choixAttaque > nbreEnnemy) {
                                displayMessage("Ennemis selectionné non existant. Recommmencez");
                            }
                        }while(choixAttaque < 1 || choixAttaque > nbreEnnemy);

                        tempHarmedEnnemy = enemies.get((choixAttaque-1));//Je prend l'ennemi, je le met dans une variable
                        ((Mage)combatant).ultralaser(tempHarmedEnnemy);//je lui lance mon sort
                        enemies.set((choixAttaque-1), tempHarmedEnnemy);//puis je le remet dans sa liste.
                        break;
                    case 4:
                        ((Mage)combatant).concentrate();
                        break;
                    default:
                        displayMessage("Le code a un problème de fonctionnement !");
                }
                break;
            case "heal":
                Scanner sc4 = new Scanner (System.in);
                do{
                    menuHeros = "";
                    displayMessage("Qui souhaitez vous soigner ? :");
                    for (int i = 0; i < nbrHero;i++){
                        menuHeros += "["+(i+1)+"] - "+heros.get(i).getName()+"\n";
                    }
                    displayMessage(menuHeros);
                    choixAllie = sc4.nextInt();
                    if (choixAllie < 1 || choixAllie > nbrHero) {
                        displayMessage("Allie selectionné non existant. Recommmencez");
                    }
                }while(choixAllie < 1 || choixAllie > nbrHero);
                tempHealedHero = heros.get((choixAllie-1));//Je prend l'allie, je le met dans une variable
                ((Healer)combatant).Heal(tempHealedHero);//je le soigne
                heros.set((choixAllie-1), tempHealedHero);//puis je le remet dans sa liste.
                break;
            case "consume":
                Scanner sc5 = new Scanner (System.in);
                menuConsumables ="";
                do{
                    displayMessage("Que souhaitez vous consommer ? :");
                    for (int i = 0; i < foodInventory.size();i++){
                        menuConsumables += "["+(i+1)+"] - "+foodInventory.get(i).info()+"\n";
                    }
                    if (combatant instanceof SpellCaster){
                        for (int i = foodInventory.size(); i < (foodInventory.size()+ potionInventory.size());i++){
                            menuConsumables += "["+(i+1)+"] - "+potionInventory.get(i-foodInventory.size()).info()+"\n";
                        }
                    }
                    displayMessage(menuConsumables);
                    choixConsumable = sc5.nextInt();
                    if (choixConsumable < 1 || choixConsumable > (foodInventory.size() + ((combatant instanceof SpellCaster)?potionInventory.size():0))) {
                        displayMessage("Allie selectionné non existant. Recommmencez");
                    }
                }while(choixConsumable < 1 || choixConsumable > (foodInventory.size() + ((combatant instanceof SpellCaster)?potionInventory.size():0)));

                if (choixConsumable > foodInventory.size()){//alors cela signifie qu'on aura choisis une potion
                    potionInventory.get(choixConsumable- foodInventory.size() -1).usePotion((SpellCaster) combatant);
                }else{
                    foodInventory.get(choixConsumable-1).eat((Hero) combatant);
                }

                break;

            default:
                displayMessage("Il y'a un problème dans le actionMaker");
        }
        return combatant;//on retourne le combatant pour le remettre ensuite dans sa liste.
    }

    public int Marketplace(){
        int choixAchat;
        Scanner sc = new Scanner (System.in);
        do{
            displayMessage("Que souhaitez vous acheter ?\n" +
                    "[1]- Armes\n" +
                    "[2]- Armures\n" +
                    "[3]- Consommable\n" +
                    "[4]- Munition - 5 Gold Unité\n" +
                    "[5]- Quitter le market\n"+
                    "\n" +
                    "Gold :"+getGold());
            choixAchat = sc.nextInt();
            if (choixAchat < 1 || choixAchat > 5) {
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(choixAchat < 1 || choixAchat > 5);

        switch (choixAchat){
            case 1:
                MarketWeaponTypeChoices();
                break;
            case 2:
                MarketArmor();
                break;
            case 3:
                MarketConsumables();
                break;
            case 4:
                MarketAmmo();
            case 5:
                return 1;
            default:
                displayMessage("Il y'a un gros problème Dans le marketPlace");
        }
        return 0;
    }


    public void MarketWeaponTypeChoices() {
        Scanner sc = new Scanner (System.in);
        int choixType;

        do{
            displayMessage("Quel type d'arme souhaitez vous acheter ?\n" +
                    "[1]- Warrior\n" +
                    "[2]- Hunter\n" +
                    "[3]- Mage\n" +
                    "[4]- Healer\n" +
                    "[5]- Revenir en arrière.");
            choixType = sc.nextInt();
            if (choixType < 1 || choixType > 5) {
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(choixType < 1 || choixType > 5);

        if (choixType == 5){
            Marketplace();
        }

        MarketWeaponChoices(choixType);
    }

    public void MarketWeaponChoices(int choixType) {
        Scanner sc = new Scanner (System.in);
        //Choix de l'arme
        String menuArme;
        int cnt;
        int choixArme;
        do{
            displayMessage("Quelle arme souhaitez vous acheter ?\n");
            menuArme = "";
            for (int i = 0; i < marketWeapon.get((choixType -1)).size();i++){
                menuArme += "["+(i+1)+"] - "+((Weapon)(marketWeapon.get((choixType -1)).get(i))).priceInfo()+"\n";
            }
            menuArme += "["+(marketWeapon.get((choixType -1)).size()+1)+"] - Revenir en arrière"+
                    "\n" +
                    "Gold :"+getGold();
            displayMessage(menuArme);
            choixArme = sc.nextInt();
            if (choixArme < 1 || choixArme > marketWeapon.get((choixType -1)).size()+1 ) {//le +1 est la parce qu'on compte le choix de retour en arrière
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(choixArme < 1 || choixArme > marketWeapon.get((choixType -1)).size()+1);

        if (choixArme == marketWeapon.get((choixArme -1)).size()+1){// On verifie si le choix de revenir en arrière a été selectionné.
            MarketWeaponTypeChoices();
        }

        //On verifie que le jouer a assez d'argent.
        if (gold < ((Item)(marketWeapon.get((choixType -1)).get((choixArme -1)))).getPrice()){
            displayMessage("Tu n'as pas assez d'argent pour acheter cet Item !");
            MarketWeaponChoices(choixType);
        }

        HashMap<Integer, Integer> herosIndexRecoder = new HashMap<>();
        int whoseTheChosen;
        String giveWeaponMenu;
        int giveWeaponSelection;
        do{
            herosIndexRecoder.clear();
            displayMessage("A qui souhaitez vous donner cette Arme ?\n");
            giveWeaponMenu = "";
            cnt = 1;
            for (int i = 0; i < heros.size();i++){
                if (((Hero)(heros.get(i))).getWhatAmI().equals(((Weapon)(marketWeapon.get((choixType -1)).get((choixArme -1)))).WhoCanHoldIt())){
                    giveWeaponMenu += "["+cnt+"] - "+heros.get(i).getName()+" : "+heros.get(i).getWeaponDamage()+" ATK\n";
                    herosIndexRecoder.put(cnt, i); //to get the index
                    cnt+=1;
                }
            }
            giveWeaponMenu += "["+cnt+"] - Revenir en arrière\n"+
                    "\n" +
                    "Gold :"+getGold();
            displayMessage(giveWeaponMenu);
            giveWeaponSelection = sc.nextInt();
            if (giveWeaponSelection < 1 || giveWeaponSelection > cnt) {
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(giveWeaponSelection < 1 || giveWeaponSelection > cnt);

        if (giveWeaponSelection == cnt){
            MarketWeaponChoices(choixType);
        }

        //Et enfin, on attribue l'arme.

        tempHero = ((Hero)(heros.get(herosIndexRecoder.get(giveWeaponSelection))));//Je recopie temporairement le héro
        tempHero.take((Item)(marketWeapon.get((choixType -1)).get((choixArme -1))));//je lui donne l'arme
        heros.set(herosIndexRecoder.get(giveWeaponSelection), tempHero);//je le remet dans sa liste

        looseMoney(((Item)(marketWeapon.get((choixType -1)).get((choixArme -1)))).getPrice());
        displayMessage(((Item)(marketWeapon.get((choixType -1)).get((choixArme -1)))).getName() +" est désormais équipé par "+((Hero)(heros.get(herosIndexRecoder.get(giveWeaponSelection)))).getName());
        Delay();

        Marketplace();
    }

    public void MarketArmor(){
        Scanner sc = new Scanner (System.in);
        //Choix de l'armure
        String menuArmor;
        int cnt;
        int choixArmure;
        do{
            displayMessage("Quel bouclier souhaitez vous acheter ?\n");
            menuArmor = "";
            for (int i = 0; i < marketArmor.size();i++){
                menuArmor += "["+(i+1)+"] - "+((Armor)(marketArmor.get(i))).priceInfo()+"\n";
            }
            menuArmor += "["+(marketArmor.size() +1)+"] - Revenir en arrière" +
                    "\n" +
                    "Gold :"+getGold();
            displayMessage(menuArmor);
            choixArmure = sc.nextInt();
            if (choixArmure < 1 || choixArmure > (marketArmor.size() +1) ) {//le +1 est la parce qu'on compte le choix de retour en arrière
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(choixArmure < 1 || choixArmure > (marketArmor.size() +1));

        if (choixArmure < 1 || choixArmure > (marketArmor.size() +1)){// On verifie si le choix de revenir en arrière a été selectionné.
            Marketplace();
        }

        //On verifie que le joueur a assez d'argent.
        if (gold < ((Item)(marketArmor.get(choixArmure-1))).getPrice()){
            displayMessage("Tu n'as pas assez d'argent pour acheter cet Item ! Il te manque "+ (((Item)(marketArmor.get(choixArmure-1))).getPrice()-gold)+" gold");
            MarketArmor();
        }

        String giveShieldMenu;
        int giveShieldSelection;
        do{
            displayMessage("A qui souhaitez vous donner cette Arme ?\n");
            giveShieldMenu = "";
            for (int i = 0; i < heros.size();i++){
                giveShieldMenu += "["+(i+1)+"] - "+heros.get(i).getName()+" : "+heros.get(i).getArmorPoint()+" DEF\n";
            }
            giveShieldMenu += "["+( heros.size() +1)+"] - Revenir en arrière\n"+
                    "\n" +
                    "Gold :"+getGold();
            displayMessage(giveShieldMenu);
            giveShieldSelection = sc.nextInt();
            if (giveShieldSelection < 1 || giveShieldSelection > ( heros.size() +1)) {
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(giveShieldSelection < 1 || giveShieldSelection > ( heros.size() +1));

        if (giveShieldSelection == ( heros.size() +1)){
            MarketArmor();
        }

        //Et enfin, on attribue l'armure.

        tempHero = ((Hero)(heros.get(giveShieldSelection -1)));//Je recopie temporairement le héro
        tempHero.take((Item)(marketArmor.get(choixArmure -1)));//je lui donne l'armure
        heros.set((giveShieldSelection -1), tempHero);//je le remet dans sa liste

        looseMoney(((Item)(marketArmor.get(choixArmure -1))).getPrice());

        displayMessage(((Item)(marketArmor.get(choixArmure -1))).getName() +" est désormais équipé par "+((Hero)(heros.get(giveShieldSelection -1))).getName());
        Delay();
        Marketplace();
    }

    public void MarketConsumables(){
        Scanner sc = new Scanner (System.in);
        int choixType;

        do{
            displayMessage("Quel type de consommable souhaitez vous acheter ?\n" +
                    "[1]- Food\n" +
                    "[2]- Potion\n" +
                    "[3]- Revenir en arrière."+
                    "\n" +
                    "Gold :"+getGold());
            choixType = sc.nextInt();
            if (choixType < 1 || choixType > 3) {
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(choixType < 1 || choixType > 3);

        if (choixType == 3){
            Marketplace();
        }
        MarketTypeConsumables(choixType);
    }

    public void MarketTypeConsumables(int choice){
        Scanner sc = new Scanner (System.in);
        int foodChoice;
        int potionChoice;
        switch (choice){
            case 1:
                do{
                    displayMessage("Quel type de nourriture souhaitez vous acheter ?\n" +
                            "[1]- couscous : 30 HP : 20 Gold\n" +
                            "[2]- lembas : 40 HP : 40 Gold\n" +
                            "[3]- bonbon : 100 HP : 100 Gold\n" +
                            "[4]- Revenir en arrière.\n"+
                            "\n" +
                            "Gold :"+getGold());
                    foodChoice = sc.nextInt();
                    if (foodChoice < 1 || foodChoice > 4) {
                        displayMessage("Choix invalide. Recommencez.");
                    }
                }while(foodChoice < 1 || foodChoice > 4);
                if (foodChoice == 4){
                    MarketConsumables();
                }
                MarketFood(foodChoice);
                break;
            case 2:
                do{
                    displayMessage("Quelle potion souhaitez vous acheter ?\n" +
                            "[1]- Potion de mana : 20 MANA : 20 Gold\n" +
                            "[2]- Canette de Monster : 40 MANA : 40 Gold\n" +
                            "[3]- White Spirit : 100 MANA : 100 Gold\n" +
                            "[4]- Revenir en arrière.\n"+
                            "\n" +
                            "Gold :"+getGold());
                    potionChoice = sc.nextInt();
                    if (potionChoice < 1 || potionChoice > 4) {
                        displayMessage("Choix invalide. Recommencez.");
                    }
                }while(potionChoice < 1 || potionChoice > 4);
                if (potionChoice == 4){
                    MarketConsumables();
                }

                MarketPotion(potionChoice);
                break;
            default:
                displayMessage("Il y'a un problème dans le code de choix de type de consommable");
        }
    }

    public void MarketFood(int choice){
        Scanner sc = new Scanner (System.in);
        int amount;
        do{
            displayMessage("Combien en voulez vous ?\n"+
                    "\n" +
                    "Gold :"+getGold());
            amount = sc.nextInt();
            if (amount < 1 || amount > 99) {
                displayMessage("Choisir moins de 99. Recommencez.");
            }
        }while(amount < 1 || amount > 99);

        if(foodInventory.get((choice-1)).getPrice()*amount > gold){
            displayMessage("Vous n'avez pas assez d'argent !");
            MarketFood(choice);
        };
        foodInventory.get((choice-1)).addQuantity(amount);
        looseMoney(foodInventory.get((choice-1)).getPrice()*amount);
        displayMessage(amount+" "+ foodInventory.get((choice-1)).getName()+" on été ajouté a l'inventaire");
        Delay();

        Marketplace();
    }

    public void MarketPotion(int choice){
        Scanner sc = new Scanner (System.in);
        int amount;
        do{
            displayMessage("Combien souhaitez vous en acheter ?\n"+
                    "\n" +
                    "Gold :"+getGold());
            amount = sc.nextInt();
            if (amount < 1 || amount > 99) {
                displayMessage("Choisir moins de 99. Recommencez.");
            }
        }while(amount < 1 || amount > 99);

        if(potionInventory.get((choice-1)).getPrice()*amount > gold){
            displayMessage("Vous n'avez pas assez d'argent !");
            MarketPotion(choice);
        };
        potionInventory.get((choice-1)).addQuantity(amount);
        looseMoney(potionInventory.get((choice-1)).getPrice()*amount);

        displayMessage(amount+ " " +potionInventory.get((choice-1)).getName()+" on été ajouté a l'inventaire.");
        Delay();

        Marketplace();
    }

    public void MarketAmmo(){
        int cnt;
        Scanner sc = new Scanner (System.in);
        HashMap<Integer, Integer> HunterIndexRecoder = new HashMap<>();
        int whoseTheChosen;
        String giveAmmoMenu;
        int giveAmmoSelection;
        do{
            HunterIndexRecoder.clear();
            displayMessage("A qui souhaitez vous donner ces munitions ?\n");
            giveAmmoMenu = "";
            cnt = 1;
            for (int i = 0; i < heros.size();i++){
                if (((Hero)(heros.get(i))).getWhatAmI().equals("Hunter")){
                    giveAmmoMenu += "["+cnt+"] - "+heros.get(i).getName()+" : "+heros.get(i).getWeaponDamage()+" ATK\n";
                    HunterIndexRecoder.put(cnt, i); //to get the index
                    cnt+=1;
                }
            }
            giveAmmoMenu += "["+cnt+"] - Revenir en arrière\n"+
                    "\n" +
                    "Gold :"+getGold();
            displayMessage(giveAmmoMenu);
            giveAmmoSelection = sc.nextInt();
            if (giveAmmoSelection < 1 || giveAmmoSelection > cnt) {
                displayMessage("Choix invalide. Recommencez.");
            }
        }while(giveAmmoSelection < 1 || giveAmmoSelection > cnt);

        if (giveAmmoSelection == cnt){
            Marketplace();
        }

        int amount;
        do{
            displayMessage("Combien souhaitez vous en acheter ?\n"+
                    "5 Gold Unité\n" +
                    "Gold :"+getGold());
            amount = sc.nextInt();
            if (amount < 1 || amount > 99) {
                displayMessage("Choisir moins de 99. Recommencez.");
            }
        }while(amount < 1 || amount > 99);

        if(5*amount > gold){
            displayMessage("Vous n'avez pas assez d'argent !");
            Marketplace();
        }
        ((Hunter)(heros.get((HunterIndexRecoder.get(giveAmmoSelection))))).addAmmo(amount);
        looseMoney((5*amount));
        displayMessage(amount+ " munitions on été ajouté a l'inventaire de "+heros.get((HunterIndexRecoder.get(giveAmmoSelection))).getName());
        Delay();

        Marketplace();
    }





    private InputParser inputParser;

    private List<ArrayList> marketWeapon;
    private ArrayList<Weapon> warriorWeapon;
    private ArrayList<Weapon> hunterWeapon;
    private ArrayList<Weapon> mageWeapon;
    private ArrayList<Weapon> healerWeapon;

    private ArrayList<Armor> marketArmor;

    private ArrayList<Food> foodInventory;
    private ArrayList<Potion> potionInventory;

    private List<Hero> heros;

    private List<ArrayList> allEnemies;
    private ArrayList<Ennemy> enemies;

    private ArrayList<Ennemy> Wave1;
    private ArrayList<Ennemy> Wave2;
    private ArrayList<Ennemy> Wave3;
    private ArrayList<Ennemy> Wave4;
    private ArrayList<Ennemy> BossWave;


    private Hero tempHero;
    private Warrior tempWarrior;
    private Hunter tempHunter;
    private Mage tempMage;
    private Healer tempHealer;

    private Ennemy tempHarmedEnnemy;
    private Hero tempHealedHero;


    private int gold;

    // Méthodes d'affichage
    // (STATIQUES pour pouvoir les appeler depuis n'importe où dans le programme)
    //
    // => pourraient éventuellement être déplacées dans le package
    //    "com.isep.utils", en s'inspirant de "InputParser" (méthodes de saisie)

    public static void displayStatus(List<Hero> h, List<Ennemy> e) {
        System.out.println("###########################################");
        System.out.println("Les gentils");
        for (Combatant c: h) {
            System.out.println(((Hero)c).getWhatAmI()+" - " +c.getName() +" : " + c.getHealthPoint() + " HP : " + c.getArmorPoint()+" DEF : "+(((c instanceof SpellCaster)? ((SpellCaster)c).getMana()+" MANA ":"")) +(((c instanceof Hunter)? ((Hunter)c).getAmmoCount()+" Ammo ":"")));
        }
        System.out.println("Les méchants");
        System.out.println("------------------------------------------------------");
        for (Combatant c: e) {
            System.out.println(c.getName() + " : " + c.getHealthPoint() + "HP : "+((Ennemy)c).getDamagePoints()+" ATK");
        }
        System.out.println("###########################################\n");
    }

    public static String EnnemyStatus(Ennemy ennemy){
        String message = ennemy.getName()+" - "+ ennemy.getHealthPoint() +"HP :"+ennemy.getDamagePoints()+" ATK";
        return message;
    }
    private static void Delay() {
        System.out.println("\n" +
                "v       PRESS ENTER TO SKIP");
        Scanner scan = new Scanner(System.in);
        String delay = scan.nextLine();
    }

    private void looseMoney(int loss) {
        gold -= loss;
    }
    private void winMoney(int gain) {
        gold += gain;
    }

    private int getGold() {
        return gold;
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
    private String name;
    private int nbrHero;
    private String typeHero;
}
