package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    private final String description;
    private Long result;
    private UserType userType;

    public SleepAnalysisResult(String description, Long result) {
        this.description = description;
        this.result = result;
    }

    public SleepAnalysisResult(String description, UserType userType) {
        this.description = description;
        this.userType = userType;
    }

    public String getDescription() {
        return description;
    }

    public String getResult() {
        if (userType != null) {
            return userType.getValue();
        } else {
            return result.toString();
        }
    }

}
