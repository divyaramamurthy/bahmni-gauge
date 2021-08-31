Bacteriology Non Std DST
=================================
Record Bacteriology Non Std DST  Results for enrolled patient and validate
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
    |dateOfSampleCollection|typeOfSample|identifier|labIdNumber|MICInterpretation|
    |2016-09-01T00:00:00.000+05:30|Sputum|12345|4321|Susceptible|
* Save the consultation
* Navigate to patient dashboard
* click specimenId on dashboard, and verify displayed dialog has the following content
    |details|
    |Susceptible|
