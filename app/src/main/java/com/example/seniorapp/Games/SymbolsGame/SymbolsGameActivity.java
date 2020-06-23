package com.example.seniorapp.Games.SymbolsGame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.seniorapp.Games.ColorGame.ColorGameActivity;
import com.example.seniorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SymbolsGameActivity extends AppCompatActivity {
    List<LinearLayout> shapeButtons = new ArrayList();
    List<LinearLayout> colorButtons = new ArrayList();
    int shapeNumner = 0;
    int colorNumner = 0;
    int toRemove = 0;

    //TODO set Timer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbols_game);
        gameInitialization();
    }

    private void gameInitialization() {
        setLvlParams();
        ImagesSeter();
        setRemoveButtonClick();
        setButtonsAction();
    }

    private void setColorButtons(int colorNumber, LinearLayout colorByttonsLayout) {
        TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        for (int i = 0; i < colorNumber; i++) {
            LinearLayout colorButton = linearButtonCreator(colorByttonsLayout, i);
            ImageView colorImage = new ImageView(this);
            colorImage.setBackgroundColor(colors.getColor(i, 0));
            colorImage.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 60);
            colorImage.setLayoutParams(layoutParams);
            setColorButtonClickAction(colorButton);
            colorButton.addView(colorImage);
            colorButtons.add(colorButton);
        }
    }

    private void setShapeButtons(int shapesNumber, LinearLayout colorByttonsLayout) {
        TypedArray shapes = getResources().obtainTypedArray(R.array.sahpes);
        for (int i = 0; i < shapesNumber; i++) {
            LinearLayout shapeButton = linearButtonCreator(colorByttonsLayout, i);
            ImageView colorImage = new ImageView(this);
            colorImage.setImageDrawable(shapes.getDrawable(i));
            colorImage.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(85, 85);
            colorImage.setLayoutParams(layoutParams);
            setShapeButtonClickAction(shapeButton);
            shapeButton.addView(colorImage);
            shapeButtons.add(shapeButton);
        }
    }

    private LinearLayout linearButtonCreator(LinearLayout colorByttonsLayout, int tagid) {
        LinearLayout colorButton = new LinearLayout(this);
        colorButton.setLayoutParams(new LinearLayout.LayoutParams(85, 85));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) colorButton.getLayoutParams();
        params.setMargins(10, 0, 10, 0);
        colorButton.setLayoutParams(params);
        colorButton.setTag(tagid);
        colorButton.setGravity(Gravity.CENTER);
        colorButton.setOrientation(LinearLayout.HORIZONTAL);
        if (tagid == 0) {
            colorButton.setBackgroundColor(Color.YELLOW);
        } else {
            colorButton.setBackgroundColor(Color.GRAY);
        }
        colorButton.setPadding(10, 10, 10, 10);
        colorByttonsLayout.addView(colorButton);
        return colorButton;
    }

    private void setColorButtonClickAction(final LinearLayout colorButton) {
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColorButtonsColorsToGray();
                colorButton.setBackgroundColor(Color.YELLOW);
                colorNumner = ((int) colorButton.getTag());
            }
        });
    }

    private void setShapeButtonClickAction(final LinearLayout shapeButton) {
        shapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearShapeButtonColorsToGray();
                shapeButton.setBackgroundColor(Color.YELLOW);
                shapeNumner = ((int) shapeButton.getTag());
            }
        });
    }

    private void clearColorButtonsColorsToGray() {
        for (LinearLayout button : colorButtons) {
            button.setBackgroundColor(Color.GRAY);
        }
    }

    private void clearShapeButtonColorsToGray() {
        for (LinearLayout button : shapeButtons) {
            button.setBackgroundColor(Color.GRAY);
        }
    }

    private LinearLayout getColorButtonsLayout() {
        return findViewById(R.id.symbol_game_colors_button_layout);
    }

    private LinearLayout getShapeButtonsLayout() {
        return findViewById(R.id.symbol_game_shape_button);
    }

    private Button getRemoveButton() {
        return findViewById(R.id.symbol_game_remove_shape_button);
    }

    private FloatingActionButton getEndGameButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }

    private Button getHelpButton() {
        return findViewById(R.id.symbol_game_help_button);
    }

    private void ImagesSeter() {
        LinearLayout button00 = findViewById(R.id.symbol_game_bytton_c0r0);
        LinearLayout button10 = findViewById(R.id.symbol_game_bytton_c1r0);
        LinearLayout button20 = findViewById(R.id.symbol_game_bytton_c2r0);
        LinearLayout button01 = findViewById(R.id.symbol_game_bytton_c0r1);
        LinearLayout button11 = findViewById(R.id.symbol_game_bytton_c1r1);
        LinearLayout button21 = findViewById(R.id.symbol_game_bytton_c2r1);
        LinearLayout button02 = findViewById(R.id.symbol_game_bytton_c0r2);
        LinearLayout button12 = findViewById(R.id.symbol_game_bytton_c1r2);
        LinearLayout button22 = findViewById(R.id.symbol_game_bytton_c2r2);
        button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(0, 0);
                setShapesFromMadeShapes();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(1, 0);
                setShapesFromMadeShapes();
            }
        });
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(2, 0);
                setShapesFromMadeShapes();
            }
        });
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(0, 1);
                setShapesFromMadeShapes();
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(1, 1);
                setShapesFromMadeShapes();
            }
        });
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(2, 1);
                setShapesFromMadeShapes();
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(0, 2);
                setShapesFromMadeShapes();
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(1, 2);
                setShapesFromMadeShapes();
            }
        });
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShapeToList(2, 2);
                setShapesFromMadeShapes();
            }
        });
    }

    private void addShapeToList(int col, int row) {
        ShapeLocationController.getInstance().addNewShapeToList(new ShapeDetails(shapeNumner, colorNumner, col, row, toRemove));
        clearBoard();
    }

    private void setShapesAndRemoveInPlace(int id, ShapeDetails shape) {
        ImageView image = findViewById(id);
        {
            image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shape.color, shape.shape), getTheme()));
        }
    }

    private void setRemoveButtonClick() {
        getRemoveButton().setBackgroundColor(Color.GRAY);
        getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toRemove == 0) {
                    toRemove = 1;
                    getRemoveButton().setBackgroundColor(Color.YELLOW);
                } else {
                    toRemove = 0;
                    getRemoveButton().setBackgroundColor(Color.GRAY);
                }
            }
        });
    }

    private void setButtonsAction() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo set end game action;
                Intent intent = new Intent(SymbolsGameActivity.this, ColorGameActivity.class);
                startActivity(intent);
            }
        });

        getHelpButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDIalog();
            }
        });
    }

    private void setShapesFromListOnBoard(List<ShapeDetails> shapeList) {
        for (ShapeDetails shape : shapeList) {
            setShapesAndRemoveInPlace(ShapeLocationController.getInstance().getIdByColRow(shape.column, shape.row), shape);
        }
    }

    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView image = findViewById(ShapeLocationController.getInstance().getIdByColRow(i, j));
                image.setImageDrawable(null);
            }
        }
    }

    private void setShapesFromMadeShapes() {
        setShapesFromListOnBoard(ShapeLocationController.getInstance().getMadeShapes());
    }

    private int getLvl() {
        //TODO get lvl from serwer
        int lvl = 5;
        return lvl;
    }

    private void setLvlParams() {
        int lvl = getLvl();
        if (lvl == 1) {
            setGameLvl(1, 3);
        } else if (lvl == 2) {
            setGameLvl(2, 3);
        } else if (lvl == 3) {
            setGameLvl(3, 3);
        } else if (lvl == 4) {
            setGameLvl(3, 4);
        } else {
            setGameLvl(4, 4);
        }
    }

    private void setGameLvl(int colorNumner, int shapeNumner) {
        setColorButtons(colorNumner, getColorButtonsLayout());
        setShapeButtons(shapeNumner, getShapeButtonsLayout());
        ShapeLocationController.getInstance().resetLists();
        setShapesFromListOnBoard(ShapeLocationController.getInstance().getRandomShapesList(4, shapeNumner, colorNumner));
    }

    private void showHelpDIalog() {
        Dialog helpDialog = new Dialog(SymbolsGameActivity.this);
        helpDialog.setContentView(R.layout.symbol_game_destination_view);
        for (ShapeDetails shape : ShapeLocationController.getInstance().destinationShapes) {
            ImageView imageView = helpDialog.findViewById(ShapeLocationController.getInstance().getHelpImageIdByColRow(shape.column, shape.row));
            imageView.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shape.color, shape.shape), getTheme()));
        }
        helpDialog.show();
    }


}