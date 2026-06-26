import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSessionsCountFunctionTest {

    private final TotalSessionsCountFunction function = new TotalSessionsCountFunction();

    @Test
    void shouldReturnZero() {
        assertEquals(0, function.apply(List.of()).getValue());
    }

    @Test
    void shouldReturnCount() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(8), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(7), SleepQuality.BAD)
        );

        assertEquals(2, function.apply(sessions).getValue());
    }
}