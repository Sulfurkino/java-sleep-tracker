import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinSleepDurationFunctionTest {

    private final MinSleepDurationFunction function = new MinSleepDurationFunction();

    @Test
    void shouldReturnShortestSleep() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025,1,1,22,0),
                        LocalDateTime.of(2025,1,2,6,0), SleepQuality.GOOD),

                new SleepingSession(LocalDateTime.of(2025,1,2,22,0),
                        LocalDateTime.of(2025,1,3,2,0), SleepQuality.GOOD)
        );

        assertEquals(240, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnZeroForEmpty() {
        assertEquals(0L, function.apply(List.of()).getValue());
    }
}