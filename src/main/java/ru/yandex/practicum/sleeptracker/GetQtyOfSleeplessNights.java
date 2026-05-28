package ru.yandex.practicum.sleeptracker;

import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class GetQtyOfSleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long totalSleeplessNights = 0L;

        if (sleepingSessions.getFirst().getStartDateTime().getHour() < 12) {
            totalSleeplessNights += 1;
        }
        int totalNights = Period.between(
                sleepingSessions.getFirst().getStartDateTime().toLocalDate(),
                sleepingSessions.getLast().getFinishDateTime().toLocalDate()
        ).getDays();
        totalSleeplessNights += totalNights - sleepingSessions.stream()
                .filter(sleepingSession -> (sleepingSession
                        .getStartDateTime().getDayOfMonth() != sleepingSession.getFinishDateTime().
                        getDayOfMonth()) || (sleepingSession.getStartDateTime().getHour() < 6))
                .count();
        return new SleepAnalysisResult("Количество бессонных ночей:", totalSleeplessNights);
    }
}