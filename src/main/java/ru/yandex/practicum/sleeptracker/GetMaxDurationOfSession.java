package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class GetMaxDurationOfSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) throws NoSuchElementException {
        return new SleepAnalysisResult("Максимальная продолжительность сессии (в минутах):",
                sleepingSessions
                        .stream()
                        .map(sleepingSession -> Duration.between(sleepingSession.getStartDateTime(),
                                sleepingSession.getFinishDateTime()).toMinutes())
                        .max(Long::compare).orElseThrow());
    }
}