package com.gabriel.berlinclock.domain;

import java.util.List;

public enum Lamp {
    YELLOW("Y"),
    RED("R"),
    OFF("O"),
    ;

    private final String letter;

    Lamp(String letter) {
        this.letter = letter;
    }

    public static Lamp getFromLetter(String letter){
        for (Lamp value : values()) {
            if(value.letter.equals(letter))
                return value;
        }
        throw new IllegalArgumentException(String.format("No lamp found with letter: '%s'", letter));
    }

    public static List<Lamp> getLampsFromString(String letters){
        return letters.chars().mapToObj(l -> getFromLetter(String.valueOf((char) l))).toList();
    }



}
