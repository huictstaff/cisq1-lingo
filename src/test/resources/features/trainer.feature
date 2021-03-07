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


Scenario: Guessing a word
  Given I playing a round
  And   The word is "<word>"
  When  I guess a "<guess>"
  Then  The feedback should be "<feedback>"

  Examples:
    | word            | guess       | feedback    |
    |Brood            | Breeo       | correct, correct, absent, absent, present   |
    |Brood            | Board       | correct, present, absent, present, correct  |
    |Brood            | Brood       | correct, correct, correct, correct, correct |