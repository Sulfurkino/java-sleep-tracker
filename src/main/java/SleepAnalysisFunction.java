import java.util.List;

public interface SleepAnalysisFunction {
    SleepAnalysisResult apply(List<SleepingSession> sessions);
}
