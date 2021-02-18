package net.starype.quiz.api.game;

import net.starype.quiz.api.game.round.RoundState;
import net.starype.quiz.api.game.player.IDHolder;

import java.util.Collection;

public class NoGuessLeft implements RoundEndingPredicate {

    private MaxGuessCounter counter;
    private Collection<? extends IDHolder<?>> players;

    @Override
    public boolean ends(RoundState roundState) {
        this.counter = roundState.getCounter();
        this.players = roundState.getPlayers();
        return !counter.existsEligible(players);
    }
}
