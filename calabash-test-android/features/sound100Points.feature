Feature:Sounds each 100 points
  As a user
  I want to hear a sound each 100 points
  Because I want to be motivated to continue playing

  @sound100Points-feature
  Scenario: Simple sound each 100 points
    Given I am in the middle of a game and I want to be alert each 100 points
    When application notifies me
    Then I am motivated