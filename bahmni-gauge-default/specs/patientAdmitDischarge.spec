Patient Admit, Discharge and Transfer
=====================================
Created by atmaramn on 20/10/2016

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Closing visit for an admitted patient displays error message
------------------------------
Tags: regression, sanity

* On the login page
* Login to the application
* Create the following patient using api
     |prefix|firstName|lastName|gender|dateOfBirth|district|
     |EMR|Hanah|Jones|F|20/01/2011|Bilaspur|
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Navigate to dashboard
* Click on registration app
* Search previously created patient with exact identifier
* Enter Visit Details for Admitted Patient
* Try close visit
* Verify Error popup with message "Admitted patient's visit cannot be closed. Discharge the patient and try again" is displayed
* Navigate to dashboard
* Logout the user


Admit patient through API, discharge and close visit 
------------------------------------------
Tags: regression, sanity

* Create and admit a patient through API
* On the login page 
* Login to the application
* Click on inpatient app 
* Select existing patient from patient listing page under tab "Admitted" 
* Select "Discharge Patient" from Patient Movement and click "Discharge" button 
* Navigate to dashboard  
* Click on registration app 
* Search previously created patient with exact identifier
* Enter Visit Details for Admitted Patient
* Close visit 
* Search previously created patient with exact identifier  
* Verify "Start OPD visit" button is "displayed"
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Verify only "Admit Patient" option is displayed in Patient Movement 
* Navigate to dashboard 
* Logout the user


Admit Patient with closed visit and verify display controls
----------------
Tags: regression, sanity

* Create a new patient through API
* On the login page 
* Login to the application
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 2|
* Assign an empty bed
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 1|
* Navigate to Inpatient Dashboard
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Click on "Patient Visit Page" link
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Close current browser tab
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Verify display control "Visits" on dashboard, has the following details
    |details|
    |IPD |
* Navigate to dashboard
* Logout the user


Admit Patient through Disposition
---------------------------------

Tags: regression, sanity

* Create a new patient through API
* Open visit for previous patient using api
    |location|type|
    |Registration Desk|IPD|
* On the login page
* Login to the application
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Navigate to consultation
* Go to "Disposition" tab
* Select "Admit Patient" disposition with notes "Admit this patient"
* Save the consultation
* Go to "Consultation" tab
* Verify Disposition details on consultation tab
* Navigate to patient dashboard
* Verify details on dashboard "Disposition" display control
* Open the current visit
* Verify details on visit page "Disposition" display control
* Navigate to patient dashboard
* Navigate to adt dashboard
* Verify display control with Caption "Disposition" on inpatient dashboard, has the following details
    |details|
    |Admit Patient|
    |<NOW[dd MMM yy]>|
    |Admit this patient|
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "To Admit"
* Select "Admit Patient" from Patient Movement and click "Admit" button with notes "Patient Admitted as requested"
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
    |Patient Admitted as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
    |Patient Admitted as requested|
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
    |Patient Admitted as requested|
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "Ward List"
* Select "Transfer Patient" from Patient Movement and click "Save" button with notes "Patient Transferred as requested"
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
    |Patient Admitted as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
    |Patient Admitted as requested|
* Verify display control "Visits" on dashboard, has the following details
    |details|
    |IPD |
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
    |Patient Admitted as requested|


Discharge and Undo Discharge Patient through Disposition
-----------------------------
Tags: regression, sanity

* Create a new patient through API
* Open visit for previous patient using api
    |location|type|
    |Registration Desk|IPD|
* On the login page
* Login to the application
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Admit Patient" from Patient Movement and click "Admit" button with notes "Patient Admitted as requested"
* Assign an empty bed
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Navigate to consultation
* Go to "Disposition" tab
* Select "Discharge Patient" disposition with notes "Discharge this patient"
* Save the consultation
* Go to "Consultation" tab
* Verify Disposition details on consultation tab
* Navigate to patient dashboard
* Verify details on dashboard "Disposition" display control
* Open the current visit
* Verify details on visit page "Disposition" display control
* Navigate to patient dashboard
* Navigate to adt dashboard
* Verify display control with Caption "Disposition" on inpatient dashboard, has the following details
    |details|
    |Discharge Patient<NOW[dd MMM yy]>|
    |Discharge this patient|
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "To Discharge"
* Select "Discharge Patient" from Patient Movement and click "Discharge" button with notes "Patient Discharged as requested"
* Navigate to dashboard
* Click on inpatient app
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 2|
* Select existing patient from patient listing page under tab "All"
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
    |details|
    |Discharge Date<NOW[dd MMM yy]>|
    |Patient Discharged as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |Discharge Date<NOW[dd MMM yy]>|
    |Patient Discharged as requested|
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
    |details|
    |Discharge Date<NOW[dd MMM yy]>|
    |Patient Discharged as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Navigate to consultation
* Go to "Disposition" tab
* Select "Undo Discharge" disposition with notes "Undo Discharge this patient"
* Save the consultation
* Go to "Consultation" tab
* Verify Disposition details on consultation tab
* Navigate to patient dashboard
* Verify details on dashboard "Disposition" display control
* Open the current visit
* Verify details on visit page "Disposition" display control
* Navigate to patient dashboard
* Navigate to adt dashboard
* Verify display control with Caption "Disposition" on inpatient dashboard, has the following details
    |details|
    |Undo Discharge<NOW[dd MMM yy]>|
    |Undo Discharge this patient|
* Select "Undo Discharge" from Patient Movement and click "Undo Discharge" button with notes "Patient Undo Discharge as requested"
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 1|
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
  |details|
  |General Ward <Patient.bedNumber>|
  |Admission Date<NOW[dd MMM yy]>|
  |Patient Admitted as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
  |details|
  |General Ward <Patient.bedNumber>|
  |Admission Date<NOW[dd MMM yy]>|
  |Patient Admitted as requested|
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
  |details|
  |General Ward <Patient.bedNumber>|
  |Admission Date<NOW[dd MMM yy]>|
  |Patient Admitted as requested|

Discharge an admitted patient and verifications
----------------
Tags: regression, sanity
* Create a new patient through API
* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_ANOTHER_LOCATION"
* Click on registration app
* Search previously created patient with exact identifier
* Start a visit "OPD"
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Click on "Continue with current Visit" button
* Navigate to inpatient search page
* Select existing patient from patient listing page under tab "Admitted"
* Select "Discharge Patient" from Patient Movement and click "Discharge" button
* Select existing patient from patient listing page under tab "All"
* Ensure inpatient icon does not exist on Patient Profile display control 
* Verify only "Undo Discharge" option is displayed in Patient Movement 
* Navigate to dashboard
* Logout the user

Verify Patient Admit, Transfer via In Patient Module
--------------------------------------------
Tags: regression, sanity

* Create a new patient through API
* Open visit for previous patient using api
    |location|type|
    |Registration Desk|OPD|
* On the login page
* Login to the application
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Click on "Yes" button
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 2|
* Assign an empty bed
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 1|
* Navigate to Inpatient Dashboard
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "Ward List"
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Available Beds: 1|
* Select "Transfer Patient" from Patient Movement and click "Save" button with notes "Patient Transferred as requested"
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Available Beds: 1|
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|
* Verify display control "Visits" on dashboard, has the following details
    |details|
    |IPD |
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
    |details|
    |General Ward <Patient.bedNumber>|
    |Admission Date<NOW[dd MMM yy]>|


[ADT] Verify Patient Discharge, Undo Discharge via In Patient Module
---------------------------------------------------------------
Tags: regression, sanity

* Create a new patient through API
* On the login page
* Login to the application
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Admit Patient" from Patient Movement and click "Admit" button with notes "Patient Admitted as requested"
* Assign an empty bed
* Navigate to dashboard
* Click on inpatient app
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Available Beds: 1|
* Select existing patient from patient listing page under tab "Admitted"
* Select "Discharge Patient" from Patient Movement and click "Discharge" button with notes "Patient Discharged as requested"
* Navigate to dashboard
* Click on inpatient app
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 2|
* Select existing patient from patient listing page under tab "All"
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
    |details|
    |Discharge Date<NOW[dd MMM yy]>|
    |Patient Discharged as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
    |details|
    |Discharge Date<NOW[dd MMM yy]>|
    |Patient Discharged as requested|
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
    |details|
    |Discharge Date<NOW[dd MMM yy]>|
    |Patient Discharged as requested|
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "All"
* Select "Undo Discharge" from Patient Movement and click "Undo Discharge" button
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 2|
* Assign an empty bed
* Verify display control "view-content" on inpatient dashboard, has the following details
    |details|
    |Total Beds: 9 |
    |Available Beds: 1|
* Navigate to Inpatient Dashboard
* Verify display control with Caption "Admission Details" on inpatient dashboard, has the following details
  |details|
  |General Ward <Patient.bedNumber>|
  |Admission Date<NOW[dd MMM yy]>|
  |Patient Admitted as requested|
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Verify display control "Admission-Details" on dashboard, has the following details
  |details|
  |General Ward <Patient.bedNumber>|
  |Admission Date<NOW[dd MMM yy]>|
  |Patient Admitted as requested|
* Open the current visit
* Verify display control "admissionDetails" on visit page, has the following details
  |details|
  |General Ward <Patient.bedNumber>|
  |Admission Date<NOW[dd MMM yy]>|
  |Patient Admitted as requested|

