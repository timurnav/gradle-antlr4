import org.example.SQLQuery;
import org.example.SQLQueryParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SimplestSelectQueryTest {

    @ParameterizedTest
    @MethodSource("data")
    void checkItWorks(String rawQuery, SQLQuery expectedQuery) {
        SQLQueryParser parser = new SQLQueryParser();
        SQLQuery actualQuery = parser.parseQuery(rawQuery);

        assertThat(actualQuery)
                .usingRecursiveComparison()
                .isEqualTo(expectedQuery);
    }

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("SELECT *\nFROM users;", new SQLQuery("users", List.of(), true)),
                Arguments.of("SELECT id\nFROM users;", new SQLQuery("users", List.of("id"), false)),
                Arguments.of(
                        "SELECT id, name, email FROM users;",
                        new SQLQuery("users", List.of("id", "name", "email"), false)
                ),
                Arguments.of(
                        "SELECT id, name /*,\n email */ FROM users;",
                        new SQLQuery("users", List.of("id", "name"), false)
                ),
                Arguments.of(
                        "SELECT id\n, name\n --, email\n FROM users;",
                        new SQLQuery("users", List.of("id", "name"), false)
                ),
                Arguments.of(
                        "SELECT id\n, name\n #, email\n FROM users;",
                        new SQLQuery("users", List.of("id", "name"), false)
                )
        );
    }
}
