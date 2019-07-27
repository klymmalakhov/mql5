@active
Feature: Event filtering

  Scenario Outline: As USER I want be able to filter the events
  THEN open details page with corresponding data

    Given I open the Economic calendar page page in "<browser for test>" browser
    When I select Current month in Period filter block
    And I select Medium in Importance filter block
    And I select CHF in Currencies filter block
    And I open first event in the list
    Then I validate that Label Importance has "<label importance value>" value
    Then I validate that Label Country has "<label country value>" value
    Then I save the events for the last "12" months
#MEDIUM
    Examples:
      | browser for test | label importance value | label country value |
      | Chrome           | LOW                    | CHF, Swiss frank    |