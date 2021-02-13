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


  Feature guessing a word:
    Given I am playing playing a game
    When I make a <guess>
    And the <guess> would be compared to the actual <word>
    Then I get <feedback> based on the actual <word>
  Example:
    # o is already given
    # m, g and e is correct AND in the correct order
    # n and other g are correct but NOT in the correct order
    # a is NOT is not correct

    | word      | guess     |  feedback                    |
    | omgeving  | omgegaan  |  <o> [m] [g] [e] (g) a a (n) |


