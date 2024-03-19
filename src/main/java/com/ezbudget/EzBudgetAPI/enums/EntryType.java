package com.ezbudget.EzBudgetAPI.enums;

public enum EntryType {

    EXPENSE("EXPENSE"), GAIN("GAIN");

    private final String type;

    EntryType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
