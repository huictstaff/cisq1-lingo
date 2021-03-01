package nl.hu.cisq1.lingo.trainer.domain.enums;

public enum RoundType {
    FIVELETTERS(5),
    SIXLETTERS(6),
    SEVENLETTERS(7);

    private int number;

    RoundType(int number) {
        this.number = number;
    }

    public int number() {
        return this.number;
    }
}
