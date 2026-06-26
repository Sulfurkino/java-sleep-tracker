import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSleepDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        double averageDuration = sessions.stream().mapToLong(session -> Duration.between(session.getStart(), session.getEnd()).toMinutes()).average().orElse(0);


        return new SleepAnalysisResult<>("Средняя продолжительность сессии сна в минутах", Math.round(averageDuration));

    }
}
