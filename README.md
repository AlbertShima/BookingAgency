# BookingAgency

- **BookingAgency Framework**
  - Created a cucumber BDD framework from scratch.
  - Added all dependencies, plugins, and needed tools for the framework.
  - Created 3 feature files with corresponding pages and step definitions.

- **@Test1 - Automation for Language and Currency on Booking.com**
  - Implemented automation for language and currency on Booking.com.
  - @Test1 works fine.

- **@Test2 - Creating a New Account**
  - Implemented all steps for creating a new account.
  - Encountered an issue when a secure button "PRESS and HOLD" appeared during account creation, which couldn't be automated.
  - To bypass this step, called a method with an existing user on Booking.com and attempted to log in using "Sign in with Google," which worked fine.
  - Other steps in @Test2 are functioning correctly.
  - @Test2 works fine except for the secure button issue.

- **@Test3 - Booking the First Hotel**
  - Implemented insertion and verification with Assert from JUnit for booking the first hotel.
  - @Test3 works fine.

- **@Test4 - Booking the Expensive Hotel**
  - Currently encountering issues in this test.
  - Able to retrieve the minimum or maximum price, but struggling to find a hotel with at least three stars and a minimum price.
  - Acknowledges the need for further work on this step.

This revised format makes it easier to understand the different aspects of your BookingAgency framework and the status of each test.
