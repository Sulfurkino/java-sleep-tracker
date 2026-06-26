import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleeplessNightsFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>("Количество бессонных ночей", 0);
        }

        SleepingSession first = sessions.get(0);
        SleepingSession last = sessions.get(sessions.size() - 1);

        LocalDate firstNight = first.getStart().toLocalTime().isBefore(LocalTime.NOON)
                ? first.getStart().toLocalDate().minusDays(1)
                : first.getStart().toLocalDate();

        LocalDate lastNight = last.getEnd().toLocalTime().isBefore(LocalTime.NOON)
                ? last.getEnd().toLocalDate().minusDays(1)
                : last.getEnd().toLocalDate();

        long sleepless = Stream.iterate(
                        firstNight,
                        date -> !date.isAfter(lastNight),
                        date -> date.plusDays(1))
                .filter(night -> sessions.stream().noneMatch(session -> sleptThisNight(session, night)))
                .count();

        return new SleepAnalysisResult<>("Количество бессонных ночей", (int) sleepless);
    }

    private boolean sleptThisNight(SleepingSession session, LocalDate night) {

        LocalDateTime nightStart = night.atStartOfDay();
        LocalDateTime nightEnd = night.atTime(6, 0);

        return session.getStart().isBefore(nightEnd)
                && session.getEnd().isAfter(nightStart);
    }
}