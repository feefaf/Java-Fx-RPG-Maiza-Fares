package com.example.javafxrpgmaizafares.isep.rpg.gentils;

import com.example.javafxrpgmaizafares.isep.rpg.Combatant;
import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;

import java.util.Scanner;

import static com.example.javafxrpgmaizafares.isep.rpg.Game.displayMessage;

public class Warrior extends Hero {

    public Warrior(String n) {
        // Le guerrier possède 100 points de vie
        super(n, 100,0);
    }

    // Implémentation de la méthode abstraite "fight" par le guerrier
    @Override
    public void attack(Combatant combatant) {
        calculatedDamage = weapon.getDamagePoints() + getDamageBoost();
        combatant.loose(calculatedDamage);
        System.out.println(getName() + " a fais perdre "+ calculatedDamage + "hp a"+ combatant.getName()+" avec son "+ weapon.getName());

    }

    @Override
    public int actionChoice() {
        Scanner sc = new Scanner (System.in);
        int choix;
        do{
            displayMessage(
                    """
                    Quelles type d'actions voulez vous faire ? :\s
                    [1]- Attaquer\s
                    [2]- Se défendre\s
                    [3]- Utiliser un consommable\n """);

            choix = sc.nextInt(); // On lit la réponse de l'utilisateur
            if (choix < 1 || choix > 3) {
                displayMessage("Insérez un numero d'action correct.");
            }
        }while(choix < 1 || choix > 3);
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

    // Implémentation de la méthode abstraite "take" par le guerrier :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            if(((Weapon) item).WhoCanHoldIt() == "Warrior"){
                weapon = (Weapon) item;
            }else{
                displayMessage("Cette arme ne peut etre équipée que par un  " + ((Weapon) item).WhoCanHoldIt());
            }
        } else if (item instanceof Armor) {
            if ((((Armor) item).WhoCanHoldIt() == "Everyone")){
                armorItem = (Armor) item;//on donne l'armure au hero
                setArmor(armorItem.getDefValue());//on donne la valeur de l'armur en guise de protection au hero
            }else{
                displayMessage("Cette armure ne peut etre équipée que par un  " + ((Armor) item).WhoCanHoldIt());
            }
        } else {
            displayMessage("Oups ! " + item.getName() + " est inutile...");
        }
    }
    @Override
    public String getWeaponName(){
        return weapon.getName();
    }
    @Override
    public String getArmorName(){
        return armorItem.getName();
    }
    @Override
    public int getWeaponDamage(){
        return weapon.getDamagePoints();
    }
    @Override
    public float getArmorPoint() {
        return armorItem.getDefValue();
    }

    @Override
    public void Status(){
        System.out.println(getName() + " : " + getHealthPoint() + " PV  ,  " + getWeaponDamage() + " ATK  ,  " + (getArmorPoint()*100) + "% DEF");
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
    private boolean defense;

    private String whatAmI = "Warrior";
}
