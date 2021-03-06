package ru.mail.polis.homework.analyzer;

/**
 * Типы фильтров (2 балла)
 */
public enum FilterType {
    SPAM(4),
    TOO_LONG(3),
    NEGATIVE_TEXT(2),
    CUSTOM(1),
    GOOD(0);

    private final int priority;

    private FilterType(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }
}
