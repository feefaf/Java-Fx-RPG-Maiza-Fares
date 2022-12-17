package com.example.javafxrpgmaizafares.isep.rpg.items;

public class Weapon extends Item {

    public Weapon(String name, String holder,int damagePoints, int price) {
        super(name);
        this.holder = holder;
        this.damagePoints = damagePoints;
        this.price = price;
    }

    public int getDamagePoints() {
        return damagePoints;
    }
    // Une arme inflige des points de dégats
    public String WhoCanHoldIt(){ return holder;}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String priceInfo(){
        String message = getName()+ " : "+getDamagePoints()+ " DMG : "+getPrice()+ " Gold";
        return (message);
    }
    private int damagePoints;
    private String holder;

    private String name;
}
