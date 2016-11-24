Patient Summary Culture And Smear
=================================

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
* Create a bacteriology specimen
    |dateOfSampleCollection|typeOfSample|identifier|smearResult|labIdNumber|
    |01-11-2016|Sputum|12345|Negative|4321|
* Save the consultation
* Navigate to patient dashboard
* click specimenId on dashboard, and verify displayed dialog has the following content
    |details|
    |Negative|


Record Culture Results for enrolled patient and validate
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
* Create a bacteriology specimen
    |dateOfSampleCollection|typeOfSample|identifier|labIdNumber|cultureResults|
    |01-11-2016|Sputum|12345|4321|Contaminated|
* Save the consultation
* Navigate to patient dashboard
* click specimenId on dashboard, and verify displayed dialog has the following content
    |details|
    |Contaminated|

