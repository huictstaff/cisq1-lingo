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


  Feature Guessing a word:
    Given I am playing playing a game
    When I make a <guess>
    And the <guess> would be compared to the actual <word>
    Then I get <feedback> based on the actual <word>

    Examples:
      # o is already given
      # m, g and e is correct AND in the correct order
      # n and other g are correct but NOT in the correct order
      # a is NOT is not correct

      | word      | guess     |  feedback                    |
      | omgeving  | omgegaan  |  <o> [m] [g] [e] (g) a a (n) |

  Feature Score increase after guessing the <word>
    Given I am playeing a game
    And that I haven't surpassed the "5" tries <limit>
    When I guess the word eventually
    Then my <score> increases
    And I can make no more new guesses
    And the game ends

  Feature Losing after failing to guess the word in "5" tries
    Given I am playing a game
    When I surpass the "5" tries <limit>
    Then I have lost
    And I can make no more new guesses
    And the game ends



  Feature User unable to start a new round while still guessing
    Given I am playing a game
    And I still can make guesses
    Then I can't start a new round


  Feature User unable to start a new round without a started game
    Given I have no already started game
    Then I can't start a new round