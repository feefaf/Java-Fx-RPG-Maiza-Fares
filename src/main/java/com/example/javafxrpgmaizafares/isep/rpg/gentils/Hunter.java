package com.example.javafxrpgmaizafares.isep.rpg.gentils;


import com.example.javafxrpgmaizafares.isep.rpg.Combatant;
import com.example.javafxrpgmaizafares.isep.rpg.Game;
import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;

import java.util.Scanner;

import static com.example.javafxrpgmaizafares.isep.rpg.Game.displayMessage;

public class Hunter extends Hero {
    public Hunter(String n) {

        super(n, 90, 0);
        ammo = 10;
    }

    @Override
    public void attack(Combatant combatant) {
        if (ammo > 0){
            calculatedDamage = weapon.getDamagePoints() +getDamageBoost();
            combatant.loose(calculatedDamage);
            System.out.println(getName() + " utilise une munition et fais perdre "+ calculatedDamage + "hp a"+ combatant.getName()+" avec son "+ weapon.getName());

            ammo -= 1;
        }else{
            combatant.loose(2);
            System.out.println(getName()+" n'a plus de munitions");
            System.out.println(getName() + " a fais perdre 2hp a"+ combatant.getName()+" avec son petit couteau de poche");

        }
    }

    @Override
    public int actionChoice() {
        Scanner sc = new Scanner(System.in);
        int choix;
        do{
            displayMessage(
                    """
                    Quelles type d'actions voulez vous faire ? :\s
                    [1]- Attaquer\s
                    [2]- Se défendre\s
                    [3]- Utiliser un consommable\n """);

            choix = sc.nextInt(); // On lit la réponse de l'utilisateur
            if (choix < 1 || choix > 4) {
                displayMessage("Insérez un numero d'action correct.");
            }
        }while(choix < 1 || choix > 4);
        return choix;
    }

    @Override
    public String actionMethodSwithcher(int choix) {
        switch (choix) {
            case 1:
                return "attack";
            case 2:
                return "defend";
            case 3:
                return "consume";
            default:
                displayMessage("Le code a un problème de fonctionnement !");
        }
        return "";
    }

    public int getAmmoCount() { return ammo; }
    public void addAmmo(int ammoAddition) { ammo += ammoAddition; }
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            if(((Weapon) item).WhoCanHoldIt() == "Hunter"){
                weapon = (Weapon) item;
            }else{
                Game.displayMessage("Cette arme ne peut etre équipée que par un  " + ((Weapon) item).WhoCanHoldIt());
            }
        } else if (item instanceof Armor) {
            if ((((Armor) item).WhoCanHoldIt() == "Everyone")){
                armorItem = (Armor) item;//on donne l'armure au hero
                setArmor(armorItem.getDefValue());//on donne la valeur de l'armur en guise de protection au hero
            }else{
                Game.displayMessage("Cette armure ne peut etre équipée que par un  " + ((Armor) item).WhoCanHoldIt());
            }
        } else {
            Game.displayMessage("Oups ! " + item.getName() + " est inutile...");
        }
    }

    @Override
    public void Status(){
        System.out.println(getName() + " : " + getHealthPoint() + " PV  ,  " + getWeaponDamage() + " ATK  ,  " + (getArmorPoint()*100) + "% DEF");
    }
    public String getWeaponName(){
        return weapon.getName();
    }
    public String getArmorName(){
        return armorItem.getName();
    }
    public int getWeaponDamage(){
        return weapon.getDamagePoints();
    }
    @Override
    public float getArmorPoint() {
        return armorItem.getDefValue();
    }

    @Override
    public boolean IsInDefense(){
        return this.defense;
    }
    @Override
    public void setIfInDefense(boolean InDefense){
        defense = InDefense;
    }

    public String getWhatAmI(){
        return whatAmI;
    }

    private Weapon weapon;
    private Armor armorItem;

    private int ammo;
    private boolean defense;

    private String whatAmI = "Hunter";

}
