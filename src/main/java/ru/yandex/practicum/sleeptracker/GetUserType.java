package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class GetUserType implements Function<List<SleepingSession>, SleepAnalysisResult> {
    static final int ELEVEN_HOURS_PM = 23;
    static final int SIX_HOURS_AM = 6;
    static final int NINE_HOURS_AM = 9;
    static final int SEVEN_HOURS_AM = 7;
    static final int ZERO_MINUTE = 0;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        long qtyOwlSessions = sleepingSessions.stream()
                .filter(sleepingSession ->
                        (
                                (
                                        (
                                                sleepingSession.getStartDateTime().getDayOfMonth()
                                                        != sleepingSession.getFinishDateTime().getDayOfMonth()
                                        )
                                                && (sleepingSession.getStartDateTime().getHour() == ELEVEN_HOURS_PM)
                                                && (sleepingSession.getStartDateTime().getMinute() > ZERO_MINUTE))
                                        || (sleepingSession.getStartDateTime().getHour() < SIX_HOURS_AM)
                        )
                                && (sleepingSession.getFinishDateTime().getHour() >= NINE_HOURS_AM)
                )
                .count();

        long qtyLarkSessions = sleepingSessions.stream()
                .filter(sleepingSession ->
                        (
                                (
                                        (
                                                sleepingSession.getStartDateTime().getDayOfMonth()
                                                        != sleepingSession.getFinishDateTime().getDayOfMonth()
                                        )
                                                && (sleepingSession.getStartDateTime().getHour() < ELEVEN_HOURS_PM)
                                )
                                        && (sleepingSession.getFinishDateTime().getHour() < SEVEN_HOURS_AM)
                        )
                )
                .count();

        long qtyPigeonSessions = sleepingSessions.stream()
                .filter(sleepingSession ->
                        (
                                sleepingSession.getStartDateTime().getDayOfMonth()
                                        != sleepingSession.getFinishDateTime().getDayOfMonth()
                        )
                                || (sleepingSession.getStartDateTime().getHour() < SIX_HOURS_AM)
                )
                .count() - qtyOwlSessions - qtyLarkSessions;

        UserType userType;
        if (qtyOwlSessions > qtyLarkSessions && qtyOwlSessions > qtyPigeonSessions) {
            userType = UserType.OWL;
        } else if (qtyLarkSessions > qtyOwlSessions && qtyLarkSessions > qtyPigeonSessions) {
            userType = UserType.LARK;
        } else {
            userType = UserType.PIGEON;
        }
        return new SleepAnalysisResult("Хронотип пользователя: ", userType);
    }
}
