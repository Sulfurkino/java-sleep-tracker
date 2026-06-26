import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageSleepDurationFunctionTest {

    private final AverageSleepDurationFunction function = new AverageSleepDurationFunction();

    @Test
    void shouldReturnAverage() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 1, 1, 22, 0),
                        LocalDateTime.of(2025, 1, 2, 6, 0), SleepQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2025, 1, 2, 22, 0),
                        LocalDateTime.of(2025, 1, 3, 8, 0), SleepQuality.GOOD)
        );

        assertEquals(540L, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnZeroForEmpty() {
        assertEquals(0L, function.apply(List.of()).getValue());
    }
}