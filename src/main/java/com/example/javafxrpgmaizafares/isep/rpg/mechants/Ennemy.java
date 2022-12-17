package com.example.javafxrpgmaizafares.isep.rpg.mechants;

import com.example.javafxrpgmaizafares.isep.rpg.Combatant;


public abstract class Ennemy extends Combatant {

    public Ennemy(String n, int h, int damagePoints) {
        super(n, h, 0, 0);
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    // Les points de dégats sont intégrés aux ennemis (ils n'ont pas d'arme)
    private int damagePoints;

    public abstract String WhoAmI();
}
