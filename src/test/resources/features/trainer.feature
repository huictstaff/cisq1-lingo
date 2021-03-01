Feature: Spel Starten
  As a Player,
  I want to be able to start a new game,
  So I can train myself for the telivision show Lingo.

  Scenario: Start a new game
  	When I start a new game,
  	Then the word to guess has "5" letters
  	And I should see the first letter
    And my score is "0"

Feature: Woord raden
  As a Player,
  I want to be able to guess a word,
  So I can see which parts of the word are correct.

  Scenario Outline: Guess a word
    Given I am playing a game
    And the word to guess is "<word>"
    When I guess "<answer>"
    Then I get "<feedback>" and "<general feedback>" back as feedback

    Examples:
      | word  | answer | feedback                                | general feedback
      | AAPJE | AREND  | CORRECT WRONG PRESENT WRONG WRONG       | WRONG
      | AAPJE | APPEL  | CORRECT PRESENT WRONG PRESENT WRONG     | WRONG
      | AAPJE | BEREN  | ILLEGAL ILLEGAL ILLEGAL ILLEGAL ILLEGAL | ILLEGAL
      | AAPJE | ADKEA  | ILLEGAL ILLEGAL ILLEGAL ILLEGAL ILLEGAL | ILLEGAL
      | AAPJE | AAPJE  | CORRECT CORRECT CORRECT CORRECT CORRECT | CORRECT


  Scenario: Losing the round
  	Given I am playing a game
  	When I get feedback "WRONG" for the 5th time 
  	Then the round was lost
  	And I cannot start a new round

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

  	
 