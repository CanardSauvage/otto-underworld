package de.waschnick.gfx.ansi;

public class NoAnsi        extends AnsiGfx {
    public NoAnsi() {
        super();
    }

    public NoAnsi(int size) {
        super(size);
    }

    public NoAnsi(StringBuilder builder) {
        super(builder);
    }

    @Override
    public AnsiGfx fg(AnsiColor color) {
        return this;
    }

    @Override
    public AnsiGfx bg(AnsiColor color) {
        return this;
    }

    @Override
    public AnsiGfx fgBright(AnsiColor color) {
        return this;
    }

    @Override
    public AnsiGfx bgBright(AnsiColor color) {
        return this;
    }

    @Override
    public AnsiGfx a(AnsiAttribute attribute) {
        return this;
    }

    @Override
    public AnsiGfx cursor(int x, int y) {
        return this;
    }

    @Override
    public AnsiGfx cursorToColumn(int x) {
        return this;
    }

    @Override
    public AnsiGfx cursorUp(int y) {
        return this;
    }

    @Override
    public AnsiGfx cursorRight(int x) {
        return this;
    }

    @Override
    public AnsiGfx cursorDown(int y) {
        return this;
    }

    @Override
    public AnsiGfx cursorLeft(int x) {
        return this;
    }

    @Override
    public AnsiGfx cursorDownLine() {
        return this;
    }

    @Override
    public AnsiGfx cursorDownLine(final int n) {
        return this;
    }

    @Override
    public AnsiGfx cursorUpLine() {
        return this;
    }

    @Override
    public AnsiGfx cursorUpLine(final int n) {
        return this;
    }

    @Override
    public AnsiGfx eraseScreen() {
        return this;
    }

    @Override
    public AnsiGfx eraseScreen(AnsiErase kind) {
        return this;
    }

    @Override
    public AnsiGfx eraseLine() {
        return this;
    }

    @Override
    public AnsiGfx eraseLine(AnsiErase kind) {
        return this;
    }

    @Override
    public AnsiGfx scrollUp(int rows) {
        return this;
    }

    @Override
    public AnsiGfx scrollDown(int rows) {
        return this;
    }

    @Override
    public AnsiGfx saveCursorPosition() {
        return this;
    }

    @Override
    @Deprecated
    public AnsiGfx restorCursorPosition() {
        return this;
    }

    @Override
    public AnsiGfx restoreCursorPosition() {
        return this;
    }

    @Override
    public AnsiGfx reset() {
        return this;
    }
}