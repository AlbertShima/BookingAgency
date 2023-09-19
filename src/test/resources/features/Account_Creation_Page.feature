Feature: Creating new account

  @Test1
  Scenario: Creating an account for the new user
    Given I am in a Sign Up page
    When I enter valid user email
    And click on "Continue with email" button
    And I enter valid password
    And click on "Create account" button
    And main page is opened
    And click on "Manage Accounts" button
    And click on "Personal Details" button
    Then "Personal Details" page is opened
    And correct value is prefilled in email verification placeholder

