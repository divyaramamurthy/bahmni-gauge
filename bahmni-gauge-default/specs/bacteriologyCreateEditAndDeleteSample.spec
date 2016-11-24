Bacteriology
=====================
Created by dharmens on 7/5/16

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Bacteriology Sample Creation
----------------------------

Tags: regression, sanity

* Create a new patient and open visit through API
* On the login page
* Login to the application
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Go to "Bacteriology" tab
* Add following bacteriology samples
    |dateOfSampleCollection|typeOfSample|identifier|note|smearResult|labIdNumber|otherSymptom|aFBDate|aFBTime|xpertTestDate|xpertTestId|xpertResult|
    |01-11-2016|Blood|12345|Sample collected Note 1|Negative|4321|Yes|02-11-2016|12:12|03-11-2016|34567|ACD - Active Case Detection|
    |04-11-2016|Blood|67890|Sample collected Note 2|Negative|9876|No|05-11-2016|12:12|06-11-2016|76543|ACD - Active Case Detection|
* Save the consultation
* Navigate to patient dashboard
* Expand all sections from display control with Caption "Bacteriology Results"
* Verify display control "Bacteriology-Results" on dashboard, has the following details
    |details|
    |Blood : 12345|
    |Smear resultNegative|
    |Smear test lab ID number4321|
    |Diagnosed with other SymptomYes|
    |Date of AFB smear02 Nov 16|
    |Date of Xpert test done03 Nov 16|
    |Xpert test ID number34567|
    |Xpert MTB resultACD - Active Case Detection|
    |Blood : 67890|
    |Smear resultNegative|
    |Smear test lab ID number9876|
    |Diagnosed with other SymptomNo|
    |Date of AFB smear05 Nov 16|
    |Date of Xpert test done06 Nov 16|
    |Xpert test ID number76543|
    |Xpert MTB resultACD - Active Case Detection|
* Open the current visit
* Verify display control with Caption "Observations" on visit page, has the following details
    |details|
    |Collection Date01 Nov 16|
    |Sample SourceBlood|
    |Id12345|
    |Consultation NoteSample collected Note 1|
    |Smear resultNegative|
    |Smear test lab ID number4321|
    |Diagnosed with other SymptomYes|
    |Date of AFB smear02 Nov 16|
    |Date of Xpert test done03 Nov 16|
    |Xpert test ID number34567|
    |Xpert MTB resultACD - Active Case Detection|
    |Collection Date04 Nov 16|
    |Sample SourceBlood|
    |Id67890|
    |Consultation NoteSample collected Note 2|
    |Smear resultNegative|
    |Smear test lab ID number9876|
    |Diagnosed with other SymptomNo|
    |Date of AFB smear05 Nov 16|
    |Date of Xpert test done06 Nov 16|
    |Xpert test ID number76543|
    |Xpert MTB resultACD - Active Case Detection|
* Navigate to dashboard
* Logout the user

Bacteriology Sample editing
----------------------------

Tags: regression, sanity

* Create a new patient and open visit through API
* On the login page
* Login to the application
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Go to "Bacteriology" tab
* Add following bacteriology samples
    |dateOfSampleCollection|typeOfSample|identifier|note|smearResult|labIdNumber|otherSymptom|aFBDate|aFBTime|xpertTestDate|xpertTestId|xpertResult|
    |01-11-2016|Blood|12345|Sample collected Note 1|Negative|4321|Yes|02-11-2016|12:12|03-11-2016|34567|ACD - Active Case Detection|
    |04-11-2016|Blood|67890|Sample collected Note 2|Negative|9876|No|05-11-2016|12:12|06-11-2016|76543|ACD - Active Case Detection|
* Save the consultation
* Edit Sample with row number "1" as following
    |dateOfSampleCollection|typeOfSample|identifier|note|smearResult|labIdNumber|otherSymptom|aFBDate|aFBTime|xpertTestDate|xpertTestId|xpertResult|
    |07-11-2016|Urine|23456|Sample collected Note 3|Negative|5432|Yes|08-11-2016|11:11|08-11-2016|45678|ACD - Active Case Detection|
* Save the consultation
* Navigate to patient dashboard
* Expand all sections from display control with Caption "Bacteriology Results"
* Verify display control "Bacteriology-Results" on dashboard, has the following details
    |details|
    |Blood : 12345|
    |Smear resultNegative|
    |Smear test lab ID number4321|
    |Diagnosed with other SymptomYes|
    |Date of AFB smear02 Nov 16|
    |Date of Xpert test done03 Nov 16|
    |Xpert test ID number34567|
    |Xpert MTB resultACD - Active Case Detection|
    |Urine : 23456|
    |Smear resultNegative|
    |Smear test lab ID number5432|
    |Diagnosed with other SymptomYes|
    |Date of AFB smear08 Nov 16|
    |Date of Xpert test done08 Nov 16|
    |Xpert test ID number45678|
    |Xpert MTB resultACD - Active Case Detection|
* Open the current visit
* Verify display control with Caption "Observations" on visit page, has the following details
    |details|
    |Collection Date01 Nov 16|
    |Sample SourceBlood|
    |Id12345|
    |Consultation NoteSample collected Note 1|
    |Smear resultNegative|
    |Smear test lab ID number4321|
    |Diagnosed with other SymptomYes|
    |Date of AFB smear02 Nov 16|
    |Date of Xpert test done03 Nov 16|
    |Xpert test ID number34567|
    |Xpert MTB resultACD - Active Case Detection|
    |Collection Date07 Nov 16|
    |Sample SourceUrine|
    |Id23456|
    |Consultation NoteSample collected Note 3|
    |Smear resultNegative|
    |Smear test lab ID number5432|
    |Diagnosed with other SymptomYes|
    |Date of AFB smear08 Nov 16|
    |Date of Xpert test done08 Nov 16|
    |Xpert test ID number45678|
    |Xpert MTB resultACD - Active Case Detection|
* Navigate to dashboard
* Logout the user


Bacteriology Sample Delete
----------------------------

Tags: regression, sanity

* Create a new patient and open visit through API
* On the login page
* Login to the application
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Go to "Bacteriology" tab
* Add following bacteriology samples
    |dateOfSampleCollection|typeOfSample|identifier|note|smearResult|labIdNumber|otherSymptom|aFBDate|aFBTime|xpertTestDate|xpertTestId|xpertResult|
    |01-11-2016|Blood|12345|Sample collected Note 1|Negative|4321|Yes|02-11-2016|12:12|03-11-2016|34567|ACD - Active Case Detection|
* Save the consultation
* Delete Sample with row number "1"
* Save the consultation
* Navigate to patient dashboard
* Verify display control with Caption "Bacteriology Results" on dashboard, has the following details
    |details|
    |No specimen for this patient|
* Open the current visit
* Verify display control with Caption "Observations" on visit page, has the following details
    |details|
    |No Observations for this patient|
* Navigate to dashboard
* Logout the user