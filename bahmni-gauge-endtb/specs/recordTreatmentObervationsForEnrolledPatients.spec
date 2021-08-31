Recording Observations for patient
==================================


Record Smear Test Results for enrolled patient and validate
----------------------------------------------------------

* Enroll patient to the treatment through API
* On the login page
* Login to the application
* Click on registration app
* Click on search patient link
* Search previously created patient with exact identifier
* Click on treatment enrollment
* Click on the patients previously program enrolled
* Navigate to consultation
* Go to "Bacteriology" tab
* Create a bacteriology specimen smear result
    |dateOfSampleCollection|identifier|typeOfSample|typeOfVisit|laboratoryName|smearDateOfTest|smearTestId|smearResult|
    |2021-05-01T00:00:00.000+05:30|899|Sputum|Baseline|Other|2021-05-04|SA12445|Negative|
* On the login page
* Login to the application
* On the dashboard page
* Click on "Patient Summary" dashboard
* Logout the user

RECORD OBSERVATIONS FOR ENROLLED PATIENT
----------------------------------------

Tags: regression, sanity, smoke

* Enroll patient to the treatment through API
* On the login page
* Login to the application
* Click on registration app
* Click on search patient link
* Search previously created patient with exact identifier
* Click on treatment enrollment
* Click on the patients previously program enrolled
* Navigate to consultation
* Select the template "Baseline" from on the observation page
* Fill baseline form
    |Date baseline assessment|Marital Status|Other Marital Status|Homeless|Employed|Other employment|ever been treated for TB in the past?|Year first TB treatment|
    |01/01/2015|Married||Yes|Pensioner||No|2016|
* Save the consultation
* Navigate to patient dashboard
* Verify observations recorded under "Baseline"
