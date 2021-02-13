Feature: Start a new game
  When I start a new game
  Then I get to see my score counter, a list of letters with a certain <length> and a start letter as a hint

  Examples:
    | length |
    | 5      |
    | 6      |
    | 7      |

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