package com.example.javafxrpgmaizafares.isep.rpg.gentils;

public abstract class SpellCaster extends Hero {
    int mana;
    public SpellCaster(String n, int h, float a, int mana) {
        super(n, h, a);
        this.mana = mana;
    }
    public abstract int getMana();
    public abstract void setMana(int mana);
    public abstract void addMana(int mana);

    public void ManaSpell(int manaspent){//obsolete
        this.mana -= manaspent;
    }
}

