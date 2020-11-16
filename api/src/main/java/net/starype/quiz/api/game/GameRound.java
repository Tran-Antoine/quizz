package net.starype.quiz.api.game;

import net.starype.quiz.api.game.event.EventHandler;
import net.starype.quiz.api.game.player.UUIDHolder;

import java.util.Collection;

public interface GameRound {

    void start(QuizGame game, Collection<? extends UUIDHolder> players, EventHandler eventHandler);
    PlayerGuessContext onGuessReceived(UUIDHolder source, String message);
    void onGiveUpReceived(UUIDHolder source);
    default void onRoundStopped(){}

    EntityEligibility initPlayerEligibility();
    RoundEndingPredicate initEndingCondition();
    ScoreDistribution initScoreDistribution();
    GameRoundReport initReport();

    GameRoundContext getContext();
}
