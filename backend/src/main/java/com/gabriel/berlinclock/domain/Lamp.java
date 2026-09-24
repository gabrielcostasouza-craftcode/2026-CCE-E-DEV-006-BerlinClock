package com.gabriel.berlinclock.domain;

import java.util.List;
import java.util.stream.Collectors;

public enum Lamp {
    YELLOW('Y'),
    RED('R'),
    OFF('O'),
    ;

    private final char letter;

    Lamp(char letter) {
        this.letter = letter;
    }

    public static Lamp getFromLetter(char letter){
        for (Lamp value : values()) {
            if(value.letter == letter)
                return value;
        }
        throw new InvalidBerlinTimeException(String.format("No lamp found with letter: '%s'", letter));
    }

    public static List<Lamp> getLampsFromString(String letters){
        return letters.chars().mapToObj(l -> getFromLetter((char) l)).toList();
    }

    public static String convertListToLetterString(List<Lamp> lamps){
        return lamps.stream().map(e -> String.valueOf(e.letter)).collect(Collectors.joining());
    }

}
