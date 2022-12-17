package com.example.javafxrpgmaizafares.isep.rpg.items.consumables;


import com.example.javafxrpgmaizafares.isep.rpg.gentils.Hero;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;

import static com.example.javafxrpgmaizafares.isep.rpg.Game.displayMessage;

public class Food extends Consumable{
    public Food(String name, int hpContenance, int nbre, int price) {
        super(name);
        this.name = name;
        this.hpRecovery = hpContenance;
        this.quantity = nbre;
        this.price = price;
    }


    @Override
    public String getName() {
        return name;
    }

    public void eat(Hero hero){
        if (quantity >0){
            hero.heal(hpRecovery);
            quantity -= 1;
            displayMessage(hero.getName()+ " consomme "+name+" et regagne "+ hpRecovery+" HP !");
        } else {
            displayMessage("Vous n'avez plus de assez de "+ name +" pour en consommer !");
        }
    }

    public int getHpRecovery() {
        return hpRecovery;
    }
    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int addition){
        quantity += addition;
    }

    @Override
    public String info(){
        String message = this.getQuantity()+"X "+ getName()+ " : "+getHpRecovery() +" HP.";
        return message;
    }

    private String name;
    private int hpRecovery;
    private int quantity;
}
