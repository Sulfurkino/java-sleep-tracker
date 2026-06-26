import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSleepDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long minDuration = sessions.stream().mapToLong(session -> Duration.between(session.getStart(), session.getEnd()).toMinutes()).min().orElse(0);
        return new SleepAnalysisResult<>("Минимальная продолжительность сессии сна в минутах", minDuration);
    }
}
