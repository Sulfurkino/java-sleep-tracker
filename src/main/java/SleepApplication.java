import java.util.List;
import java.util.function.Function;

public class SleepApplication {

    private static final List<
            Function<List<SleepingSession>, ? extends SleepAnalysisResult<?>>
            > FUNCTIONS =
            List.of(
                    new TotalSessionsCountFunction(),
                    new MinSleepDurationFunction(),
                    new MaxSleepDurationFunction(),
                    new AverageSleepDurationFunction(),
                    new BadSleepSessionCounterFunction(),
                    new SleeplessNightsFunction(),
                    new ChronotypeFunction()
            );

    public static void main(String[] args) throws Exception {

        String filePath = args[0];

        SleepingSessionReader reader = new SleepingSessionReader();

        List<SleepingSession> sessions = reader.read(filePath);

        FUNCTIONS.stream()
                .map(function -> function.apply(sessions))
                .forEach(result ->
                        System.out.println(result.getDescription() + ": " + result.getValue()));
    }
}
