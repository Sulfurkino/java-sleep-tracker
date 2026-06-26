import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadSleepSessionCounterFunctionTest {

    private final BadSleepSessionCounterFunction function = new BadSleepSessionCounterFunction();

    @Test
    void shouldCountBadSessions() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.BAD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.BAD)
        );

        assertEquals(2L, function.apply(sessions).getValue());
    }

    @Test
    void shouldReturnZero() {
        assertEquals(0L, function.apply(List.of()).getValue());
    }
}