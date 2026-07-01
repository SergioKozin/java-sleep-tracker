package ru.yandex.practicum.sleeptracker;

public enum UserType {
    OWL("Сова"),
    LARK("Жаворонок"),
    PIGEON("Голубь");
    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
