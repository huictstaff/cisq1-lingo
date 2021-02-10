Feature: Starting a game
  As a player,
  I want to start a game,
  So I can play the game.

  Rule: A player can have multiple games

  Scenario: Player starts a game
    When I start a new game
    Then the score is "0" for that game
    And a new round is started

  Scenario Outline: Start a new round
    Given I am playing a game
    And the round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the next word to guess has "<next length>" letters

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

    # Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round
    And the game is over

    Scenario Outline: Guessing a word
      Given I am playing a game
      When I make a guess
      And my "<guess>" is the correct "<word>"
      Then I win the round
      And I can start a new round
      And I score points

      Examples:
        |     guess   |   word     |
        | apple       | apple      |
        | banana      | banana     |
        | car         | car        |

      # Failure path
      Given I am playing a game
      When I make a guess
      And my "<guess>" is not the correct "<word>"
      Then I receive "<feedback>"
      And the game shows me which letters are correct
      And the game tells me which letters are correct, but in the wrong position
      And the game tells me which letters are incorrect

      # Feedback legend
      # C = Correct letter
      # P = Position is wrong
      # I = Incorrect letter
      Examples:
        |     guess   |   word     |    feedback    |
        | bingo       | apple      | IIIII          |
        | apace       | apple      | CPPIC          |
        | apple       | apple      | CCCCC          |