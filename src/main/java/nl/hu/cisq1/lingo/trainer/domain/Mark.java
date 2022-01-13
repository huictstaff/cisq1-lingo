package nl.hu.cisq1.lingo.trainer.domain;

public enum Mark {
    Correct,
    Absent,
    Present;

    public static String getString(Mark feedback) throws Exception {
        switch (feedback){
            case Correct:
                return "Correct";
            case Absent:
                return "Absent";
            case Present:
                return "Present";
        }
        throw new Exception("Status unknown, use one of the following: Correct, Absent, Present");
    }

}
