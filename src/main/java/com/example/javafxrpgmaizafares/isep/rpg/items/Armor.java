package com.example.javafxrpgmaizafares.isep.rpg.items;

public class Armor extends Item{

    public Armor(String name, float dV, int price) {
        super(name);
        this.name = name;
        this.holder = "Everyone";
        this.defValue = dV;
        this.price = price;
    }

    //getters
    public float getDefValue() { return defValue; }
    public String WhoCanHoldIt(){ return holder;}

    //setters
    public void setDefValue(float defValue) { this.defValue = defValue; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String priceInfo(){
        String message = getName()+ " : "+(getDefValue()*100)+ "% : "+getPrice()+ " Gold";
        return (message);
    }
    private float defValue;
    private String holder;
    private String name;
}
