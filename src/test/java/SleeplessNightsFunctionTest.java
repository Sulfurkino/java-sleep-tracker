import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleeplessNightsFunctionTest {

    private final SleeplessNightsFunction function = new SleeplessNightsFunction();

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(0, function.apply(List.of()).getValue());
    }

    @Test
    void shouldReturnOneSleeplessNight() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        SleepQuality.GOOD)
        );

        assertEquals(1, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnTwoSleeplessNights() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 17, 0),
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        SleepQuality.GOOD),

                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        LocalDateTime.of(2025, 10, 3, 11, 0),
                        SleepQuality.GOOD)
        );

        assertEquals(2, function.apply(sessions).getValue());
    }
}