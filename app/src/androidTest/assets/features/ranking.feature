Feature: ranking best scores
  As a player
  I want to view the ranking
  Because I want to see the best scores of the players

  @ranking-feature
  Scenario: show ranking list from main menu
    Given I´m in the main menu of game
    When I press the show ranking button
    Then I can see the best scores of the players