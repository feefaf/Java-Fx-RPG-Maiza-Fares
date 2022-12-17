package com.example.javafxrpgmaizafares.isep.rpg;

public abstract class Combatant {

    public Combatant(String n, int h, float a, int dB) {
        name = n;
        healthPoint = h;
        armor = a;
        damageBoost = dB;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getHealthPoint() {
        return healthPoint;
    }
    public abstract String getWeaponName();
    public abstract String getArmorName();
    public abstract int getWeaponDamage();
    public abstract float getArmorPoint();
    public int getDamageBoost(){ return damageBoost;}

    //setter
    public void setDamageBoost(int dB) { damageBoost = dB;}
    public void setArmor(float armor){ this.armor = armor;}
    public void loose(int hp) {
        healthPoint -= hp;
        // ... équivalant à : healthPoint = healthPoint - hp;
    }

    public void setHealthPoint(int hp){
        healthPoint = hp;
    }
    public void heal(int hp){

        this.healthPoint += hp;
    }

    // Chaque "vrai" combatant (non "abstract") implémente un code différent
    // pour la méthode "fight"
    public abstract void attack(Combatant combatant);
    public abstract int actionChoice();
    public abstract String actionMethodSwithcher(int choix);
    public abstract void Status();
    public abstract boolean IsInDefense();
    public abstract void setIfInDefense(boolean InDefense);
    private String name;
    private int healthPoint;

    private float armor;
    private int damageBoost;
    private boolean defense;
}
