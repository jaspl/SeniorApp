package com.example.seniorapp.Utils;

public enum Category {
    KOLORY("KOLORY"),
    ZWERZĘTA("ZWIERZĘTA"),
    RODZINA("RODZINA"),
    OWOCE("OWOCE"),
    WARZYWA("WARZYWA"),
    DOM_MIESZKANIE("DOM I MIESZKANIE"),
    EMOCJE("EMOCJE"),
    CZŁOWIEK("CZŁOWIEK"),
    W_MIESZKANIU("W MIESZKANIU"),
    RZECZY_OSOBISTE("RZECZY OSOBISTE"),
    POGODA_I_KLIMAT("POGODA I KLIMAT"),
    ZAWODY("ZAWODY"),
    ZAKUPY_I_USŁUGI("ZAKUPY I USŁUGI"),
    KULTURA("KULTURA"),
    SPORT("SPORT"),
    ZDROWIE("ZDROWIE"),
    PAŃSTWO_I_SPOŁECZEŃSTWO("PAŃSTWO I SPOŁECZEŃSTWO"),
    BUDOWNICTWO_I_ARCHITEKTURA("BUDOWNICTWO I ARCHITEKTURA"),
    EKOLOGIA("EKOLOGIA"),
    CHARAKTER("CHARAKTER"),
    PODRÓŻOWANIE_I_TURYSTYKA("PODRÓŻOWANIE I TURYSTYKA"),
    NAUKA_I_TECHNIKA("NAUKA I TECHNIKA"),
    MOTORYZACJA("MOTORYZACJA"),
    KOMUNIKACJA("KOMUNIKACJA"),
    CZAS_WOLNY("CZAS WOLNY");

    public final String label;

    private Category(String label) {
        this.label = label;
    }
}
