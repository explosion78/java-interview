package net.weirdwired.java.interview.strings;

import org.javatuples.Pair;
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
}
