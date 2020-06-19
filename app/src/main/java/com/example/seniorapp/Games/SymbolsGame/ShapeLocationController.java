package com.example.seniorapp.Games.SymbolsGame;

import java.util.ArrayList;
import java.util.List;

public class ShapeLocationController {
    List<ShapeDetails> destinationShapes= new ArrayList();
    List<ShapeDetails> madeShapes= new ArrayList();
    private static ShapeLocationController INSTANCE;

    private ShapeLocationController() {
    }

    public static ShapeLocationController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ShapeLocationController();
        }
        return INSTANCE;
    }
    public void getRandomShapes(){

    }
    public void addNewShapeToList(){

    }
}
