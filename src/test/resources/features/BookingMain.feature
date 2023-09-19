Feature:
  @bookingFirstHotel
  Scenario Outline: Book first displayed hotel
    Given I have account created
    And I am in "Home" page
    When I set up destination as "Rome"
    And I set dates "30-09-2023" - "20-10-2023"
    #For every two people added I will increase the number of rooms by 1
    And I select 3 adults and 1 children
    And I click on "Search" button
    And I click on "See availability" for fist hotel in the list
    And "Hotel Details" page is opened for selected hotel
      # verify at least that hotel name,
      # rating is displayed,
      # information in available rooms section matches your previously entered information
    And I click on "Reserve" button
    And I click on "I'll Reserve" button
    Then "Checkout" page is displayed
      # verify that dates are correct, check if price matches the price in details page,
      # reservation time counter is decreasing, check other information based on previous
      # inputs (amount of adults, etc.)
    And I enter valid booking information "<firstName>", "<lastName>", "<email>", "<description>"
    And I click on "Next: Final Details" button
    And "Final Details" page is displayed

    Examples:
      | firstName | lastName | email                          | description                                                    |
      | Albert    | Shima    | shimabertitestdevlab@gmail.com | Hello Tomas and Ilia. I would like to be part of your team. :) |


  Scenario: Book cheapest hotel in city
    Given I have account created
    And I am in "Home" page
    When I set up destination as "Riga"
       #make sure you have strict method for selecting cities from dropdown and select city which strictly match entered name.
    And I set dates "15-03-2023" - "20-03-2023"
    And I select 2 adults and 0 children
    And I click on "Search" button
    And I click on "See availability" for the cheapest hotel in the list with a rating above "3" stars
       #Choose most convenient way to achieve that
    And " Hotel Details" page is opened for selected hotel
       # verify at least that hotel name, rating is displayed, information in available rooms section
       # matches your previously entered information
    And I click on "I'll Reserve" button for the most expensive available room in the hotel
    Then "Checkout" page is displayed
       # verify that dates are correct, check if price matches the price in details page, reservation
       # time counter is decreasing, check other information based on previous inputs (amount of adults, etc.)
    And I enter valid booking information
    And I click on "Next: Final Details" button
    And "Final Details" page is displayed



