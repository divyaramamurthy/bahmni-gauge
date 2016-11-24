Observation
=====================
Created by prabhashs on 11/15/16

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Consultation - Enter and verify data entered on Observation templates
----------------
Tags: regression, sanity

* Create patient and open visit with details through API
* On the login page
* Login to the application
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Fill "History and Examination" template with following observation details
    |Chief Complaint|for|Chief Complaint Notes|History Notes|Examination Notes|Smoking History|
    |Headache: :Accept|0|pain|repeat|repeat|No|
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
    |Dummy data|Dummy histroy|10/10/2016|Dummy procedure|15/10/2016|dummy note|Radiology Notes|Other Notes|Additional Advice on Discharge|Plan for follow up|
* Select the template "Obstetrics" from on the observation page
* Fill "Obstetrics" template with following observation details
    |Fundal Height (Weeks)|P/A Presenting Part|FHS|LMP|Amount of Liquor|
    |2|Transverse|Present|11/11/2016|90ml|
* Select the template "Second Vitals" from on the observation page
* Fill "Second Vitals" template with following observation details
    |Pulse|Systolic|Diastolic|Posture|Temperature|RR|SPO2|
    |71|111|77|Sitting|99|17|99|
* Save the consultation
* Navigate to patient dashboard
* Verify display control "History and Examinations" on dashboard, has the following details
    |Chief Complaint|Chief Complaint Notes|History Notes|Examination Notes|Smoking History|
    |Headache|pain|repeat|repeat|No|
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
