package nl.hu.cisq1.lingo.trainer.domain;

public class LingoGame {
    private LingoRound lingoRound;
    private int points;
    private int round;

    public LingoGame(LingoRound round){
        lingoRound = round;
    }

    public void playRound(String attempt){
        lingoRound.guess(attempt);
        if (lingoRound.gameOver()){

        }
    }


    /*
    na 5 kansen wordt er gecheckt of je het woord hebt geraden,
    als niet dan opnieuw beginnen en punten printen
    als je het woord hebt geraden voor 5 pogingen dan
    krijg je punten op basis van welke beurt het is
    een Quit functie toevoegen.
    een functie toevoegen dat:
        -de huidige hint geeft,
        -de poging geeft
        -de marks terug geeft
    */
    private void setPoints(){
        points = 5 * (5-lingoRound.currentTurn()) +5;
    }
}
