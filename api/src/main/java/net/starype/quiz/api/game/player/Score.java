package net.starype.quiz.api.game.player;

public class Score {
    private int answeredQuestion;
    private double points;
    private double accuracy;

    public int getAnsweredQuestion() {
        return answeredQuestion;
    }

    public double getPoints() {
        return points;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAnsweredQuestion(int answeredQuestion) {
        this.answeredQuestion = answeredQuestion;
    }

    public void increment(double increment) {
        this.points += increment;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}