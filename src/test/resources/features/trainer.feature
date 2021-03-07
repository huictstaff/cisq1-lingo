Feature: Training for Lingo
  As a player,
  I want to guess 5,6,7 letter words
  In order to get more points

Scenario: Start new game
  When I start a new game
  Then The word to guess must has "5" letters
  And  The first letter is visible
  And  my score is "0"


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


Scenario Outline: Guessing a word
  Given I playing a round
  And   The word is "<word>"
  When  I guess a "<guess>"
  Then  The feedback should be "<feedback>"

  Examples:
    | word  | guess | feedback                                    |
    | Brood | Breeo | correct, correct, absent, absent, present   |
    | Brood | Board | correct, present, absent, present, correct  |
    | Brood | Brood | correct, correct, correct, correct, correct |

Scenario Outline: Win a round
  Given I playing a round
  When  I guess the correct word from the "<attempt>" attempt
  Then  I won the round
  And   My score should be increased "<point>" points

  Examples:
    | attempt | point |
    |   1     | 25    |
    |   2     | 20    |
    |   3     | 15    |
    |   4     | 10    |
    |   5     | 5     |


Scenario: Lose a game
  Given I playing a round
  When  I guess 5 wrong words
  Then  The round should ends
  And   I lost the game

Scenario: Guessing the same word
  Given I playing a round
  When  I guess the word "A"
  Then  I can not guess the same word "A" again

Scenario: Lose a game
  Given I am playing a game
  And the round was lost
  Then I cannot guess a new word

Scenario: playing a round
  Given I playing a round
  When  The round not ended
  Then  I cannot start a new round

Scenario: Start a game
  When I dont have a game
  Then I cannot start a new round