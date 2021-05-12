package net.weirdwired.java.interview.strings;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Theories.class)
public class StringChallengesTest {
    @DataPoints("string data for unique chars")
    public static List<Pair<String, Boolean>> strings =
            Stream.of(
                    new Pair<>("abcd", true),
                    new Pair<>("aaaa", false),
                    new Pair<>("한국말이야", true)).toList();

    @DataPoints("string data for comparing equal or one off")
    public static List<Triplet<String, String, Boolean>> strings2 =
            Stream.of(
                    new Triplet<>("abcd", "abcd", true),
                    new Triplet<>("aaaa", "aaab", true),
                    new Triplet<>("aaa", "aaab", true),
                    new Triplet<>("aaa", "aba", true),
                    new Triplet<>("aaa", "aaaa", true),
                    new Triplet<>("aaa", "abaa", false),
                    new Triplet<>("aaa", "aaaaa", false),
                    new Triplet<>("aaabb", "aaaa", false)).toList();

    @Theory
    @DisplayName("Should identify string with unique characters with map")
    public void testHasUniqueCharactersWithMap(@FromDataPoints("string data for unique chars") Pair<String, Boolean> string) {
        // Arrange
        // Act
        boolean result = StringChallenges.hasUniqueCharactersWithMap(string.getValue0());

        // Assert
        assertThat(result).isEqualTo(string.getValue1());
    }

    @Theory
    @DisplayName("Should identify string with unique characters with set")
    public void testHasUniqueCharactersWithSet(@FromDataPoints("string data for unique chars") Pair<String, Boolean> string) {
        // Arrange
        // Act
        boolean result = StringChallenges.hasUniqueCharactersWithSet(string.getValue0());

        // Assert
        assertThat(result).isEqualTo(string.getValue1());
    }

    @Test
    @DisplayName("Should escape white space with %20")
    public void testEscapeString() {
        // Arrange
        String testString = "  String   with spaces  ";
        String expected = "%20%20String%20%20%20with%20spaces%20%20";

        // Act
        char[] result = StringChallenges.escapeString(testString.toCharArray());
        char[] result2 = StringChallenges.escapeStringWithLoop(testString.toCharArray());

        // Assert
        assertThat(new String(result)).isEqualTo(expected);
        assertThat(new String(result2)).isEqualTo(expected);
    }

    @Theory
    @DisplayName("Should return true if equal or one off")
    public void testEqualOrOneOff(@FromDataPoints("string data for comparing equal or one off") Triplet<String, String, Boolean> strings) {
        // Arrange
        // Act
        boolean result = StringChallenges.equalOrOneOff(strings.getValue0(), strings.getValue1());

        // Assert
        assertThat(result).isEqualTo(strings.getValue2());
    }
}
