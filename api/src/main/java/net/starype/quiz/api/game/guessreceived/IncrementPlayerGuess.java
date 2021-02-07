package net.starype.quiz.api.game.guessreceived;

import net.starype.quiz.api.game.MaxGuessCounter;
import net.starype.quiz.api.game.SettablePlayerGuessContext;
import net.starype.quiz.api.game.player.Player;

/**
 * ConditionalConsumer that increments the number of guesses of current Player
 */
public class IncrementPlayerGuess extends ConditionalConsumer {

    @Override
    public void execute(RoundState roundState, SettablePlayerGuessContext playerGuessContext) {
        MaxGuessCounter counter = roundState.getCounter();
        Player<?> player = playerGuessContext.getPlayer();
        counter.incrementGuess(player);
    }
}