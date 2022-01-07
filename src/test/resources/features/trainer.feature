Feature: starting a game
  As a user,
  I want to start a game.
  In order to start playing the lingo game.

Scenario: a new game  is started
  Given the application is running
  When i start a new game
  Then a new game is started
  And a new round is started with a 5 letter word.

  Scenario Outline: Start a new round
    Given I am playing a game
    And the round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the word to guess has "<next length>" letters

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |
  # Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: guess a word
    Given i am playing a game
    Then i am give a <word> to guess
    When i put in my <guess>
    Then i am given a <feedback> over my guess
    Examples:
      |word  |guess |feedback|
      |Lopen |lacht |Correct.Absent,Absent,Absent,Absent    |
      |Lopen |Loner |Correct,Correct,Absent,Correct,Absent  |
      |Lopen |Loder |Correct,Correct,Absent.Correct,Absent  |
      |Lopen |Loper |Correct,Correct,Correct,Correct,Absent |
      |Lopen |Lopen |Correct,Correct,Correct,Correct,Correct|