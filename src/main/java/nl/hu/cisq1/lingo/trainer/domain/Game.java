package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

@Getter
public class Game {
    private Round currentRound;
    private boolean going;

    public Game(String word) {
        this.currentRound = new Round(word);
        this.going = true;
    }

    public Feedback guess(String guess) {
        Feedback feedback = currentRound.guess(guess);
        switch(feedback.totalMark()) {
            case ILLEGAL: {
                stopGame();
                break;
            }
            case WRONG: {
                if (currentRound.getTried() == 5) {
                    stopGame();
                }
                break;
            }
        }
        return feedback;
    }

    private void stopGame() {
        this.going = false;
    }

    public void startNewRound(String word) {
        if (!going) return;
        this.currentRound = new Round(word);
    }

}
