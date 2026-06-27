import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class SleepingSessionReader {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepingSession> read(String filePath) throws IOException {

        try (var lines = Files.lines(Path.of(filePath))) {

            return lines
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .toList();
        }
    }


    private SleepingSession parseLine(String line) {
        try {
            String[] parts = line.split(";");

            if (parts.length != 3) {
                throw new IllegalArgumentException();
            }

            LocalDateTime start = LocalDateTime.parse(parts[0], FORMATTER);

            LocalDateTime end = LocalDateTime.parse(parts[1], FORMATTER);

            SleepQuality quality = SleepQuality.valueOf(parts[2]);

            return new SleepingSession(start, end, quality);

        } catch (Exception e) {
            System.out.println("Пропущена некорректная строка: " + line);
            return null;
        }
    }
}