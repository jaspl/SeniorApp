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
    public void setLvl(int lvl){
        //TODO add time
        if (lvl ==1){
            setGame(4,1,2);
        }else if (lvl ==2){
            setGame(6,1,3);
        }else if (lvl ==3){
            setGame(6,1,3);
        }else if (lvl ==4){
            setGame(8,0,3);
        }else{
            setGame(8,0,3);
        }
    }

    public void setGame(int pairsNumber, int rowStart,int rowEnd) {
        for (int i = 0; i < pairsNumber; i++) {
            for (int j = 0; j < 2; j++) {
                setNewCards(i,rowStart,rowEnd);
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

    private void setNewCards(int pairNumber,int rowStart,int rowEnd) {
        Card card = new Card();
        card.setCardSign(pairNumber);
        do {
            card.setColumn(getRandomValue(3,0));
            card.setRow(getRandomValue(rowEnd,rowStart));
        }
        while (checkIfThereIsSthOnThisPlace(card.column, card.row));
        addCardToList(card);
    }

    private void showCards() {
        for (Card card : MemoryGameDataHolder.getInstance().cards) {
            System.out.println("Id:" + card.cardSign + "    row:" + card.row + "     col:" + card.column);
        }
    }

    private void addCardToList(Card card) {
        MemoryGameDataHolder.getInstance().cards.add(card);
    }
    private int getRandomValue(int max, int min) {
       return (int) (Math.random() * (max - min + 1) + min);
    }
}
