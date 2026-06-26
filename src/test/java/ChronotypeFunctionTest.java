import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChronotypeFunctionTest {

    private final ChronotypeFunction function = new ChronotypeFunction();

    @Test
    void shouldReturnOwl() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 10, 0),
                        SleepQuality.GOOD),

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 40),
                        LocalDateTime.of(2025, 10, 3, 9, 30),
                        SleepQuality.GOOD)
        );

        assertEquals(Chronotype.OWL, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnLark() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 30),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD),

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 21, 0),
                        LocalDateTime.of(2025, 10, 3, 6, 30),
                        SleepQuality.GOOD)
        );

        assertEquals(Chronotype.LARK, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnDove() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 30),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        SleepQuality.GOOD),

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 22, 20),
                        LocalDateTime.of(2025, 10, 3, 8, 15),
                        SleepQuality.GOOD)
        );

        assertEquals(Chronotype.DOVE, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnDoveWhenEqualCounts() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD),

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 21, 30),
                        LocalDateTime.of(2025, 10, 3, 6, 0),
                        SleepQuality.GOOD)
        );

        assertEquals(Chronotype.DOVE, function.apply(sessions).getValue());
    }
}