package com.example.seniorapp.Games.SymbolsGame;

import android.util.Log;

import com.example.seniorapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShapeLocationController {
    public List<ShapeDetails> destinationShapes = new ArrayList();
    public List<ShapeDetails> madeShapes = new ArrayList();
    private static ShapeLocationController INSTANCE;

    public ShapeLocationController() {
    }

    public static ShapeLocationController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShapeLocationController();
        }
        return INSTANCE;
    }

    public List<ShapeDetails> getMadeShapes() {
        return madeShapes;
    }

    public List<ShapeDetails> getRandomShapesList(int figuresCount, int shapesCount, int colorCount) {
        destinationShapes.clear();
        for (int i = 0; i < figuresCount; i++) {
            ShapeDetails shapeDetails;
            do {
                shapeDetails = new ShapeDetails(getRandomValue(shapesCount), getRandomValue(colorCount), getRandomValue(3), getRandomValue(3), 0);
            } while (checkIfThereIsSthOnThisPlace(shapeDetails.column, shapeDetails.row));
            destinationShapes.add(shapeDetails);
        }
        return destinationShapes;
    }

    public void addNewShapeToList(ShapeDetails shapeDetails) {
        if (shapeDetails.toRemove == 1) {
            checkIfThereIsSthToRemoveAndRemove(shapeDetails);
        } else {
            checkIfThereIsSthToRemoveAndRemove(shapeDetails);
            madeShapes.add(shapeDetails);
        }
        checkIfGameFinished();
    }

    private void checkIfThereIsSthToRemoveAndRemove(ShapeDetails shapeDetails) {
        for (int i = 0; i < madeShapes.size(); i++) {
            ShapeDetails shape = madeShapes.get(i);
            if (shapeDetails.row == shape.row && shapeDetails.column == shape.column) {
                madeShapes.remove(i);
            }
        }
    }


    private int getRandomValue(int randomToNumber) {
        Random rand = new Random();
        return rand.nextInt(randomToNumber);
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

    public int getLayoutIdByColRow(int col, int row) {
        if (col == 0 && row == 0) {
            return R.id.symbol_game_bytton_c0r0;
        } else if (col == 1 && row == 0) {
            return R.id.symbol_game_bytton_c1r0;
        } else if (col == 2 && row == 0) {
            return R.id.symbol_game_bytton_c2r0;
        } else if (col == 0 && row == 1) {
            return R.id.symbol_game_bytton_c0r1;
        } else if (col == 1 && row == 1) {
            return R.id.symbol_game_bytton_c1r1;
        } else if (col == 2 && row == 1) {
            return R.id.symbol_game_bytton_c2r1;
        } else if (col == 0 && row == 2) {
            return R.id.symbol_game_bytton_c0r2;
        } else if (col == 1 && row == 2) {
            return R.id.symbol_game_bytton_c1r2;
        } else {
            return R.id.symbol_game_bytton_c2r2;
        }
    }

    public int getHelpImageIdByColRow(int col, int row) {
        if (col == 0 && row == 0) {
            return R.id.symbol_game_image_c0r0_2;
        } else if (col == 1 && row == 0) {
            return R.id.symbol_game_image_c1r0_2;
        } else if (col == 2 && row == 0) {
            return R.id.symbol_game_image_c2r0_2;
        } else if (col == 0 && row == 1) {
            return R.id.symbol_game_image_c0r1_2;
        } else if (col == 1 && row == 1) {
            return R.id.symbol_game_image_c1r1_2;
        } else if (col == 2 && row == 1) {
            return R.id.symbol_game_image_c2r1_2;
        } else if (col == 0 && row == 2) {
            return R.id.symbol_game_image_c0r2_2;
        } else if (col == 1 && row == 2) {
            return R.id.symbol_game_image_c1r2_2;
        } else {
            return R.id.symbol_game_image_c2r2_2;
        }
    }

    public Boolean checkIfGameFinished() {
        int count = 0;
        if (madeShapes.size() == destinationShapes.size()) {
            for (ShapeDetails shape : destinationShapes) {
                for (ShapeDetails shape2 : madeShapes) {
                    if (shape.column == shape2.column && shape.row == shape2.row && shape.color == shape2.color && shape.shape == shape2.shape) {
                        count++;
                    }
                }
            }
        }
        if (count == (destinationShapes.size())) {
            Log.d("gra zakończona", "udało ci się odgadnąć wszystkie pola");
            return true;
        } else {
            Log.d("gra w toku", "liczba pkształtów celu jest równa twoim");
            return false;
        }
    }

    private boolean checkIfThereIsSthOnThisPlace(int col, int row) {
        boolean ifThereIsSth = false;
        for (ShapeDetails shape : destinationShapes) {
            if (shape.row == row && shape.column == col) {
                ifThereIsSth = true;
            }
        }
        return ifThereIsSth;
    }

    public void resetLists() {
        destinationShapes.clear();
        madeShapes.clear();
    }
}
