package de.waschnick.gfx.ansi;

import de.waschnick.gfx.Gfx;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class AnsiGfx implements Gfx {

    private static final char FIRST_ESC_CHAR = 27;
    private static final char SECOND_ESC_CHAR = '[';

    public static final String DISABLE = AnsiGfx.class.getName() + ".disable";

    private static Callable<Boolean> detector = new Callable<Boolean>() {
        public Boolean call() throws Exception {
            return !Boolean.getBoolean(DISABLE);
        }
    };

    public static void setDetector(final Callable<Boolean> detector) {
        if (detector == null) {
            throw new IllegalArgumentException();
        }
        AnsiGfx.detector = detector;
    }

    public static boolean isDetected() {
        try {
            return detector.call();
        } catch (Exception e) {
            return true;
        }
    }

    private static final InheritableThreadLocal<Boolean> holder = new InheritableThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return isDetected();
        }
    };

    public static void setEnabled(final boolean flag) {
        holder.set(flag);
    }

    public static boolean isEnabled() {
        return holder.get();
    }

    public static AnsiGfx ansi() {
        if (isEnabled()) {
            return new AnsiGfx();
        } else {
            return new NoAnsi();
        }
    }

    public static AnsiGfx ansi(StringBuilder builder) {
        if (isEnabled()) {
            return new AnsiGfx(builder);
        } else {
            return new NoAnsi(builder);
        }
    }

    public static AnsiGfx ansi(int size) {
        if (isEnabled()) {
            return new AnsiGfx(size);
        } else {
            return new NoAnsi(size);
        }
    }


    private final StringBuilder builder;
    private final ArrayList<Integer> attributeOptions = new ArrayList<Integer>(5);

    public AnsiGfx() {
        this(new StringBuilder());
    }

    public AnsiGfx(AnsiGfx parent) {
        this(new StringBuilder(parent.builder));
        attributeOptions.addAll(parent.attributeOptions);
    }

    public AnsiGfx(int size) {
        this(new StringBuilder(size));
    }

    public AnsiGfx(StringBuilder builder) {
        this.builder = builder;
    }

    public AnsiGfx fg(AnsiColor color) {
        attributeOptions.add(color.fg());
        return this;
    }

    public AnsiGfx fgBlack() {
        return this.fg(AnsiColor.BLACK);
    }

    public AnsiGfx fgBlue() {
        return this.fg(AnsiColor.BLUE);
    }

    public AnsiGfx fgCyan() {
        return this.fg(AnsiColor.CYAN);
    }

    public AnsiGfx fgDefault() {
        return this.fg(AnsiColor.DEFAULT);
    }

    public AnsiGfx fgGreen() {
        return this.fg(AnsiColor.GREEN);
    }

    public AnsiGfx fgMagenta() {
        return this.fg(AnsiColor.MAGENTA);
    }

    public AnsiGfx fgRed() {
        return this.fg(AnsiColor.RED);
    }

    public AnsiGfx fgYellow() {
        return this.fg(AnsiColor.YELLOW);
    }

    public AnsiGfx bg(AnsiColor color) {
        attributeOptions.add(color.bg());
        return this;
    }

    public AnsiGfx bgCyan() {
        return this.fg(AnsiColor.CYAN);
    }

    public AnsiGfx bgDefault() {
        return this.bg(AnsiColor.DEFAULT);
    }

    public AnsiGfx bgGreen() {
        return this.bg(AnsiColor.GREEN);
    }

    public AnsiGfx bgMagenta() {
        return this.bg(AnsiColor.MAGENTA);
    }

    public AnsiGfx bgRed() {
        return this.bg(AnsiColor.RED);
    }

    public AnsiGfx bgYellow() {
        return this.bg(AnsiColor.YELLOW);
    }

    public AnsiGfx fgBright(AnsiColor color) {
        attributeOptions.add(color.fgBright());
        return this;
    }

    public AnsiGfx fgBrightBlack() {
        return this.fgBright(AnsiColor.BLACK);
    }

    public AnsiGfx fgBrightBlue() {
        return this.fgBright(AnsiColor.BLUE);
    }

    public AnsiGfx fgBrightCyan() {
        return this.fgBright(AnsiColor.CYAN);
    }

    public AnsiGfx fgBrightDefault() {
        return this.fgBright(AnsiColor.DEFAULT);
    }

    public AnsiGfx fgBrightGreen() {
        return this.fgBright(AnsiColor.GREEN);
    }

    public AnsiGfx fgBrightMagenta() {
        return this.fgBright(AnsiColor.MAGENTA);
    }

    public AnsiGfx fgBrightRed() {
        return this.fgBright(AnsiColor.RED);
    }

    public AnsiGfx fgBrightYellow() {
        return this.fgBright(AnsiColor.YELLOW);
    }

    public AnsiGfx bgBright(AnsiColor color) {
        attributeOptions.add(color.bgBright());
        return this;
    }

    public AnsiGfx bgBrightCyan() {
        return this.fgBright(AnsiColor.CYAN);
    }

    public AnsiGfx bgBrightDefault() {
        return this.bgBright(AnsiColor.DEFAULT);
    }

    public AnsiGfx bgBrightGreen() {
        return this.bgBright(AnsiColor.GREEN);
    }

    public AnsiGfx bgBrightMagenta() {
        return this.bg(AnsiColor.MAGENTA);
    }

    public AnsiGfx bgBrightRed() {
        return this.bgBright(AnsiColor.RED);
    }

    public AnsiGfx bgBrightYellow() {
        return this.bgBright(AnsiColor.YELLOW);
    }

    public AnsiGfx a(AnsiAttribute attribute) {
        attributeOptions.add(attribute.value());
        return this;
    }

    public AnsiGfx cursor(final int x, final int y) {
        return appendEscapeSequence('H', x, y);
    }

    public AnsiGfx cursorToColumn(final int x) {
        return appendEscapeSequence('G', x);
    }

    public AnsiGfx cursorUp(final int y) {
        return appendEscapeSequence('A', y);
    }

    public AnsiGfx cursorDown(final int y) {
        return appendEscapeSequence('B', y);
    }

    public AnsiGfx cursorRight(final int x) {
        return appendEscapeSequence('C', x);
    }

    public AnsiGfx cursorLeft(final int x) {
        return appendEscapeSequence('D', x);
    }

    public AnsiGfx cursorDownLine() {
        return appendEscapeSequence('E');
    }

    public AnsiGfx cursorDownLine(final int n) {
        return appendEscapeSequence('E', n);
    }

    public AnsiGfx cursorUpLine() {
        return appendEscapeSequence('F');
    }

    public AnsiGfx cursorUpLine(final int n) {
        return appendEscapeSequence('F', n);
    }

    public AnsiGfx eraseScreen() {
        return appendEscapeSequence('J', AnsiErase.ALL.value());
    }

    public AnsiGfx eraseScreen(final AnsiErase kind) {
        return appendEscapeSequence('J', kind.value());
    }

    public AnsiGfx eraseLine() {
        return appendEscapeSequence('K');
    }

    public AnsiGfx eraseLine(final AnsiErase kind) {
        return appendEscapeSequence('K', kind.value());
    }

    public AnsiGfx scrollUp(final int rows) {
        return appendEscapeSequence('S', rows);
    }

    public AnsiGfx scrollDown(final int rows) {
        return appendEscapeSequence('T', rows);
    }

    public AnsiGfx saveCursorPosition() {
        return appendEscapeSequence('s');
    }

    @Deprecated
    public AnsiGfx restorCursorPosition() {
        return appendEscapeSequence('u');
    }

    public AnsiGfx restoreCursorPosition() {
        return appendEscapeSequence('u');
    }

    public AnsiGfx reset() {
        return a(AnsiAttribute.RESET);
    }

    public AnsiGfx bold() {
        return a(AnsiAttribute.INTENSITY_BOLD);
    }

    public AnsiGfx boldOff() {
        return a(AnsiAttribute.INTENSITY_BOLD_OFF);
    }

    public AnsiGfx a(String value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(boolean value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(char value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(char[] value, int offset, int len) {
        flushAttributes();
        builder.append(value, offset, len);
        return this;
    }

    public AnsiGfx a(char[] value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(CharSequence value, int start, int end) {
        flushAttributes();
        builder.append(value, start, end);
        return this;
    }

    public AnsiGfx a(CharSequence value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(double value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(float value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(int value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(long value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(Object value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx a(StringBuffer value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    public AnsiGfx newline() {
        flushAttributes();
        builder.append(System.getProperty("line.separator"));
        return this;
    }

    public AnsiGfx format(String pattern, Object... args) {
        flushAttributes();
        builder.append(String.format(pattern, args));
        return this;
    }

    /**
     * Uses the {@link AnsiRenderer}
     * to generate the ANSI escape sequences for the supplied text.
     *
     * @param text text
     * @return this
     * @since 1.1
     */
    public AnsiGfx render(final String text) {
        a(AnsiRenderer.render(text));
        return this;
    }

    /**
     * String formats and renders the supplied arguments.  Uses the {@link AnsiRenderer}
     * to generate the ANSI escape sequences.
     *
     * @param text format
     * @param args arguments
     * @return this
     * @since 1.1
     */
    public AnsiGfx render(final String text, Object... args) {
        a(String.format(AnsiRenderer.render(text), args));
        return this;
    }

    @Override
    public String toString() {
        flushAttributes();
        return builder.toString();
    }

    ///////////////////////////////////////////////////////////////////
    // Private Helper Methods
    ///////////////////////////////////////////////////////////////////

    private AnsiGfx appendEscapeSequence(char command) {
        flushAttributes();
        builder.append(FIRST_ESC_CHAR);
        builder.append(SECOND_ESC_CHAR);
        builder.append(command);
        return this;
    }

    private AnsiGfx appendEscapeSequence(char command, int option) {
        flushAttributes();
        builder.append(FIRST_ESC_CHAR);
        builder.append(SECOND_ESC_CHAR);
        builder.append(option);
        builder.append(command);
        return this;
    }

    private AnsiGfx appendEscapeSequence(char command, Object... options) {
        flushAttributes();
        return _appendEscapeSequence(command, options);
    }

    private void flushAttributes() {
        if (attributeOptions.isEmpty()) {
            return;
        }
        if (attributeOptions.size() == 1 && attributeOptions.get(0) == 0) {
            builder.append(FIRST_ESC_CHAR);
            builder.append(SECOND_ESC_CHAR);
            builder.append('m');
        } else {
            _appendEscapeSequence('m', attributeOptions.toArray());
        }
        attributeOptions.clear();
    }

    private AnsiGfx _appendEscapeSequence(char command, Object... options) {
        builder.append(FIRST_ESC_CHAR);
        builder.append(SECOND_ESC_CHAR);
        int size = options.length;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(';');
            }
            if (options[i] != null) {
                builder.append(options[i]);
            }
        }
        builder.append(command);
        return this;
    }

}