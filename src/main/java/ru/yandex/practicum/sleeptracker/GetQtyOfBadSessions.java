package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class GetQtyOfBadSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult("Количество сессий с плохим качеством сна:",
                sleepingSessions
                        .stream()
                        .filter(sleepingSession ->
                                sleepingSession.getSleepingQuality().equals(SleepingQuality.BAD))
                        .count());
    }
}
