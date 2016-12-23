Observation
=====================
Created by prabhashs on 11/15/16

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Consultation - Enter and verify data entered on Observation templates
----------------
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
    |details|
    |Chief ComplaintHeadache|
    |Chief Complaint NotesPain|
    |History Notesrepeat|
    |Examination Notesrepeat|
    |Smoking HistoryNo|
 * Verify display control with Caption "Vitals" on "Consultation" tab, has the following details
    |details|
    |Pulse71|
    |Systolic111|
    |Diastolic77|
    |PostureSitting|
    |Temperature99|
    |RR17|
    |SPO299|
 * Verify display control with Caption "Gynaecology" on "Consultation" tab, has the following details
    |details|
    |P/S (Per Speculum) - CervixNormal|
 * Verify display control with Caption "Discharge Summary" on "Consultation" tab, has the following details
      |details|
      |Hospital CourseDummy data|
      |History and Examination NotesDummy histroy|
      |Date of Operation10-Oct-2016|
      |Operative ProcedureDummy procedure|
      |Follow up Date15-Oct-2016|
      |Lab Investigation Notesdummy note|
      |Radiology NotesRadiology Notes|
      |Other NotesOther Notes|
      |Additional Advice on DischargeAdditional Advice on Discharge|
      |Plan for follow upPlan for follow up|
  * Verify display control with Caption "Obstetrics" on "Consultation" tab, has the following details
      |details|
      |Fundal Height (Weeks)2|
      |P/A Presenting PartTransverse|
      |FHSPresent|
      |LMP11-Nov-2016|
      |Amount of Liquor90ml|
  * Verify display control with Caption "Second Vitals" on "Consultation" tab, has the following details
      |Pulse71|
      |Systolic111|
      |Diastolic77|
      |PostureSitting|Temperature99|RR17|SPO299|
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
    |details|
    |Hospital CourseDummy data|
    |History and Examination NotesDummy histroy|
    |Date of Operation10 Oct 16|
    |Operative ProcedureDummy procedure|
    |Follow up Date15 Oct 16|
    |Lab Investigation Notesdummy note|
    |Radiology NotesRadiology Notes|
    |Other NotesOther Notes|
    |Additional Advice on DischargeAdditional Advice on Discharge|
    |Plan for follow upPlan for follow up|
* Verify display control with Caption "Advice on Discharge" on visit page, has the following details
    |details|
    |Additional Advice on DischargeAdditional Advice on Discharge|
    |Follow up Date15 Oct 16|
    |Plan for follow upPlan for follow up|

Consultation - Add More Complaints
----------------
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
* Navigate to dashboard link
* Verify display control "History and Examinations" on dashboard, has the following details
        |details|
        |History and Examination |
        |Chief Complaint Headache|
        |Chief Complaint HeartBurn|
        |Chief Complaint Injury|
* Navigate to latest visit page
* Verify display control with Caption "Observations" on visit page, has the following details
      |details|
      |History and Examination |
      |Chief Complaint Headache|
      |Chief Complaint HeartBurn|
      |Chief Complaint Injury|
* Navigate to consultation
* Remove chief complaint on "History and Examination" template
  |Chief Complaint|
  |Headache|
  |HeartBurn|
* Navigate to dashboard link
* Verify display control "History and Examinations" on dashboard, has the following details
      |details|
      |History and Examination |
      |Chief Complaint Injury|
* Navigate to latest visit page
* Verify display control with Caption "Observations" on visit page, has the following details
    |details|
    |History and Examination |
    |Chief Complaint Injury|


Ability to Upload Consultation Image
----------------
Tags: regression, sanity

* Create patient and open visit with details through API
* Login to app and navigate to "Clinical" app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Upload  consultation images with Notes on "History and Examination"
  |Image|Comment|
  |MRIScan.jpg|MRIScan|
  |xray.jpeg|MRIScan|






