Feature: Specify word length.
  As a User,
  I want to be able to choose word length for a specific practice session,
  In order to practice for a specific gametype.


Feature: Start new game
  As a User,
  I want to be able to start a new game,
  In order to begin a game of lingo.

Scenario:
  Given I am a user
  When I start the Trainer
  Then I should be able to start a new game

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

Scenario Outline:
  Given I have submitted a <word>
  When I make a correct <guess> about the kind and position of a letter
  Then I get positive <feedback> for the presence and position letter

  Given I have submitted a <word>
  When I make a correct <guess> about the kind of letter
  Then I get positive <feedback> for the presence of that letter

  Given I have submitted a <word>
  When I make an incorrect <guess> about the kind and position of a letter
  Then I get negative <feedback> for that letter
  Examples:
    | word   | guess | feedback |
    | cheese | creamy| OXOXXX   |
    | christ | triste| VVVVVX   |


