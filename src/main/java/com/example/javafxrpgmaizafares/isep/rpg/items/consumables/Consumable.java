package com.example.javafxrpgmaizafares.isep.rpg.items.consumables;

import com.example.javafxrpgmaizafares.isep.rpg.items.Item;

public abstract class Consumable extends Item {
    public Consumable(String name) {
        super(name);
    }

    public abstract String getName();
    public abstract String info();

}
