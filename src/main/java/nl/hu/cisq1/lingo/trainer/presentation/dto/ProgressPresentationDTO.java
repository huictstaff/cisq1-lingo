package nl.hu.cisq1.lingo.trainer.presentation.dto;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;

import java.util.List;

public class ProgressPresentationDTO {
    public Long id;
    public Integer score;
    public List<Feedback> feedbackHistory;
    public String newHint;


    private ProgressPresentationDTO() {
    }

    public static class Builder {
        public final Long id;
        public Integer score;
        public List<Feedback> feedbackHistory;
        public String newHint;

        public Builder(Long id) {
            this.id = id;
        }

        public Builder score(Integer score) {
            this.score = score;

            return this;
        }

        public Builder feedbackHistory(List<Feedback> feedbackHistory) {
            this.feedbackHistory = feedbackHistory;

            return this;
        }

        public Builder newHint(String newHint) {
            this.newHint = newHint;

            return this;
        }

        public ProgressPresentationDTO build() {
            ProgressPresentationDTO progress = new ProgressPresentationDTO();
            progress.id = this.id;
            progress.score = this.score;
            progress.feedbackHistory = this.feedbackHistory;
            progress.newHint = this.newHint;

            return progress;
        }
    }
}

