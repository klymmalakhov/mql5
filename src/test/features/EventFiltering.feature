@active
Feature: Event filtering

  Scenario Outline: As USER I want be able to filter the events
  WHEN I use the filter form
  THEN open details page with corresponding data

    Given I open the Economic calendar page page in "<browser for test>" browser
    When I select "<filter period>" in Period filter block
    And I select "<filter importance>" in Importance filter block
    And I select "<filter currency>" in Currencies filter block
    And I open first event in the list
    Then I validate that Label Importance has "<filter importance>" value
    Then I validate that Label Country has "<filter currency>" value
    Then I save the events for the last "12" months

    Examples:
      | browser for test | filter importance | filter currency   | filter period |
      | Chrome           | Medium            | CHF - Swiss frank | Current Month |