package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.seniorapp.Games.ColorGame.ColorGameActivity;
import com.example.seniorapp.Games.HangManGame.HangManActivity;
import com.example.seniorapp.Games.MemoryGame.MemoryGameActivity;
import com.example.seniorapp.Games.NumberGame.NumberGameActivity;
import com.example.seniorapp.Games.SymbolsGame.SymbolsGameActivity;

public class GameSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector);
        setAllButtonsClick();
    }
    @Override
    public void onBackPressed() {
    }

    private void setAllButtonsClick() {
        LinearLayout hangman = findViewById(R.id.hangman);
        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, HangManActivity.class));
            }
        });
        LinearLayout symbolGame = findViewById(R.id.symbol_game);
        symbolGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, SymbolsGameActivity.class));
            }
        });
        LinearLayout colorGame = findViewById(R.id.color_game);
        colorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, ColorGameActivity.class));
            }
        });
        LinearLayout memoryGame = findViewById(R.id.memory_game);
        memoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, MemoryGameActivity.class));
            }
        });
        LinearLayout numberGame = findViewById(R.id.number_game);
        numberGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, NumberGameActivity.class));
            }
        });
        LinearLayout logOut = findViewById(R.id.log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, StartInActivity.class));
            }
        });
        Button colorGameHowToPlay = findViewById(R.id.color_game_how_to_play_button);
        colorGameHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog("Jest to gra polegająca na wyświetlaniu nazwy koloru zapisanej innym kolorem czcionki niż słowo na nie wskazujące. Zadaniem gracza jest określenie koloru czcionki.", "Gra w kolory");
            }
        });
        Button hangManHowToPlay = findViewById(R.id.hungman_how_to_play_button);
        hangManHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog("Jest to gra polegająca na odgadywaniu słów. Gracz losuje słowo, zostaje mu pokazana kategoria opisująca słowo oraz ilość liter. Następnie użytkownik musi odgadnąć wszystkie litery w słowie. Jeśli trafi dobrze, to ukazuje się litera w odpowiadającym jej miejscu, jeśli źle to pojawia się kolejny poziom symbolicznej szubienicy. Jeżeli ukaże się cały obrazek szubienicy, to gracz przegrywa, natomiast jeśli gracz odgadnie słowo przed ukończeniem się całego obrazka, to wygrywa.\n", "Wisielec");
            }
        });
        Button symbolGameHowToPlay = findViewById(R.id.symbol_game_how_to_play_button);
        symbolGameHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog("Jest to gra polegająca na odnajdywaniu par takich samych kart. Użytkownik na początku rozgrywki będzie widział przez kilka sekund wszystkie karty odsłonięte. Następnie gdy przestaną być widoczne, będzie musiał wskazać pola, w których znajdują się takie same karty, poprzez wybranie jednej karty, a następnie kliknięciu pole, w którym powinna znajdować się jej para. Jeśli karty będą do siebie pasować, to pozostają odkryte na planszy, jeśli nie to zostają obie zakryte. Gra kończy się gdy użytkownik odnajdzie wszystkie pary.\n", "Gra w symbole");
            }
        });
        Button numberGameHowToPlay = findViewById(R.id.number_game_how_to_play_button);
        numberGameHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog("Jest to gra spostrzegawcza, polegająca na odnajdywaniu w ciągu znaków, dwóch takich cyfr, których suma jest równa podanej w poleceniu. Użytkownikowi pokażą się pola ze znakami, po czym będzie on miał możliwość wybrania dwóch pól leżących koło siebie, jeśli suma tych pól rzeczywiście będzie wynosiła podaną sumę, to pola zmienią kolor na zielony, jeśli nie to na kolor czerwony. Gra kończy się gdy gracz zakreśli wszystkie występujące pary na planszy.\n", "Gra w cyfry");
            }
        });
        Button memoryGameHowToPlay = findViewById(R.id.memory_how_to_play_button);
        memoryGameHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog("Jest to gra pamięciowa, polegająca na zapamiętaniu kształtu figur, ich kolorów oraz rozłożenia ukazanych na 9-polowej planszy w określonym czasie. Po odpowiednim czasie ukazuje się użytkownikowi pusta plansza, na której musi rozłożyć figury w identyczny sposób jak na wcześniej ukazanym obrazku. Rozgrywka kończy się gdy ułoży wszystkie figury w odpowiednich kolorach na swoim miejscu.\n", "Memory");
            }
        });

    }

    private void setDialog(String message, String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setTitle(title);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}