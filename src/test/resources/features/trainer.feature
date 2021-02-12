# language: en

Feature: Trainer for Lingo
  As a User,
  I want to train my Lingo skills,
  In order to improve my word guessing skills.

  Scenario: Start a new game
    When  I start a new game
    Then  the word to guess has "<amount>" letters
    And   I should see the first letter
    And   my score is "<score>"

  Scenario Outline: Start a new round
    Given I am playing a game
    And   the round was won
    And   the last word was "previous length" letters long
    When  I start a new round
    Then  I should see a "next length" letter word

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

    Given I am playing a game
    And   the round was lost
    Then  I cannot start a new round

  Scenario Outline: Guessing a word
    Given the word is "<word>"
    When  I guess "<guess>"
    Then  the feedback should be "<feedback>"

    Examples:
    Between * means correct, letter is present in the to be guessed word and at the right index
    Between + means present in the "word" but at the wrong index
    Between - means absent. Letter is not present in "word"
      | word               | guess   | feedback
      | panic              | plasti  | *p*-l-+a+-s--t-+i+
      | puzzle             | muzzle  | -m-*u**z**z**l**e*
      | jacuzzi            | jazzman | *j**a*+z++z+-a--n-

  Scenario: Score increase
    Given the "<guess>" is correct
    When  the round is over
    Then  increase the score

  Scenario: User eliminated
    When I am eliminated
    Then I cannot guess a new word

  Scenario: Word already guessed
    When I already guessed the word
    Then I cannot guess the word

  Scenario: eliminated user tries to guess
    Given  I am eliminated
    When   I try to guess again
    Then   I get notified that I am eliminated
    And    I need to start a new game in order to play

  Scenario: guessing user tries to start a new round
    When  I haven't guessed a word (yet)
    And   I am not eliminated
    Then  I can't start a new round

  Scenario: no existing game
    Given use case isn't start a game
    When  there is no existing game
    Then  I get notified that I have to start a new game in order to play
