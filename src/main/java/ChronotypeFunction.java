import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronotypeFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Chronotype>> {

    @Override
    public SleepAnalysisResult<Chronotype> apply(List<SleepingSession> sessions) {

        long owls = sessions.stream().filter(this::isNightSleep).filter(session -> session.getStart().toLocalTime().isAfter(LocalTime.of(23, 0)) && session.getEnd().toLocalTime().isAfter(LocalTime.of(9, 0))).count();

        long larks = sessions.stream().filter(this::isNightSleep).filter(session -> session.getStart().toLocalTime().isBefore(LocalTime.of(22, 0)) && session.getEnd().toLocalTime().isBefore(LocalTime.of(7, 0))).count();

        long doves = sessions.stream().filter(this::isNightSleep).count() - owls - larks;

        Chronotype result;

        if (owls > larks && owls > doves) {
            result = Chronotype.OWL;
        } else if (larks > owls && larks > doves) {
            result = Chronotype.LARK;
        } else {
            result = Chronotype.DOVE;
        }

        return new SleepAnalysisResult<>("Хронотип пользователя", result);
    }

    private boolean isNightSleep(SleepingSession session) {
        return session.getStart().toLocalDate().isBefore(session.getEnd().toLocalDate());
    }
}