package de.waschnick;

import de.waschnick.game.OttoUnderworldGame;
import de.waschnick.gfx.Gfx;
import de.waschnick.gfx.ansi.AnsiGfx;
import de.waschnick.sound.NoSound;
import de.waschnick.sound.Sound;

public class OttoUnderWorldApplication {

    public static void main(String[] args) {

        Gfx gfx = new AnsiGfx();
        Sound sound = new NoSound();

        OttoUnderworldGame game = new OttoUnderworldGame(gfx, sound);
        game.run();
    }
}
