package nl.hu.cisq1.lingo.trainer.presentation.dto;

import nl.hu.cisq1.lingo.trainer.domain.GameStatus;

public class GamePresentationDTO {
    public Long id;
    public Integer score;
    public String gameStatus;

    private GamePresentationDTO() {
    }

    public static class Builder {
        public final Long id;
        public Integer score;
        public String gameStatus;

        public Builder(Long id) {
            this.id = id;
        }

        public GamePresentationDTO.Builder score(Integer score) {
            this.score = score;

            return this;
        }

        public GamePresentationDTO.Builder gameStatus(String gameStatus) {
            this.gameStatus = gameStatus;

            return this;
        }


        public GamePresentationDTO build() {
            GamePresentationDTO game = new GamePresentationDTO();
            game.id = this.id;
            game.score = this.score;
            game.gameStatus = this.gameStatus;

            return game;
        }
    }
}
