Recording Observations
======================
Tags: regression, sanity, test, recordObservationsForPatients

Record Observations for patient
-------------------------------

* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Click on registration app
* Click on create new patient link
* Create the following patient
    |prefix|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|Automation|Female|20/01/2011|50|187|Darna|Achham|Nepal|2 - Janajati|

* Start a visit "General"
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Navigate to consultation
* In "Patient_Vitals" form, fill up the following details

    |key|value|
    |---|-----|
    |Systolic|150|
    |Diastolic|85|
    |Posture|Sitting|
    |Temperature|110|
    |Heart Rate|75|
    |Respiratory Rate|15|
    |Oxygen Saturation|90|
    |Weight|140|

* Save the consultation
* Navigate to patient dashboard
* Verify display control "Vitals" on dashboard, has the following details

    |details|
    |-------|
    |Systolic (120 - 140)150mmHg|
    |Diastolic (80 - 90)85mmHg|
    |PostureSitting|
    |Temperature (98 - 100)110F|
    |Heart Rate (60 - 100)75per min|
    |Respiratory Rate (12 - 20)15per min|
    |Oxygen Saturation (95 - 100)90%|
    |Weight140kg|
