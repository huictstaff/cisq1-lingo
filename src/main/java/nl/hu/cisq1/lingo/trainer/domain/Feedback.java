package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;
import java.util.List;

public class Feedback implements Serializable {
    private String attempt;
    private List<Mark> marks;
}
