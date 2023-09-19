Feature:

  Scenario: Book first displayed hotel
    Given I have account created
    And I am in "Home" page
    When I set up destination as "Liepaja"
    And I set dates "30-09-2023" - "20-10-2023"
    And I select "3" adults and "0" children
    And I click on "Search" button
    And I click on "See availability" for fist hotel in the list
    And "Hotel Details" page is opened for selected hotel
      # verify at least that hotel name, rating is displayed,
      # information in available rooms section matches your previously entered information
    And I click on "Reserve" button
    And I click on "I'll Reserve" button
    Then "Checkout" page is displayed
      # verify that dates are correct, check if price matches the price in details page,
      # reservation time counter is decreasing, check other information based on previous
      # inputs (amount of adults, etc.)
    And I enter valid booking information
    And I click on "Next: Final Details" button
    And "Final Details" page is displayed


