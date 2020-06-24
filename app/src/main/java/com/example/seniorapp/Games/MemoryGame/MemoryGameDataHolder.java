package com.example.seniorapp.Games.MemoryGame;

import com.example.seniorapp.Games.SymbolsGame.ShapeLocationController;

import java.util.ArrayList;
import java.util.List;

public class MemoryGameDataHolder {
    List <Card> cards = new ArrayList<>();
    private static MemoryGameDataHolder INSTANCE;

    public MemoryGameDataHolder() {
    }

    public static MemoryGameDataHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MemoryGameDataHolder();
        }
        return INSTANCE;
    }


}
