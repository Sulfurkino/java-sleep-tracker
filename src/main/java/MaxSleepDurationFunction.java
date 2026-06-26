import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long maxDuration = sessions.stream().mapToLong(session -> Duration.between(session.getStart(), session.getEnd()).toMinutes()).max().orElse(0);

        return new SleepAnalysisResult<>("Максимальная продолжительность сессии сна в минутах", maxDuration);

    }
}
