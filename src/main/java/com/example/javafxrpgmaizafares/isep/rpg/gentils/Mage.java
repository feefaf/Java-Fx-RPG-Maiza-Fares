package com.example.javafxrpgmaizafares.isep.rpg.gentils;

//import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.False;


import com.example.javafxrpgmaizafares.isep.rpg.Combatant;
import com.example.javafxrpgmaizafares.isep.rpg.items.Armor;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;
import com.example.javafxrpgmaizafares.isep.rpg.items.Weapon;

import java.util.Scanner;

import static com.example.javafxrpgmaizafares.isep.rpg.Game.displayMessage;

public class Mage extends SpellCaster{
    public Mage(String n, int mana) {
        super(n, 80,0, mana);
    }

    @Override
    public void attack(Combatant combatant) {
        calculatedDamage = weapon.getDamagePoints() + getDamageBoost();
        combatant.loose(calculatedDamage);
        displayMessage(getName() + " a fais perdre "+ calculatedDamage + "hp a"+ combatant.getName()+" avec son "+ weapon.getName());
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
                    [3]- Lancer un Sort\s
                    [4]- Utiliser un consommable\n 
                    """);

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
                return "spell";
            case 4:
                return "consume";
            default:
                displayMessage("Le code a un problème de fonctionnement !");
        }
        return "";
    }

    //les sorts
    public void castFireball(Combatant combatant) {
        if (this.mana >= 20) { // La boule de feu coûte 20 points de mana
            System.out.println(getName()+ " lance une boule de feu sur "+combatant.getName());
            this.mana -= 20; // On retire 20 points de mana au mage

            calculatedDamage = (25+getDamageBoost());
            combatant.loose(calculatedDamage);

            System.out.println("Le sort a fais perdre "+ calculatedDamage + " hp a "+ combatant.getName());
        } else {
            System.out.println(getName()+ "n'a pas assez de mana pour lancer ce sort !");
            this.attack(combatant); // On appelle la méthode attack()
        }
    }

    public void castLightningBolt(Combatant combatant) {
        if (this.mana >= 30) { // L'éclair coûte 30 points de mana
            System.out.println("Le mage lance un éclair  sur "+combatant.getName());
            this.mana -= 30; // On retire 30 points de mana au mage
            calculatedDamage = (40+getDamageBoost());
            combatant.loose(calculatedDamage);
            System.out.println("Le sort a fais perdre "+ calculatedDamage + "hp a"+ combatant.getName());
        } else {
            System.out.println("Le mage n'a pas assez de mana pour lancer ce sort !");
            this.attack(combatant); // On appelle la méthode attack()
        }
    }
    public void ultralaser(Combatant combatant) {
        if (this.mana >= 100) { // L'ultralaser coûte 100 points de mana
            System.out.println(getName()+ " lance un ULTRALASEEEER !");
            this.mana -= 100; // On retire 100 points de mana au mage

            calculatedDamage = (120+getDamageBoost());
            combatant.loose(calculatedDamage);
            System.out.println("Ce sort surpuissant a fais perdre "+ calculatedDamage + "hp a"+ combatant.getName());

        } else {
            System.out.println(getName()+ "n'a pas assez de mana pour lancer ce sort !");
            this.attack(combatant); // On appelle la méthode attack()

        }
    }

    public void concentrate(){
        addMana(40);
        displayMessage(getName()+" se concentre et regagne 40 MANA.");
    }
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            if(((Weapon) item).WhoCanHoldIt() == "Mage"){
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
    public void Status(){
        System.out.println(getName() + " : " + getHealthPoint() + " PV  ,  " + getWeaponDamage() + " ATK  ,  " + (getArmorPoint()*100) + "% DEF , "+getMana()+" MANA");
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
    public int getMana(){
        return this.mana;
    }
    @Override
    public void setMana(int mana){
        this.mana = mana;
    }
    @Override
    public void addMana(int mana){
        this.mana += mana;
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
    private int calculatedDamage;

    private String whatAmI = "Mage";
}