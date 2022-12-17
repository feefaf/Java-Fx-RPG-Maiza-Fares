package com.example.javafxrpgmaizafares;


import com.example.javafxrpgmaizafares.isep.rpg.Game;
import com.example.javafxrpgmaizafares.isep.rpg.gentils.Healer;
import com.example.javafxrpgmaizafares.isep.rpg.gentils.Hunter;
import com.example.javafxrpgmaizafares.isep.rpg.gentils.Mage;
import com.example.javafxrpgmaizafares.isep.rpg.gentils.Warrior;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Il faudra initialiser un "InputParser"...
        Game game = new Game(null);
        game.start();
    }

}