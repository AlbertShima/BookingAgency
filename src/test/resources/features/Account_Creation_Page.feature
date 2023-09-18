Feature: Creating an account
  @Test1
  Scenario: Creating an account for the new user
    Given I am in a Sign Up page
    When I enter valid user email
    And click on "Continue_with_email" button
    And I enter valid password
    And click on "Create_Account" button
    And main page is opened
    And I click on "Manage Accounts" button under account menu
    And I click on "Personal Details" button under manage accounts section
    Then "Personal Details" page is opened And correct value is prefilled in email verification placeholder
