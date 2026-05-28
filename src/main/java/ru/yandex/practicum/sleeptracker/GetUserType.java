package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class GetUserType implements Function<List<SleepingSession>, SleepAnalysisResult> {
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
                                                && (sleepingSession.getStartDateTime().getHour() == 23)
                                                && (sleepingSession.getStartDateTime().getMinute() > 0))
                                        || (sleepingSession.getStartDateTime().getHour() < 6)
                        )
                                && (sleepingSession.getFinishDateTime().getHour() >= 9)
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
                                                && (sleepingSession.getStartDateTime().getHour() < 23)
                                )
                                        && (sleepingSession.getFinishDateTime().getHour() < 7)
                        )
                )
                .count();

        long qtyPigeonSessions = sleepingSessions.stream()
                .filter(sleepingSession ->
                        (
                                sleepingSession.getStartDateTime().getDayOfMonth()
                                        != sleepingSession.getFinishDateTime().getDayOfMonth()
                        )
                                || (sleepingSession.getStartDateTime().getHour() < 6)
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
