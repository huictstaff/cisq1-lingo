Feature: Getting words
  As a User,
  I want to get a word in a range from 5 to 7 letters
  In order guess that word.

  Scenario: A word with the right amount of letters is given.
    Given I request a certain amount of letter word,
    When I do a request for a "6" letter word
    Then I should get a "6" letter word.

  Scenario: Start new game
    When I do a request for a new game.
    Then I get the first letter of a 5 letter word to guess.

  Scenario: Starting a new Round
    Given the game has been started
    When I do a request for a new round.
    Then I can see a letter
    Then I can guess the word.

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

  Scenario Outline: Guessing a word
    Given that I've got the first letter of a <word>
    When I'll give a <guess> back
    Then I want to receive <feedback>
    Examples:
      | word            | guess       | feedback
      | Banana          | Baantje     | Ba.....
      | Banana          | Baadden     | Ba.....
      | Banana          | bagatel     | Ba.a...

  Scenario Outline: Player wins game
    Given I am playing a game
    And the round was won
    And the last "<last score>" points
    And I did <tries> tries
    When I start a new round
    Then the new "<new score> = <last score> + (5 * (5 - <tries>) +5)"

    Examples:
      | last score |new score | tries |
      | 5          | 20       | 3     |
      | 15         | 35       | 2     |
      | 7          | 17       | 4     |

    #todo toevoegen:
#  2.	Na vijf raadpogingen binnen een ronde is de speler af
  Scenario: Player loses game
    Given I have tried five words
    And none of the words was correct.
    Then the game is over.

#  3.	Speler kan geen woord raden als het woord al geraden is
#  4.	Speler kan geen woord raden als speler af is
#  5.	Speler kan geen nieuwe ronde starten als speler nog aan het raden is
#  6.	Speler kan geen nieuwe ronde starten als speler geen spel heeft
