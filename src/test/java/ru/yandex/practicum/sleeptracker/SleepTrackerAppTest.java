package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepTrackerAppTest {
    public static List<SleepingSession> sleepingSessions = null;

    @BeforeAll
    public static void beforeAll() throws RuntimeException {

        try (BufferedReader bufferedReader
                     = new BufferedReader(new FileReader("src/main/resources/sleep_log.txt"))) {
            List<String> lines = bufferedReader.lines()
                    .toList();
            SleepTrackerAppTest.sleepingSessions = lines.stream()
                    .map(s -> s.split(";"))
                    .map(p -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
                        return new SleepingSession(
                                LocalDateTime.parse(p[0], formatter),
                                LocalDateTime.parse(p[1], formatter),
                                SleepingQuality.valueOf(p[2]));
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldGetAverageDuration() {
        Assertions.assertEquals("345", new GetAvgDurationOfSession().apply(sleepingSessions).getResult());
    }

    @Test
    void shouldGetMaxDuration() {
               Assertions.assertEquals("500", new GetMaxDurationOfSession().apply(sleepingSessions).getResult());
    }

    @Test
    void shouldGetMinDuration() {
               Assertions.assertEquals("45", new GetMinDurationOfSession().apply(sleepingSessions).getResult());
    }

    @Test
    void shouldGetQtyOfBadSessions() {
            Assertions.assertEquals("2", new GetQtyOfBadSessions().apply(sleepingSessions).getResult());
    }

    @Test
    void shouldGetQtyOfSessions() {
            Assertions.assertEquals("13", new GetQtyOfSessions().apply(sleepingSessions).getResult());
    }

    @Test
    void shouldGetQtyOfSleeplessNights() {
             Assertions.assertEquals("20", new GetQtyOfSleeplessNights().apply(sleepingSessions).getResult());
    }

    @Test
    void shouldGetUserTypePigeon() {
             Assertions.assertEquals("Голубь", new GetUserType().apply(sleepingSessions).getResult());
    }
}