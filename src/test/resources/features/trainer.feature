Feature: Training for Lingo
  As a player
  I want to play Lingo games
  so that I can improve my Lingo skills
  
Scenario: Start new game
  When I want to start a new Lingo game
  Then I should see a new Lingo game being started
  
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
  Given I am playing a game
  And a "<word>" has been chosen
  When I try a "<guess>"
  Then I should see "<feedback>"
  
  Examples:
  | word  | guess  | feedback 											  |
  | BAARD | BERGEN | INVALID, INVALID, INVALID, INVALID, INVALID, INVALID |
  | BAARD | BONJE  | CORRECT, ABSENT, ABSENT, ABSENT, ABSENT 			  |
  | BAARD | BARST  | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT			  |
  | BAARD | DRAAD  | ABSENT, PRESENT, CORRECT, PRESENT, CORRECT 		  |
  | BAARD | BAARD  | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT 		  |