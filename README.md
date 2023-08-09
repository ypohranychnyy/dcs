**UI Testing**

**Scenario**:
* Navigate to the Insider website and assert that the homepage is correctly loaded.
* From the navigation bar, hover over the "Company" menu and click "Careers".
* On the Careers page, validate the visibility of the following blocks: Locations, Teams, and Life at Insider.
* Navigate directly to Quality Assurance careers.
* Click "See all QA jobs", filter jobs by location "Istanbul, Turkey" and department "Quality Assurance".
* Validate that all job postings have "Quality Assurance" in the Position and Department and "Istanbul, Turkey" in the Location.
* Click the "View Role" button and validate that the user is redirected to the Lever Application form page.

**Requirements**:
* The test script should be written using a combination of a programming language and a testing framework. Preferably, Java + Selenium.
* BDD frameworks (e.g., Cucumber, Quantum, Codeception) are not to be used.
* If a test fails at any step, a screenshot should be captured.
* The test case should be executable on both Chrome and Firefox browsers, and it should be possible to switch browsers parametrically.
* The test code should adhere to the Page Object Model (POM) design pattern.

**Implementation**:
* InsiderTest class contains the test scenario script
* Page-object patter is located in Pages directory
* CookiesBanner is taken to components as it may appear on any page and this component should be reusable if the test is to start from a different page
* WaitForCondition is also a reusable function taken separately.
* Screenshots are take on every failure and rewritten in case of the following failures


**API Testing**

**Scenario**:
* The test performs CRUD (Create, Read, Update, Delete) operations on the Petstore API "pet" endpoints. Both positive and negative scenarios are covered.

**Test Description**:
* Create: A new pet is created and added. The response is verified to ensure the pet was added correctly.
* Read: The previously added pet is fetched by its ID, and its details are verified.
* Update: The name of the pet is updated and then fetched again to verify the update.
* Delete: The pet is deleted. A subsequent request to get the pet details should result in a 404 status, indicating the pet is not found.


**Implementation**:
The test has been implemented using the provided code snippet. The PetApi class from the client library is used to interact with the endpoints.

