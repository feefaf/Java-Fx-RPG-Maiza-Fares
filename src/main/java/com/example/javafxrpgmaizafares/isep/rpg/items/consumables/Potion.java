package com.example.javafxrpgmaizafares.isep.rpg.items.consumables;

import com.example.javafxrpgmaizafares.isep.rpg.gentils.SpellCaster;
import static com.example.javafxrpgmaizafares.isep.rpg.Game.displayMessage;

public class Potion extends Consumable{
    public Potion(String name, int manaContenance, int nbre, int price) {
        super(name);
        this.name = name;
        this.manaRecovery = manaContenance;
        this.quantity = nbre;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void usePotion(SpellCaster manaUser){
        if (quantity >0){
            manaUser.heal(manaRecovery);
            quantity -= 1;
            displayMessage(manaUser.getName()+ " consomme "+ name +" et regagne "+ manaRecovery+" MANA !");
        } else {
            displayMessage("Vous n'avez plus de assez de "+ name +" pour en consommer !");
        }
    }

    public int getQuantity() {
        return quantity;
    }
    public void addQuantity(int addition){
        quantity += addition;
    }

    @Override
    public String info(){
        String message = getQuantity()+"X "+ name + " : "+manaRecovery+" MANA.";
        return message;
    }

    private String name;
    private int manaRecovery;
    private int quantity;
}
