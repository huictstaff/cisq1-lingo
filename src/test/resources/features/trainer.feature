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
      # A = Absent
      Examples:
        |     guess   |   word     |    feedback    |
        | bingo       | apple      | AAAAA          |
        | apace       | apple      | CPPAC          |
        | apple       | apple      | CCCCC          |

    Scenario Outline: Gaining points for a correct guess
      When my "<guess>" is "<completely correct>"
      Then I gain "<points>" equal to "<my performance>"
      And my "<score>" is raised

      #Correct guess legend
      # my performance is the amount of attempts
      # score calculation = 5 * (5 – amount of attempts) + 5
      # This example plays 3 rounds, to showcase the correct raising of the score
      Examples:
        |     guess   |   completely correct     |    my performance    |    points    |    score    |
        | apple       | true                     | 1                    | 25           | 25          |
        | banana      | true                     | 3                    | 15           | 40          |
        | apple       | true                     | 5                    | 5            | 45          |

    Scenario: The player did not guess the word
      When I guessed the maximum amount of times in a round
      And I did not guess the word
      Then I do not gain any points
      And the game is over

    Scenario: The player attempts a guess when the word is already guessed correctly
      Given I guessed the word
      When I attempt to guess the word again
      Then my attempt will be ignored
      And my score will not change
      And the amount of attempts will not increase

    Scenario: The player attempts a guess when the game is over
      Given the game is over
      When I attempt to guess
      Then my attempt will be ignored
      And my score will not schange
      And the game will not start again

    Scenario: The player tries to start a new round when he is still guessing
      Given I am playing a round
      When I try to start a new round
      Then my request is ignored

    Scenario: The player tries to start a new round, but is not playing a game
      Given I am not playing a game
      When I attempt to start a new round
      Then My request is ignored
      And I receive an error message telling me I am not part of a game