package de.waschnick.gfx.ansi;

import javafx.scene.paint.Color;

import java.util.Locale;

public class AnsiRenderer {

    public static final String BEGIN_TOKEN = "@|";

    private static final int BEGIN_TOKEN_LEN = 2;

    public static final String END_TOKEN = "|@";

    private static final int END_TOKEN_LEN = 2;

    public static final String CODE_TEXT_SEPARATOR = " ";

    public static final String CODE_LIST_SEPARATOR = ",";

    private AnsiRenderer() {
    }

    static public String render(final String input) throws IllegalArgumentException {
        StringBuffer buff = new StringBuffer();

        int i = 0;
        int j, k;

        while (true) {
            j = input.indexOf(BEGIN_TOKEN, i);
            if (j == -1) {
                if (i == 0) {
                    return input;
                } else {
                    buff.append(input.substring(i, input.length()));
                    return buff.toString();
                }
            } else {
                buff.append(input.substring(i, j));
                k = input.indexOf(END_TOKEN, j);

                if (k == -1) {
                    return input;
                } else {
                    j += BEGIN_TOKEN_LEN;
                    String spec = input.substring(j, k);

                    String[] items = spec.split(CODE_TEXT_SEPARATOR, 2);
                    if (items.length == 1) {
                        return input;
                    }
                    String replacement = render(items[1], items[0].split(CODE_LIST_SEPARATOR));

                    buff.append(replacement);

                    i = k + END_TOKEN_LEN;
                }
            }
        }
    }

    static private String render(final String text, final String... codes) {
        Ansi ansi = Ansi.ansi();
        for (String name : codes) {
            Code code = Code.valueOf(name.toUpperCase(Locale.ENGLISH));

            if (code.isColor()) {
                if (code.isBackground()) {
                    ansi = ansi.bg(code.getColor());
                } else {
                    ansi = ansi.fg(code.getColor());
                }
            } else if (code.isAttribute()) {
                ansi = ansi.a(code.getAttribute());
            }
        }

        return ansi.a(text).reset().toString();
    }

    public static boolean test(final String text) {
        return text != null && text.contains(BEGIN_TOKEN);
    }


}