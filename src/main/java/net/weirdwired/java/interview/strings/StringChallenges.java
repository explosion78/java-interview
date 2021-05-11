package net.weirdwired.java.interview.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringChallenges {

    // using map
    public static boolean hasUniqueCharactersWithMap(String str) {
        Map<Character, Boolean> chars = new HashMap<>();
        return str.chars()
                .mapToObj(x -> (char) x)
                .filter(x -> !Character.isWhitespace(x)) // ignore white space
                .allMatch(c -> Character.isValidCodePoint(c) && chars.put(c, true) == null); // valid code point and no existing char
    }

    // using set
    public static boolean hasUniqueCharactersWithSet(String str) {
        Set<Character> chars = new HashSet<>();
        return str.chars()
                .mapToObj(x -> (char) x)
                .filter(x -> !Character.isWhitespace(x)) // ignore white space
                .allMatch(c -> Character.isValidCodePoint(c) && chars.add(c)); // valid code point and no existing char
    }
}
