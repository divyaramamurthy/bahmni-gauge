Radiology Upload Scenarios
=====================
Created by atmaramn on 17/11/2016

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Upload radiology documents
--------------------------

Tags: test

* Create a new patient and open visit through API
* On the login page
* Login to the application
* Click on radiology upload app
* Select existing patient from patient listing page under tab "Active Patients"
* Upload following images in visit "2"
    |image|name|
    |xray.jpeg|Knee - Right, 1 or 2 views (X-ray)|
    |xray.jpeg|Knee - Right, 1 or 2 views (X-ray)|
* Save radiology upload
* Create new visit on radiology upload page
    |type|startDate|endDate|image|name|
    |LAB VISIT|01/03/2015|02/03/2015|xray.jpeg|Knee - Right, 1 or 2 views (X-ray)|
* Save radiology upload
//* Remove image no "1" on visit "2"
//* Remove image no "1" on visit "2"
//* Undo remove image no "1" on visit "2"
//* Upload following images in visit "2"
//    |image|name|
//    |xray.jpeg|Knee - Right, 1 or 2 views (X-ray)|
//* Save radiology upload

