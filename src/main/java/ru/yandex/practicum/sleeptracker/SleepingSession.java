package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime startDateTime;
    private final LocalDateTime finishDateTime;
    private final SleepingQuality sleepingQuality;

    public SleepingSession(LocalDateTime startDateTime, LocalDateTime finishDateTime, SleepingQuality sleepingQuality) {
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.sleepingQuality = sleepingQuality;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getFinishDateTime() {
        return finishDateTime;
    }

    public SleepingQuality getSleepingQuality() {
        return sleepingQuality;
    }
}
