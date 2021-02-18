package net.starype.quiz.api.game;

import net.starype.quiz.api.game.round.*;
import net.starype.quiz.api.game.question.Question;

public class ClassicalRoundFactory {

    public StandardRound create(Question question, double maxAwarded, int maxGuesses) {

        IsGuessValid isGuessValid = new IsGuessValid();

        MaxGuessCounter counter = new MaxGuessCounter(maxGuesses);
        RoundState roundState = new RoundState(counter, counter);
        LeaderboardDistribution distribution = new LeaderboardDistribution(maxAwarded, roundState.getLeaderboard());

        GuessReceivedAction consumer =
                new InvalidateCurrentPlayerCorrectness().withCondition(isGuessValid)
                        .followedBy(new MakePlayerEligible().withCondition(isGuessValid))
                        .followedBy(new IncrementPlayerGuess().withCondition(isGuessValid.negate()))
                        .followedBy(new UpdateLeaderboard().withCondition(isGuessValid.negate()
                                .and(new IsCorrectnessZero().negate())))
                        .followedBy(new ConsumePlayerGuess().withCondition(isGuessValid.negate().and(new IsCorrectnessZero())))
                        .followedBy(new UpdatePlayerEligibility().withCondition(isGuessValid.negate()));

        return new StandardRound.Builder()
                .withGuessReceivedAction(consumer)
                .withGiveUpReceivedConsumer(new ConsumePlayerGuess())
                .withQuestion(question)
                .withScoreDistribution(distribution)
                .withPlayerEligibility(counter)
                .withRoundState(roundState)
                .withEndingCondition(new NoGuessLeft().or(new FixedLeaderboardEnding()))
                .build();
    }
}
