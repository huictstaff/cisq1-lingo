  Feature: example feature
    As a User,
    I want to play lingo,
    In order to learn how to play lingo
  Feature: Start new game.
    When I choose to start a new game.
    Then A new game should be started

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
