Feature: Start a new game
  When I start a new game
  Then I get a word to guess that has "5" letters

Feature: Start a new round
  Given I am playing a game
  And the round was won
  And the last round had "<previous length>" letters
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