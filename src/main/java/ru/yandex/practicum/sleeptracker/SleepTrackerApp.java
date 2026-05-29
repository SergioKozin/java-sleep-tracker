package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    public static void main(String[] args) throws RuntimeException {
        List<Function<List<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            List<String> lines = bufferedReader.lines()
                    .toList();
            List<SleepingSession> sleepingSessions = lines.stream()
                    .map(s -> s.split(";"))
                    .map(p -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
                        return new SleepingSession(
                                LocalDateTime.parse(p[0], formatter),
                                LocalDateTime.parse(p[1], formatter),
                                SleepingQuality.valueOf(p[2]));
                    })
                    .toList();

            functions.add(new GetQtyOfSessions());
            functions.add(new GetMinDurationOfSession());
            functions.add(new GetMaxDurationOfSession());
            functions.add(new GetAvgDurationOfSession());
            functions.add(new GetQtyOfBadSessions());
            functions.add(new GetQtyOfSleeplessNights());
            functions.add(new GetUserType());

            functions.forEach(
                    (function) -> {
                        System.out.print(function.apply(sleepingSessions).getDescription());
                        System.out.println(function.apply(sleepingSessions).getResult());
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}