package net.weirdwired.java.interview.strings;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringChallenges {

    // using map
    public static boolean hasUniqueCharactersWithMap(String str) {
        Map<Character, Boolean> chars = new HashMap<>();
        return str.chars()
                .filter(x -> !Character.isWhitespace(x)) // ignore white space
                .allMatch(c -> Character.isValidCodePoint(c) && chars.put((char) c, true) == null); // valid code point and no existing char
    }

    // using set
    public static boolean hasUniqueCharactersWithSet(String str) {
        Set<Character> chars = new HashSet<>();
        return str.chars()
                .filter(x -> !Character.isWhitespace(x)) // ignore white space
                .allMatch(c -> Character.isValidCodePoint(c) && chars.add((char) c)); // valid code point and no existing char
    }

    public static char[] escapeString(char[] str) {
        return new String(str).chars()
                .flatMap(x -> {
                    if (Character.isWhitespace(x)) {
                        return "%20".chars();
                    }
                    return IntStream.of(x);
                })
                .mapToObj(Character::toString).collect(Collectors.joining()).toCharArray(); //unfortunately, there's no CharStream or any relevant methods for char arrays
    }

    public static char[] escapeStringWithLoop(char[] str) {
        List<Character> result = new ArrayList<>();
        for (char c : str) {
            if (Character.isWhitespace(c)) {
                result.addAll(Arrays.asList('%', '2', '0'));
            } else {
                result.add(c);
            }
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining()).toCharArray(); // it's just not easy to deal with char[]
    }

    public static boolean equalOrOneOff(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else if (StrictMath.abs(str1.length() - str2.length()) > 1) {
            return false;
        } else {
            char[] longer = str1.length() > str2.length() ? str1.toCharArray() : str2.toCharArray();
            char[] shorter = str1.length() > str2.length() ? str2.toCharArray() : str1.toCharArray();
            int index = -1;

            for (int i = 0; i < shorter.length; i++) {
                if (shorter[i] == longer[index >= 0 && longer.length > shorter.length ? i + 1 : i]) {
                    continue;
                } else if (index >= 0) {
                    return false;
                }
                index = i;
            }

            return index < 0 || shorter.length == longer.length;
        }
    }
}
