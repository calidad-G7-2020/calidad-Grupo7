Feature: ranking best scores
  As a player
  I want to view the ranking
  And I´m in the main menu
  Because I want to see the best scores of the players

  @ranking-feature
  Scenario: show ranking list from main menu
    Given I´m in the main menu
    When I press the show ranking button
    Then I can see the best scores of the players