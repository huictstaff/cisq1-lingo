Feature: Lingo Trainer
  As a Lingo player,
  I want to be able to train in the Lingo game,
  to participate in the Lingo television series

  Scenario: Start new game
    When I start the game
    Then A word of "5" letters will be generated
    And the first round should start
    And I will be presented with the first letter of the word
    And My score should be "0"

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

    #Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: Guessing a word
    Given The current "<word>" is not yet guessed
    And "<word>" and "<guess>" do not match
    When I make a new "<guess>"
    Then I should recieve "<feedback>"

    Examples:
      | word  | guess   | feedback                                                          |
      | BAARD | BERGEN  | INCORRECT, INCORRECT, INCORRECT, INCORRECT, INCORRECT, INCORRECT  |
      | BAARD | BONJE   | CORRECT, ABSENT, ABSENT, ABSENT, ABSENT                           |
      | BAARD | BARST   | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT                         |
      | BAARD | DRAAD   | ABSENT, PRESENT, CORRECT, PRESENT, CORRECT                        |
      | BAARD | BAARD   | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT                       |
