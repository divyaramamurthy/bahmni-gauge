Observation Template
====================
     
Enter the observation and verify data on Display Controls
---------------------------------------------------------
Tags: regression, sanity

* Create patient and open visit with details through API with details
   |height|weight|registrationFee|
   |172|67|200|
* Login to app and navigate to "Clinical" app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Fill "History and Examination" template with following observation details
    |Chief Complaint|for|Chief Complaint Notes|History Notes|Examination Notes|Smoking History|
    |Headache: :Accept|0|Pain|repeat|repeat|No|
* Fill "Vitals" template with following observation details
    |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
    |71|111|77|Sitting|99|17|99|
* Select the template "Gynaecology" from on the observation page
* Fill "Gynaecology" template with following observation details
    |P/S (Per Speculum) - Cervix|
    |Normal:VIA +ve|
* Select the template "Discharge Summary" from on the observation page
* Fill "Discharge Summary" template with following observation details
    |Hospital Course|History and Examination Notes|Date of Operation|Operative Procedure|Follow up Date|Lab Investigation Notes|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
    |Dummy data|Dummy histroy|10/10/2016|Dummy procedure|10/15/2016|dummy note|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
* Select the template "Obstetrics" from on the observation page
* Fill "Obstetrics" template with following observation details
    |Fundal Height (Weeks)|P/A Presenting Part|FHS|LMP|Amount of Liquor|
    |2|Transverse|Present|11/11/2016|90ml|
* Select the template "Second Vitals" from on the observation page
* Fill "Second Vitals" template with following observation details
    |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
    |71|111|77|Sitting|99|17|99|
* Save the consultation
* Go to "Consultation" tab
* Verify display control with Caption "History and Examination" on "Consultation" tab, has the following details
    |Chief Complaint|Chief Complaint Notes|History Notes|Examination Notes|Smoking History|
    |Headache|Pain|repeat|repeat|No|
 * Verify display control with Caption "Vitals" on "Consultation" tab, has the following details
    |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
    |71|111|77|Sitting|99|17|99|
 * Verify display control with Caption "Gynaecology" on "Consultation" tab, has the following details
    |P/S (Per Speculum) - Cervix|
    |Normal|
 * Verify display control with Caption "Discharge Summary" on "Consultation" tab, has the following details
      |Hospital Course|History and Examination Notes|Date of Operation|Operative Procedure|Follow up Date|Lab Investigation Notes|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
      |Dummy data|Dummy histroy|10-Oct-2016|Dummy procedure|15-Oct-2016|dummy note|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
  * Verify display control with Caption "Obstetrics" on "Consultation" tab, has the following details
      |Fundal Height (Weeks)|P/A Presenting Part|FHS|LMP|Amount of Liquor|
      |2|Transverse|Present|11-Nov-2016|90ml|
  * Verify display control with Caption "Second Vitals" on "Consultation" tab, has the following details
      |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
      |71|111|77|Sitting|99|17|99|
* Navigate to patient dashboard
* Verify display control "History and Examinations" on dashboard, has the following details
    |Chief Complaint|Chief Complaint Notes|History Notes|Examination Notes|Smoking History|
    |Headache|Pain|repeat|repeat|No|
* Verify display control "Vitals" on dashboard, has the following details
    |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
    |71/min|111mm Hg|77mm Hg|Sitting|99F|17/min|99%|
* Verify display control "Gynaecology" on dashboard, has the following details
    |P/S (Per Speculum) - Cervix|
    |Normal|
* Verify display control "Obstetrics" on dashboard, has the following details
    |Fundal Height (Weeks)|P/A Presenting Part|FHS|LMP|Amount of Liquor|
    |2|Transverse|Present|11/11/2016|90ml|
* Verify display control "Second Vitals" on dashboard, has the following details
    |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
    |71/min|111mm Hg|77mm Hg|Sitting|99F|17/min|99%|
* Navigate to latest visit page
* Verify display control with Caption "Vitals Flowsheet" on visit page, has the following details
    |HEIGHT|WEIGHT|
    |172|67|
* Verify display control with Caption "Observations" on visit page, has the following details
    |details|
    ||71, 111, 77, Sitting, 99, 17, 99|
* Open "Discharge Summary" tab on visit page
* Verify display control with Caption "Clinical Notes" on visit page, with labels and details
    |Hospital Course|History and Examination Notes|Date of Operation|Operative Procedure|Follow up Date|Lab Investigation Notes|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
    |Dummy data|Dummy histroy|10 Oct 2016|Dummy procedure|15 Oct 2016|dummy note|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
* Verify display control with Caption "Advice on Discharge" on visit page, has the following details
    |Additional Advice on Discharge|Follow up Date|Plan for follow up|
    |Additional Advice on Discharge|15 Oct 16|Plan for follow up|



Ability to select and delete multiple values against a single field
--------------------------------------------------------------------
Tags: regression, sanity

* Create a new patient and open visit through API
* Login to app and navigate to "Clinical" app
* Select existing patient from patient listing page under tab "All"
* Navigate to consultation
* Select the template "Tuberculosis - Followup" from on the observation page
* Fill Tuberculosis - Followup template with following observation details
    |Adverse Effects|
    |Numbness:Dizziness|
* Save the consultation
* Go to "Consultation" tab
* Verify display control with Caption "Tuberculosis - Followup" on "Consultation" tab, has the following details
    |Adverse Effects|
    |Numbness|
    |Dizziness|
* Navigate to dashboard link
* Verify display control "Tuberculosis Follow Up" on dashboard, has the following details
      |Tuberculosis - Followup |
      |Adverse Effects|
      |Dizziness|
      |Numbness|
* Navigate to consultation
* Go to "Observation" tab
* Remove Adverse effect from "Tuberculosis - Followup"
      |Adverse Effects|
      |Numbness|
 * Save the consultation
 * Go to "Consultation" tab
 * Verify display control with Caption "Tuberculosis - Followup" on "Consultation" tab, has the following details
      |Adverse Effects|
      |Dizziness|
 * Navigate to dashboard link
 * Verify display control "Tuberculosis Follow Up" on dashboard, has the following details
       |Tuberculosis - Followup |
       |Adverse Effects|
       |Dizziness|

Ability to select multiple autocomplete values using Add More
--------------------------------------------------------------
Tags: regression, sanity

* Create patient and open visit with details through API with details
   |height|weight|registrationFee|
   |172|67|200|
* Login to app and navigate to "Clinical" app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Add chief complaint on "History and Examination" template
  |Chief Complaint|
  |Headache|
  |HeartBurn|
  |Injury|
* Go to "Consultation" tab
* Verify display control with Caption "History and Examination" on "Consultation" tab, has the following details
  |Chief Complaint|
  |Headache|
  |HeartBurn|
  |Injury|
* Navigate to dashboard link
* Verify display control "History and Examinations" on dashboard, has the following details
        |History and Examination |
        |Chief Complaint|
        |Headache|
        |HeartBurn|
        |Injury|
* Navigate to latest visit page
* Verify display control with Caption "Observations" on visit page, has the following details
      |History and Examination |
      |Chief Complaint|
      |Headache|
      |HeartBurn|
      |Injury|
* Navigate to consultation
* Remove chief complaint on "History and Examination" template
  |Chief Complaint|
  |Headache|
  |HeartBurn|
* Go to "Consultation" tab
* Verify display control with Caption "History and Examination" on "Consultation" tab, has the following details
  |Chief Complaint|
  |Injury|
* Navigate to dashboard link
* Verify display control "History and Examinations" on dashboard, has the following details
      |History and Examination |
      |Chief Complaint|
      |Injury|
* Navigate to latest visit page
* Verify display control with Caption "Observations" on visit page, has the following details
    |History and Examination|
    |Chief Complaint|
    |Injury|

Ability to Upload Consultation Image
--------------------------------
Tags: regression, sanity

* Create patient and open visit with details through API
* Login to app and navigate to "Clinical" app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Upload consultation images with Notes on "History and Examination"
  |Image            |Comment    |
  |MRIScan.jpg      |MRIScan    |
  |xray.jpeg        |XRayCopy   |
  |ultrasound.jpeg  |US Copy    |
* Go to "Consultation" tab
* Verify display control with Caption "History and Examination" on "Consultation" tab, has the following details
  |details|
  |Image|
  |Image|
  |Image|
* Navigate to dashboard link
* Verify display control "History and Examinations" on dashboard, has the following details
  |Image|
  |MRIScan|
  |XRayCopy|
  |US Copy|
* Navigate to latest visit page
* Verify display control with Caption "Observations" on visit page, has the following details
  |Image|
  |MRIScan|
  |XRayCopy|
  |US Copy|
* Navigate to dashboard link
* Navigate to consultation
* Go to "Observations" tab
* Remove image "1" from "History and Examinations" on Consultation page
* Go to "Consultation" tab
* Verify display control with Caption "History and Examination" on "Consultation" tab, has the following details
  |details|
  |Image|
  |Image|
* Navigate to dashboard link
* Verify display control "History and Examinations" on dashboard, has the following details
  |Image|
  |MRIScan|
  |XRayCopy|
* Navigate to latest visit page
* Verify display control with Caption "Observations" on visit page, has the following details
  |Image|
  |MRIScan|
  |XRayCopy|

Able to control Text Box enable/disable by Boolean Obs
---------------------------------------------------------
Tags: form event handler

* Create patient and open visit with details through API with details
   |height|weight|registrationFee|
   |172|67|200|
* Login to app and navigate to "Clinical" app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Add the "3129" existing form
* Verify "3129" is added to the left pane
* Click on "Yes" button
* Verify "textarea" in "Chief Complaint Notes" is disabled