package ru.yandex.practicum.sleeptracker;

import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class GetQtyOfSleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    static final int TWELVE_HOURS_PM = 12;
    static final int SIX_HOURS_AM = 6;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long totalSleeplessNights = 0L;

        if (sleepingSessions.getFirst().getStartDateTime().getHour() < TWELVE_HOURS_PM) {
            totalSleeplessNights += 1;
        }
        int totalNights = Period.between(
                sleepingSessions.getFirst().getStartDateTime().toLocalDate(),
                sleepingSessions.getLast().getFinishDateTime().toLocalDate()
        ).getDays();
        totalSleeplessNights += totalNights - sleepingSessions.stream()
                .filter(sleepingSession -> (sleepingSession
                        .getStartDateTime().getDayOfMonth() != sleepingSession.getFinishDateTime()
                        .getDayOfMonth()) || (sleepingSession.getStartDateTime().getHour() < SIX_HOURS_AM))
                .count();
        return new SleepAnalysisResult("Количество бессонных ночей:", totalSleeplessNights);
    }
}