package de.waschnick.gfx.ansi;

public enum AnsiErase {
    FORWARD(0, "FORWARD"),
    BACKWARD(1, "BACKWARD"),
    ALL(2, "ALL");

    private final int value;
    private final String name;

    AnsiErase(int index, String name) {
        this.value = index;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int value() {
        return value;
    }
}