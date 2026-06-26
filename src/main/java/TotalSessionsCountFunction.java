import java.util.List;
import java.util.function.Function;

public class TotalSessionsCountFunction implements Function<List<SleepingSession>,SleepAnalysisResult<Integer>> {

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sessions){
        return new SleepAnalysisResult<>( "Всего количество сессий сна равно " , sessions.size());
    }

}
