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

  Given I am playing a game
  And the round was lost
  Then I cannot start a new round

Scenario Outline: Guessing a word
  Given I am playing a game
  And a round was started
  And the word to guess is "<word>"
  When I will try to guess the word using "<guess>"
  Then I will receive "<feedback>"

  | word  | guess   | feedback                                                      |
  | APPEL | AAPJE   | CORRECT, ABSENT, CORRECT, ABSENT, PRESENT                     |
  | APPEL | GROND   | ABSENT, ABSENT, ABSENT, ABSENT, ABSENT                        |
  | APPEL | AARDBEI | INVALID, INVALID, INVALID, INVALID, INVALID, INVALID, INVALID |
  | APPEL | APPEL   | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT                   |

Scenario: Raising the score
  Given I am playing a game
  And a round was just finished
  Then I should have my score raised

Scenario: Guessing limit
  Given I am playing a round
  And I used all my guesses
  Then the round should be closed

Scenario: Word already guessed
  Given I finished a round
  When I try to guess the word again
  Then the game notifies me I already finished that round

Scenario: Player lost
  Given I lost a round
  When I try to start a new round
  Then the game tells me I lost and need a new game

Scenario: Start round while playing
  Given I am playing a round
  And the round is not finished
  When I try to start a new round
  Then the game tells me I need to finish my round

Scenario: Start round while no game
  Given I have no game yet
  When I try to start a new round
  Then the game tells me I need a game to be able to start a round