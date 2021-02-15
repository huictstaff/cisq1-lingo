Feature: Start a Game
  As a User,
  I want to start a game
  In order to start using lingo trainer

Scenario: User starts a game
  When User clicks on the start game button
  Then A game should be started at round 1

Feature: User starts a new round
  When User has won last round
  Then A new round should be started with the correct word length

Scenario Outline: User starts a new round
  Given User is playing a game
  And the round was won
  And the last word had "<previous length>" letters
  When User starts a new round
  Then the word to guess has "<next length>" letters

  Examples:
    | previous length | next length |
    | 5               | 6           |
    | 6               | 7           |
    | 7               | 5           |

  #Failure path
  Given User is playing a game
  And the round was lost
  Then User cannot start a new round

Feature: Guessing a word
  As a User,
  I want to guess a word
  In order to know if my guessed word is correct

Scenario Outline: Guessing a word
  Given User is playing a game
  When User makes a "<guess>"
  And User his "<guess>" is compared with the "<word>"
  Then "<feedback>" is given

  Examples:
    | word | guess | feedback |
    | test | test  | [correct,correct,correct,correct] |
    | test | tell  | [correct,correct,absent,absent]   |
    | test | tits  | [correct,absent,present,present]  |

Feature: Score gets higher when word is guessed correctly
  As a User,
  I want my score to be higher when I guess a word correctly
  In order to see progress

Scenario Outline: Score gets higher when word is guessed correctly
  When User guesses the word correctly
  And User's score is "<oldscore>"
  And User tried "<tries>" times
  Then my score is "<newscore>"

  Examples:
  | oldscore | newscore | tries |
  | 20       | 45       | 1     |
  | 20       | 40       | 2     |
  | 20       | 35       | 3     |
  | 20       | 30       | 4     |
  | 20       | 25       | 5     |

  #Failure Path
  Given User does not guess the word correctly in 5 rounds
  Then User's score should stay the same

Feature: Round is lost when the word is not guessed correctly in 5 rounds
  As a User,
  I want my round to be over when I did not guess the word correctly
  In order to proceed to the next round

Scenario: User's round is lost when the User does not guess a word in 5 rounds
  When User's his number of guesses is 5
  Then the round is over
  And the next round is started

Feature: Word cannot be guessed when it already is guessed
  As a User,
  I don't want to be able to guess a word when it's already guessed
  In order to play the game like it is supposed to be played

Scenario: User cannot guess a word when the word is already guessed
  When User has guessed the word correctly
  Then User cannot guess the word of the current round
  And the round is over

Feature: A new round cannot be started when the current round is not over yet
  As a User,
  I don't want my round to end when i'm in the middle of playing the round
  In order to play the game like it is supposed to be played

Scenario: User cannot start a new round when User's current round is not over yet
  When User is playing a round that is not over yet
  Then a new round cannot be started

Feature: A new round cannot be started when a game is not playing
  As a User,
  I don't want to be able to start a round when I'm not playing a game yet
  In order to play the game like it is supposed to be played

Scenario: User cannot start a new round when the User is not playing a game
  Given User is not playing a game
  Then a round cannot be started

