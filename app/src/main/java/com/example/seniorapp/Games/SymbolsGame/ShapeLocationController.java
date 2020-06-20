package com.example.seniorapp.Games.SymbolsGame;

import android.widget.LinearLayout;

import com.example.seniorapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ShapeLocationController {
    List<ShapeDetails> destinationShapes = new ArrayList();
    List<ShapeDetails> madeShapes = new ArrayList();
    private static ShapeLocationController INSTANCE;

    public ShapeLocationController() {
    }

    public static ShapeLocationController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShapeLocationController();
        }
        return INSTANCE;
    }

    public List<ShapeDetails> getRandomShapesList(int figuresCount, int shapesCount, int colorCount) {
        for (int i = 0; i < figuresCount; i++) {
            ShapeDetails shapeDetails = new ShapeDetails();
            shapeDetails.setShape(getRandomValue(shapesCount));
            shapeDetails.setColor(getRandomValue(colorCount));
            shapeDetails.setRow(getRandomValue(3));
            shapeDetails.setColumn(getRandomValue(3));
            shapeDetails.setToRemove(0);
            destinationShapes.add(shapeDetails);
        }
        return destinationShapes;
    }

    public void addNewShapeToList(ShapeDetails shapeDetails) {
        //TODO
        madeShapes.add(shapeDetails);
    }

    private int getRandomValue(int randomToNumber) {
        return ThreadLocalRandom.current().nextInt(0, randomToNumber);
    }

    public int getIdByColRow(int col, int row) {
        if (col == 0 && row == 0) {
            return R.id.symbol_game_image_c0r0;
        } else if (col == 1 && row == 0) {
            return R.id.symbol_game_image_c1r0;
        } else if (col == 2 && row == 0) {
            return R.id.symbol_game_image_c2r0;
        } else if (col == 0 && row == 1) {
            return R.id.symbol_game_image_c0r1;
        } else if (col == 1 && row == 1) {
            return R.id.symbol_game_image_c1r1;
        } else if (col == 2 && row == 1) {
            return R.id.symbol_game_image_c2r1;
        } else if (col == 0 && row == 2) {
            return R.id.symbol_game_image_c0r2;
        } else if (col == 1 && row == 2) {
            return R.id.symbol_game_image_c1r2;
        } else {
            return R.id.symbol_game_image_c2r2;
        }
    }

    public void compareShapesChangeIfSame(ShapeDetails shapeDetails, List<ShapeDetails> shapeDetailsList) {
        //TODO

    }

    private int getRandomExceptOne(int number, int maxNr) {
        int temp = number; // This will be your value to be compared to random value
        for (int i = 0; i < maxNr; i++) { // assuming your filteredArraylist is size 10
            int randomValue = ThreadLocalRandom.current().nextInt(0, maxNr);
            if (randomValue == temp) {
                i--; // it will repeat the generation of random value if temp and randomValue is same
            } else {
                temp = randomValue; // you will put here the ne random value to be compared to next value
                return randomValue;
            }
        }
        return temp;
    }
}
