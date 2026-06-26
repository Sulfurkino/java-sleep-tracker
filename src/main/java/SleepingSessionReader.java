import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepingSessionReader {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepingSession> read(String filePath) throws IOException {

        try (var lines = Files.lines(Path.of(filePath))) {

            return lines
                    .map(this::parseLine)
                    .toList();
        }
    }


    private SleepingSession parseLine(String line) {

        String[] parts = line.split(";");

        if (parts.length !=3){
            throw new IllegalArgumentException( "Некорректная строка логов - " + line);
        }
        LocalDateTime start =
                LocalDateTime.parse(parts[0], FORMATTER);

        LocalDateTime end =
                LocalDateTime.parse(parts[1], FORMATTER);

        SleepQuality quality =
                SleepQuality.valueOf(parts[2]);

        return new SleepingSession(start, end, quality);
    }
}