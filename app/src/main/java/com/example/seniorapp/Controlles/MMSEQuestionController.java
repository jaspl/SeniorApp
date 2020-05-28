package com.example.seniorapp.Controlles;

import com.example.seniorapp.Patterns.MMSEQuestion;

import java.util.ArrayList;
import java.util.List;

public class MMSEQuestionController {

    public List<MMSEQuestion> getQuestionList() {
        MMSEQuestion q1 = new MMSEQuestion(1, 1, "Który jest rok?");
        MMSEQuestion q2 = new MMSEQuestion(2, 1, "Jaką jest porę roku?");
        MMSEQuestion q3 = new MMSEQuestion(3, 1, "Jaki jest miesiąc ?");
        MMSEQuestion q4 = new MMSEQuestion(4, 1, "Jaka jest dzisiejsza data?");
        MMSEQuestion q5 = new MMSEQuestion(5, 1, "Jaki jest dzień tygodnia?");
        MMSEQuestion q6 = new MMSEQuestion(6, 1, "Na terenie jakiego kraju się znajdujemy?");
        MMSEQuestion q7 = new MMSEQuestion(7, 1, "W jakim jesteśmy rejonie kraju?");
        MMSEQuestion q8 = new MMSEQuestion(8, 1, "W jakiej jesteśmy miejscowości?");
        MMSEQuestion q9 = new MMSEQuestion(9, 1, "Jaka jest adres tego domu lub Jak się nazywa budynek w którym jesteśmy?");
        MMSEQuestion q10 = new MMSEQuestion(10, 1, "W jakim pokoju jesteśmy  lub na jakim piętrze jesteśmy?");
        MMSEQuestion q11 = new MMSEQuestion(11, 3, "Mam zamiar wymienić trzy przedmioty. Kiedy skończę, chcę, żebyś je powtórzył. Pamiętaj, czym one są, ponieważ za kilka minut poproszę cię o ponowne nadanie im nazwy. Wypowiadaj powoli następujące słowa w odstępach 1-sekundowych - piłka / samochód / człowiek.");
        MMSEQuestion q12 = new MMSEQuestion(12, 5, "Przeliteruj słowo OBRAZ i przeliteruj je od tyłu.");
        MMSEQuestion q13 = new MMSEQuestion(13, 3, "Jakie były trzy przedmioty które prosiłem/łam cię abyś zapamiętał?");
        MMSEQuestion q14 = new MMSEQuestion(14, 1, "Jak to się nazywa?- pokaż zegarek na ręce.");
        MMSEQuestion q15 = new MMSEQuestion(15, 1, "Jak to się nazywa? - pokaż ołówek.");
        MMSEQuestion q16 = new MMSEQuestion(16, 1, "Powtórz po mnie: Nie, jeśli, i, lub, ale.");
        MMSEQuestion q17 = new MMSEQuestion(17, 1, "Przeczytaj słowa na stronie, a następnie zrób to, co mówi. (Podaj osobie arkusz z ZAMKNIJ OCZY. Jeśli badany czyta i nie zamyka oczu, powtórz do trzech razy. Ocena tylko wtedy, gdy obiekt zamyka oczy).");
        MMSEQuestion q18 = new MMSEQuestion(18, 1, "(Podaj osobie ołówek i papier). Napisz dowolne pełne zdanie na tym kawałku papieru. (Uwaga: zdanie musi mieć sens. Zignoruj błędy ortograficzne).");
        MMSEQuestion q19 = new MMSEQuestion(19, 1, "(Pokaż obrazek, podaj gumkę i ołówek przed osobą.) Proszę skopiować ten projekt.\nZezwalaj na wiele prób. Poczekaj, aż osoba skończy i oddaj ją. Ocena tylko dla poprawnie skopiowanego diagramu z 4-stronnym rysunkiem między dwoma 5-stronnymi cyframi.");
        MMSEQuestion q20 = new MMSEQuestion(20, 3, "(Zapytaj osobę, czy jest praworęczna lub leworęczna. Weź kawałek papieru i trzymaj go przed osobą.) Weź ten papier do prawej / lewej ręki (w zależności od tego, który nie jest dominujący), złóż papier na pół obiema rękami i połóż papier na podłodze. (Zdobądź 1 punkt za każdą poprawnie wykonaną instrukcję. Trzyma papier poprawnie w rękuSkłada go na pół Kładzie to na podłodze)");

        List<MMSEQuestion> list = new ArrayList<>();
        list.add(q1);
        list.add(q2);
        list.add(q3);
        list.add(q4);
        list.add(q5);
        list.add(q6);
        list.add(q7);
        list.add(q8);
        list.add(q9);
        list.add(q10);
        list.add(q11);
        list.add(q12);
        list.add(q13);
        list.add(q14);
        list.add(q15);
        list.add(q16);
        list.add(q17);
        list.add(q18);
        list.add(q19);
        list.add(q20);
        return list;
    }
}

