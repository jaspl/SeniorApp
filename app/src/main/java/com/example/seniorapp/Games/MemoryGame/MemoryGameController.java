package com.example.seniorapp.Games.MemoryGame;

import com.example.seniorapp.Games.SymbolsGame.ShapeDetails;
import com.example.seniorapp.R;

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MemoryGameController {
    public int getIdByColRow(int col, int row) {
        if (col == 0 && row == 0) {
            return R.id.memory_game_image_c0_r0;
        } else if (col == 1 && row == 0) {
            return R.id.memory_game_image_c1_r0;
        } else if (col == 2 && row == 0) {
            return R.id.memory_game_image_c2_r0;
        } else if (col == 3 && row == 0) {
            return R.id.memory_game_image_c3_r0;
        } else if (col == 0 && row == 1) {
            return R.id.memory_game_image_c0_r1;
        } else if (col == 1 && row == 1) {
            return R.id.memory_game_image_c1_r1;
        } else if (col == 2 && row == 1) {
            return R.id.memory_game_image_c2_r1;
        } else if (col == 3 && row == 1) {
            return R.id.memory_game_image_c3_r1;
        } else if (col == 0 && row == 2) {
            return R.id.memory_game_image_c0_r2;
        } else if (col == 1 && row == 2) {
            return R.id.memory_game_image_c1_r2;
        } else if (col == 2 && row == 2) {
            return R.id.memory_game_image_c2_r2;
        } else if (col == 3 && row == 2) {
            return R.id.memory_game_image_c3_r2;
        } else if (col == 0 && row == 3) {
            return R.id.memory_game_image_c0_r3;
        } else if (col == 1 && row == 3) {
            return R.id.memory_game_image_c1_r3;
        } else if (col == 2 && row == 3) {
            return R.id.memory_game_image_c2_r3;
        } else
            return R.id.memory_game_image_c3_r3;
    }

    private int getLvl() {
        int lvl = 1;
        return lvl;
    }

    public void setGame(int pairsNumber) {
        for (int i = 0; i < pairsNumber; i++) {
            for (int j = 0; j < 2; j++) {
                setNewCards(i);
            }
        }
        showCards();
    }

    private boolean checkIfThereIsSthOnThisPlace(int col, int row) {
        boolean ifThereIsSth = false;
        for (Card card : MemoryGameDataHolder.getInstance().cards) {
            if (card.row == row && card.column == col) {
                ifThereIsSth = true;
                break;
            }
        }
        return ifThereIsSth;
    }

    private void setNewCards(int pairNumber) {
        Card card = new Card();
        card.setCardSign(pairNumber);
        do {
            //TODO get random valuses not equals
            card.setColumn(getRandomValue(4));
            card.setRow(getRandomValue(4));
        }
        while (checkIfThereIsSthOnThisPlace(card.column, card.row));
        addCardToList(card);

    }

    private void showCards() {
        for (Card card : MemoryGameDataHolder.getInstance().cards) {
            System.out.println("Id:" + card.cardSign + "    row:" + card.column + "     col:" + card.column);
        }

    }

    private void addCardToList(Card card) {
        MemoryGameDataHolder.getInstance().cards.add(card);
    }
    private int getRandomValue(int randomToNumber) {
        Random rand = new Random();
        return rand.nextInt(randomToNumber);
    }
}
