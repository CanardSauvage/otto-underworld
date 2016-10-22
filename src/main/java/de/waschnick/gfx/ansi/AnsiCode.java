package de.waschnick.gfx.ansi;


public enum AnsiCode {
    //
    // TODO: Find a better way to keep Code in sync with AnsiColor/Attribute/Erase
    //

    // AnsiColors
    BLACK(AnsiColor.BLACK),
    RED(AnsiColor.RED),
    GREEN(AnsiColor.GREEN),
    YELLOW(AnsiColor.YELLOW),
    BLUE(AnsiColor.BLUE),
    MAGENTA(AnsiColor.MAGENTA),
    CYAN(AnsiColor.CYAN),
    WHITE(AnsiColor.WHITE),

    // Foreground AnsiColors
    FG_BLACK(AnsiColor.BLACK, false),
    FG_RED(AnsiColor.RED, false),
    FG_GREEN(AnsiColor.GREEN, false),
    FG_YELLOW(AnsiColor.YELLOW, false),
    FG_BLUE(AnsiColor.BLUE, false),
    FG_MAGENTA(AnsiColor.MAGENTA, false),
    FG_CYAN(AnsiColor.CYAN, false),
    FG_WHITE(AnsiColor.WHITE, false),

    // Background AnsiColors
    BG_BLACK(AnsiColor.BLACK, true),
    BG_RED(AnsiColor.RED, true),
    BG_GREEN(AnsiColor.GREEN, true),
    BG_YELLOW(AnsiColor.YELLOW, true),
    BG_BLUE(AnsiColor.BLUE, true),
    BG_MAGENTA(AnsiColor.MAGENTA, true),
    BG_CYAN(AnsiColor.CYAN, true),
    BG_WHITE(AnsiColor.WHITE, true),

    // Attributes
    RESET(AnsiAttribute.RESET),
    INTENSITY_BOLD(AnsiAttribute.INTENSITY_BOLD),
    INTENSITY_FAINT(AnsiAttribute.INTENSITY_FAINT),
    ITALIC(AnsiAttribute.ITALIC),
    UNDERLINE(AnsiAttribute.UNDERLINE),
    BLINK_SLOW(AnsiAttribute.BLINK_SLOW),
    BLINK_FAST(AnsiAttribute.BLINK_FAST),
    BLINK_OFF(AnsiAttribute.BLINK_OFF),
    NEGATIVE_ON(AnsiAttribute.NEGATIVE_ON),
    NEGATIVE_OFF(AnsiAttribute.NEGATIVE_OFF),
    CONCEAL_ON(AnsiAttribute.CONCEAL_ON),
    CONCEAL_OFF(AnsiAttribute.CONCEAL_OFF),
    UNDERLINE_DOUBLE(AnsiAttribute.UNDERLINE_DOUBLE),
    UNDERLINE_OFF(AnsiAttribute.UNDERLINE_OFF),

    // Aliases
    BOLD(AnsiAttribute.INTENSITY_BOLD),
    FAINT(AnsiAttribute.INTENSITY_FAINT),;

    @SuppressWarnings("unchecked")
    private final Enum n;

    private final boolean background;

    @SuppressWarnings("unchecked")
    private AnsiCode(final Enum n, boolean background) {
        this.n = n;
        this.background = background;
    }

    @SuppressWarnings("unchecked")
    private AnsiCode(final Enum n) {
        this(n, false);
    }

    public boolean isAnsiColor() {
        return n instanceof AnsiColor;
    }

    public AnsiColor getAnsiColor() {
        return (AnsiColor) n;
    }

    public boolean isAttribute() {
        return n instanceof AnsiAttribute;
    }

    public AnsiAttribute getAttribute() {
        return (AnsiAttribute) n;
    }

    public boolean isBackground() {
        return background;
    }
}