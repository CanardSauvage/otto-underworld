package de.waschnick.game;

import de.waschnick.engine.OttoEventLover;

import java.util.Arrays;
import java.util.List;

public class OttoUnderworldGame {

    private List<OttoEventLover> eventLovers;

    public OttoUnderworldGame(OttoEventLover... eventLovers) {
        this.eventLovers = Arrays.asList(eventLovers);
    }

    public void run() {
        // this is our game loop an we pool 10 times a second, in the meantime we will wait
    }
}
