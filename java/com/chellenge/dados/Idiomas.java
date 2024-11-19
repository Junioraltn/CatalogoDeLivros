package com.challenge.chellenge.dados;

import java.util.Arrays;

public enum Idiomas {
    en ("Inglês"),
    fr ("Francês"),
    de ("Alemão"),
    fi ("Finlandês"),
    es ("Espanhol"),
    it ("Italiano"),
    nl ("Holandês"),
    pt ("Português"),
    sv ("Sueco"),
    da ("Dinamarquês");



    private String idioma;

    Idiomas(String idioma) {
    this.idioma = idioma;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idioma.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        return null;
    }

    public String getIdioma() {
        return idioma;
    }
}
