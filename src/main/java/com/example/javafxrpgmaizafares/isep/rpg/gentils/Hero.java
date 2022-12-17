package com.example.javafxrpgmaizafares.isep.rpg.gentils;


import com.example.javafxrpgmaizafares.isep.rpg.Combatant;
import com.example.javafxrpgmaizafares.isep.rpg.items.Item;

public abstract class Hero extends Combatant {

    public Hero(String n, int h, float a) {

        super(n, h, a, 0);

    }

    // Abstrait car n'importe quel hero peut prendre un objet mais son
    // utilisation dépend du type du héro (une arme n'est pas utile à un mage)
    public abstract void take(Item item);
    public abstract String getWhatAmI();
    //public abstract void removeItem(Combatant combatant);
    public int calculatedDamage;

    private String whatAmI;

}
