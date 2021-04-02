package nl.hu.cisq1.lingo.trainer.domain.character;

import nl.hu.cisq1.lingo.trainer.domain.Mark;

/**
 * Wrapper class for Feedback. This class contains a char and a Mark.
 * Is used as Key Value pair in Feedback.
 */
public class MarkedLingoCharacter extends LingoCharacter {
    protected Character character;
    private Mark mark;

    public MarkedLingoCharacter(Character character) {
        super(character);
    }

    public MarkedLingoCharacter(Character character, Mark mark) {
        super(character);
        this.mark = mark;
    }

    @Override
    public Character getCharacter() {
        return super.getCharacter();
    }

    public Mark getMark() {
        return mark;
    }
}
