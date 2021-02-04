Feature: Displaying first letter
  As a Player,
  I want to see the first letter of a random word,
  In order to start guessing.

Scenario: Starting a new game
  When I click the start button
  Then a new game should start
  And sees a new word displayed

  #obselete
Scenario: Starting a new round
  Given the game has been started
  And the last round was won
  When I see a new letter
  Then I can guess the word

Scenario Outline: Start a new round
  Given I am playing a game
  And the round was won
  And the last word had <previous length> letters
  When I start a new round
  Then the word to guess has <next length> letters

Examples:
  | previous length | next length |
  | 5               | 6           |
  | 6               | 7           |
  | 7               | 5           |

# Failure path
Scenario: Ending Game
  Given I am playing a game
  And the round was lost
  Then I cannot start a new round

Scenario Outline: Guessing a word
  Given a game has been started
  And a letter is displayed from an active <word>
  When I make a <guess>
  Then the game provides <feedback>

Examples:
  | word | guess | feedback |
  | hugoo| hygge | CORRECT, ABSENT, CORRECT, ABSENT, ABSENT  |
  | hugoo| huggo | CORRECT, CORRECT, CORRECT, ABSENT, CORRECT |

Scenario: winning a round
  Given a "word" has been guessed
  And all the letters are correct
  Then the round has ended
  And the player has won
  And the score increases

Scenario: increasing score
  Given a round has been won
  Then the score increases by 5*(5-number of rounds)+5

#failure
Scenario: Player defeated
  Given an active round
  When I guesses a "word"
  And the guess is incorrect
  And it's the 5th round
  Then the round has been lost
  And the player has been defeated

#failure
Scenario: Cannot guess a word that has already been guessed
  Given an active round
  When I guess a "word"
  And it is already present in the "guess" list
  Then I cannot guess that "word"

