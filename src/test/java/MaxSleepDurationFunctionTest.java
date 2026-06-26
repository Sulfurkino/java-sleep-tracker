import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSleepDurationFunctionTest {

    private final MaxSleepDurationFunction function = new MaxSleepDurationFunction();

    @Test
    void shouldReturnLongestSleep() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 1, 1, 22, 0),
                        LocalDateTime.of(2025, 1, 2, 8, 0), SleepQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2025, 1, 2, 22, 0),
                        LocalDateTime.of(2025, 1, 3, 4, 0), SleepQuality.GOOD)
        );

        assertEquals(600, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnZeroForEmpty() {
        assertEquals(0L, function.apply(List.of()).getValue());
    }
}