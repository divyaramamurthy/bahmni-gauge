Create EndTB Patient Scenarios
===============================


CREATE PATIENT AND VERIFY
-------------------------

Tags: sanity

* On the login page
* Login to the application
* Verify Login Page
* Click on registration app
* Click on create new patient link
* Create the following patient
    |Patient Identifier|firstName|lastName|gender|dateOfBirth|district|nationalIdentificationNumber|
    |EMR|Super|Man|M|2011-01-20T00:00:00.000+05:30|Bilaspur|13892|
* Logout the user


VALIDATE PATIENT CREATE FAILS WITH SAME ID
------------------------------------------

Tags: sanity

* On the login page
* Login to the application
* Verify Login Page
* Click on registration app
* Click on create new patient link
* Create the following patient
    |patientIdentifier|firstName|lastName|gender|dateOfBirth|district|nationalIdentificationNumber|
    |EMR|Super|Woman|F|2001-01-20T00:00:00.000+05:30|Bilaspur|13893|

* Click on create new patient link
* Create the following patient with ID as recently created Patient
    |patientIdentifier|firstName|lastName|gender|dateOfBirth|district|nationalIdentificationNumber|
    |EMR|Super|Woman|F|2001-01-20T00:00:00.000+05:30|Bilaspur|13893|
* Verify the patient creation fails
* Logout the user

SEARCH PATIENT WITH FILTERS
---------------------------

Tags: sanity

* Create random patient through API
* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD"
* Verify Login Page
* Click on registration app
* Click on search patient link
* Search previously created patient with exact identifier
* Ensure that the patient edit page is opened for previously created patient
* Click on search patient link
* Search previously created patient with name
* Select the patient from the search results
* Ensure that the patient edit page is opened for previously created patient
* Logout the user

